
package conwayGameOfLife;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cellularAutomata.CellWithEvents;
import cellularAutomata.CellsManager;
import cellularAutomata.CellsManagerImpl;

public class IS_ConwayGameOfLife {

	private static short LINE = 150;//40;
	private static short COLUMN = 200;//80;
		
	private static final char CELLALIVESYMBOL = '0';
	private static final int DISPLAY_DELAY = 300; 	
	private static int RANDOMCOLOR = new Random().nextInt(0xFFFFFF);
	
	private static int iteration = 0; 	
	private static boolean verboseMode = false;
	private static boolean graphicMode = true;
	private static boolean multicolour = false;
	
	protected static CellsManager cells;
	
	
	public static void main(String[] args){
		printUsage();
		argsManager(args);
		
		cells = new CellsManagerImpl(LINE, COLUMN, CELLALIVESYMBOL);
		
		JFrame frame = new JFrame();	
		if(graphicMode){
			//////////////////////////////
			/// JFrame design
			frame.setTitle("Linear automata - loading...");
			frame.setSize(630, 500);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel pan = new JPanel();
			pan.setLayout(new GridLayout(0,COLUMN));
			pan.setBackground(Color.black);
			
			frame.setContentPane(pan);
			frame.getFocusOwner();
			frame.setVisible(true);
			frame.setAlwaysOnTop(true);
			frame.setAlwaysOnTop(false);
			frame.validate();
			//////////////////////////////
			///add cells
			for(int loadingLine=0; loadingLine<LINE;loadingLine++){
				for(int c=0; c<COLUMN; c++){
					pan.add(new CtrlCellWithEventsDraw((CellWithEvents) cells.getCell(loadingLine,c), RANDOMCOLOR, multicolour).getDesign());
				}
				frame.setTitle("Conway'sGameOfLife - [" + (loadingLine*100)/LINE + "% loading...]");
				frame.validate();
			}
		}
		for(int l=0; l<LINE; l++)
			cells.getCell(l,COLUMN-1).resurrect();
		for(int c=0; c<COLUMN;c++)
			cells.getCell(0,c).resurrect();
		
		int iterationCount = 0;
		if(graphicMode){
			frame.setTitle("Conway'sGameOfLife - " + iterationCount + " iterations");
			frame.repaint();
		}
		
		if(verboseMode){
			System.out.println("Iteration n°" + iterationCount + " =====================================================================");
			System.out.println(cells.toString());
		}
		while((iteration==0 && computeNextStep()) || (iterationCount < iteration && computeNextStep())){
			iterationCount++;
			if(graphicMode)
				frame.setTitle("Conway'sGameOfLife - " + iterationCount + " iterations");
			try {
				Thread.sleep(DISPLAY_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(verboseMode){
				System.out.println("Iteration n°" + iterationCount+ " =====================================================================");
				System.out.println(cells.toString());
			}
		}
	}
	
	private static boolean computeNextStep(){
		boolean stateChanged = false;
		
		CellsManager computationCells = cells.clone();
		
		for(int l=0; l<LINE; l++){
			for(int c=0; c<COLUMN;c++){
				byte cellAliveCount = 0;
				if(computationCells.getCell((l-1 + LINE) % LINE,(c-1 + COLUMN) % COLUMN).isAlive())
					cellAliveCount++;
				if(computationCells.getCell((l-1 + LINE) % LINE,c).isAlive())
					cellAliveCount++;
				if(computationCells.getCell((l-1 + LINE) % LINE,(c+1 + COLUMN) % COLUMN).isAlive())
					cellAliveCount++;
				if(computationCells.getCell(l,(c-1 + COLUMN) % COLUMN).isAlive())
					cellAliveCount++;
				if(computationCells.getCell(l,(c+1 + COLUMN) % COLUMN).isAlive())
					cellAliveCount++;
				if(computationCells.getCell((l+1 + LINE) % LINE,(c-1 + COLUMN) % COLUMN).isAlive())
					cellAliveCount++;
				if(computationCells.getCell((l+1 + LINE) % LINE,c).isAlive())
					cellAliveCount++;
				if(computationCells.getCell((l+1 + LINE) % LINE,(c+1 + COLUMN) % COLUMN).isAlive())
					cellAliveCount++;
				
				if(computationCells.getCell(l,c).isAlive())
					if(cellAliveCount<2 || cellAliveCount>3){
						cells.getCell(l,c).kill();
						stateChanged = true;
					}else
						cells.getCell(l,c).setIsAlive(true);
				else if(cellAliveCount == 3){
						cells.getCell(l,c).resurrect();
						stateChanged = true;
				}
			}
		}
		return stateChanged;
	}
	
	private static void printUsage() {
		System.out.println("\n              ===========================================================");
		System.out.println("              |================| Conway's game of life |================|");
		System.out.println("              |=========================================================|");
		System.out.println("              |                                                         |");
		System.out.println("              | usage   : program iteration verbose console multicolour |");
		System.out.println("              | default : conwayGameOfLife.IS_ConwayGameOfLife          |");
		System.out.println("              |                                                         |");
		System.out.println("              |=========================================================|");
		System.out.println("              | Parameters details :                                    |");
		System.out.println("              |  >>> iteration : integer (0 = infinite)                 |");
		System.out.println("              |  >>> verbose mode : -v                                  |");
		System.out.println("              |  >>> console mode (no window) : -c                      |");
		System.out.println("              |  >>> multicolour : -m                                   |");
		System.out.println("              |                                                         |");
		System.out.println("              |=========================================================|");
		System.out.println("              |=================================| By : Timothée SOLLAUD |");
		System.out.println("              ===========================================================\n");
	}
	
	private static boolean argsManager(String[] args) {
		boolean argsFound = false;
		String regexArgIteration = "^\\d+$";
		if (args.length>0){
			for(int i=0; i<args.length; ++i)
				if (args[i].matches(regexArgIteration)){
					iteration = Integer.parseInt(args[i]);
					argsFound = true;
				}else if (args[i].compareToIgnoreCase("-m")==0){
					multicolour = true;
					argsFound = true;
				}else if (args[i].compareToIgnoreCase("-v")==0){
					verboseMode = true;
					argsFound = true;
				}else if (args[i].compareToIgnoreCase("-c")==0){
					graphicMode = false;
					LINE = 20;
					COLUMN = 40;
					argsFound = true;
				}
		}
		verboseMode = verboseMode || (!graphicMode && !verboseMode);
		
		return argsFound;
	}
}
