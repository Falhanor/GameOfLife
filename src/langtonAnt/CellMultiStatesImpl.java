package langtonAnt;

public class CellMultiStatesImpl implements CellMultiStates {
	
	protected byte stateQty = 2; //==> default binary states
	protected byte state = 0; //==> default state
	protected char states[] = {' ','1'}; //==> default states are empty and '1'.
	protected boolean isAnt = false;
	
	
	public CellMultiStatesImpl(char[] statesSet) {
		this.states = statesSet.clone();
		this.stateQty = (byte) this.states.length;
	}

	@Override
	public int getState() {
		return this.state;
	}
	
	@Override
	public void nextState() {
		this.state = (byte) ((this.state + stateQty + 1) % stateQty);
	}
	
	@Override
	public boolean isAnt() {
		return this.isAnt;
	}
	
	@Override
	public void setIsAnt(boolean isAnt) {
		this.isAnt = isAnt;
	}
	
	@Override
	public void kill() {
		if (this.state==0)
			return;
		this.state = 0;
	}

	@Override
	public void setStatesSet(char[] statesSet) {
		this.states = statesSet.clone();
		this.stateQty = (byte) this.states.length;
		this.kill();
	}
	
	@Override
	public byte getStateQty() {
		return stateQty;
	}

	@Override
	public String toString(){
		if(isAnt)
			return "#";
		else
			return String.valueOf(states[state]);
	}

}
