# Jump In Project
## Group Members:
- Mika Argyle
- Zewen Chen
- Tiantian Lin
- Ruixuan Ni
- Craig Worthington

## Deliverables
* Command.java: Command object for user-generated commands
* CommandWord.java: Tests commands entered by the user
* Game.java: Main file for the game
* Parser.java: Parses user inputs to generate commands
* PlayBoard.java: Main file for the game board
* Direction.java: Enum file for valid gamepiece movement and orientations
* Fox.java: Main object for Fox gamepiece
* Square.java: Main object for the each square on the game board

## Execution Instructions
### 1. Set the game pieces:
Start the game by running the Game.java file

Set game pieces on the board by entering the following commands:
```java
set <gamepiece name> <position> <index (optional)>
``` 

For example, the command to set rabbit1 at position (2,3) would be the following: `set rabbit1 2,3`. Mushrooms also follow the same syntax, i.e. `set mushroom 3,4`

To set the position of a fox, enter if it is a row or column, then enter the column index. For example `set fox1 column 1` would add a fox in column 1

Note: To set foxes in the correct position, a move might be necessary. For example, to set a fox in positions (1,3) and (1,4), first set the fox in row 1, then move him three spaces to the right with `move fox1 right 3`

### 3. Move the pieces
To move a rabbit, use the following syntax
```java
move <rabbit name> <direction>
```
For example, to move rabbit1 down, type `move rabbit1 down`. This will check if this is a valid move, and will move the rabbit into the next available space below it.

Valid directions are `left, right, up, down`

To move a fox, use the following format
```java
move <fox name> <direction> <number of spaces>
```
For example, to move a fox right three spaces, type `move rabbit1 down`


## Known Issues
