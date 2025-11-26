package com.ewan.chess;

import java.util.Scanner;

public class ChessTester {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ChessGame game = new ChessGame();
		
		ChessBoard board = game.getBoard();
		
		board.printBoard();
		
		while (true) {
			
			if (game.isCheckMate(game.getCurrentTurn()))
				break;
			
			if (game.isStaleMate(game.getCurrentTurn()))
				break;
			
			
			if (game.isInCheck(game.getCurrentTurn())) {
				System.out.println(" ");
				System.out.println("Check!");
			}
				
				
				
			//Recieve and Convert Player input
			System.out.print("\nPlayer: " + game.getCurrentTurn() + ". Make a Move (eg: g1 f3): ");
			
    
			String stringFrom = sc.next();
			String stringTo = sc.next();
    
			System.out.println();
			
			Position from = Position.fromString(stringFrom);
			Position to = Position.fromString(stringTo);
			
			
			
			//Make the move
			boolean moveSuccess = game.makeMove(from, to);
    
			if (!moveSuccess) {
				System.out.println("Move failed! Try again.");
				continue;
			}
    
			board.printBoard();
			
			
		}	
	}

}
