
public class Game {
	private Parser parser;
	private PlayBoard board;
	
	public Game() {
		parser = new Parser();
		board = new PlayBoard();
	}
	
	private void printWelcome() {
		System.out.println();
        System.out.println("Welcome to the Jump-In Game!");
        System.out.println("Start to set your puzzel.");
        System.out.println("type your command in this format: command name command2");
        System.out.println("for example: set rabbit1 2,3 | set fox1 row 1");
        System.out.println("Type 'help' if you need help.");
        board.printBoard();
	}
	
	private void printHelp() {
        System.out.println("type your command in this format: command name direction");
        System.out.println("command for use: set, move, quit, help");
        System.out.println("name of pieces can use: rabbit1, 2, 3; fox1, 2; mushroom");
        System.out.println("direction for rabbit: up, down, left, right");
        System.out.println("for fox, give the exact distance, for example: move fox1 right 3");
	}
	
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
	
	private boolean isRabbit(String str) {
		return (str.equals("rabbit1")||str.equals("rabbit2")||str.equals("rabbit3"));
	}
	
	private boolean isFox(String str) {
		return (str.equals("fox1")||str.equals("fox2"));
	}
	
	private void setPieces(Command command) {
		String name = command.getNameCalled();
		
		//set rabbit (example: set rabbit1 2,3)
		if(name.equals("rabbit1") ||name.equals("rabbit2") || name.equals("rabbit3")) {
			String[] location = command.getSecondWord().split(",");
			int row = Integer.parseInt(location[0]);
			int col = Integer.parseInt(location[1]);
			
			boolean set = board.setRabbit(row, col);
			if(!set) {
				System.out.println("can set rabbit");
			}
		}
		//set mushroom (example: set mushroom 1,1)
		else if(name.equals("mushroom")) {
			String[] location = command.getSecondWord().split(",");
			int row = Integer.parseInt(location[0]);
			int col = Integer.parseInt(location[1]);
			
			boolean set = board.setMushroom(row, col);
			if(!set) {
				System.out.println("can set mushroom");
			}
		}
		//set fox (example: set fox row 2)
		else if(name.equals("fox1") || name.equals("fox2")) {
			Direction direction = readDirection(command.getSecondWord());
			if(!command.hasNum()) {
				System.out.println("please give the exact " + command.getSecondWord() + " number");
				return;
			}
			int num = command.getNum();
			boolean set = board.setFox(num, direction);
			if(!set) {
				System.out.println("can't set fox");
			}
		}
		
		else {
			System.out.println("can't read this name");
		}
		
	}
	
	private void movePieces(Command command) {
		String name = command.getNameCalled();
		boolean move = false;
		
		if(isRabbit(name)) {
			Direction direction = readDirection(command.getSecondWord());
			if(name.equals("rabbit1")) {
				move = board.jumpTo(board.getRabbit(1), direction);
			}
			else if(name.equals("rabbit2")) {
				move = board.jumpTo(board.getRabbit(2), direction);
			}
			else if(name.equals("rabbit3")) {
				move = board.jumpTo(board.getRabbit(3), direction);
			}
		}
		
		else if(isFox(name)) {
			String direction = command.getSecondWord();
			int num = command.getNum();
			if(direction.equals("left") || direction.equals("up")) {
				if(name.equals("fox1")) {
					int point = board.getFoxLocation(board.getFox(1));
					move = board.MoveTo(board.getFox(1), point-num);
				}
				else if(name.equals("fox2")) {
					int point = board.getFoxLocation(board.getFox(2));
					move = board.MoveTo(board.getFox(2), point-num);
				}
			}
			else if(direction.equals("right")|| direction.equals("down")) {
				if(name.equals("fox1")) {
					int point = board.getFoxLocation(board.getFox(1));
					move = board.MoveTo(board.getFox(1), point+num);
				}
				else if(name.equals("fox2")) {
					int point = board.getFoxLocation(board.getFox(2));
					move = board.MoveTo(board.getFox(2), point+num);
				}	
			}
			else move = false;
		}
		if(!move) {
			System.out.println("can't move this piece");
		}
		
	}
	
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;
		
		if(command.isUnknown()) {
			System.out.println("Can't understand this command");
			return false;
		}
		
		String commandWord = command.getCommandWord();
		if(commandWord.equals("set")) {
			setPieces(command);
			board.printBoard();
		}
		else if(commandWord.equals("quit")) {
			wantToQuit = true;
		}
		else if(commandWord.equals("move")) {
			movePieces(command);
			if(board.getCToWin()==0) {
				System.out.println("win!");
				return wantToQuit = true;
			}
			board.printBoard();
		}
		else if(commandWord.equals("help")) {
			printHelp();
		}
		
		return wantToQuit;
	}
	
	private void play() {
		
		printWelcome();
		boolean finished = false;
		while(!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		System.out.println("Thank you for playing");
		
		
	}
	
	public static void main(String[] args) {
		Game newGame = new Game();
		newGame.play();
	}

}
