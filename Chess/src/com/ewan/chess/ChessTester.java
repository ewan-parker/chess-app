package com.ewan.chess;

import java.util.Scanner;

public class ChessTester {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ChessGame game = new ChessGame();
		
		ChessBoard board = game.getBoard();
		
		board.printBoard();
		
		while (true) {
			
			System.out.print("\nPlayer: " + game.getCurrentTurn() + ". Make a Move (eg: e3 e4): ");
			
			String origin = sc.next();
			String destination = sc.next();
		
			Position from = Position.fromString(origin);
			Position to = Position.fromString(destination);;
			
			System.out.println();
			System.out.println("From row: " + from.getRow() + " col: " + from.getCol());
			Piece piece = board.getPieceAt(from);
			System.out.println("Piece at from: " + piece);
			System.out.println();
			
			
			
			
			 
			game.makeMove(from, to);
			
			board.printBoard();
			
		}
		
		
	}

}
