package com.ewan.chess;

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
			
		board.getLegalMoves(from);
		
		// handle special cases: castling, en-passant, promotion
		
		if (!board.getLegalMoves(from).contains(to)) {
			
			System.out.printf("%nThats Not a legal move!%n%n");
			return false;
		}	
		
		
		board.movePiece(from, to);
		
		 // handle castling access & en-passant
		
		currentTurn = toggleTurn(currentTurn);
		
		return true; //if move was successful.
	}
	
	
	
	
	
	//Checks for various states of a game
	
	
	
	
	public boolean isInCheck(PieceColour colour) {
		
		
		return false;
	}
	
	public boolean isCheckMate(PieceColour colour) {
		
		
		
		return false;
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
  
