package chessgui.pieces;

import chessgui.Board;

public class King extends Piece {

    public King(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    @Override
    public boolean canMove(int destination_x, int destination_y) {
        int xDiff = Math.abs(destination_x - this.x);
        int yDiff = Math.abs(destination_y - this.y);

        if (xDiff <= 1 && yDiff <= 1) {
            return true;
        }
        return false;
    }
}
