package langtonAnt;

import java.util.EventListener;


public interface CellMultiStatesWithEvents extends CellMultiStates {
	
	public interface CellEventListener extends EventListener {
		void cellStateChanged(CellMultiStates c);
		void isAntChanged(CellMultiStates c);
	}
	
	public void addCellMultiStatesListener(CellEventListener listener);
	public void removeCellMultiStatesListener(CellEventListener listener);
	public CellEventListener[] getCellMultiStatesListeners();
}
