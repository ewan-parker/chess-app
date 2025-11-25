package com.ewan.chess;

import java.util.Scanner;

public class ChessTester {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ChessGame game = new ChessGame();
		
		ChessBoard board = game.getBoard();
		
		board.printBoard();
		
		while (true) {
			System.out.print("\nPlayer: " + game.getCurrentTurn() + ". Make a Move (eg: g1 f3): ");
    
			String stringFrom = sc.next();
			String stringTo = sc.next();
    
			
			Position from = Position.fromString(stringFrom);
			Position to = Position.fromString(stringTo);
			
    
			System.out.println();
			System.out.println("From row: " + from.getRow() + " col: " + from.getCol());
			Piece piece = board.getPieceAt(from);
			System.out.println("Piece at from: " + piece);
			System.out.println();
    
			boolean moveSuccess = game.makeMove(from, to);
    
			if (!moveSuccess) {
				System.out.println("Move failed! Try again.");
				continue;
			}
    
			board.printBoard();
		}
		
		
		
		
	}

}
