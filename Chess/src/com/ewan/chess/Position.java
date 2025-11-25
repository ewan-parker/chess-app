package com.ewan.chess;

// Represents a square on the board

public class Position {
	
	private int row;
	private int col; //1 = a, 2 = b, 3 = c, 4 = d, 5 = e, 6 = f, 7 = g, 8 = h.
	
	//constructors:
	
	//
	public Position(int r, int c) { // Explicit Initialization
		this.row = r;
		this.col = c;
	}
	
	public Position() { // Initialize to some default values; they’ll be overwritten by fromString
		this.row = 0;
		this.col = 0;
	}
	
	//mutator:
	public void setPosition(int r,int c) {	
		row = r;
		col = c;		
	}
	
	
	//Accessors:
	public int getRow() { return row; }
	
	public int getCol() { return col; }
	
	public static Position fromString(String notationOfSquare) {
		
		//Verify format
		if (notationOfSquare == null || notationOfSquare.length() != 2) 
			throw new IllegalArgumentException("Invalid input: must be like e4");
		
		
		
		char file = Character.toLowerCase(notationOfSquare.charAt(0)); // column letter
		char rank = notationOfSquare.charAt(1); 
		
		
		//Verify again if input is in the right format
		if (file < 'a' || file > 'h') 
			throw new IllegalArgumentException("Invalid file (column): must be a-h");
		
		if (rank < '1' || rank > '8') 
			throw new IllegalArgumentException("Invalid rank (row): must be 1-8");
    

		int convertedRow = 8 - Character.getNumericValue(rank);
		int convertedCol = file - 'a';
		
		return new Position(convertedRow, convertedCol);
	}
	
	//A .equals() method for positions
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Position other = (Position) obj;
		return this.row == other.row && this.col == other.col;
	}
	@Override
	public int hashCode() {
		return 31 * row + col; // 31 is standard, you can use any prime
	}
		
}
