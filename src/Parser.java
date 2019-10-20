import java.util.Scanner;

public class Parser {
	
	private CommandWord commands;
	private Scanner reader;
	
	/**
	 * Constructor for parser object
	 */
	public Parser() {
		commands = new CommandWord();
		reader = new Scanner(System.in);
	}
	
	/**
	 * Create a command object from the text parsed from the console
	 * @return Command object created from the console
	 */
	public Command getCommand() {
		String inputLine;
		String name = null;
		String word1 = null;
		String word2 = null;
		int num3 = -1;
		
		System.out.print("> ");
		
		inputLine = reader.nextLine();
		
		// Find out up to three words on the line
		Scanner tokenizer = new Scanner(inputLine);
		
		try {
			if (tokenizer.hasNext()) {
				word1 = tokenizer.next();
				if (tokenizer.hasNext()) {
					name = tokenizer.next();
					if (tokenizer.hasNext()) {
						word2 = tokenizer.next();
						if (tokenizer.hasNextInt()) {
							num3 = tokenizer.nextInt();
						}
					}
				}
			}
		} finally {
			tokenizer.close();
		}
		
		if (commands.isCommand(word1)) {
			return new Command(word1,name,word2,num3);
		} else {
			return new Command(null, name,word2,num3);
		}
	}
}
