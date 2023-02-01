package chessgui.pieces;

import chessgui.Board;

public class Bishop extends Piece {

    public Bishop(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    @Override
    public boolean canMove(int destination_x, int destination_y) {
    	/*
    	 * This code defines a method canMove in a class that extends an abstract class representing a chess piece. 
    	 * The method determines whether the piece can move to a
    	 *  destination square on the chess board.
    	 *  The method takes two arguments: destination_x and destination_y, which represent
    	 *  the coordinates of the destination square on the board.
    	 *  The method first calculates the absolute difference between the current x and y
    	 *   coordinates of the piece and the destination coordinates. If the absolute difference between x and
    	 *    destination_x is equal to the absolute difference between y and destination_y, then it means the move is a
    	 *     diagonal move, otherwise it returns false.
    	 *  Next, the method calculates the direction of the move by using the signum method of the 
    	 *  Integer class, which returns the sign of an integer value, i.e., -1 if the value is negative, 0 if the
    	 *   value is zero, and 1 if the value is positive.
    	 *  Finally, the method checks if there are any pieces blocking the path between the current position and 
    	 *  the destination. If any piece is found, the method returns false. If no pieces are found, the method returns
    	 *   true, indicating that the piece can move to the destination square.
    	 * **/
        int xDiff = Math.abs(destination_x - this.x);
        /*
         * This code calculates the absolute difference between the x coordinate of the current piece and the destination_x 
         * coordinate,
         *  and stores the result in xDiff. It also calculates the absolute difference between the y coordinate of the current
         *   piece and the destination_y coordinate, and stores the result in yDiff.
         * Math.abs is a method in the Math class that returns the absolute value of a number. 
         * By using Math.abs, this code ensures that the xDiff and yDiff values are positive, even if the current
         *  piece is moving to a square that is further away in the negative direction on either the x or y axis.
         * **/
        int yDiff = Math.abs(destination_y - this.y);
        // check if the move is diagonal
        if (xDiff == yDiff) {
        	/*
        	 * This code checks if the piece can move diagonally by checking if xDiff is equal to yDiff. 
        	 * If the piece can move diagonally, it then checks if there are any pieces blocking the path by using a for loop.
        	 * In the for loop, the xDirection and yDirection variables are calculated using the Integer.signum method.
        	 *  The Integer.signum method returns the sign of a number as an integer, either 1, -1, or 0,
        	 *  depending on the sign of the input number. In this case, it returns 1 if the destination_x coordinate is
        	 *   further away in the positive direction on the x axis compared to the current piece's x coordinate, -1 if the
        	 *    destination_x coordinate is further away in the negative direction, and 0 if the two x coordinates are equal.
        	 *     Similarly, yDirection returns 1 if the destination_y coordinate is further away in the positive direction on
        	 *      the y axis compared to the current piece's y coordinate, -1 if the destination_y coordinate is further away
        	 *       in the negative direction, and 0 if the two y coordinates are equal.
        	 * In the for loop, the xCheck and yCheck variables are calculated by adding i * xDirection and i * yDirection
        	 *  to the current piece's x and y coordinates, respectively. This allows the code to check the squares on the
        	 *   path from the current piece's position to the destination_x and destination_y coordinates. If the board.getPiece
        	 *    method returns a non-null value for any of the squares on the path, it means that there is a piece blocking the 
        	 *    path and the method returns false. If the for loop completes without finding a blocking piece, the method returns
        	 *    
        	 *     true, indicating that the move is possible.
        	 * If xDiff is not equal to yDiff, the method returns false immediately,
        	 *  indicating that the move is not possible as the piece can only move diagonally.
        	 * **/
            // check if there are no pieces blocking the path
            int xDirection = Integer.signum(destination_x - this.x);
            int yDirection = Integer.signum(destination_y - this.y);
            for (int i = 1; i < xDiff; i++) {
                int xCheck = this.x + i * xDirection;
                int yCheck = this.y + i * yDirection;
                if (board.getPiece(xCheck, yCheck) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}