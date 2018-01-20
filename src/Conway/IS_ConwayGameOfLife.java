package Conway;

import cellularAutomata.Cell;
import cellularAutomata.CellsManager;
import cellularAutomata.CellsManagerImpl;

public class IS_ConwayGameOfLife {

	private static final short LINE = 50;
	private static final short COLUMN = 100;
	private static final short START_ALIVE[][] = {{25,49},{26,49},{27,49}};
	private static final char CELLALIVESYMBOL = '¤';//'\u25a0';
			
	protected static CellsManager cells;
	
	public IS_ConwayGameOfLife() {}
	
	
	public static void main(String[] args){;
		cells = new CellsManagerImpl(LINE, COLUMN, CELLALIVESYMBOL);
		for(short[] xy : START_ALIVE)
			cells.getCell(xy[0],xy[1]).resurrect();
		
		int iterationCount = 0;
		System.out.println("Iteration n°" + iterationCount);
		System.out.println(cells.toString());
		
		do{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cells = computeNextStep();
			iterationCount++;
			System.out.println("Iteration n°" + iterationCount);
			System.out.println(cells.toString());
		}while(true);
	}
	
	private static CellsManager computeNextStep(){
		CellsManager computedCells = new CellsManagerImpl(LINE, COLUMN, CELLALIVESYMBOL);
		for(int l=0; l<LINE; l++){
			for(int c=0; c<COLUMN;c++){
				byte cellAliveCount = 0;
				if(cells.getCell((l-1 + LINE) % LINE,(c-1 + COLUMN) % COLUMN).isAlive())
					cellAliveCount++;
				if(cells.getCell((l-1 + LINE) % LINE,c).isAlive())
					cellAliveCount++;
				if(cells.getCell((l-1 + LINE) % LINE,(c+1 + COLUMN) % COLUMN).isAlive())
					cellAliveCount++;
				if(cells.getCell(l,(c-1 + COLUMN) % COLUMN).isAlive())
					cellAliveCount++;
				if(cells.getCell(l,(c+1 + COLUMN) % COLUMN).isAlive())
					cellAliveCount++;
				if(cells.getCell((l+1 + LINE) % LINE,(c-1 + COLUMN) % COLUMN).isAlive())
					cellAliveCount++;
				if(cells.getCell((l+1 + LINE) % LINE,c).isAlive())
					cellAliveCount++;
				if(cells.getCell((l+1 + LINE) % LINE,(c+1 + COLUMN) % COLUMN).isAlive())
					cellAliveCount++;
				
				if(cells.getCell(l,c).isAlive())
					if(cellAliveCount<2 || cellAliveCount>3)
						computedCells.getCell(l,c).kill();
					else
						computedCells.getCell(l,c).resurrect();
				else
					if(cellAliveCount == 3)
						computedCells.getCell(l,c).resurrect();
			}
		}
		return computedCells;
	}
}
