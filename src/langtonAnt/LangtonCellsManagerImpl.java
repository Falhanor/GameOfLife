package langtonAnt;

import langtonAnt.LangtonAnt.enumOrientation;

public class LangtonCellsManagerImpl implements LangtonCellsManager {
	
	private CellMultiStates cells[][];
	private int line;
	private int column;
	
	private LangtonAnt ant;
	
	private String rule = "LR";
	
	
	public LangtonCellsManagerImpl(int l, int c, char antSymbol,int antLine, int antColumn, enumOrientation antOrientation, String rule) {
		this.line = l;
		this.column =c;
		this.cells = new CellMultiStates[line][column];
		this.ant = new LangtonAntImpl(antColumn, antLine, antSymbol, c-1, l-1, antOrientation);
		this.rule = rule;
		initCells(generateCellStatesSet());
	}
	
	private char[] generateCellStatesSet(){
		char[] cellStatesSet = rule.toCharArray();
		cellStatesSet[0] = ' ';
		for (int i=1; i<cellStatesSet.length; i++){
			cellStatesSet[i] = (char) ('0'+ i);
		}
		return cellStatesSet;
	}
		
	private void initCells(char[] cellStatesSet){
		for(int l=0; l<line; l++)
			for(int c=0; c<column;c++)
				this.cells[l][c]=new CellMultiStatesWithEventsImpl(cellStatesSet);
	}
	
	@Override
	public CellMultiStates getCell(int l,int c) {
		return this.cells[l][c];
	}

	@Override
	public CellMultiStates getAntCell() {
		int[] antXY = this.ant.getPosition();
		return this.cells[antXY[1]][antXY[0]];
	}

	public void moveAnt() throws Exception{
		CellMultiStates antCell = getAntCell();
		antCell.setIsAnt(false);
		char movement = this.rule.charAt(antCell.getState());
		switch (movement){
			case 'L':
				this.ant.goLeft();
				break;
			case 'R':
				this.ant.goRight();
				break;
			default:
				throw new Exception("incorrect movement character : " + movement);
		}
		antCell.nextState();
		getAntCell().setIsAnt(true);
	}
	
	public String toString(){
		int[] antXY = this.ant.getPosition();
		StringBuffer stb =new StringBuffer();
		for(int l=0; l<line; l++){
			for(int c=0; c<column;c++)
				if(l==antXY[1] && c==antXY[0])
					stb.append(this.ant.getAntDisplay());
				else
					stb.append(this.cells[l][c].toString());
			stb.append("\n");
		}
		return stb.toString();
	}

}
