//Michael Ren

public class Piece {
	
	private int x;
	private int y;
	private boolean king;
	private boolean isBlack;
	private int choice; 

	public Piece() {
		x = -1;
		y = -1;
		king = false;
		isBlack = true;
		choice = 0; 
	}
	
	public Piece(int x, int y, boolean isBlack) {
		this.x = x;
		this.y = y;
		king = false;
		this.isBlack = isBlack;
		choice = 0;  
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public boolean isKing() {
		return king;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y; 
	}

	public void setKing(boolean king) {
		this.king = king;
	}

	public boolean isBlack() {
		return isBlack; 
	}

	public void setChoice(int k) { 
		choice = k; 
	}

	public int choice() {
		return choice;
	}
	public String getPiece() {
		return "(" + x + " , " + y + ") \t King: " + king;
	}
}