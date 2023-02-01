package chessgui.pieces;

import chessgui.Board;

public class Queen extends Piece {
	public Queen(int x, int y, boolean is_white, String file_path, Board board)
	{
	    super(x,y,is_white,file_path, board);
	}

	@Override
	public boolean canMove(int destination_x, int destination_y) {
	    if(destination_x == this.getX() && destination_y != this.getY()) {
	        // moving along the same row
	        return true;
	    }
	    else if(destination_y == this.getY() && destination_x != this.getX()) {
	        // moving along the same column
	        return true;
	    }
	    else if(Math.abs(destination_x - this.getX()) == Math.abs(destination_y - this.getY())) {
	        // moving diagonally
	        return true;
	    }
	    return false;
	}
}
