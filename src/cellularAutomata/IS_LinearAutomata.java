package cellularAutomata;

import java.util.Scanner;

public class IS_GameOfLife {
	
	private static final short LINE = 50;
	private static final short COLUMN = 100;
	private static final short START_ALIVE = 50;
			
	protected static RulesManager rulesGenerator;
	protected static CellsManager cells;
	protected static IRule rule;
	
	public IS_GameOfLife() {}
	
	
	public static void main(String[] args){;
		rulesGenerator= new RulesManager();
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
			rule = selectRule(sc);
			play();
			//===================================
		}
		
		sc.close();
	}
	
	private static void play(){
		System.out.println("============================================ Rule N°" + rule.ruleNumber() + " ============================================");
		cells = new CellsManager(LINE, COLUMN);
		cells.getCell(0,START_ALIVE).resurrectCell();
		//System.out.println(cells.toString());
		
		cells.compute(rule);
		System.out.println(cells.toString());
	}

	private static IRule selectRule(Scanner sc){
		IRule selectedRule = null;
		
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
