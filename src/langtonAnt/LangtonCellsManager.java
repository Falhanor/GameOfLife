package langtonAnt;

public interface LangtonCellsManager {
	//public LangtonCellsManagerImpl(int l, int c, char antSymbol,int antLine, int antColumn, String rule);
	
	public CellMultiStates getCell(int l,int c);
	public CellMultiStates getAntCell();

	public void moveAnt() throws Exception;
	public String toString();
}
