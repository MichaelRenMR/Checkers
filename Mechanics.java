//Michael Ren

import java.util.*;
public class Mechanics {

	public static ArrayList<Piece> canMove(Piece[][] board, int x, int y) { 	//returns a <Piece>arraylist of all possible locations on the board the piece at board[x][y] can move to	
		ArrayList<Piece> moves = new ArrayList<>();
		if (board[x][y].isBlack() || (board[x][y].isKing())) {
			if (board[(x+1+10)%10][(y+1+10)%10] == null) {
				moves.add(new Piece((x+1+10)%10, (y+1+10)%10, true));
			}
			if (board[(x+1+10)%10][(y-1+10)%10] == null) {
				moves.add(new Piece((x+1+10)%10, (y-1+10)%10, true));
			}
		}
		if (!board[x][y].isBlack() || (board[x][y].isKing())) {
			if (board[(x-1+10)%10][(y+1+10)%10] == null) {
				moves.add(new Piece((x-1+10)%10, (y+1+10)%10, false));
			}	
			if (board[(x-1+10)%10][(y-1+10)%10] == null) {
				moves.add(new Piece((x-1+10)%10, (y-1+10)%10, false));
			}	
		}
		return moves;
	}

	public static ArrayList<Piece> canJump(Piece[][] board, int x, int y) {	//returns a <Piece>arraylist of all possible locations on the board a piece at board[x][y] can jump to and kill something
		ArrayList<Piece> jumps = new ArrayList<>();
		
		if (board[x][y].isBlack()) {
			if (board[(x+1+10)%10][(y+1+10)%10] != null) {
				if (!board[(x+1+10)%10][(y+1+10)%10].isBlack() && board[(x+2+10)%10][(y+2+10)%10] == null) {	//if white and empty space top left for black pieces 
					jumps.add(new Piece(((x+2+10)%10), ((y+2+10)%10), true));
				}
			}	
			if (board[(x+1+10)%10][(y-1+10)%10] != null) {
				if (!board[(x+1+10)%10][(y-1+10)%10].isBlack() && board[(x+2+10)%10][(y-2+10)%10] == null) {	//if white and empty space top right for black pieces
					jumps.add(new Piece(((x+2+10)%10), ((y-2+10)%10), true));
				}	
			}
		}
		
		if(!board[x][y].isBlack()) {
			if (board[(x-1+10)%10][(y+1+10)%10] != null) {
				if (board[(x-1+10)%10][(y+1+10)%10].isBlack() && board[(x-2+10)%10][(y+2+10)%10] == null) {		//if black and empty space bottom left for white pieces 
					jumps.add(new Piece(((x-2+10)%10), ((y+2+10)%10), false));
				}	
			}																								
			if (board[(x-1+10)%10][(y-1+10)%10] != null) {
				if (board[(x-1+10)%10][(y-1+10)%10].isBlack() && board[(x-2+10)%10][(y-2+10)%10] == null) {		//if black and empty space bottom right for white pieces 
						jumps.add(new Piece(((x-2+10)%10), ((y-2+10)%10), false));
				}
			}	
		}

		if (!board[x][y].isBlack() && board[x][y].isKing()) {													//same as above, but for king
			if (board[(x+1+10)%10][(y+1+10)%10] != null) {
				if (board[(x+1+10)%10][(y+1+10)%10].isBlack() && board[(x+2+10)%10][(y+2+10)%10] == null) {	
					jumps.add(new Piece(((x+2+10)%10), ((y+2+10)%10), true));
				}
			}	
			if (board[(x+1+10)%10][(y-1+10)%10] != null) {
				if (board[(x+1+10)%10][(y-1+10)%10].isBlack() && board[(x+2+10)%10][(y-2+10)%10] == null) {	
					jumps.add(new Piece(((x+2+10)%10), ((y-2+10)%10), true));
				}	
			}
		}

		if(board[x][y].isBlack() && board[x][y].isKing()) {
			if (board[(x-1+10)%10][(y+1+10)%10] != null) {
				if (!board[(x-1+10)%10][(y+1+10)%10].isBlack() && board[(x-2+10)%10][(y+2+10)%10] == null) {		
					jumps.add(new Piece(((x-2+10)%10), ((y+2+10)%10), false));
				}	
			}																									
			if (board[(x-1+10)%10][(y-1+10)%10] != null) {
				if (!board[(x-1+10)%10][(y-1+10)%10].isBlack() && board[(x-2+10)%10][(y-2+10)%10] == null) {		
						jumps.add(new Piece(((x-2+10)%10), ((y-2+10)%10), false));
				}
			}	
		}
		return jumps;
	}

	public static Piece[][] multiKill(Piece[][] board, int pieceX, int pieceY) {	//recursive method to allow for multikills. 
		Scanner sc = new Scanner(System.in); 
		int moveCmd = -1;
		ArrayList<Piece> allMoves = new ArrayList<>(); 
		allMoves.addAll(Mechanics.canJump(board, pieceX, pieceY)); 					//make arraylist of all possible moves current piece can take 
		if (allMoves.size() > 0) {
			System.out.println("M-M-M-MULTIKILLLLLLL");
			for (int i = 0; i < allMoves.size(); i++) {
				allMoves.get(i).setChoice(i+1);
				board[allMoves.get(i).x()][allMoves.get(i).y()] = allMoves.get(i);
			}
			
			Draw.currentBoard(board); 
			
			while(!Mechanics.isBounded(moveCmd, 0, allMoves.size())) {
				System.out.println("Input number for location you would like your selected piece to move to.");		//input next move 
				moveCmd = sc.nextInt();
				sc.nextLine(); 
			}
			moveCmd--; 
			
			board[allMoves.get(moveCmd).x()][allMoves.get(moveCmd).y()] = board[pieceX][pieceY]; 
			board[pieceX][pieceY] = null; 
			board[(pieceX + allMoves.get(moveCmd).x())/2][(pieceY + allMoves.get(moveCmd).y())/2] = null; 			//kill piece 
			pieceX = allMoves.get(moveCmd).x();
			pieceY = allMoves.get(moveCmd).y();

			if (Mechanics.isKing(board, allMoves.get(moveCmd).x(), allMoves.get(moveCmd).y())) {			//end turn if king
				return board;
			}
			if (Mechanics.checkWin(board)) {																//end turn if win
					return board;
			}
			board = Mechanics.multiKill(board, pieceX, pieceY); 											//continue multokill otherwise 
		}
		return board; 
	}

	public static boolean checkWin(Piece[][] board) {			//checks for win
		boolean blackPieces = true; 
		boolean whitePieces = true; 

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null) {
					if (board[i][j].isBlack()){
						blackPieces = false; 
					}
					if (!board[i][j].isBlack()) {
						whitePieces = false; 
					}
				}
			}
		}
		return blackPieces || whitePieces; 
	}

	public static boolean isBounded(int k, int min, int max) {		//returns whether the statement min<=k<=max is true. 
		return k>=min && k <= max;
	}

	public static boolean isKing(Piece[][] board, int pieceX, int pieceY) {		//makes pieces kings. returns boolean so that if a king is made turn ends based on boolean value 
		if ((pieceX == 0 && !board[pieceX][pieceY].isBlack()) || (pieceX == 7 && board[pieceX][pieceY].isBlack())) {
				board[pieceX][pieceY].setKing(true);
			    board[pieceX][pieceY].setChoice(0);
				return true;		
		}
		return false; 
	}
}