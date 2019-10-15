
public class Command {
	
	//format like: set rabbit (2,3); go rabbit North; quit
	private String commandWord;
	private String name;
	private String secondWord;
	
	private int num;
	
	public Command(String firstWord, String secondWord, String thirdWord, int num) {
		commandWord = firstWord;
		name = secondWord;
		this.secondWord = thirdWord;
		this.num = num;
	}
	
	public Command(String firstWord, String secondWord, String thirdWord) {
		commandWord = firstWord;
		name = secondWord;
		this.secondWord = thirdWord;
		this.num = -1;
	}
	
	public String getNameCalled() {
		return name;
	}
	
	public String getCommandWord() {
		return commandWord;
	}
	
	public String getSecondWord() {
		return secondWord;
	}
	
	public int getNum() {
		return num;
	}
	
	public boolean isUnknown() {
		return commandWord==null;
	}
	
	public boolean hasSecondCommand() {
		return secondWord!=null;
	}
	
	public boolean hasNum() {
		return num != -1;
	}
	
	public boolean hasName() {
		return name!= null;
	}

}
