//Michael Ren

public class Draw {

	public static void standard(Piece[][] board) {			//initialize traditional playing board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 1) {
					if (i < 3) {
						board[i][j] = new Piece(i, j, true);
					}
					if (i > 4) {
						board[i][j] = new Piece(i, j, false);
					}
				}
			}
		}
		for (int i = 0; i < 10; i++) {
			board[8][i] = new Piece();
			board[9][i] = new Piece();
			board[i][8] = new Piece(); 
			board[i][9] = new Piece();
		}
	}	

	public static void currentBoard(Piece[][] board) {			//draw current board
		System.out.println("\t   0  1  2  3  4  5  6  7");
		System.out.println("\t   ______________________");
		for (int i = 0; i < 8; i++) {
			System.out.print("\t" + i + " |");
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == null) {					//print * for square with no pieces
					System.out.print("*  ");
				}
				else if (board[i][j].choice() != 0) {		//print choice selection if there is one and set it equal to 0 after, as well as null. 
					System.out.print(board[i][j].choice()+"  ");
					board[i][j] = null;
				}
				else if (board[i][j].isBlack() && board[i][j].isKing()) {			//print black king
					System.out.print("BK ");
				}
				else if (!board[i][j].isBlack() && board[i][j].isKing()) {		//print whiteking
					System.out.print("WK ");
				}
				else if (board[i][j].isBlack()) {				//print normal black pieces
					System.out.print("B  ");
				}
				else if (!board[i][j].isBlack()) {				//print normal white pieces
					System.out.print("W  ");
				}
			}
			System.out.println();
		}
	}

	public static void test(Piece[][] board) {					//initialize testboard
		board[1][2] = new Piece(1, 2, true); 
		board[1][4] = new Piece(1, 4, true); 
		board[1][6] = new Piece(1, 6, true); 
		board[3][4] = new Piece(3, 4, true); 
		board[4][3] = new Piece(4, 3, false);
		board[4][5] = new Piece(4, 6, false); 
		board[6][1] = new Piece(6, 1, false); 
		board[6][3] = new Piece(6, 3, false); 
		for (int i = 0; i < 10; i++) {
			board[8][i] = new Piece();
			board[9][i] = new Piece();
			board[i][8] = new Piece(); 
			board[i][9] = new Piece();
		}
	}
}