
public class Command {
	
	//format like: set rabbit (2,3); go rabbit North; quit
	private String commandWord;
	private String name;
	private String secondWord;
	
	private int num;
	
	/**
	 * Constructor for the Command class
	 * @param firstWord The first command word
	 * @param secondWord The second command word
	 * @param thirdWord The third command word
	 * @param num the number to be added
	 */
	public Command(String firstWord, String secondWord, String thirdWord, int num) {
		commandWord = firstWord;
		name = secondWord;
		this.secondWord = thirdWord;
		this.num = num;
	}
	
	/**
	 * Constructor for the Command class
	 * @param firstWord The first command word
	 * @param secondWord The second command word
	 * @param thirdWord The third command word
	 */
	public Command(String firstWord, String secondWord, String thirdWord) {
		commandWord = firstWord;
		name = secondWord;
		this.secondWord = thirdWord;
		this.num = -1;
	}
	
	/**
	 * Get the name in the command
	 * @return the name in the command
	 */
	public String getNameCalled() {
		return name;
	}
	
	/**
	 * Get the command word from the command
	 * @return the command word
	 */
	public String getCommandWord() {
		return commandWord;
	}
	
	/**
	 * Get the second word in the command
	 * @return the second word
	 */
	public String getSecondWord() {
		return secondWord;
	}
	
	public int getNum() {
		return num;
	}
	
	/**
	 * Test if the command word is unknown
	 * @return True if commandWord is null
	 */
	public boolean isUnknown() {
		return commandWord == null;
	}
	
	/**
	 * Test if the second command exists
	 * @return True if secondWord is not null
	 */
	public boolean hasSecondCommand() {
		return secondWord != null;
	}
	
	/**
	 * Test if the command has a number
	 * @return True if num is not -1
	 */
	public boolean hasNum() {
		return num != -1;
	}
	
	/**
	 * Test if the command has a name
	 * @return True if name is not null
	 */
	public boolean hasName() {
		return name != null;
	}

}
