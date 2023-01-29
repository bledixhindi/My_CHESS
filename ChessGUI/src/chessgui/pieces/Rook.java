package chessgui.pieces;

import chessgui.Board;

public class Rook extends Piece {

    public Rook(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    @Override
    public boolean canMove(int destination_x, int destination_y) {
        // check if destination is out of bound
        if (destination_x < 0 || destination_x > 7 || destination_y < 0 || destination_y > 7) {
            return false;
        }

        // check if destination is same as current position
        if (destination_x == getX() && destination_y == getY()) {
            return false;
        }

        // check if the path is clear horizontally or vertically
        if (destination_x == getX() || destination_y == getY()) {
            int x_direction = Integer.compare(destination_x, getX());
            int y_direction = Integer.compare(destination_y, getY());

            // check if the path is clear
            for (int i = 1; i < Math.max(Math.abs(destination_x - getX()), Math.abs(destination_y - getY())); i++) {
                if (getBoard().getPiece(getX() + i * x_direction, getY() + i * y_direction) != null) {
                    return false;
                }
            }

            // check if destination is occupied by the same colored piece
            if (getBoard().getPiece(destination_x, destination_y) != null && getBoard().getPiece(destination_x, destination_y).isWhite() == isWhite()) {
                return false;
            }
            return true;
        }
        return false;
    }

	private Board getBoard() {
		// TODO Auto-generated method stub
		return null;
	}
}
