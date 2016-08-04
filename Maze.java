
import java.util.Random;

public class Maze {

	private boolean board[][];                              
	final int height;
	final int length;
	final int stepLimit;
	private Random rand = new Random();
	
	public Maze( int height, int length, int stepLimit){
		if ( (height % 2) == 0){
			height += 1;
		}
		this.height = height;
		this.length = length;
		this.stepLimit = stepLimit;
		board = new boolean[this.height][this.length];
		
		this.startPath();
		this.printBoard();
		System.out.println("\n");
		this.setFinish();
		this.printBoard();
	}
	
	private void startPath(){
		int middle = (height)/2;
		RCLocation currentLoc = new RCLocation( middle, 0);
		DirectionInDegrees dir = new DirectionInDegrees(0);
		
		board[ currentLoc.getR() ][ currentLoc.getC() ] = true;
		
		
		this.newPath(currentLoc, dir);
	}
	
	private void newPath(RCLocation currentLoc, DirectionInDegrees dir){
		int counter = 0;
		boolean canStep = true;
		boolean canCreateLeft = true;
		boolean canCreateRight = true;
		
		DirectionInDegrees left = new DirectionInDegrees(dir.getDirection()+ 90);
		DirectionInDegrees right = new DirectionInDegrees(dir.getDirection() - 90);
		
		
		
		int selection;
		
		while (counter < stepLimit){
			counter++;
			
			selection = rand.nextInt(2) + 1;
			
			if(selection == 1){
				canCreateLeft = testMove(currentLoc.getNeighborArrayInDirection(left));
				if (canCreateLeft){
					this.newPath(currentLoc, left);
				}
			} else {
				canCreateRight = testMove(currentLoc.getNeighborArrayInDirection(right));
				if (canCreateRight){
					this.newPath(currentLoc, right);
				}
			}
			
			if(selection == 2){
				canCreateRight = testMove(currentLoc.getNeighborArrayInDirection(right));
				if (canCreateRight){
					this.newPath(currentLoc, right);
				}
			} else {
				canCreateLeft = testMove(currentLoc.getNeighborArrayInDirection(left));
				if (canCreateLeft){
					this.newPath(currentLoc, left);
				}
			}
			canStep = testMove(currentLoc.getNeighborArrayInDirection(dir));
			if (!canStep)
				counter+=3;
			else{
				currentLoc = currentLoc.advanceInDirection(dir);
				board[currentLoc.getR()][currentLoc.getC()] = true;
			}
		}
	}
	
	
	public int getHeight(){
		return height;
	}
	
	public int getLength(){
		return length;
	}
	
	
	private boolean testMove(RCLocation loc[]){
		
		for (int index = 0; index < loc.length;index++){
			if ( loc[index].getR() < 0)
				return false;
			if ( loc[index].getC() < 0)
				return false;
			if ( loc[index].getR() >= height)
				return false;
			if ( loc[index].getC() >= length)
				return false;
			if (  board[ loc[index].getR() ][ loc[index].getC() ] == true)
				return false;
		}
		return true;
	}
	
	private boolean testFinishPath (RCLocation loc){
		boolean canSetPath = true;
		boolean loopEnder = false;
		DirectionInDegrees dir = new DirectionInDegrees(180);
		RCLocation[] neighborArray = loc.getNeighborArrayInDirection(dir);
		
		try
		{
			if (loc.getC() == length -1) {
				while (loopEnder == false){
					for (int index = 0; index < neighborArray.length;index++){
						if ( neighborArray[index].getR() <= 0){
							return false;
						}
						if ( neighborArray[index].getC() <= 0){
							return false;
						}
						if ( neighborArray[index].getR() >= height ){
							return false;
						}
					}
						if (  board[ neighborArray[0].getR() ][ neighborArray[0].getC() ] == true || board[ neighborArray[4].getR() ][ neighborArray[4].getC() ] == true ){
							canSetPath = false;
							loopEnder = true;
							RCLocation newNeighbor = new RCLocation( neighborArray[2].getR(), neighborArray[2].getC() - 1); //this neighbor is not included in the array
							if( board[ newNeighbor.getR()][ newNeighbor.getC()] == true)
								canSetPath = true;
						}
						if ( (board[ neighborArray[1].getR() ][ neighborArray[1].getC() ] == true || board[ neighborArray[3].getR() ][ neighborArray[3].getC() ] == true) && board[ neighborArray[2].getR() ][ neighborArray[2].getC() ] == false){
							canSetPath = false;
							loopEnder = true;
						}
						if ( this.testMove(neighborArray) == false ) // finish path wants to run into another path
							loopEnder = true;
					
					loc.advanceInDirection(dir);
					neighborArray = loc.getNeighborArrayInDirection(dir);
			
				}
				return canSetPath;
			}else
				return false;
		}
		catch (ArrayIndexOutOfBoundsException exc)
		{
			return false;
		}
	}
	
	private void setFinish(){
		int middle = (height)/2;
		RCLocation loc = new RCLocation(middle, length - 1);
		DirectionInDegrees dir = new DirectionInDegrees(180);
		int addValue = 0;
		while ( !this.testFinishPath(loc) || Math.abs(addValue) > height/2){
			if (addValue > 0)
				addValue = addValue * -1;
			else 
				addValue = addValue*-1 + 1; //addValue values will look like this: (0, 1, -1, 2, -2, 3, -3)
			loc = new RCLocation(middle + addValue, length - 1);
		}	
		if (this.testFinishPath(loc)){
			while (testMove(loc.getNeighborArrayInDirection(dir))){
				board[loc.getR()][loc.getC()] = true;
				loc.advanceInDirection(dir);
			}
			board[loc.getR()][loc.getC()] = true;
		}
		
	}
	
	public boolean[][] getArray(){
		return board;
	}
	
	
	private void printBoard() {
		for(int row = 0; row < height; row++){
			for (int col = 0; col < length; col++){
				System.out.print(board[row][col]?"0":"1");
			}
			System.out.print("\n");
		}
	}
	
}

