# Checkers
Simple game of [American Checkers](https://www.thesprucecrafts.com/play-checkers-using-standard-rules-409287) written in Java with terminal graphics. Meant for two players. Created in early 2019. 

## Code Breakdown 
### CheckersMain.java 
Includes most of the main game logic for the game. Initializes the board and playing pieces then runs the game on loop. 

### Draw.java 
Responsible for printing the board in the terminal. Also includes code for creating the board and allows for simple initialization of nonstandard board configurations. The current boards provided are `standard()`, which creates the standard initial boardstate and `test()`, which creates a board configured to test for kills, multikills, men, and kings. 

### Mechanics.java 
Contains the main game mechanics. Included methods determine all possible moves the current player can take and make moves based on player input. Also checks for win at the end of every turn. 

### Piece.java 
The `Piece` class. Class attributes include an xy coordinate representing the piece's location, whether the piece is a king or man, whether the piece is white or black, and the move that the player makes with the piece (defaulted to zero). 

## Usage 
Build all files and run `CheckersMain.java`.

## Future Development
No future development is planned for this project. 
