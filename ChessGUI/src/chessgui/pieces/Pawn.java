package chessgui.pieces;

import chessgui.Board;

public class Pawn extends Piece {

    private boolean has_moved;
    
    public Pawn(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
        has_moved = false;
        this.board = board;

    }
    
    public void setHasMoved(boolean has_moved)
    {
        this.has_moved = has_moved;
    }
    
    public boolean getHasMoved()
    {
        return has_moved;
    }
    
    @Override
    public boolean canMove(int destination_x, int destination_y)
    {
        // check if the destination is within the board boundaries
        if (destination_x < 0 || destination_x > 7 || destination_y < 0 || destination_y > 7)
            return false;

        // check if the pawn is white or black
        int direction = isWhite() ? 1 : -1;

        // check if the pawn is trying to move diagonally without capturing a piece
        if (destination_x != getX() && Math.abs(destination_x - getX()) != Math.abs(destination_y - getY()))
            return false;

        // check if the pawn is trying to move forward
        if (destination_x == getX()) {
            // check if the pawn has moved before
            if (has_moved) {
                // check if the pawn is moving one square forward
                if (destination_y != getY() + direction)
                    return false;
            } else {
                // check if the pawn is moving two squares forward
                if (destination_y != getY() + (direction * 2))
                    return false;
                // check if there is a piece blocking the pawn's path
                if (getBoard().getPiece(destination_x, getY() + direction) != null)
                    return false;
            }
        } else {
            // check if the pawn is capturing a piece diagonally
            if (destination_y != getY() + direction)
                return false;
            if (getBoard().getPiece(destination_x, destination_y) == null)
                return false;
        }

        return true;
    }

	private Board getBoard() {
		// TODO Auto-generated method stub
		return board;
	}
}
