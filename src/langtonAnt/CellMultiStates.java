package langtonAnt;

public interface CellMultiStates {
	int getState();
	void nextState();
	boolean isAnt();
	void setIsAnt(boolean isAnt);
	void kill();
	
	void setStatesSet(char[] statesSet);
	
	byte getStateQty();
	
	String toString();
}
