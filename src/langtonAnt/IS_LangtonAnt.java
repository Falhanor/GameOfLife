package langtonAnt;


public class IS_LangtonAnt {
	private static final short LINE = 50;
	private static final short COLUMN = 100;
	private static final short ANTY = 25;
	private static final short ANTX = 50;
	private static final char ANTCHAR = '#';
	
	private static final int DISPLAY_DELAY = 10; 	
	
	private static int iteration = 11000; 	
	private static String rule = "LR";
	protected static LangtonCellsManager cells;
	
	public static void main(String[] args){
		printUsage();
		argsManager(args);
		
		cells = new LangtonCellsManagerImpl(LINE, COLUMN, ANTCHAR, ANTY, ANTX, rule);
		//init antCellAsDifferent
		cells.getAntCell().nextState();
		
		//run parameters
		System.out.println("Run parameters : " + iteration + " iterations with rule \"" + rule + "\".");
		
		int iterationCount = 0;
		System.out.println("\nIteration n°" + iterationCount + " =====================================================================");
		System.out.println(cells.toString());
		
		while (++iterationCount <= iteration){
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
