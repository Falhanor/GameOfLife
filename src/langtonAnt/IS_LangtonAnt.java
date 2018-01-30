package langtonAnt;


public class IS_LangtonAnt {
	private static final short LINE = 50;
	private static final short COLUMN = 100;
	private static final short ANTY = 25;
	private static final short ANTX = 50;
	private static final char ANTCHAR = '#';
	
	private static final String RULE = "LR";
	
	private static final int DISPLAY_DELAY = 10; 	
	
	private static final int ITERATION = 11000; 	
	
	protected static LangtonCellsManager cells;
	
	public static void main(String[] args){
		cells = new LangtonCellsManagerImpl(LINE, COLUMN, ANTCHAR, ANTY, ANTX, RULE);
		
		//init antCellAsDifferent
		cells.getAntCell().nextState();
		
		
		int iterationCount = 0;
		System.out.println("Iteration n°" + iterationCount + " =====================================================================");
		System.out.println(cells.toString());
		
		while (++iterationCount < ITERATION){
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
	
}
