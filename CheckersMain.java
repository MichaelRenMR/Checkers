//Michael Ren

import java.util.*;
public class CheckersMain {

	public static void main(String[] args) {

		Piece[][] board = new Piece[10][10]; 
		Draw.standard(board);	//Draw.standard(board) initializes standard competitive board. Draw.test(board) initializes board with easy king, multikill, and win setup. 
		Scanner sc = new Scanner(System.in); 
		int pieceX;
		int pieceY; 
		int firstJumpIndex; 
		int moveCmd;
		int turn = 1; 
		ArrayList<Piece> allMoves = new ArrayList<>();
		System.out.println("Welcome to Checkers!");
		System.out.println("B - Black Piece\t W - White Piece\tBK - Black King\t WK - White King\t* - Empty space");
		System.out.println("Black goes first.");
		
		while(true) {

			System.out.println("Player " + turn + " turn:");
			Draw.currentBoard(board);
			System.out.println("Which piece would you like to move? Input row and column number with a space separating.");
			
			while(true) {					//taking inputs. 
			
				pieceX = sc.nextInt(); 
				pieceY = sc.nextInt(); 
				sc.nextLine();
			
				allMoves.clear(); 
				firstJumpIndex = 0;
			
				if (board[pieceX][pieceY] != null && Mechanics.isBounded(pieceX, 0, 7) && Mechanics.isBounded(pieceY, 0, 7)) {
					if ((turn == 1 && board[pieceX][pieceY].isBlack()) || (turn == 2 && !board[pieceX][pieceY].isBlack())) {
						allMoves.addAll(Mechanics.canMove(board, pieceX, pieceY));
						firstJumpIndex = allMoves.size(); 
						allMoves.addAll(Mechanics.canJump(board, pieceX, pieceY));
						if (allMoves.size() > 0) {
							break;
						}
					}
				}
				
				System.out.println("One of your pieces is not occupying that square or you can't move that piece! Pick a different square.");
			}
			
			for (int i = 0; i < allMoves.size(); i++) {					//Drawing possible moves 
				allMoves.get(i).setChoice(i+1);							//set choice of pieces in allmoves to index. 
				board[allMoves.get(i).x()][allMoves.get(i).y()] = allMoves.get(i);
			}
			
			Draw.currentBoard(board); 
			moveCmd = -2;
			
			while(!Mechanics.isBounded(moveCmd, -1, allMoves.size())) {		//Taking inputs for move command of selected piece 
				System.out.println("Input number for location you would like your selected piece to move to. If you want to move a different piece instead, enter 0. ");
				moveCmd = sc.nextInt();
				sc.nextLine(); 
			}
			
			moveCmd--;
			if (moveCmd == -1) { 
					continue;
			}
			
			board[allMoves.get(moveCmd).x()][allMoves.get(moveCmd).y()] = board[pieceX][pieceY]; 
			board[pieceX][pieceY] = null; 

			if (Mechanics.isKing(board, allMoves.get(moveCmd).x(), allMoves.get(moveCmd).y())) {		//end turn if king is made 
				turn = 3 - turn;
				continue;
			}

			if (moveCmd >= firstJumpIndex) {															//initialize multikill if kill is made 
				board[(pieceX + allMoves.get(moveCmd).x())/2][(pieceY + allMoves.get(moveCmd).y())/2] = null;		//kill piece
				pieceX = allMoves.get(moveCmd).x();
				pieceY = allMoves.get(moveCmd).y();
				board = Mechanics.multiKill(board, pieceX, pieceY);							//start multikill method
				if (Mechanics.checkWin(board)) {				//break out of loop and progress to win output
					break;
				}
				System.out.println("No multikill available or cannot multikill after kingship!"); //default error 
			}
			
			System.out.println("======================================");
			turn = 3 - turn;	//alternate between player 1 and 2 turn. 
		} 
		sc.close();
		System.out.println("Player " + turn + " wins!");
	}
}