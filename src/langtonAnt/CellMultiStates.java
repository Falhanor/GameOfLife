package langtonAnt;

public interface CellMultiStates {
	void nextState();
	void kill();
	
	void setStatesSet(char[] statesSet);
	
	byte getStateQty();
	
	String toString();
}
