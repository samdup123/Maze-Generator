
class RCLocation{
	
	private int r;
	private int c;
	private RCLocation[] neighborArrayEast;
	private RCLocation[] neighborArrayWest;
	private RCLocation[] neighborArraySouth;
	private RCLocation[] neighborArrayNorth;
	
	public RCLocation(int r, int c){
		this.r = r;
		this.c = c;
		
	}
	
	
	public int getR(){
		return r;
	}
	
	public int getC(){
		return c;
	}
	
	public RCLocation advanceInDirection(DirectionInDegrees dir){
		if (dir.getDirection() == 0)
			return this.advanceEast();
		else if (dir.getDirection() == 90)
			return this.advanceNorth();
		else if (dir.getDirection() == 180)
			return this.advanceWest();
		else
			return this.advanceSouth();
	}
	
	public RCLocation[] getNeighborArrayInDirection(DirectionInDegrees dir){
		if (dir.getDirection() == 0){
			this.getNeighborArrayEast();
			return neighborArrayEast;
		}
		else if (dir.getDirection() == 90){
			this.getNeighborArrayNorth();
			return neighborArrayNorth;
		}
		else if (dir.getDirection() == 180){
			this.getNeighborArrayWest();
			return neighborArrayWest;
		}
		else{
			this.getNeighborArraySouth();
			return neighborArraySouth;
		}
	}
	
	private RCLocation advanceEast(){
		return new RCLocation(r,c+1);
	}
	
	public RCLocation advanceWest(){
		return new RCLocation(r,c-1);
	}
	
	private RCLocation advanceNorth(){
		return new RCLocation(r-1,c);
	}
	
	private RCLocation advanceSouth(){
		return new RCLocation(r+1,c);
	}
	
	
	private void getNeighborArrayEast (){
		neighborArrayEast = new RCLocation[5];
		neighborArrayEast[0] = this.getNeighborNE();
		neighborArrayEast[1] = this.getNeighborNEE();
		neighborArrayEast[2] = this.getNeighborE();
		neighborArrayEast[3] = this.getNeighborSEE();
		neighborArrayEast[4] = this.getNeighborSE();
	}
	
	private void getNeighborArraySouth (){
		neighborArraySouth = new RCLocation[5];
		neighborArraySouth[0] = this.getNeighborSE();
		neighborArraySouth[1] = this.getNeighborSSE();
		neighborArraySouth[2] = this.getNeighborS();
		neighborArraySouth[3] = this.getNeighborSSW();
		neighborArraySouth[4] = this.getNeighborSW();
	}
	
	private void getNeighborArrayWest (){
		neighborArrayWest = new RCLocation[5];
		neighborArrayWest[0] = this.getNeighborSW();
		neighborArrayWest[1] = this.getNeighborSWW();
		neighborArrayWest[2] = this.getNeighborW();
		neighborArrayWest[3] = this.getNeighborNWW();
		neighborArrayWest[4] = this.getNeighborNW();
	}
	
	private void getNeighborArrayNorth (){
		neighborArrayNorth = new RCLocation[5];
		neighborArrayNorth[0] = this.getNeighborNW();
		neighborArrayNorth[1] = this.getNeighborNNW();
		neighborArrayNorth[2] = this.getNeighborN();
		neighborArrayNorth[3] = this.getNeighborNNE();
		neighborArrayNorth[4] = this.getNeighborNE();
	}
	
	private RCLocation getNeighborNNE(){
		return new RCLocation ( (r-2) , (c+1) );
	}
	
	private RCLocation getNeighborNE(){
		return new RCLocation ( (r-1) , (c+1) );
	}
	
	private RCLocation getNeighborNEE(){
		return new RCLocation ( (r-1) , (c+2) );
	}
	
	private RCLocation getNeighborE(){
		return new RCLocation ( r     , (c+2) );
	}
	
	private RCLocation getNeighborSEE(){
		return new RCLocation ( (r+1) , (c+2) );
	}
	
	private RCLocation getNeighborSE(){
		return new RCLocation ( (r+1) , (c+1) );
	}
	
	private RCLocation getNeighborSSE(){
		return new RCLocation ( (r+2) , (c+1) );
	}
	
	private RCLocation getNeighborS(){
		return new RCLocation ( (r+2) , c     );
	}
	
	private RCLocation getNeighborSSW(){
		return new RCLocation ( (r+2) , (c-1) );
	}
	
	private RCLocation getNeighborSW(){
		return new RCLocation ( (r+1) , (c-1) );
	}
	
	private RCLocation getNeighborSWW(){
		return new RCLocation ( (r+1) , (c-2) );
	}
	
	private RCLocation getNeighborW(){
		return new RCLocation ( r     , (c-2) );
	}
	
	private RCLocation getNeighborNWW(){
		return new RCLocation ( (r-1) , (c-2) );
	}
	
	private RCLocation getNeighborNW(){
		return new RCLocation ( (r-1) , (c-1) );
	}
	
	private RCLocation getNeighborNNW(){
		return new RCLocation ( (r-2) , (c-1) );
	}
	
	private RCLocation getNeighborN(){
		return new RCLocation ( (r-2) , c     );
	}
	
}
