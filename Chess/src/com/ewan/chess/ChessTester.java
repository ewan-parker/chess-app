package com.ewan.chess;

public class ChessTester {

	public static void main(String[] args) {
		
		ChessBoard board = new ChessBoard();
		
		board.printBoard();
		
		System.out.printf("%n%nMake the move knight c3 for white: %n%n");
		
		
		board.movePiece(new Position(7,1), new Position(5,2));
		
		board.printBoard();

	}

}
