package langtonAnt;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.Random;
import java.awt.Color;


public class IS_LangtonAnt {
	private static final short LINE = 100;
	private static final short COLUMN = 175;
	private static final short ANTY = 50;
	private static final short ANTX = 85;
	private static final char ANTCHAR = '#';
	private static final int RANDOMCOLOR = new Random().nextInt(0xFFFFFF);
	
	private static final int DISPLAY_DELAY = 0; 	
	
	private static int iteration = 0; 	
	private static String rule = "LR";
	protected static LangtonCellsManager cells;
	
	public static void main(String[] args){
		printUsage();
		argsManager(args);
		
		//////////////////////////////
		/// JFrame design
		JFrame fenetre = new JFrame();
		fenetre.setTitle("LangtonAnt - iterations with rule \"" + rule + "\" & color seed = " + RANDOMCOLOR);
	    fenetre.setSize(920, 600);
	    fenetre.setLocationRelativeTo(null);
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JPanel pan = new JPanel();
	    pan.setLayout(new GridLayout(LINE,COLUMN));
	    pan.setBackground(Color.white);
	    ///////////////////////////////
	    // add CellLabels
		cells = new LangtonCellsManagerImpl(LINE, COLUMN, ANTCHAR, ANTY, ANTX, rule);
		
		for(int l=0; l<LINE; l++){
			for(int c=0; c<COLUMN; c++){
				pan.add(new CtrlCellMultiStatesLabel((CellMultiStatesWithEvents) cells.getCell(l,c), RANDOMCOLOR).getDesign());
			}
		}
		fenetre.setContentPane(pan);
		fenetre.setVisible(true);
	    
	    ///////////////////////////////
	    // compute
		//init antCellAsDifferent
		cells.getAntCell().setIsAnt(true);
	    
		//run parameters
		System.out.println("Run parameters : " + iteration + " iterations with rule \"" + rule + "\" & color seed = " + RANDOMCOLOR);
		
		int iterationCount = 0;
		System.out.println("\nIteration n°" + iterationCount + " =====================================================================");
		System.out.println(cells.toString());
		
		while (iteration==0 || iterationCount <= iteration){
			++iterationCount;
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
			fenetre.setTitle("LangtonAnt - " + iterationCount + " iterations with rule \"" + rule + "\" & color seed = " + RANDOMCOLOR);
			System.out.println("Iteration n°" + iterationCount+ " =====================================================================");
			System.out.println(cells.toString());
		}
	}

	private static void printUsage() {
		System.out.println("\n              =====================================================");
		System.out.println("              |=================| Langton's Ant |=================|");
		System.out.println("              |===================================================|");
		System.out.println("              |                                                   |");
		System.out.println("              | usage   : program rule iteration                  |");
		System.out.println("              | default : langtonAnt.IS_LangtonAnt " + rule + " " + iteration + "       |");
		System.out.println("              |                                                   |");
		System.out.println("              |===================================================|");
		System.out.println("              |===========================| By : Timothée SOLLAUD |");
		System.out.println("              =====================================================\n");
	}

	private static boolean argsManager(String[] args) {
		boolean argsFound = false;
		String regexArgRule = "^[L|R]*$";
		String regexArgIteration = "^\\d+$";
		if (args.length>0){
			if (args[0].matches(regexArgRule)){
				rule = args[0];
				argsFound = true;
			}else if (args[0].matches(regexArgIteration)){
				iteration = Integer.parseInt(args[0]);
				argsFound = true;
			}
		}
		if(args.length>1){
			if (args[1].matches(regexArgRule)){
				rule = args[1];
				argsFound = true;
			}else if (args[1].matches(regexArgIteration)){
				iteration = Integer.parseInt(args[1]);
				argsFound = true;
			}
		}
		return argsFound;
	}
	
}
