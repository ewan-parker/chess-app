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

---

## Version 1.1 - `November 25, AM`

### Added **Castling**! `Move the king 2 squares towards the rook to castle.`
- **Castling rules enforced:**  
  - No pieces between the king and the chosen rook  
  - Neither the king nor the castling rook has moved yet

**Limitations:**  Castling does not yet check if the king is in check, passes through a square under attack, or would end in check.

[`Version 1.1 Sample Output`](ExampleOutputs/ExampleOutput-V1.1) 

---

## Version 1.2 - `November 25, PM`

### **Updated Castling:** 
- is now near-rigourous, you now cannot castle while a piece is attacking the kings path. only remaining castling issue belongs to check

### Added **Check:**
- `Check!` will be printed to the board if your king is in check.
- Program detects if king is under attack
- **King Movement:** The king now may not move to a square which it will be in check.

⚠️**Limitations:**
  - moves may reveal the king to attacking pieces.
  - players may move, including castle, while in check.

[`Version 1.2 Sample Output`](ExampleOutputs/ExampleOutput-V1.2) 

---

## Version 1.3 - `November 26, AM`

### **Completed Check and Castling:**
- King can no longer **castle out of check** or through attacked squares.  
- Castling logic now correctly considers **all squares the king traverses**.
- You can no longer make a move that leaves you in check, or ignore being in check and move another piece

⚠️**Limitations:**
  - Checkmate and stalemate detection still not implemented.  
  - En-passant and pawn promotion still not implemented.  

[`Version 1.3 Sample Output`](ExampleOutputs/ExampleOutput-V1.3)  

---

## Version 1.4 - `November 26, PM`

### Added **Checkmate and Stalemate**
- The game now ends when either player cannot make a move.
- The type of ending is printed to the console with a message explaining the games ending.

[`Version 1.4 Sample Output`](ExampleOutputs/ExampleOutput-V1.4)

---

## Version 1.5 - `November 27, AM`

### Added **En-Passant**
- Historically one of the last major additions to chess, assumed to be adopted around the 15th century.
- En passant is a pawn specific move that allows the capture of a rank adjacent enemy pawn that has made a 2 square advance on the last turn

### Added **Graveyard**
- We should remember that our pieces went out to battle for us, I've added a graveyard of pieces to commemorate taken pieces.

[`Version 1.5 Sample Output`](ExampleOutputs/ExampleOutput-V1.5.txt)

---

