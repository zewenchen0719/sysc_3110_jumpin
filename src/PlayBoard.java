import gamepieces.Direction;
import gamepieces.Fox;
import gamepieces.Square;
import java.util.ArrayList;

public class PlayBoard {
	private Square board[][];
	private ArrayList<Square> rabbits; //3 rabbits
	private Fox[] f1, f2;  //2 foxes
	private int cmushroom; //3 mushroom

	public PlayBoard() {
		board = new Square[5][5];
		for(int i=0; i<5; i++) {//row
			for(int j=0; j<5; j++) {//column
				board[i][j] = new Square(i,j); //(row,column)
			}
		}
		//set Hole
		board[0][0].setName("  Hole ");
		board[0][4].setName("  Hole ");
		board[2][2].setName("  Hole ");
		board[4][0].setName("  Hole ");
		board[4][4].setName("  Hole ");

		//cToWin = 0;
		rabbits = new ArrayList<Square>();

	}

	//tell if all rabbits are in the hole
	public boolean isWin() {
		if(rabbits.size() == 0) return false;

		boolean isWin = true;
		for(Square rabbit: rabbits) {
			if(rabbit.AtHole()) {
				continue;
			}
			else {
				isWin = false;
				break;
			}
		}
		return isWin;
	}

	public Fox[] getFox(int i) {
		switch(i) {
			case 1: return f1;
			case 2: return f2;
			default: return null; // Changed to null to prevent bugs
		}
	}

	public Square getRabbit(int i){
		return rabbits.get(i);
	}

	public boolean setMushroom(int x, int y) {
		if(cmushroom <3) {
			board[x][y].setName("Mushroom");
			cmushroom++;
			return true;
		}
		return false;
	}

	//set rabbits
	public boolean setRabbit(int x, int y) {
		if(board[x][y].isOccupied()) return false;
		if(rabbits.size() > 3) return false;

		int num = rabbits.size()+1;
		String name = "Rabbit" + num;
		rabbits.add(new Square(x, y, name));
		board[x][y] = rabbits.get(rabbits.size()-1);
		//cToWin++;
		return true;
	}

	private Fox[] setFoxHelper(int x, Direction direction) {
		int empty = 0;
		Fox[] temp = new Fox[2];

		if(direction.equals(Direction.HORIZONTAL)) {
			for(int j=0; j<5; j++) { // find if there are two empty board connected to put a fox in
				if(empty<1) {
					if(!board[x][j].isOccupied()) {
						empty++;
					} else {
						empty = 0;
					}
				} else if(!board[x][j].isOccupied()){
					temp[0] = new Fox(x,j,Direction.HORIZONTAL);
					temp[1] = new Fox(x,j-1,Direction.HORIZONTAL);
					board[x][j] = temp[0];
					board[x][j-1] = temp[1];
					return temp;
				}
			}
		} else if(direction.equals(Direction.VERTICAL)) {
			for(int i=0; i<5; i++) {
				if(empty<1){
					if(!board[i][x].isOccupied()) empty++;
					else empty = 0;
				} else if(!this.board[i][x].isOccupied()) {
					temp[0] = new Fox(i,x,Direction.VERTICAL);
					temp[1] = new Fox(i-1,x,Direction.VERTICAL);
					board[i][x] = temp[0];
					board[i-1][x] = temp[1];
					return temp;
				}
			}
		}
		throw new IllegalArgumentException("cannot set fox there");
	}

	//set fox
	public boolean setFox(int x, Direction direction) {
		if(x==0||x==2||x==4) return false;

		if(f1 == null) {
			f1 = setFoxHelper(x, direction);
			f1[0].setName("  fox1 ");
			f1[1].setName("  fox1 ");
			return true;
		}

		else if(f2 == null) {
			f2 = setFoxHelper(x, direction);
			f2[0].setName("  fox2 ");
			f2[1].setName("  fox2 ");
			return true;
		}

		return false;
	}

	//help to move on board
	private void Move(Square s, int x, int y) {
		int row = s.getRow();
		int col = s.getColumn();

		board[x][y] = s;
		board[row][col] = new Square(row, col);

		s.Move(x, y);

	}

	//rules for rabbit to move
	public boolean jumpTo(Square r, Direction direction) {

		if(r==null) return false;

		//get rabbit's location
		int row = r.getRow();
		int col = r.getColumn();

		if(direction.equals(Direction.NORTH)) {
			if(row > 0 && this.board[row-1][col].isOccupied()) {
				for(int i=0; i<=row; i++) {
					if(board[row-i][col].isOccupied()) continue;
					else {
						Move(r, row-i, col);
						return true;
					}
				}
			}
		}
		else if(direction.equals(Direction.SOUTH)) {
			if(row < 4 && this.board[row+1][col].isOccupied()) {
				for(int i=0; i<5-row; i++) {
					if(board[row+i][col].isOccupied()) continue;
					else {
						Move(r, row+i, col);
						return true;
					}
				}
			}
		}
		else if(direction.equals(Direction.EAST)) {
			if(col < 4 && board[row][col+1].isOccupied()) {
				for(int j=0; j<5-col; j++) {
					if(board[row][col+j].isOccupied()) continue;
					else {
						Move(r, row, col+j);
						return true;
					}
				}
			}
		}
		else if(direction.equals(Direction.WEST)) {
			if(col > 0 && board[row][col-1].isOccupied()) {
				for(int j=0; j<=col; j++) {
					if(board[row][col-j].isOccupied()) continue;
					else {
						Move(r, row, col-j);
						return true;
					}
				}
			}
		}
		return false;

	}

	protected int getFoxLocation(Fox[] f) {
		if(f[0].getDirection().equals(Direction.HORIZONTAL)) {
			return f[0].getColumn();
		}
		else if(f[0].getDirection().equals(Direction.VERTICAL)) {
			return f[0].getRow();
		}
		else return 0;
	}

	//move fox
	public boolean MoveTo(Fox[] f, int point) {
		//get fox's location
		Fox head = f[0];
		int row = head.getRow();
		int col = head.getColumn();

		if(head.getDirection().equals(Direction.HORIZONTAL)) {
			if(point > 4) point = 4;
			if(point < 0) point = 1;

			if(point > col) {
				Move(f[0], row, point);
				Move(f[1], row, point-1);
			}

			else {
				Move(f[1], row, point-1);
				Move(f[0], row, point);
			}
			return true;
		}
		else if(head.getDirection().equals(Direction.VERTICAL)) {
			if(point > 4) point = 4;
			if(point < 0) point = 1;

			if(point > row) {
				Move(f[0], point, col);
				Move(f[1], point-1, col);
			}
			else {
				Move(f[1], point-1, col);
				Move(f[0], point, col);
			}
			return true;
		}
		return false;
	}


	public void printBoard() {
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				System.out.print(board[i][j].toString());
			}
			System.out.println();
		}
		//let player set the puzzle
		//then start

	}

}
