package langtonAnt;

public class LangtonAntImpl implements LangtonAnt {
	
	private enum enumOrientation {
		  NORTH,
		  SOUTH,
		  EAST,
		  WEST;	
	}
	private int antColumnX;
	private int antLineY;
	private int maxX;
	private int maxY;
	private char antChar='#';
	private enumOrientation orientation = enumOrientation.NORTH;	
	
	public LangtonAntImpl(int antColumnX, int antLineY, char antDisplay,int maxX,int maxY) {
		this.antColumnX = antColumnX;
		this.antLineY = antLineY;
		this.antChar = antDisplay;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	@Override
	public int[] getPosition() {
		 int[] position = {this.antColumnX, this.antLineY}; 
		return position;
	}

	@Override
	public void goRight() {
		switch (this.orientation){
			case NORTH:
				this.antColumnX+=1;
				this.orientation=enumOrientation.EAST;
				break;
			case SOUTH:
				this.antColumnX-=1;
				this.orientation=enumOrientation.WEST;
				break;
			case EAST:
				this.antLineY+=1;
				this.orientation=enumOrientation.SOUTH;
				break;
			case WEST:
				this.antLineY-=1;
				this.orientation=enumOrientation.NORTH;
				break;
			default:
				break;
		}	
		controlPosition();
	}

	private void controlPosition() {
		if (this.antColumnX == -1)
			this.antColumnX = this.maxX;
		if (this.antColumnX == this.maxX+1)
			this.antColumnX = 0;
		
		if (this.antLineY == -1)
			this.antLineY = this.maxY;
		if (this.antLineY == this.maxY+1)
			this.antLineY = 0;
	}

	@Override
	public void goLeft() {
		switch (this.orientation){
			case NORTH:
				this.antColumnX-=1;
				this.orientation=enumOrientation.WEST;
				break;
			case SOUTH:
				this.antColumnX+=1;
				this.orientation=enumOrientation.EAST;
				break;
			case EAST:
				this.antLineY-=1;
				this.orientation=enumOrientation.NORTH;
				break;
			case WEST:
				this.antLineY+=1;
				this.orientation=enumOrientation.SOUTH;
				break;
			default:
				break;
		}
		controlPosition();
	}

	@Override
	public String getAntDisplay() {
		return String.valueOf(this.antChar);
	}

}
