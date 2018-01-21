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
				cells[l][c]=new CellImpl(false,this.cellAliveSymbol);
	}
	
	public Cell getCell(int l,int c) {
		return cells[l][c];
	}

	public void computeLine(Rule rule){
		for(int l=1; l<line; l++)
			for(int c=0; c<column;c++){
				Cell originePrevious = cells[l-1][(c-1 + column) % column];
				Cell origineSame = cells[l-1][c];
				Cell origineNext = cells[l-1][(c+1 + column) % column];
				try {
					cells[l][c].setIsAlive(rule.apply(originePrevious.isAlive(), origineSame.isAlive(), origineNext.isAlive()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
	
	@Override
	public void computeBlock(Rule rule) {
		for(int l=0; l<line; l++)
			for(int c=0; c<column;c++){
				Cell origineAL = cells[(l-1 + line) % line][(c-1 + column) % column];
				Cell origineA = cells[(l-1 + line) % line][c];
				Cell origineAR = cells[(l-1 + line) % line][(c+1 + column) % column];
				Cell origineL = cells[l][(c-1 + column) % column];
				Cell origineR = cells[l][(c+1 + column) % column];
				Cell origineBL = cells[(l+1 + line) % line][(c-1 + column) % column];
				Cell origineB = cells[(l+1 + line) % line][c];
				Cell origineBR = cells[(l+1 + line) % line][(c+1 + column) % column];
				try {
					cells[l][c].setIsAlive(rule.apply(origineAL.isAlive(), origineA.isAlive(), origineAR.isAlive(), origineL.isAlive(),origineR.isAlive(), origineBL.isAlive(), origineB.isAlive(), origineBR.isAlive()));
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
