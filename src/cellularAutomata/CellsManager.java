package cellularAutomata;

public interface CellsManager extends Cloneable{
	
	public Cell getCell(int l,int c);
	public void computeAllLines (Rule rule);
	public void computeLine (Rule rule, int line);
	
	public String toString();
	public CellsManager clone();
	
}
