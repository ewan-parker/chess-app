package com.ewan.chess;

import java.util.List;
import java.util.ArrayList;

public class ChessGame {
	
	private ChessBoard board;
	private PieceColour currentTurn;
	
	private GameState state;
	
	
	 // Special Cases:
    private boolean whiteKingMoved;
    private boolean blackKingMoved;
    private boolean[] whiteRooksMoved; // [queenSide, kingSide]
    private boolean[] blackRooksMoved; // [queenSide, kingSide]
	
	
	private Position enPassantTarget; // square that can be captured with en-passant
	
	
	
	
	// Constructor
	public ChessGame() {
		
		state = GameState.ACTIVE;
		
		board = new ChessBoard();
		
		currentTurn = PieceColour.WHITE;
		
		whiteKingMoved = false;
		blackKingMoved = false;
		whiteRooksMoved = new boolean[2];
		blackRooksMoved = new boolean[2];
		enPassantTarget = null;	
	}
	
	
	public boolean makeMove(Position from, Position to) {
		
		
		Piece piece = board.getPieceAt(from);
		
		if (piece == null) {
			System.out.println("You moved nothing?");
			return false;
		}
		
		if (piece.getColour() != currentTurn) {
			System.out.println("Not your turn!");
			return false;
			}
		
		boolean isWhite = piece.getColour() == PieceColour.WHITE;
		
		
		
			
		
		//Check legal moves for that piece.
		List<Position> legalMoves = board.getLegalMoves(from);
		
		if (piece.getType() == PieceType.KING)
			legalMoves.addAll(getCastlingOptions(from));
		
		if (!legalMoves.contains(to)) {
			System.out.printf("%nThats Not a legal move!%n%n");
			return false;
		}
		
		
		
		//Handle case: Castling:
		
		if (piece.getType() == PieceType.KING && Math.abs(from.getCol() - to.getCol()) == 2) {

			//Move the king to its square
			board.movePiece(from, to); 

			//Update the kings access to castle.
			if (isWhite) whiteKingMoved = true;
			else blackKingMoved = true;
			
			
			int row = (isWhite ? 7 : 0);
			
			
			
			// King-Side Castling
			if (to.getCol() == 6) {

				board.movePiece(new Position(row, 7), new Position(row, 5)); //Castle king side rook
				
				if (isWhite) whiteRooksMoved[1] = true;
				else blackRooksMoved[1] = true;
				
			} 
			
			// Queen-Side Castling
			if (to.getCol() == 2) { 
				
				board.movePiece(new Position(row, 0), new Position(row, 3));
				
				if (isWhite) whiteRooksMoved[0] = true;
				else blackRooksMoved[0] = true;
			}	
			
		} else { 
			//Normal Move 
			board.movePiece(from, to);
			
			if (piece.getType() == PieceType.KING) {
				if (isWhite) whiteKingMoved = true;
				else blackKingMoved = true;
			}
			
			if (piece.getType() == PieceType.ROOK) {
						
				if (isWhite) {
					if (from.getCol() == 0) whiteRooksMoved[0] = true;
					if (from.getCol() == 7) whiteRooksMoved[1] = true;
				}
				
				if (!isWhite) {
					if (from.getCol() == 0) blackRooksMoved[0] = true;
					if (from.getCol() == 7) blackRooksMoved[1] = true;
				}
			}
		}
			

		
		currentTurn = toggleTurn(currentTurn);
		
		return true; //if move was successful.
	}
	
	
	private List<Position> getCastlingOptions(Position from) {
		
		
		List<Position> castlingOptions = new ArrayList<>();
		
		boolean pathSafe;
		
		Piece king = board.getPieceAt(from);
		if (king == null || king.getType() != PieceType.KING) return castlingOptions;
		
		boolean isWhite = king.getColour() == PieceColour.WHITE;
		
		int row = isWhite ? 7 : 0;
		
		Piece leftRook = board.getPieceAt(new Position(row, 0));
		Piece rightRook = board.getPieceAt(new Position(row, 7));
		
		Position b = new Position(row,1);
		Position c = new Position(row,2);
		Position d = new Position(row,3);
		Position f = new Position(row,5);
		Position g = new Position(row,6);
		
		
		// King must be on starting square
		if (isWhite && !(from.getRow() == 7 && from.getCol() == 4)) return castlingOptions;
		if (!isWhite && !(from.getRow() == 0 && from.getCol() == 4)) return castlingOptions;
		
		 //King must not have moved
		if (isWhite && whiteKingMoved) return castlingOptions;	
		if (!isWhite && blackKingMoved) return castlingOptions;
		
		

		
		//White's Castles
		
		if (isWhite && leftRook != null && leftRook.getType() == PieceType.ROOK && leftRook.getColour() == PieceColour.WHITE && !whiteRooksMoved[0] && board.isEmpty(b) && board.isEmpty(c) && board.isEmpty(d)) { //Queen Side
			
			pathSafe = !(board.squareAttacked(d, PieceColour.BLACK) || board.squareAttacked(c, PieceColour.BLACK) || board.squareAttacked(b, PieceColour.BLACK));

			if (pathSafe) {
				castlingOptions.add(c); // Queen-side castle was allowed
			}
			
			
		}
			
		if (isWhite && rightRook != null && rightRook.getType() == PieceType.ROOK && rightRook.getColour() == PieceColour.WHITE && !whiteRooksMoved[1] && board.isEmpty(f) && board.isEmpty(g)) { //King Side
			
			pathSafe = !(board.squareAttacked(f, PieceColour.BLACK) || board.squareAttacked(g, PieceColour.BLACK));

			if (pathSafe) 
				castlingOptions.add(g); // King-side castle was allowed
		}
		
	
		
		//Black's Castles
		
		if (!isWhite && leftRook != null && leftRook.getType() == PieceType.ROOK && leftRook.getColour() == PieceColour.BLACK && !blackRooksMoved[0] && board.isEmpty(b) && board.isEmpty(c) && board.isEmpty(d)) { //Queen Side
			
			pathSafe = !(board.squareAttacked(d, PieceColour.WHITE) || board.squareAttacked(c, PieceColour.WHITE) || board.squareAttacked(b, PieceColour.WHITE));

			if (pathSafe) 
				castlingOptions.add(c); // Queen-side castle was allowed
			
		
		}
		
		if (!isWhite && rightRook != null && rightRook.getType() == PieceType.ROOK && rightRook.getColour() == PieceColour.BLACK && !blackRooksMoved[1] && board.isEmpty(f) && board.isEmpty(g)) { //King Side
			
			pathSafe = !(board.squareAttacked(f, PieceColour.WHITE) || board.squareAttacked(g, PieceColour.WHITE));

			if (pathSafe) 
				castlingOptions.add(g); // King-side castle was allowed
			
		}
		
			
			
		
		return castlingOptions;
	}
	
	
	
	
	
	
	
	//Checks for various states of a game
	
	
	public boolean isInCheck(PieceColour colour) {
		
		board = getBoard();
		Position kingFound = null;
		
		
		
		//Find the King of desired colour.
		boolean found = false;
		
		for (int i = 0; i < 8; i++ ) {
			
			if (found == false) {
				
				for (int k = 0; k < 8; k++) {

					Position pos = new Position(i,k);
						
					if (board.getPieceAt(pos) != null) {
						if (board.getPieceAt(pos).getType() == PieceType.KING && board.getPieceAt(pos).getColour() == colour) {
								kingFound = pos;
								found = true;
								break;
						}
					}
				}
			}
		}
		
		if (kingFound == null) throw new IllegalStateException("King not found!");
		
		
		// Detect Checks:
		PieceColour opponentColour;
		
		opponentColour = (colour == PieceColour.WHITE) ? PieceColour.BLACK : PieceColour.WHITE;
		
		return (board.squareAttacked(kingFound, opponentColour));
			
	}
	
	public boolean isCheckMate(PieceColour colour) {
		
		
		
		return false; //TODO
	}
	
	
	public boolean isStalemate(PieceColour colour) {
		
		
		return false;
	}
	
	//Accessors: 

	public ChessBoard getBoard() { return board; }
    public PieceColour getCurrentTurn() { return currentTurn; }
    
    //Mutators:
    public PieceColour toggleTurn(PieceColour currentTurn) {
    	
    	if (currentTurn == PieceColour.WHITE) {
			return PieceColour.BLACK;
		}
    	else if (currentTurn == PieceColour.BLACK)
			return PieceColour.WHITE;
    	
    	return null;
    	}
    
}
  
