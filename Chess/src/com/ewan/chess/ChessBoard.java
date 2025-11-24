package com.ewan.chess;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
	
	private Piece[][] squares = new Piece[8][8];
	
	//Captured Pieces:
	private List<Piece> capturedPieces = new ArrayList<>(); //Make a list of piece objects that were captured.
	
	public List<Piece> getCapturedPieces() {
        return capturedPieces;
    }
	
	
	
	
	// Constructor:
	public ChessBoard() {
		
		//Place the pawns with a loop.
		for (int c = 0; c < 8; c++) {	
			squares[6][c] = new Piece(PieceType.PAWN, PieceColour.WHITE);
			squares[1][c] = new Piece(PieceType.PAWN, PieceColour.BLACK);	
		}
		
		// Black Pieces
		squares[0][0] = new Piece(PieceType.ROOK, PieceColour.BLACK);
		squares[0][1] = new Piece(PieceType.KNIGHT, PieceColour.BLACK);
		squares[0][2] = new Piece(PieceType.BISHOP, PieceColour.BLACK);
		squares[0][3] = new Piece(PieceType.QUEEN, PieceColour.BLACK);
		squares[0][4] = new Piece(PieceType.KING, PieceColour.BLACK);
		squares[0][5] = new Piece(PieceType.BISHOP, PieceColour.BLACK);
		squares[0][6] = new Piece(PieceType.KNIGHT, PieceColour.BLACK);
		squares[0][7] = new Piece(PieceType.ROOK, PieceColour.BLACK);

		// White Pieces:
		squares[7][0] = new Piece(PieceType.ROOK, PieceColour.WHITE);
		squares[7][1] = new Piece(PieceType.KNIGHT, PieceColour.WHITE);
		squares[7][2] = new Piece(PieceType.BISHOP, PieceColour.WHITE);
		squares[7][3] = new Piece(PieceType.QUEEN, PieceColour.WHITE);
		squares[7][4] = new Piece(PieceType.KING, PieceColour.WHITE);
		squares[7][5] = new Piece(PieceType.BISHOP, PieceColour.WHITE);
		squares[7][6] = new Piece(PieceType.KNIGHT, PieceColour.WHITE);
		squares[7][7] = new Piece(PieceType.ROOK, PieceColour.WHITE);

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
	
	
	public List<Position> getLegalMoves(Position from) {
		List<Position> moves = new ArrayList<>();
		Piece piece = squares[from.getRow()][from.getCol()];
		
		if (piece == null)
			return moves;
		
		switch(piece.getType()) {
		
			case PAWN: { //Currently doesnt have en-passant
				 int row = from.getRow();
				 int col = from.getCol();
				 int direction = piece.getColour() == PieceColour.WHITE ? -1 : 1; // white (-1) moves up, black (1) moves down
				 
				 //One square forward:
				 Position oneStep = new Position(row + direction, col);
				 if (isInBounds(oneStep) && isEmpty(oneStep)) {
					 moves.add(oneStep);
					 
					 
					 //Two squares forward (only allowed when oneStep is allowed).
					 boolean startingRow = (piece.getColour() == PieceColour.WHITE && row == 6) || (piece.getColour() == PieceColour.BLACK && row == 1);
					 Position twoStep = new Position(row + 2*direction, col);
					 if (startingRow && isEmpty(twoStep)) { moves.add(twoStep); }
					 
				 }
				 
				 //Diagonal capture
				 
				 int[] captureColumns = { col - 1, col + 1 };
				 
				 for (int i = 0; i < captureColumns.length; i++) {
					 Position diagonal = new Position(row+direction, captureColumns[i]);
					 
					 if (isInBounds(diagonal) && !isEmpty(diagonal)) {
						 Piece target = getPieceAt(diagonal);
						 if (target.getColour() != piece.getColour()) { moves.add(diagonal); }
					 }
				 }
				 
				 break;
			}
			
			case KNIGHT: {
				int row = from.getRow();
				int col = from.getCol();
				
				//Knight Moves:
				
				int[][] offsets = { { 2, 1}, { 2, -1}, // offsets[pair][location]
								    {-2, 1}, {-2, -1},
								    { 1, 2}, { 1, -2},
								    {-1, 2}, {-1, -2} };
				
				for (int i=0; i< offsets.length; i++) {
					
					Position knightMove = new Position(row + offsets[i][0] , col + offsets[i][1]);
								
						if (isInBounds(knightMove)) { //if the move is on the board
							
							Piece threatened = getPieceAt(knightMove); //check the square for a piece
										
							if (threatened == null) { moves.add(knightMove); } //if the square is empty, its a valid move.
										
							else if ( threatened.getColour() != piece.getColour() ) { moves.add(knightMove); } //if the square is an enemy piece, its a valid move.
								
						}
				}
				
				break;
			}
			
			case BISHOP: {
				int row = from.getRow();
				int col = from.getCol();
				
				int[][] bishopDirections = { {1,1}, {1,-1}, {-1,1}, {-1,-1} }; // directions[pair][location]
				
				slidingMoves(bishopDirections, piece, moves, row, col, 8);
				
				break;
			}
			
			case ROOK: {
				
				int row = from.getRow();
				int col = from.getCol();
				
				int[][] rookDirections = { {1,0}, {-1,0}, {0,1}, {0,-1} }; // directions[pair][location]
				
				slidingMoves(rookDirections, piece, moves, row, col, 8);
		
				break;
			}
			case QUEEN: {
				
				int row = from.getRow();
				int col = from.getCol();
				
				int[][] queenDirections = { {1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1} }; // directions[pair][location]
				
				slidingMoves(queenDirections, piece, moves, row, col, 8);
		
				break;
			}
			case KING: { //Castling moves belong to the king, not the rook.
				
				int row = from.getRow();
				int col = from.getCol();
				
				int[][] kingDirections = { {1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1} }; // directions[pair][location]
				
				slidingMoves(kingDirections, piece, moves, row, col, 1);
		
				break;
			}
			
		}
		
		return moves;
	}
	
	//Helper methods for getLegalMoves():
	private boolean isInBounds(Position pos) {
	    int r = pos.getRow();
	    int c = pos.getCol();
	    return r >= 0 && r < 8 && c >= 0 && c < 8;
	}
	
	private void slidingMoves(int[][] directions, Piece piece, List<Position> moves, int row, int col, int maxSteps) {
		
		for (int i = 0; i < directions.length; i++) {
		
			for (int k = 1; k <= maxSteps; k++) {
		
				Position movePosition = new Position(row + k*directions[i][0], col + k*directions[i][1]);
						
				if (isInBounds(movePosition)) {
							
					Piece threatened = getPieceAt(movePosition); //check the square for a piece
											
					if (threatened == null) { moves.add(movePosition); } //if the square is empty, its a valid move.
											
					else if ( threatened.getColour() != piece.getColour() ) { moves.add(movePosition); break; } //if the square has a enemy piece, validate the move and stop counting
								
					else { break; } // occurs when the square is occupied by one of the players own pieces
								
				} else { break; } // occurs when the square is off of the board
						
					
			}	
		}
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
	
	
	//Mutators
	public void movePiece(Position from, Position to) {
	    Piece movingPiece = squares[from.getRow()][from.getCol()];
	    Piece capturedPiece = squares[to.getRow()][to.getCol()];
	
	    
	    // Move the piece
	    squares[to.getRow()][to.getCol()] = movingPiece;
	    squares[from.getRow()][from.getCol()] = null;

	    //Add capturedPiece to capturedPieces list.
	    if (capturedPiece != null) {
	    	capturedPieces.add(capturedPiece);
	    }
	    
	}
	
	
	
}
