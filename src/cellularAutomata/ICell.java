package cellularAutomata;

public interface ICell {
	boolean isAlive();
	void setIsAlive(boolean isAlive);
	
	void resurrectCell();
	void killCell();
	
	String toString();
}
