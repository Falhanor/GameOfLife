package langtonAnt;

import java.awt.Color;

import langtonAnt.CellMultiStatesWithEvents;
import langtonAnt.CellMultiStatesWithEvents.CellEventListener;

public class CtrlCellMultiStatesLabel implements CellEventListener {
	
	private CellMultiStatesWithEvents cell;
	private CtrlCellMultiStateLabelDesign ctrlCellMultiStateLabelDesign;
	private int colorSeed = 0;
	
	public CtrlCellMultiStatesLabel(CellMultiStatesWithEvents cellMultiStates, int colorSeed) {
		this.cell =  cellMultiStates;
		this.colorSeed = colorSeed;
		this.cell.addCellMultiStatesListener(this);
		ctrlCellMultiStateLabelDesign = new CtrlCellMultiStateLabelDesign();
			
		initialize();	
	}
	
	public CtrlCellMultiStateLabelDesign getDesign(){
		return ctrlCellMultiStateLabelDesign;
	}
	
	private void initialize() {		
		ctrlCellMultiStateLabelDesign.setOpaque(true);
		setColor();
	}

	////////////////////////////////////////////////////////
	// Function linked with design
	//
	private void setColor(){
		if(cell.isAnt()){
			this.ctrlCellMultiStateLabelDesign.setBackground(Color.red);
		}else{
			//this.ctrlCellMultiStateLabelDesign.setText(""+cell.getState());
			if(cell.getState()==0)
				this.ctrlCellMultiStateLabelDesign.setBackground(Color.black);
			else
				this.ctrlCellMultiStateLabelDesign.setBackground(new Color(cell.getState()*colorSeed));
		}
	}
	
	
	////////////////////////////////////////////////////////
	// Events answers
	//
	//Cell events
	@Override
	public void cellStateChanged(CellMultiStates c) {
		setColor();
	}

	@Override
	public void isAntChanged(CellMultiStates c) {
		setColor();		
	}
	
	
}
