package cellularAutomata;


public class CellsManager implements ICellsManager {
	
	private ICell cells[][];
	private int line;
	private int column;
	
	
	public CellsManager(int l, int c) {
		line = l;
		column =c;
		cells = new ICell[line][column];
		initCells(line, column);
	}

	private void initCells(int line, int column){
		for(int l=0; l<line; l++)
			for(int c=0; c<column;c++)
				cells[l][c]=new Cell(false);
	}
	
	public ICell getCell(int l,int c) {
		return cells[l][c];
	}

	public void compute(IRule rule){
		for(int l=1; l<line; l++)
			for(int c=0; c<column;c++){
				ICell originePrevious = cells[l-1][(c-1 + column) % column];
				ICell origineSame = cells[l-1][(c + column) % column];
				ICell origineNext = cells[l-1][(c+1 + column) % column];
				try {
					cells[l][c].setIsAlive(rule.apply(originePrevious.isAlive(), origineSame.isAlive(), origineNext.isAlive()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
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
