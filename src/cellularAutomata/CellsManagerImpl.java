package cellularAutomata;


public class CellsManagerImpl implements CellsManager {
	
	private Cell cells[][];
	private int line;
	private int column;
	private char cellAliveSymbol = '0';
	
	
	public CellsManagerImpl(int l, int c, char cellAliveSymbol) {
		this.line = l;
		this.column =c;
		this.cellAliveSymbol = cellAliveSymbol;
		this.cells = new Cell[line][column];
		initCells();
	}
	
	private void initCells(){
		for(int l=0; l<line; l++)
			for(int c=0; c<column;c++)
				cells[l][c]=new CellWithEventsImpl(false,this.cellAliveSymbol);
	}
	
	public Cell getCell(int l,int c) {
		return cells[l][c];
	}
	
	@Override
	public void computeLine(Rule rule, int line){
		for(int c=0; c<column;c++){
			Cell originePrevious = cells[line-1][(c-1 + column) % column];
			Cell origineSame = cells[line-1][c];
			Cell origineNext = cells[line-1][(c+1 + column) % column];
			try {
				cells[line][c].setIsAlive(rule.apply(originePrevious.isAlive(), origineSame.isAlive(), origineNext.isAlive()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void computeAllLines(Rule rule) {
		for(int l=1; l<line; l++)
			computeLine(rule, l);
	}
	
	public String toString(){
		StringBuffer stb =new StringBuffer();
		for(int l=0; l<line; l++){
			for(int c=0; c<column;c++)
				stb.append(cells[l][c].toString());
			stb.append("\n");
		}
		return stb.toString();
	}

	
}
