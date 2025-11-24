package com.ewan.chess;

// Represents a square on the board

public class Position {
	
	private int row;
	private int col; //1 = a, 2 = b, 3 = c, 4 = d, 5 = e, 6 = f, 7 = g, 8 = h.
	
	//constructor:
	public Position(int r, int c) {
		this.row = r;
		this.col = c;
	}
	
	//mutator:
	public void setPosition(int r,int c) {	
		row = r;
		col = c;		
	}
	
	
	//Accessors:
	public int getRow() { return row; }
	
	public int getCol() { return col; }
}
