# Patch Notes

## Version 1.0 - `November 24`

- Playable **console-based chess game** in 'ChessTester.java' 
- Players can take turns moving pieces using the boards square notation (e.g: to play 1. Nf3 `g1 f3`).  
- Supports all **standard pieces**: King, Queen, Rook, Bishop, Knight, Pawn.  
- **Board prints to console** after each move.  
- Captures are tracked and stored in a list, but not yet printed to the console.  
- **No special moves yet**:  
  - Castling not implemented.  
  - En-passant not implemented.  
  - Pawn promotion not implemented.  
- **Game continues indefinitely**; currently does not enforce check, checkmate, or stalemate rules.

[`Version 1.0 Sample Output`](ExampleOutputs/ExampleOutput-V1.0) 

## Version 1.1 - `November 25`
- Added **Castling**! `Move the king 2 squares towards the rook to castle.`
- **Castling rules enforced:**  
  - No pieces between the king and the chosen rook  
  - Neither the king nor the castling rook has moved yet  
- ⚠️ **Limitations:**  
  - Castling does not yet check if the king is in check, passes through a square under attack, or would end in check.
 
[`Version 1.1 Sample Output`](ExampleOutputs/ExampleOutput-V1.1) 
