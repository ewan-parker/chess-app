package com.ewan.chess;

public class Piece {

	private PieceType type;
    private PieceColour colour;
    private Position position;

    public Piece(PieceType type, PieceColour colour, Position pos) {
	    this.type = type;
	    this.colour = colour;
	    this.position = pos;
    }
    
    public void setPosition(Position newPos) {
    	this.position = newPos;
    }

    
    //Accessors:
	public PieceType getType() {
    	return type;
    }

    public PieceColour getColour() {
    	return colour;
    }
    
    public Position getPosition() {
    	return position;
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

