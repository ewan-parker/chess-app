package com.ewan.chess;

import java.util.List;
import java.util.ArrayList;

public class MoveRecord {
	
	
	
	// List of all the moves in a game.
	private List<SingleMove> moves = new ArrayList<>();
	
	
	public void addMove(SingleMove move) {
		moves.add(move);
	}
	
	public SingleMove undoLastMove() {
		if (moves.isEmpty()) return null;
		
		return moves.remove(moves.size() - 1);
	}
	
	public SingleMove getLastMove() {
		if (moves.isEmpty()) return null;
		
		return moves.remove(moves.size() - 1);
	}
	
	public List<SingleMove> getMoves() {
		return moves;
	}

	
	public static class SingleMove {
        public Position from;
        public Position to;
        public Piece movedPiece;
        public Piece capturedPiece; // null if no piece captured
        public PieceColour playerColour;

        public SingleMove(Position from, Position to, Piece movedPiece, Piece capturedPiece, PieceColour playerColour) {
            this.from = from;
            this.to = to;
            this.movedPiece = movedPiece;
            this.capturedPiece = capturedPiece;
            this.playerColour = playerColour;
        }
    }
}
