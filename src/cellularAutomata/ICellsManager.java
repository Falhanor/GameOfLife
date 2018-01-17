package cellularAutomata;

public interface ICellsManager {
	
	public ICell getCell(int l,int c);
	public void compute(IRule rule);
	
	public String toString();
}
