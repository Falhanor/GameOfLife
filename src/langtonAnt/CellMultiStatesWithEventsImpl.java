package langtonAnt;

import javax.swing.event.EventListenerList;


public class CellMultiStatesWithEventsImpl extends CellMultiStatesImpl implements CellMultiStatesWithEvents {
	
	
	public CellMultiStatesWithEventsImpl(char[] statesSet) {
		super(statesSet);
	}

	/////////////////////////////////////////////////////
	// Cell Events
	//
	private final EventListenerList cellMultiStatesListeners = new EventListenerList();
	
	@Override
	public void addCellListener(CellMultiStatesEventListener listener) {
		cellMultiStatesListeners.add(CellMultiStatesEventListener.class, listener);
	}
	@Override
	public void removeCellListener(CellMultiStatesEventListener listener) {
		cellMultiStatesListeners.remove(CellMultiStatesEventListener.class, listener);
	}
	@Override
	public CellMultiStatesEventListener[] getCellListeners() {
		return cellMultiStatesListeners.getListeners(CellMultiStatesEventListener.class);
	}
	
	protected void fireIsAntChanged(CellMultiStates c) {
		for(CellMultiStatesEventListener listener : getCellListeners()){
			listener.isAntChanged(c);
		}
	}
	protected void fireCellStateChanged(CellMultiStates c) {
		for(CellMultiStatesEventListener listener : getCellListeners()){
			listener.cellStateChanged(c);
		}
	}
	/////////////////////////////////////////////////////
	
	@Override
	public void nextState() {
		super.nextState(); 
		this.fireCellStateChanged(this);
	}
	
	@Override
	public void setIsAnt(boolean isAnt) {
		super.setIsAnt(isAnt);
		fireIsAntChanged(this);
	}
	
	@Override
	public void kill() {
		super.kill();
		this.fireCellStateChanged(this);
	}

}
