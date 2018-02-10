package langtonAnt;

import javax.swing.event.EventListenerList;


public class CellMultiStatesWithEventsImpl implements CellMultiStatesWithEvents {
	
	protected byte stateQty = 2; //==> default binary states
	protected byte state = 0; //==> default state
	protected char states[] = {' ','1'}; //==> default states are empty and '1'.
	protected boolean isAnt = false;
	
	/////////////////////////////////////////////////////
	// Cell Events
	//
	private final EventListenerList cellMultiStatesListeners = new EventListenerList();
	
	@Override
	public void addCellMultiStatesListener(CellEventListener listener) {
		cellMultiStatesListeners.add(CellEventListener.class, listener);
	}
	@Override
	public void removeCellMultiStatesListener(CellEventListener listener) {
		cellMultiStatesListeners.remove(CellEventListener.class, listener);
	}
	@Override
	public CellEventListener[] getCellMultiStatesListeners() {
		return cellMultiStatesListeners.getListeners(CellEventListener.class);
	}
	
	protected void fireIsAntChanged(CellMultiStates c) {
		for(CellEventListener listener : getCellMultiStatesListeners()){
			listener.isAntChanged(c);
		}
	}
	protected void fireCellStateChanged(CellMultiStates c) {
		for(CellEventListener listener : getCellMultiStatesListeners()){
			listener.cellStateChanged(c);
		}
	}
	/////////////////////////////////////////////////////
	
	
	public CellMultiStatesWithEventsImpl(char[] statesSet) {
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
		this.fireCellStateChanged(this);
	}
	
	@Override
	public boolean isAnt() {
		return this.isAnt;
	}
	
	@Override
	public void setIsAnt(boolean isAnt) {
		this.isAnt = isAnt;
		fireIsAntChanged(this);
	}
	
	@Override
	public void kill() {
		if (this.state==0)
			return;
		this.state = 0;
		this.fireCellStateChanged(this);
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
