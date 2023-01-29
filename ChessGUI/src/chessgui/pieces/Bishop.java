package chessgui.pieces;

import chessgui.Board;

public class Bishop extends Piece {

    public Bishop(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    @Override
    public boolean canMove(int destination_x, int destination_y) {
        int xDiff = Math.abs(destination_x - this.x);
        int yDiff = Math.abs(destination_y - this.y);
        // check if the move is diagonal
        if (xDiff == yDiff) {
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