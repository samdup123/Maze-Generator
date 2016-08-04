
public class DirectionInDegrees {

	private int value;
	

	public DirectionInDegrees(int degrees){
		this.value = 0;
		if ( (Math.abs(degrees) % 90) == 0){
			if (degrees < 0){
				degrees = (degrees* -1);
				if (degrees % 180 != 0)
					degrees = degrees + 180;
			}
			if (degrees >= 360)
				degrees = degrees % 360;
			this.value = degrees;
		}
	}
	
	
	public int getDirection(){
		return value;
	}
}
