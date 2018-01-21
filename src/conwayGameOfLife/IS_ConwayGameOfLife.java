
package conwayGameOfLife;

import cellularAutomata.CellsManager;
import cellularAutomata.CellsManagerImpl;

public class IS_ConwayGameOfLife {

	private static final short LINE = 40;
	private static final short COLUMN = 80;
		
	private static final char CELLALIVESYMBOL = '0';
	private static final int DISPLAY_DELAY = 300; 	
	
	protected static CellsManager cells;
	
	
	public static void main(String[] args){;
		cells = new CellsManagerImpl(LINE, COLUMN, CELLALIVESYMBOL);
		
		for(int l=0; l<LINE; l++)
			cells.getCell(l,COLUMN-1).resurrect();
		for(int c=0; c<COLUMN;c++)
			cells.getCell(LINE-1,c).resurrect();
				
		int iterationCount = 0;
		System.out.println("Iteration n°" + iterationCount + " =====================================================================");
		System.out.println(cells.toString());
		
		while(computeNextStep()){
			try {
				Thread.sleep(DISPLAY_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			iterationCount++;
			System.out.println("Iteration n°" + iterationCount+ " =====================================================================");
			System.out.println(cells.toString());
		}
	}
	
	private static boolean computeNextStep(){
		boolean stateChanged = false;
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
					if(cellAliveCount<2 || cellAliveCount>3){
						computedCells.getCell(l,c).kill();
						stateChanged = true;
					}else
						computedCells.getCell(l,c).setIsAlive(true);
				else if(cellAliveCount == 3){
						computedCells.getCell(l,c).resurrect();
						stateChanged = true;
				}
			}
		}
		cells = computedCells;
		return stateChanged;
	}
}
