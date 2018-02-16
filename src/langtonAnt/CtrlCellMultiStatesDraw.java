package langtonAnt;

import java.awt.Color;

import langtonAnt.CellMultiStatesWithEvents.CellMultiStatesEventListener;

public class CtrlCellMultiStatesDraw implements CellMultiStatesEventListener {
	
	private CellMultiStatesWithEvents cell;
	private CtrlCellMultiStateDrawDesign ctrlCellMultiStateLabelDesign;
	private int colorSeed = 0;
	
	public CtrlCellMultiStatesDraw(CellMultiStatesWithEvents cellMultiStates, int colorSeed) {
		this.cell =  cellMultiStates;
		this.colorSeed = colorSeed;
		this.cell.addCellListener(this);
		ctrlCellMultiStateLabelDesign = new CtrlCellMultiStateDrawDesign();
			
		initialize();	
	}
	
	public CtrlCellMultiStateDrawDesign getDesign(){
		return ctrlCellMultiStateLabelDesign;
	}
	
	private void initialize() {		
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
