package chessgui.pieces;

import chessgui.Board;

public class Knight extends Piece {

    public Knight(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    public boolean canMove(int destination_x, int destination_y) {
        int x_diff = Math.abs(destination_x - this.x);
        int y_diff = Math.abs(destination_y - this.y);

        if ((x_diff == 2 && y_diff == 1) || (x_diff == 1 && y_diff == 2)) {
            return true;
        } else {
            return false;
        }
    }
}
