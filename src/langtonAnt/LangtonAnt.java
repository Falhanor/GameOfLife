package langtonAnt;

public interface LangtonAnt {
	int[] getPosition();
	
	void  goRight();
	void goLeft();
	
	String getAntDisplay();
	
	public enum enumOrientation {
		  NORTH,
		  SOUTH,
		  EAST,
		  WEST;	
	}
}
