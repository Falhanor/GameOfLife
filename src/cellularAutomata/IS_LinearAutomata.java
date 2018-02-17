package cellularAutomata;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class IS_LinearAutomata {
	
	private static int LINE = 201;
	private static int COLUMN = LINE*2 + 1;
	private static int START_ALIVE = (COLUMN-1)/2;
	private static final char CELLALIVESYMBOL = '0';
	private static int RANDOMCOLOR = new Random().nextInt(0xFFFFFF);
	private static final byte DISPLAY_MARGIN = 50;
	
	protected static RulesManager rulesGenerator;
	protected static CellsManager cells;
	protected static Rule rule;
	
	private static boolean verboseMode = false;
	private static boolean graphicMode = true;
	private static boolean listRules = false;
	
	
	public static void main(String[] args){
		printUsage();
		argsManager(args);
		
		rulesGenerator= new RulesLinearManagerImpl();
		if(listRules)
			System.out.println(rulesGenerator.toString());
		
		Scanner sc = new Scanner(System.in);
		if(!graphicMode && selectAutoSimulation(sc)){
			//=== AutoPlay (console mode only)===
			for (short r=0; r<256;r++){
				rule = rulesGenerator.getRule(r);
				play();
			}
			//===================================	
		}else{
			//=== ManualPlay ====================
			do{
				rule = selectRule(sc);
				play();
				printUsage();
			}while (true);
			//===================================
		}
		sc.close();
	}
	
	private static void play(){
		cells = new CellsManagerImpl(LINE, COLUMN, CELLALIVESYMBOL);
		cells.getCell(0,START_ALIVE).resurrect();
		cells.computeAllLines(rule);
		show();
	}

	private static void show(){
		if(graphicMode){
			RANDOMCOLOR = new Random().nextInt(0xFFFFFF);
			//////////////////////////////
			/// JFrame design
			JFrame frame = new JFrame();
			frame.setTitle("Linear automata - loading...");
			selectFrameSize(frame,0);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
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
			for(int loadingLine=0; loadingLine<LINE; loadingLine++){
				for(int c=0; c<COLUMN; c++){
					frame.getContentPane().add(new CtrlCellDraw(cells.getCell(loadingLine,c), RANDOMCOLOR).getDesign());
				}
				frame.setTitle("Linear automata - [" + (loadingLine*100)/LINE + "% loading...] - " + loadingLine + " iterations with rule \"" + rule.ruleNumber() + "\", color seed = " + RANDOMCOLOR);
				selectFrameSize(frame,loadingLine);
				frame.revalidate();
			}
			frame.revalidate();
			frame.setTitle("Linear automata - " + (LINE-1) + " iterations with rule \"" + rule.ruleNumber() + "\", color seed = " + RANDOMCOLOR);
			///
		}		
		if(verboseMode){
			System.out.println("============================================ Rule N°" + String.format("%03d", rule.ruleNumber()) + " ============================================");
			System.out.println(cells.toString());
			System.out.println("====================================================================================================");
		}
	}
	
	private static void selectFrameSize(JFrame frame, int line){
		int idealWidth = COLUMN + DISPLAY_MARGIN;
		int idealHeight = line + DISPLAY_MARGIN;
		int width = frame.getWidth();
		int height = frame.getHeight();
		
		boolean resized = false;
		if (width < idealWidth){
			frame.setSize(idealWidth,frame.getHeight());
			width = idealWidth;
			resized = true;
		}
		if (height < idealHeight){	
			frame.setSize(width, idealHeight);
			resized = true;
		}
		
		if (resized)
			frame.setLocationRelativeTo(null);
	}
	
	private static Rule selectRule(Scanner sc){
		Rule selectedRule = null;
		
		boolean error = false;
		do {
			System.out.println("Selectionnez un numero de règle (0-255) ?");
			try{		
				short ruleNumber = sc.nextShort();
				
				selectedRule = rulesGenerator.getRule(ruleNumber);
				if (selectedRule==null)
						throw new Exception();
				
				error = false;
			}catch (Exception e){
				error = true;
				System.out.println("Saisie incorrecte !");
			}
		}while (error);
	
		return selectedRule;
	}
	
	private static boolean selectAutoSimulation(Scanner sc){
		boolean isAutoSimulation = false;
		
		boolean error = false;
		do {
			System.out.println("Select simulation type : 1 = Automatic or  2 = Manual ?");
			try{		
				byte i = sc.nextByte();
				
				if(i!=1 && i!=2)
					throw new Exception();
				
				isAutoSimulation = (i==1);
				error = false;
			}catch (Exception e){
				error = true;
				System.out.println("Saisie incorrecte !");
			}
		}while (error);
		
		return isAutoSimulation;
	}
	
	private static void printUsage() {
		
		System.out.println("\n              ====================================================================");
		System.out.println("              |=======================| Linear automata |========================|");
		System.out.println("              |==================================================================|");
		System.out.println("              |                                                                  |");
		System.out.println("              | usage   : program iteration verbose console                      |");
		System.out.println("              | default : cellularAutomata.IS_LinearAutomata " + LINE + "                 |");
		System.out.println("              |                                                                  |");
		System.out.println("              |==================================================================|");
		System.out.println("              | Parameters details :                                             |");
		System.out.println("              |  >>> iteration : integer (0 to " + String.format("%1$5s", getMaxHeight()) + ")                            |");
		System.out.println("              |                  => grid [iteration x iteration*2]               |");
		System.out.println("              |  >>> verbose mode : -v                                           |");
		System.out.println("              |  >>> console mode (no window) : -c                               |");
		System.out.println("              |                                                                  |");
		System.out.println("              | Some remarkable rules :                                          |");
		System.out.println("              |    - Full screen    -> 0 151 233* 237* 251*                      |");
		System.out.println("              |    - Zip            -> 1* 17 27 41* 51 59 97* 107 123 185        |");
		System.out.println("              |    - Single line    -> 2 4* 6 14                                 |");
		System.out.println("              |    - Wave           -> 3 15 43 47                                |");
		System.out.println("              |    - 3D             -> 5 9 13 25 37 57* 62* 77* 91 109* 111 131* |");
		System.out.println("              |    - Stripes        -> 7 19 23                                   |");
		System.out.println("              |    - Sierpinski     -> 18 22 60 126 129 153 165 182 195 218      |");
		System.out.println("              |    - Split          -> 29 139 155 173 205 207 219                |");
		System.out.println("              |    - Chaos          -> 30* 86 135 149                            |");
		System.out.println("              |    - Mountain       -> 45 75* 89 101                             |");  
		System.out.println("              |    - Scale          -> 73*                                       |");  
		System.out.println("              |    - Half triangle  -> 28 92 141 206* 230                        |");
		System.out.println("              |    - Triangle       -> 50 54 94 133 147 158 163 179* 222* 246    |");
		System.out.println("              |    - Pyramidception -> 105* 150*                                 |");
		System.out.println("              |    - Emergence      -> 110* 124 137* 193                         |");
		System.out.println("              |    - Nested pattern -> 169 225*                                  |");
		System.out.println("              |                                                                  |");
		System.out.println("              |                                   (*= best off)                  |");
		System.out.println("              |                                                                  |");
		System.out.println("              |==================================================================|");
		System.out.println("              |==========================================| By : Timothée SOLLAUD |");
		System.out.println("              ====================================================================\n");
	}
	
	private static boolean argsManager(String[] args) {
		boolean argsFound = false;
		String regexArgIteration = "^\\d+$";
		if (args.length>0){
			for(int i=0; i<args.length; ++i)
				if (args[i].matches(regexArgIteration)){
					int argLine = Integer.parseInt(args[i]);
					int maxHeight = getMaxHeight()-1;
					LINE = (argLine > maxHeight)? ++maxHeight : ++argLine;
					COLUMN = LINE*2 + 1;
					START_ALIVE = (COLUMN)/2;
					argsFound = true;
				}else if (args[i].compareToIgnoreCase("-v")==0){
					verboseMode = true;
					argsFound = true;
				}else if (args[i].compareToIgnoreCase("-c")==0){
					graphicMode = false;
					argsFound = true;
				}else if (args[i].compareToIgnoreCase("-l")==0){
					listRules = true;
					argsFound = true;
				}
		}
		verboseMode = verboseMode || (!graphicMode && !verboseMode);
		
		return argsFound;
	}
	
	private static int getMaxHeight(){
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		int maxHeight = (int) graphicsEnvironment.getMaximumWindowBounds().getHeight()-DISPLAY_MARGIN;
		int maxWidth = (int) (graphicsEnvironment.getMaximumWindowBounds().getWidth())-DISPLAY_MARGIN/2;
		return (maxHeight*2 > maxWidth)?maxWidth/2:maxHeight;
	}
	
}
