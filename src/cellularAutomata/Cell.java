package cellularAutomata;

public interface Cell {
	boolean isAlive();
	void setIsAlive(boolean isAlive);
	
	void invertState();
	void resurrect();
	void kill();
	
	String toString();
}
