package com.ewan.chess;

public class ChessPiece {

    private PieceType type;
    private PieceColour colour;

    public ChessPiece(PieceType type, PieceColour colour) {
	    this.type = type;
	    this.colour = colour;
    }

    public PieceType getType() {
    	return type;
    }

    public PieceColour getColour() {
    	return colour;
    }

    @Override
    public String toString() {
	    switch(type) {
	        case PAWN:   return colour == PieceColour.WHITE ? "P" : "p";
	        case KNIGHT: return colour == PieceColour.WHITE ? "N" : "n";
	        case BISHOP: return colour == PieceColour.WHITE ? "B" : "b";
	        case ROOK:   return colour == PieceColour.WHITE ? "R" : "r";
	        case QUEEN:  return colour == PieceColour.WHITE ? "Q" : "q";
	        case KING:   return colour == PieceColour.WHITE ? "K" : "k";
	        default: return "?";
    }
}
}
