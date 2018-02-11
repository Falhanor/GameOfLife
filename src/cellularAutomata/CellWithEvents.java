package cellularAutomata;

import java.util.EventListener;


public interface CellWithEvents extends Cell {
	
	public interface CellEventListener extends EventListener {
		void cellStateChanged(Cell c);
	}
	
	public void addCellListener(CellEventListener listener);
	public void removeCellListener(CellEventListener listener);
	public CellEventListener[] getCellListeners();
}
