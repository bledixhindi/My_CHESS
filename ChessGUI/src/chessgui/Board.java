package chessgui;
import javax.swing.*;  
import java.awt.*;  
import javax.swing.*;  
import java.awt.*;  
import chessgui.pieces.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.*;  
import java.awt.*;  
import javax.swing.*;  
import java.awt.*;  
@SuppressWarnings("serial")
public class Board extends JComponent {
        /**
         * This code initializes a chess game with a 8x8 grid represented by a 2D integer array BoardGrid. 
         * The game has two arrays of pieces: White_Pieces and Black_Pieces, each storing the pieces of a particular color.
         *  Additionally, there are two arrays to store graphical information: Static_Shapes and Piece_Graphics.
         *  The Active_Piece member variable is used to store the currently selected piece. 
         * The initGrid() function sets up the starting positions of all the pieces on the board, 
         * by adding instances of each type of chess piece (King, Queen, Bishop, Knight, Rook, Pawn) to their respective color arrays.
         *  The images for the pieces are stored in a specified file path.
         * */
    public int turnCounter = 0;
    private static Image NULL_IMAGE = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);

    private final int Square_Width = 65;
    public ArrayList<Piece> White_Pieces;
    public ArrayList<Piece> Black_Pieces;
    

    public ArrayList<DrawingShape> Static_Shapes;
    public ArrayList<DrawingShape> Piece_Graphics;

    public Piece Active_Piece;

    private final int rows = 8;
    private final int cols = 8;
    private Integer[][] BoardGrid;
    private String board_file_path = "images" + File.separator + "board.png";
    private String active_square_file_path = "images" + File.separator + "active_square.png";

    public void initGrid()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                BoardGrid[i][j] = 0;
            }
        }


        White_Pieces.add(new King(3,0,true,"King.png",this));
        White_Pieces.add(new Queen(4,0,true,"Queen.png",this));
        White_Pieces.add(new Bishop(2,0,true,"Bishop.png",this));
        White_Pieces.add(new Bishop(5,0,true,"Bishop.png",this));
        White_Pieces.add(new Knight(1,0,true,"Knight.png",this));
        White_Pieces.add(new Knight(6,0,true,"Knight.png",this));
        White_Pieces.add(new Rook(0,0,true,"Rook.png",this));
        White_Pieces.add(new Rook(7,0,true,"Rook.png",this));
        White_Pieces.add(new Pawn(0,1,true,"Pawn.png",this));
        White_Pieces.add(new Pawn(1,1,true,"Pawn.png",this));
        White_Pieces.add(new Pawn(2,1,true,"Pawn.png",this));
        White_Pieces.add(new Pawn(3,1,true,"Pawn.png",this));
        White_Pieces.add(new Pawn(4,1,true,"Pawn.png",this));
        White_Pieces.add(new Pawn(5,1,true,"Pawn.png",this));
        White_Pieces.add(new Pawn(6,1,true,"Pawn.png",this));
        White_Pieces.add(new Pawn(7,1,true,"Pawn.png",this));

        Black_Pieces.add(new King(3,7,false,"King.png",this));
        Black_Pieces.add(new Queen(4,7,false,"Queen.png",this));
        Black_Pieces.add(new Bishop(2,7,false,"Bishop.png",this));
        Black_Pieces.add(new Bishop(5,7,false,"Bishop.png",this));
        Black_Pieces.add(new Knight(1,7,false,"Knight.png",this));
        Black_Pieces.add(new Knight(6,7,false,"Knight.png",this));
        Black_Pieces.add(new Rook(0,7,false,"Rook.png",this));
        Black_Pieces.add(new Rook(7,7,false,"Rook.png",this));
        Black_Pieces.add(new Pawn(0,6,false,"Pawn.png",this));
        Black_Pieces.add(new Pawn(1,6,false,"Pawn.png",this));
        Black_Pieces.add(new Pawn(2,6,false,"Pawn.png",this));
        Black_Pieces.add(new Pawn(3,6,false,"Pawn.png",this));
        Black_Pieces.add(new Pawn(4,6,false,"Pawn.png",this));
        Black_Pieces.add(new Pawn(5,6,false,"Pawn.png",this));
        Black_Pieces.add(new Pawn(6,6,false,"Pawn.png",this));
        Black_Pieces.add(new Pawn(7,6,false,"Pawn.png",this));
    	  
    }


	public Board() {
     	/***
     	 * This is a constructor for a Java class named "Board".
     	 *  It creates a 2D array called "BoardGrid" with rows and cols as dimensions, two ArrayLists called "Static_Shapes"
     	 *   and "Piece_Graphics", two more ArrayLists called "White_Pieces" and "Black_Pieces". 
     	 *  The constructor sets the background color, preferred size, minimum size, and maximum size of the Board.
     	 *   It also adds three listeners: "mouseAdapter", "componentAdapter", and "keyAdapter".
     	 *  The constructor sets the visibility of the Board to true, requests focus and finally calls a method "drawBoard()".
     	 * */
        BoardGrid = new Integer[rows][cols];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        White_Pieces = new ArrayList();
        Black_Pieces = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));

        this.addMouseListener(mouseAdapter);
        this.addComponentListener(componentAdapter);
        this.addKeyListener(keyAdapter);


        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }


    private void drawBoard()
    {
    	
    	/**
    	 * 
    	 * This is a private method named "drawBoard()". It clears the ArrayLists "Piece_Graphics" and "Static_Shapes".
    	 *  It then loads an image from the file "board_file_path" and adds it to the ArrayList "Static_Shapes". 
    	 *  If there is an active piece, it adds an image of an "active_square" to "Static_Shapes". 
    	 *  The method then adds all white pieces to "Piece_Graphics" by looping through the "White_Pieces" 
    	 *  ArrayList and loading images of each piece using the method "loadImage()".
    	 *  Similarly, it adds all black pieces to "Piece_Graphics".
    	 *   Finally, it calls the "repaint()" method to update the graphics.
    	 * **/
        Piece_Graphics.clear();
        Static_Shapes.clear();
        //initGrid();
        
        Image board = loadImage(board_file_path);
        Static_Shapes.add(new DrawingImage(board, new Rectangle2D.Double(0, 0, board.getWidth(null), board.getHeight(null))));
        if (Active_Piece != null)
        {
            Image active_square = loadImage("images" + File.separator + "active_square.png");
            Static_Shapes.add(new DrawingImage(active_square, new Rectangle2D.Double(Square_Width*Active_Piece.getX(),Square_Width*Active_Piece.getY(), active_square.getWidth(null), active_square.getHeight(null))));
        }
        for (int i = 0; i < White_Pieces.size(); i++)
        {
            int COL = White_Pieces.get(i).getX();
            int ROW = White_Pieces.get(i).getY();
            Image piece = loadImage("images" + File.separator + "white_pieces" + File.separator + White_Pieces.get(i).getFilePath());  
            Piece_Graphics.add(new DrawingImage(piece, new Rectangle2D.Double(Square_Width*COL,Square_Width*ROW, piece.getWidth(null), piece.getHeight(null))));
        }
        for (int i = 0; i < Black_Pieces.size(); i++)
        {
            int COL = Black_Pieces.get(i).getX();
            int ROW = Black_Pieces.get(i).getY();
            Image piece = loadImage("images" + File.separator + "black_pieces" + File.separator + Black_Pieces.get(i).getFilePath());  
            Piece_Graphics.add(new DrawingImage(piece, new Rectangle2D.Double(Square_Width*COL,Square_Width*ROW, piece.getWidth(null), piece.getHeight(null))));
        }
        this.repaint();
    }

    
    public Piece getPiece(int x, int y) {
    	
    	/*
    	 * This is a public method named "getPiece()" which takes two integer parameters x and y.
    	 *  It loops through the ArrayList "White_Pieces" and checks if the x and y coordinates of each piece match the given x and y.
    	 *   If a match is found, the method returns the piece. If no match is found in the "White_Pieces" ArrayList,
    	 *    the method does the same for the "Black_Pieces" ArrayList.
    	 *  If no match is found in either ArrayList, the method returns null.
    	 * **/
        for (Piece p : White_Pieces)
        {
            if (p.getX() == x && p.getY() == y)
            {
                return p;
            }
        }
        for (Piece p : Black_Pieces)
        {
            if (p.getX() == x && p.getY() == y)
            {
                return p;
            }
        }
        return null;
    }

    private MouseAdapter mouseAdapter = new MouseAdapter() {
    	/*
    	 * This is a private mouse adapter named "mouseAdapter". 
    	 * It is an anonymous inner class that extends the "MouseAdapter" class. 
    	 * The class defines an override for the "mouseClicked()" method, 
    	 * but the method body is empty. This means that when a mouse click event occurs, no action will be taken.
    	 * **/
        @Override
        public void mouseClicked(MouseEvent e) {

                
        }

        @Override
        public void mousePressed(MouseEvent e) {
        	
        	/*
        	 * This is a MousePressed method that implements a mouse listener in Java. 
        	 * When a mouse click is detected, it finds the coordinates (column and row) of the square that was clicked on a chess board. 
        	 * It checks whose turn it is (white or black) by checking the value of turnCounter.
        	 *  If the player clicks on a piece of his own color, it sets that piece as the active piece.
        	 *   If the active piece is clicked again, it is de-selected.
        	 *    If the active piece is selected and the player clicks on a valid move for the piece,
        	 *     it makes the move by updating the piece's position, removing any piece that was present in the target square, 
        	 *     and setting hasMoved to true if the active piece is a pawn. 
        	 * Finally, it increments the turnCounter and redraws the board.
        	 * **/
            int d_X = e.getX();
            int d_Y = e.getY();  
            int Clicked_Row = d_Y / Square_Width;
            int Clicked_Column = d_X / Square_Width;
            boolean is_whites_turn = true;
            if (turnCounter%2 == 1)
            {
                is_whites_turn = false;
            }
            
            Piece clicked_piece = getPiece(Clicked_Column, Clicked_Row);
            
            if (Active_Piece == null && clicked_piece != null && 
                    ((is_whites_turn && clicked_piece.isWhite()) || (!is_whites_turn && clicked_piece.isBlack())))
            {
                Active_Piece = clicked_piece;
            }
            else if (Active_Piece != null && Active_Piece.getX() == Clicked_Column && Active_Piece.getY() == Clicked_Row)
            {
                Active_Piece = null;
            }
            else if (Active_Piece != null && Active_Piece.canMove(Clicked_Column, Clicked_Row) 
                    && ((is_whites_turn && Active_Piece.isWhite()) || (!is_whites_turn && Active_Piece.isBlack())))
            {
                // if piece is there, remove it so we can be there
                if (clicked_piece != null)
                {
                    if (clicked_piece.isWhite())
                    {
                        White_Pieces.remove(clicked_piece);
                    }
                    else
                    {
                        Black_Pieces.remove(clicked_piece);
                    }
                }
                // do move
                Active_Piece.setX(Clicked_Column);
                Active_Piece.setY(Clicked_Row);
                
                // if piece is a pawn set has_moved to true
                if (Active_Piece.getClass().equals(Pawn.class))
                {
                    Pawn castedPawn = (Pawn)(Active_Piece);
                    castedPawn.setHasMoved(true);
                }
                
                
                Active_Piece = null;
                turnCounter++;
            }
            
            drawBoard();
        }

        @Override
        public void mouseDragged(MouseEvent e) {		
        	
        	/*
        	 * This is an empty implementation of the mouseDragged method in the MouseAdapter inner class. 
        	 * It doesn't contain any functionality.
        	 * **/
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        	/*
        	 * The method mouseReleased doesn't contain any code and it doesn't do anything. 
        	 * It could be used to handle mouse events when the mouse is released over the game board, 
        	 * such as to check if a piece was moved successfully, but without further context it is difficult to say.
        	 * **/
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) 
        {
        	/*
        	 * This is the mouseWheelMoved method, part of the mouseAdapter. 
        	 * It will be called when a mouse wheel event occurs. Currently, it doesn't do anything as its body is empty.
        	 *  You can add your desired functionality in this method if you want to handle mouse wheel events.
        	 * **/
        }	

        
    };

    private void adjustShapePositions(double dx, double dy) {
    	/*
    	 * This function is adjusting the position of a shape in the Static_Shapes list.
    	 *  The shape's position is being adjusted by dx in the x-direction and dy in the y-direction.
    	 *   The updated position is then being painted on the canvas using the repaint() method.
    	 * **/
        Static_Shapes.get(0).adjustPosition(dx, dy);
        this.repaint();

    } 
        
        
      
    private Image loadImage(String imageFile) {
    	/*
    	 * The method loadImage(String imageFile) loads an image file and returns it as an Image object.
    	 *  If the file could not be loaded, it returns a default NULL_IMAGE. 
    	 *  This method makes use of the ImageIO class to read the file and returns the loaded image.




    	 * **/
        try {
                return ImageIO.read(new File(imageFile));
        }
        catch (IOException e) {
                return NULL_IMAGE;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
    	/*
    	 * This code overrides the paintComponent method of the parent class,
    	 *  which is called by the Java GUI framework whenever a component needs to be repainted.
    	 *  The method starts by calling super.paintComponent(g) which updates the component by calling its parent's 
    	 *  paintComponent method. Then it creates a Graphics2D object g2 from the Graphics object g to 
    	 *  use more advanced drawing capabilities.
    	 *  Next, it calls the drawBackground method with g2 as a parameter to draw the background of the component.
    	 *   Finally, it calls the drawShapes method with g2 as a parameter to draw the shapes on top of the background.
    	 * **/
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        drawBackground(g2);
        drawShapes(g2);
    }

    private void drawBackground(Graphics2D g2) {
    	/*
    	 * This method fills the component with the background color, creating a solid color background.
    	 * **/
        g2.setColor(getBackground());
        g2.fillRect(0,  0, getWidth(), getHeight());
    }
       

    private void drawShapes(Graphics2D g2) {
    	/*
    	 * This function draws all the shapes in the Static_Shapes and Piece_Graphics list. 
    	 * For each shape in the list, the draw method is called on that shape, passing in the Graphics2D object g2.
    	 *  This will draw the shapes onto the component.
    	 * **/
        for (DrawingShape shape : Static_Shapes) {
            shape.draw(g2);
        }	
        for (DrawingShape shape : Piece_Graphics) {
            shape.draw(g2);
        }
    }

    private ComponentAdapter componentAdapter = new ComponentAdapter() {
    	/*
    	 * This code defines a ComponentAdapter object named componentAdapter in an anonymous inner class.
    	 *  The adapter class provides empty implementations of the methods defined in the ComponentListener interface, 
    	 *  which listens for events related to changes in the component's size, position, visibility, etc. 
    	 *  When a component event occurs, one of the methods of this adapter class will be called.
    	 *  Note: By providing an empty implementation for each of the ComponentListener methods, 
    	 *  the adapter acts as a null object pattern for the ComponentListener interface, effectively suppressing any events 
    	 *  related to the component that would otherwise be handled by a listener.
    	 * **/
        @Override
        public void componentHidden(ComponentEvent e) {

        }

        @Override
        public void componentMoved(ComponentEvent e) {

        }

        @Override
        public void componentResized(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {

        }	
    };

    private KeyAdapter keyAdapter = new KeyAdapter() {
    	/*
    	 * This is a KeyAdapter class, which allows a component to receive key events.
			The class implements the KeyListener interface and overrides 3 methods: keyPressed, keyReleased, and keyTyped.
 			Currently, these methods are empty and do not contain any logic.
    	 *  It is up to you to decide how you want to handle these key events.
    	 * **/
        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }	
    };

	public static boolean isOccupied(int x, int y) {
		/*
		 * This is a method named isOccupied in the current class that takes two integer arguments, x and y, 
		 * and returns a boolean value indicating whether the given position (x, y) is occupied or not. 
		 * Currently, it returns always false, so it needs to be implemented properly to check the actual state of the game board.
		 * **/
		// TODO Auto-generated method stub
		return false;
	}

}



interface DrawingShape {
    boolean contains(Graphics2D g2, double x, double y);
    void adjustPosition(double dx, double dy);
    void draw(Graphics2D g2);
}


class DrawingImage implements DrawingShape {
	/*
	 * This is the implementation of the DrawingImage class which implements
	 *  the DrawingShape interface. The class has three instance variables: image, rect. 
	 *  The DrawingImage class has a constructor that takes in an Image object and a Rectangle2D object,
	 *  and sets the instance variables image and rect to the passed values. 
	 *  The class implements the methods of the DrawingShape interface - contains, adjustPosition, and draw.
	 *  The contains method returns whether the given point (x, y) is contained within the
	 *  rect object. The adjustPosition method adjusts the position of the rectangle by adding dx 
	 *  to its x-coordinate and dy to its y-coordinate. The draw method draws the image onto the graphics context g2
	 *  using the Rectangle2D object as the bounds for the image.
	 * **/
    public Image image;
    public Rectangle2D rect;

    public DrawingImage(Image image, Rectangle2D rect) {
    	
    	/*
    	 * The constructor of the DrawingImage class creates a new instance of the class with the specified image
    	 *  and rectangle. The image and the rectangle are stored as fields of the instance.




    	 * **/
            this.image = image;
            this.rect = rect;
    }

    @Override
    public boolean contains(Graphics2D g2, double x, double y) {
    	
    	/*
    	 * The contains method returns a boolean value indicating whether the given point
    	 *  (represented by x and y) is contained within the bounds of the rect object, which is a Rectangle2D instance. 
    	 *  It does this by calling the 
    	 * contains method on the rect object and passing the x and y values as arguments.
    	 * **/
            return rect.contains(x, y);
    }

    @Override
    public void adjustPosition(double dx, double dy) {
    	
    	/*
    	 * This is a Java method that adjusts the position of a rectangle represented by the rect object.
    	 *  The method takes two arguments, dx and dy, which represent the changes in the x and y positions of the rectangle, 
    	 *  respectively. The method sets the new position of the rectangle by using the setRect method of the Rectangle2D class, 
    	 *  updating the x and y positions of the rectangle by adding dx and dy to their current values,
    	 *  respectively. The width and height of the rectangle remain unchanged.
    	 * **/
            rect.setRect(rect.getX() + dx, rect.getY() + dy, rect.getWidth(), rect.getHeight());	
    }

    @Override
    public void draw(Graphics2D g2) {
    	/*
    	 * This is a Java method that draws an image onto a graphics context represented by the g2 object. 
    	 * The image is specified by the image object,
    	 *  and the location to draw it is determined by the rect object.
    	 *  The method starts by getting the bounds of the rectangle using the getBounds2D method, which returns a
    	 *   Rectangle2D object 
    	 *  that represents the smallest rectangle that can completely contain the rect object.
    	 *  The method then uses the drawImage method of the Graphics2D class to draw the image onto the graphics context.
    	 *  The drawImage method takes several arguments:
    	 *  (int)bounds.getMinX() and (int)bounds.getMinY() specify the x and y positions of the top-left corner of the destination rectangle, respectively.
    	 *  (int)bounds.getMaxX() and (int)bounds.getMaxY() specify the x and y positions of the bottom-right corner of the destination rectangle, respectively.
    	 *  0 and 0 specify the x and y positions of the top-left corner of the source rectangle (the part of the image to draw), respectively.
    	 *  image.getWidth(null) and image.getHeight(null) specify the width and height of the source rectangle, respectively.
    	 *  null is a ImageObserver object that is notified of changes to the image. In this case, null is passed because no notifications are needed.
    	 * **/
            Rectangle2D bounds = rect.getBounds2D();
            g2.drawImage(image, (int)bounds.getMinX(), (int)bounds.getMinY(), (int)bounds.getMaxX(), (int)bounds.getMaxY(),
                                            0, 0, image.getWidth(null), image.getHeight(null), null);
    }	
}
