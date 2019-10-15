
public class CommandWord {
	
	private static final String[] validCommands = {
			"set", "quit", "move", "help",
			"south", "north", "east", "west",
			"row", "column",
			"left", "right"
	};
	
	private static final String[] validNames = {
			"rabbit1", "rabbit2", "rabbit3",
			"fox1", "fox2",
			"mushroom"
	};
	
	public CommandWord() {
		
	}
	
	public boolean isCommand(String str) {
		for(int i=0; i<validCommands.length; i++) {
			if(validCommands[i].equals(str))return true;
		}
		return false;
	}
	
	public boolean isName(String str) {
		for(int i=0; i<validNames.length; i++) {
			if(validNames[i].equals(str))return true;
		}
		return false;
	}
	
	public void showAll() {
		for(String command: validCommands) {
			System.out.print(command + " ");
		}
		System.out.println();
	}

}
