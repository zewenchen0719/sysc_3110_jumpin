public class Fox extends Square{
	
	private Direction direction;
	
	public Fox(int x, int y, Direction direction, String name) {
		super(x,y, name);
		this.direction = direction;
	}
	
	public Fox(int x, int y, Direction direction) {
		super(x,y);
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
}
