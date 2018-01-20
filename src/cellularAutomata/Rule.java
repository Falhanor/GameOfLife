package cellularAutomata;

public interface IRule {
	short ruleNumber();
	String ruleBinary();
	
	boolean apply(boolean stateCellPrev, boolean stateCellSame, boolean stateCellNext) throws Exception;
	
	String toString();
}
