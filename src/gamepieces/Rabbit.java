package gamepieces;

public class Rabbit extends Square {
	
	private RabbitColour colour;
	
	public Rabbit(int x, int y) {
		super(x, y);
		
	}
	
	public Rabbit(int x, int y, RabbitColour colour) {
		super(x, y);
		this.colour = colour;
	}

	public Rabbit(int x, int y, String name) {
		super(x, y, name);
		if (name.equalsIgnoreCase("grey")) {
			this.colour = RabbitColour.GREY;
		} else if (name.equalsIgnoreCase("white")) {
			this.colour = RabbitColour.WHITE;
		} else if (name.equalsIgnoreCase("brown")) {
			this.colour = RabbitColour.BROWN;
		} else {
			this.colour = null;
		}
	}

}
