package cellularAutomata;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class IS_LinearAutomata {
	
	private static final short LINE = 200;
	private static final short COLUMN = 400;
	private static final short START_ALIVE = 200;
	private static final char CELLALIVESYMBOL = '0';
	private static final int RANDOMCOLOR = new Random().nextInt(0xFFFFFF);
	
	protected static RulesManager rulesGenerator;
	protected static CellsManager cells;
	protected static Rule rule;
	
	
	public IS_LinearAutomata() {}
	
	
	public static void main(String[] args){
		printUsage();
		rulesGenerator= new RulesLinearManagerImpl();
		//System.out.println(rulesGenerator.toString());
		
		Scanner sc = new Scanner(System.in);
		
		if(selectAutoSimulation(sc)){
			//======= AutoPlay ==================
			for (short r=0; r<256;r++){
				rule = rulesGenerator.getRule(r);
				play();
			}
			//===================================	
		}else{
			//======= ManualPlay ===============
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
		//System.out.println(cells.toString());
		
		cells.computeLine(rule);
		
		//////////////////////////////
		/// JFrame design
		JFrame fenetre = new JFrame();
		fenetre.setTitle("Linear automata - loading...");
		fenetre.setSize(1024, 600);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(0,COLUMN));
		pan.setBackground(Color.black);
		
		fenetre.setContentPane(pan);
		fenetre.getFocusOwner();
		fenetre.setVisible(true);
		fenetre.setAlwaysOnTop(true);
		fenetre.setAlwaysOnTop(false);
		fenetre.validate();
		//////////////////////////////
		///add cells
		for(int loadingLine=0; loadingLine<LINE; loadingLine++){
			for(int c=0; c<COLUMN; c++){
				fenetre.getContentPane().add(new CtrlCellDraw((CellWithEvents) cells.getCell(loadingLine,c), RANDOMCOLOR).getDesign());
			}
			fenetre.setTitle("Linear automata - [" + (loadingLine*100)/LINE + "% loading...] - " + loadingLine + " iterations with rule \"" + rule.ruleNumber() + "\", color seed = " + RANDOMCOLOR);
			fenetre.revalidate();
		}
		fenetre.revalidate();
		fenetre.setTitle("Linear automata - " + LINE + " iterations with rule \"" + rule.ruleNumber() + "\", color seed = " + RANDOMCOLOR);
		///////
		System.out.println("============================================ Rule N°" + String.format("%03d", rule.ruleNumber()) + " ============================================");
		System.out.println(cells.toString());
		System.out.println("====================================================================================================");
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
		System.out.println("\n              =====================================================");
		System.out.println("              |================| Linear automata |================|");
		System.out.println("              |===================================================|");
		System.out.println("              |                                                   |");
		System.out.println("              | Some remarkable rules :                           |");
		System.out.println("              |     30 -> chaos      18/90 -> Sierpinksi          |");
		System.out.println("              |     57 -> Pyramid      129 -> Sierpinksi negative |");
		System.out.println("              |    110 -> emergence    225 -> Nested pattern      |");
		System.out.println("              |                                                   |");
		System.out.println("              |===================================================|");
		System.out.println("              |===========================| By : Timothée SOLLAUD |");
		System.out.println("              =====================================================\n");
	}
}
