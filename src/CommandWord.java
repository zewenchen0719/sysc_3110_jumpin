import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandWord {
	
	private static final List<String> VALID_COMMANDS = Collections.unmodifiableList(Arrays.asList(new String[] {"set", "quit", "move", "help", "south", "north", "east", "west", "row", "column", "left", "right"}));
	private static final List<String> VALID_NAMES = Collections.unmodifiableList(Arrays.asList(new String[] {"rabbit1", "rabbit2", "rabbit3", "fox1", "fox2","mushroom"}));
	
	public CommandWord() {
		
	}
	
	/**
	 * Test if the string is a valid command
	 * @param str The string to test
	 * @return True if the string is a valid command
	 */
	public boolean isCommand(String str) {
		if (VALID_COMMANDS.contains(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Test if the string is a valid name
	 * @param str the string to test
	 * @return True if the string is a valid name
	 */
	public boolean isName(String str) {
		if (VALID_NAMES.contains(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Show all commands on the console
	 */
	public void showAll() {
		for(String command: VALID_COMMANDS) {
			System.out.print(command + " ");
		}
		System.out.println();
	}

}
