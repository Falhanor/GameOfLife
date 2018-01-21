package cellularAutomata;

public interface Rule {
	short ruleNumber();
	String ruleBinary();
	
	boolean apply(boolean stateCellPrev, boolean stateCellSame, boolean stateCellNext) throws Exception;
	boolean apply(boolean stateCellAL, boolean stateCellA, boolean stateCellAR, boolean stateCellL, boolean stateCellR, boolean stateCellBL, boolean stateCellB, boolean stateCellBR) throws Exception;
	
	String toString();
}
