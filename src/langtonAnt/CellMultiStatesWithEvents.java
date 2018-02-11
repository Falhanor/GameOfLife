package langtonAnt;


import java.util.EventListener;



public interface CellMultiStatesWithEvents extends CellMultiStates
{
	
	public interface CellMultiStatesEventListener extends EventListener {
		void cellStateChanged(CellMultiStates c);
		void isAntChanged(CellMultiStates c);
	}
	
	public void addCellListener(CellMultiStatesEventListener listener);
	public void removeCellListener(CellMultiStatesEventListener listener);
	public CellMultiStatesEventListener[] getCellListeners();
}
