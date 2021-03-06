package langtonAnt;

import langtonAnt.LangtonAnt.enumOrientation;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;


public class IS_LangtonAnt {
	private static final short LINE = 150;//100;
	private static final short COLUMN = 300;//175;
	private static final short ANTY = 75;//50;
	private static final short ANTX = 150;//85;
	private static final char ANTCHAR = '#';
	private static final int RANDOMCOLOR = new Random().nextInt(0xFFFFFF);
	private static final int DISPLAY_DELAY = 1;
	
	private static boolean verboseMode = false;
	private static int iteration = 0; 	
	private static enumOrientation startAntOrientation = enumOrientation.NORTH;
	private static String rule = "LR";
	
	protected static LangtonCellsManager cells;


	public static void main(String[] args){
		printUsage();
		argsManager(args);
		
		//////////////////////////////
		/// JFrame design
		final JFrame frame = new JFrame();
		frame.setTitle("LangtonAnt - loading...");
	    frame.setSize(1024, 600);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    final JPanel pan = new JPanel();
	    pan.setLayout(new GridLayout(0,COLUMN));
		pan.setBackground(new Color(RANDOMCOLOR).brighter());
		
		frame.setContentPane(pan);
		frame.setVisible(true);
	    
	    ///////////////////////////////
	    // add CellLabels
		cells = new LangtonCellsManagerImpl(LINE, COLUMN, ANTCHAR, ANTY, ANTX, startAntOrientation ,rule);
		for(int loadingLine=0; loadingLine<LINE; loadingLine++){
			for(int c=0; c<COLUMN; c++){
				pan.add(new CtrlCellMultiStatesDraw((CellMultiStatesWithEvents) cells.getCell(loadingLine,c), RANDOMCOLOR).getDesign());
			}
			if (loadingLine%10 == 0){
				frame.setTitle("LangtonAnt - loading... " + (loadingLine*100)/LINE + "%");
				frame.revalidate();
			}
		}
		frame.revalidate();
		frame.setTitle("LangtonAnt - iterations with rule \"" + rule + "\", orientation =" + startAntOrientation + ", color seed = " + RANDOMCOLOR);
		
		
		///////////////////////////////
	    // compute
		//init antCellAsDifferent
		cells.getAntCell().setIsAnt(true);
	    
		//run parameters
		int iterationCount = 0;
		if(verboseMode){
			displayData(iterationCount);
		}
		
		
		while (iteration==0 || iterationCount < iteration){
			try {
				Thread.sleep(DISPLAY_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			try {
				cells.moveAnt();
			} catch (Exception e) {
				e.printStackTrace();
			}
			frame.setTitle("LangtonAnt - " + iterationCount + " iterations with rule \"" + rule + "\", orientation =" + startAntOrientation + ", color seed = " + RANDOMCOLOR);
			if(verboseMode){
				displayData(iterationCount);
			}
			++iterationCount;
		}
	}
	
	private static void displayData(int iterationCount){
		System.out.println("Run parameters : " + iteration + " iterations with rule \"" + rule + "\", orientation =" + startAntOrientation + ", color seed = " + RANDOMCOLOR);
		System.out.println("\nIteration n°" + iterationCount + " =====================================================================");
		System.out.println(cells.toString());
	}

	private static void printUsage() {
		System.out.println("\n              =======================================================");
		System.out.println("              |==================| Langton's Ant |==================|");
		System.out.println("              |=====================================================|");
		System.out.println("              |                                                     |");
		System.out.println("              | usage   : program rule iteration orientation verbose|");
		System.out.println("              | default : langtonAnt.IS_LangtonAnt 0 " + rule + " " + startAntOrientation.toString().charAt(0) + "           |");
		System.out.println("              |                                                     |");
		System.out.println("              |=====================================================|");
		System.out.println("              | Parameters details :                                |");
		System.out.println("              |  >>> verbose mode : -v                              |");
		System.out.println("              |  >>> iteration : integer (0 = infinite)             |");
		System.out.println("              |  >>> orientation : [N|S|E|W]                        |");
		System.out.println("              |  >>> rule must matches with regex \"^[L|R]*$\"        |");
		System.out.println("              |      - LR / RL             : Langton's ant          |");
		System.out.println("              |      - LLRR                : cardioid               |");
		System.out.println("              |      - LRRL                : brain in box           |");
		System.out.println("              |      - LLLR                : grid                   |");
		System.out.println("              |      - LLLRR               : ball                   |");
		System.out.println("              |      - LLLRRR / RRRRL      : stripes                |");
		System.out.println("              |      - LLLLRRRR            : brain                  |");
		System.out.println("              |      - RLLLLRRRLLL         : spiral                 |");
		System.out.println("              |      - RRLLLRLLLRRR        : projector              |");
		System.out.println("              |      - LLRRRLRRRRRRRR      : emergence              |");
		System.out.println("              |      - LRLRLLLLLLLLLR      : psychedelic            |");
		System.out.println("              |      - LLRRRLRRRRRRRRLR    : emergence 2            |");
		System.out.println("              |      - RLLLLLLLRRRRRLRLLRL : growing ball           |");
		System.out.println("              |=====================================================|");
		System.out.println("              |============================| By : Timothée SOLLAUD  |");
		System.out.println("              =======================================================\n");
	}

	private static boolean argsManager(String[] args) {
		boolean argsFound = false;
		String regexArgRule = "^[L|R]*$";
		String regexArgIteration = "^\\d+$";
		if (args.length>0){
			for(int i=0; i<args.length; ++i)
				if (args[i].matches(regexArgRule)){
					rule = args[i];
					argsFound = true;
				}else if (args[i].matches(regexArgIteration)){
					iteration = Integer.parseInt(args[i]);
					argsFound = true;
				}else if (args[i].compareToIgnoreCase("-v")==0){
					verboseMode = true;
					argsFound = true;
				}else if (args[i].compareToIgnoreCase(String.valueOf('N'))==0){
					startAntOrientation = enumOrientation.NORTH;
					argsFound = true;
				}else if (args[i].compareToIgnoreCase(String.valueOf('S'))==0){
					startAntOrientation = enumOrientation.SOUTH;
					argsFound = true;
				}else if (args[i].compareToIgnoreCase(String.valueOf('E'))==0){
					startAntOrientation = enumOrientation.EAST;
					argsFound = true;
				}else if (args[i].compareToIgnoreCase(String.valueOf('W'))==0){
					startAntOrientation = enumOrientation.WEST;
					argsFound = true;
				}
		}
		return argsFound;
	}
	
}
