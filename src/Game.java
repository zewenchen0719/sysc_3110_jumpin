import gamepieces.Direction;

public class Game {
	private Parser parser;
	private PlayBoard board;
	
	public Game() {
		parser = new Parser();
		board = new PlayBoard();
	}
	
	/**
	 * Print welcome messages to console
	 */
	private void printWelcome() {
		System.out.println();
        System.out.println("Welcome to the Jump-In Game!");
        System.out.println("type your command in this format: command name command2");
        System.out.println("for example: move rabbit up | move fox1 left 1");
        System.out.println("Type 'help' if you need help.");
        board.printBoard();
	}
	
	/**
	 * Print help messages to the console
	 */
	private void printHelp() {
        System.out.println("type your command in this format: command name direction");
        System.out.println("command for use: move, quit, help");
        System.out.println("name of pieces can use: rabbit1, 2, 3; fox1, 2; mushroom");
        System.out.println("direction for rabbit: up, down, left, right");
        System.out.println("for fox, give the exact distance, for example: move fox1 right 3");
	}
	
	/**
	 * Read the direction from the console
	 * @param direction The direction parsed from the console
	 * @return The direction enum equivalent to what was entered in the console
	 */
	private Direction readDirection(String direction) {
		switch(direction) {
			case "up": return Direction.NORTH;
			case "down": return Direction.SOUTH;
			case "left": return Direction.WEST;
			case "right": return Direction.EAST;
			case "column": return Direction.VERTICAL;
			case "row" : return Direction.HORIZONTAL;
			default: return null;
		}
	}
	
	/**
	 * Test if the string is a valid rabbit string
	 * @param str the string entered by the user
	 * @return True if the string is a valid rabbit name
	 */
	private boolean isRabbit(String str) {
		return (str.equals("rabbit1") || str.equals("rabbit2") || str.equals("rabbit3"));
	}
	
	/**
	 * Test if the string is a valid fox string
	 * @param str The string entered by the user
	 * @return True if the string is a valid fox string
	 */
	private boolean isFox(String str) {
		return (str.equals("fox1") || str.equals("fox2"));
	}
	
	
	
	/**
	 * Move a piece on the board
	 * @param command The command object used to move a piece
	 */
	private void movePieces(Command command) {
		String name = command.getNameCalled();
		boolean move = false;
		
		if (isRabbit(name)) {
			Direction direction = readDirection(command.getSecondWord());
			if (name.equals("rabbit1")) {
				move = board.jumpTo(board.getRabbit(1), direction);
			} else if (name.equals("rabbit2")) {
				move = board.jumpTo(board.getRabbit(2), direction);
			} else if (name.equals("rabbit3")) {
				move = board.jumpTo(board.getRabbit(3), direction);
			}
		} else if (isFox(name)) {
			if(!command.hasNum()) move = false;
			
			else{
				String direction = command.getSecondWord();
				int num = command.getNum();
				if (direction.equals("left") || direction.equals("up")) {
					if (name.equals("fox1")) {
						int point = board.getFoxLocation(board.getFox(1));
						move = board.moveTo(board.getFox(1), point-num);
					} else if (name.equals("fox2")) {
						int point = board.getFoxLocation(board.getFox(2));
						move = board.moveTo(board.getFox(2), point-num);
					}
				} 
				else if (direction.equals("right")|| direction.equals("down")) {
					if (name.equals("fox1")) {
						int point = board.getFoxLocation(board.getFox(1));
						move = board.moveTo(board.getFox(1), point+num);
					} else if (name.equals("fox2")) {
						int point = board.getFoxLocation(board.getFox(2));
						move = board.moveTo(board.getFox(2), point+num);
					}	
				}
				else {
					move = false;
				}
			} 
			
		}
		
		if (!move) {
			System.out.println("can't move this piece");
		}
	}
	
	/**
	 * Process a command from the console
	 * @param command The command to be processed
	 * @return True if the user wants to quit
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;
		
		if (command.isUnknown()) {
			System.out.println("Can't understand this command");
			return false;
		}
		
		String commandWord = command.getCommandWord();
		/*if (commandWord.equals("set")) {
			setPieces(command);
			board.printBoard();
		}*/
		if (commandWord.equals("quit")) {
			wantToQuit = true;
		}
		else if (commandWord.equals("move")) {
			movePieces(command);
			if (board.isWin()) {
				System.out.println("win!");
				return wantToQuit = true;
			}
			board.printBoard();
		}
		else if (commandWord.equals("help")) {
			printHelp();
		}
		
		return wantToQuit;
	}
	
	/**
	 * Play the game
	 */
	private void play() {
		printWelcome();
		boolean finished = false;
		
		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		
		System.out.println("Thank you for playing");
	}
	
	/**
	 * Main function for the game
	 * @param args
	 */
	public static void main(String[] args) {
		Game newGame = new Game();
		newGame.play();
	}

}