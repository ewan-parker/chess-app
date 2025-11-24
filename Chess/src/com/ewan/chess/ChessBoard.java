package com.ewan.chess;

public class ChessBoard {
	
	private Piece[][] squares = new Piece[8][8];
	
	// Constructor:
	public ChessBoard() {
		
		//Place the pawns with a loop.
		for (int c = 0; c < 8; c++) {	
			squares[6][c] = new Piece(PieceType.PAWN, PieceColour.WHITE, new Position(6, c));
			squares[1][c] = new Piece(PieceType.PAWN, PieceColour.BLACK, new Position(1, c));	
		}
		
		// Black Pieces
		squares[0][0] = new Piece(PieceType.ROOK, PieceColour.BLACK, new Position(0, 0));
		squares[0][1] = new Piece(PieceType.KNIGHT, PieceColour.BLACK, new Position(0, 1));
		squares[0][2] = new Piece(PieceType.BISHOP, PieceColour.BLACK, new Position(0, 2));
		squares[0][3] = new Piece(PieceType.QUEEN, PieceColour.BLACK, new Position(0, 3));
		squares[0][4] = new Piece(PieceType.KING, PieceColour.BLACK, new Position(0, 4));
		squares[0][5] = new Piece(PieceType.BISHOP, PieceColour.BLACK, new Position(0, 5));
		squares[0][6] = new Piece(PieceType.KNIGHT, PieceColour.BLACK, new Position(0, 6));
		squares[0][7] = new Piece(PieceType.ROOK, PieceColour.BLACK, new Position(0, 7));

		// White Pieces:
		squares[7][0] = new Piece(PieceType.ROOK, PieceColour.WHITE, new Position(7, 0));
		squares[7][1] = new Piece(PieceType.KNIGHT, PieceColour.WHITE, new Position(7, 1));
		squares[7][2] = new Piece(PieceType.BISHOP, PieceColour.WHITE, new Position(7, 2));
		squares[7][3] = new Piece(PieceType.QUEEN, PieceColour.WHITE, new Position(7, 3));
		squares[7][4] = new Piece(PieceType.KING, PieceColour.WHITE, new Position(7, 4));
		squares[7][5] = new Piece(PieceType.BISHOP, PieceColour.WHITE, new Position(7, 5));
		squares[7][6] = new Piece(PieceType.KNIGHT, PieceColour.WHITE, new Position(7, 6));
		squares[7][7] = new Piece(PieceType.ROOK, PieceColour.WHITE, new Position(7, 7));

	}
	
	//Accessors:
	
	public Piece getPieceAt(Position pos) {
		return squares[pos.getRow()][pos.getCol()];
	}
	
	public boolean isEmpty(Position pos) {
		if (squares[pos.getRow()][pos.getCol()] == null)
			return true;
		else
			return false;
	}
	
	public void movePiece(Position from, Position to) {
		
		//Get piece at beginning square
		Piece piece = squares[from.getRow()][from.getCol()];
		
		//Place piece at destination
		squares[to.getRow()][to.getCol()] = piece;
		
		//Clear original square:
		squares[from.getRow()][from.getCol()] = null;
		
		if (piece != null) { piece.setPosition(to); }
		
		
		
	}
	
	//Board Printing for console play:
	
	public void printBoard() {
    for (int r = 0; r < 8; r++) {
        for (int c = 0; c < 8; c++) {
            Piece p = squares[r][c];
            if (p != null) {
                System.out.print(p.toString() + " ");
            } else {
                System.out.print(". "); // empty square
            }
        }
        System.out.println();
    }
}
	
	
	
	
	
}
