
public class Rabbit extends Square{
	private boolean moveable; //state of rabbit (reach the hole or not)
	
	public Rabbit(int x, int y, String name) {
		super(x,y, name);
		moveable = true;
	}
	
	public void disableMove() {
		moveable = false;
	} 
	
	public boolean isMoveable() {
		return moveable;
	}
	
	@Override
	public void Move(int x, int y) {
		//rabbit can only be moved if it's not in the hole
		if(moveable) {
			super.Move(x, y);
		}
	}
	
	
}
