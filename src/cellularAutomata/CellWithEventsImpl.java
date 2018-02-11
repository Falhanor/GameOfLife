package cellularAutomata;

import javax.swing.event.EventListenerList;

public class CellWithEventsImpl extends CellImpl implements CellWithEvents {

	public CellWithEventsImpl(boolean isAlive, char isAliveSymbol) {
		super(isAlive, isAliveSymbol);
	}
	
	/////////////////////////////////////////////////////
	// Cell Events
	//
	private final EventListenerList cellListeners = new EventListenerList();

	@Override
	public void addCellListener(CellEventListener listener) {
		cellListeners.add(CellEventListener.class, listener);
	}

	@Override
	public void removeCellListener(CellEventListener listener) {
		cellListeners.remove(CellEventListener.class, listener);
	}

	@Override
	public CellEventListener[] getCellListeners() {
		return cellListeners.getListeners(CellEventListener.class);
	}
	
	protected void fireCellStateChanged(Cell c) {
		for(CellEventListener listener : getCellListeners()){
			listener.cellStateChanged(c);
		}
	}
	/////////////////////////////////////////////////////
	
	
	public void invertState() {
		super.invertState();
		this.fireCellStateChanged(this);
	}
	
	public void kill() {
		super.kill();
		this.fireCellStateChanged(this);
	}

	public void resurrect() {
		super.resurrect();
		this.fireCellStateChanged(this);
	}
}
