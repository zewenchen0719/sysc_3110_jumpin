package gamepieces;

import java.lang.IllegalArgumentException;

public class Square {
	//position
	private int column;
	private int row;
	private String name;
	
	public Square(int x, int y) {
		//board is set to 5x5
		if(x>4 || y>4) throw new IllegalArgumentException("unavailable position");
		
		column = y;
		row = x;
		name = null;
	}
	
	public Square(int x, int y, String name) {
		this(x,y);
		this.name = name;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean AtHole() {
		if(row == 0 & ((column == 0 || column == 4))) return true;
		else if(row == 4 & ((column == 0 || column == 4))) return true;
		else if(row == 2 &&column == 2) return true;
		return false;
	}
	
	public void Move(int x, int y) {
		row = x;
		column = y;
	}
	
	public boolean isOccupied() {
		//tell if the square is occupied
		return (name != null && (!name.equals("  Hole ")));
	}
	
	public String toString() {
		if(name == null) {
			return " (" + row + "," + column + ") ";
		}
		return name;
	}

}
