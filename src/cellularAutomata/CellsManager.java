package cellularAutomata;

public interface CellsManager {
	
	public Cell getCell(int l,int c);
	public void computeLine (Rule rule);
	void computeBlock(Rule rule);
	
	public String toString();
	
}
