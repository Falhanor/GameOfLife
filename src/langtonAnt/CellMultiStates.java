package langtonAnt;

public interface CellMultiStates {
	int getState();
	void nextState();
	void kill();
	
	void setStatesSet(char[] statesSet);
	
	byte getStateQty();
	
	String toString();
}
