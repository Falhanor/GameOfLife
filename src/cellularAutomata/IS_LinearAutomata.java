package cellularAutomata;

import java.util.Scanner;

public class IS_LinearAutomata {
	
	private static final short LINE = 50;
	private static final short COLUMN = 100;
	private static final short START_ALIVE = 50;
	private static final char CELLALIVESYMBOL = '0';
	
	protected static RulesManager rulesGenerator;
	protected static CellsManager cells;
	protected static Rule rule;
	
	public IS_LinearAutomata() {}
	
	
	public static void main(String[] args){;
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
			}while (true);
			//===================================
		}
		
		sc.close();
	}
	
	
	private static void play(){
		System.out.println("============================================ Rule N°" + String.format("%03d", rule.ruleNumber()) + " ============================================");
		cells = new CellsManagerImpl(LINE, COLUMN, CELLALIVESYMBOL);
		cells.getCell(0,START_ALIVE).resurrect();
		//System.out.println(cells.toString());
		
		cells.computeLine(rule);
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
}
