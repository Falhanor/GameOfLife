package cellularAutomata;

import java.awt.Color;

import cellularAutomata.CellWithEvents.CellEventListener;


public class CtrlCellLabel implements CellEventListener {
	
	private CellWithEvents cell;
	private CtrlCellLabelDesign ctrlCellLabelDesign;
	private int colorSeed = 0;
	
	public CtrlCellLabel(CellWithEvents cell, int colorSeed) {
		this.cell =  cell;
		this.colorSeed = colorSeed;
		this.cell.addCellListener(this);
		ctrlCellLabelDesign = new CtrlCellLabelDesign();
			
		initialize();	
	}
	
	public CtrlCellLabelDesign getDesign(){
		return ctrlCellLabelDesign;
	}
	
	private void initialize() {		
		ctrlCellLabelDesign.setOpaque(true);
		setColor();
	}

	////////////////////////////////////////////////////////
	// Function linked with design
	//
	private void setColor(){
		if(cell.isAlive()){
			this.ctrlCellLabelDesign.setBackground(new Color(colorSeed));
		}else{
			this.ctrlCellLabelDesign.setBackground(Color.black);
		}
	}
	
	
	////////////////////////////////////////////////////////
	// Events answers
	//
	//Cell events
	@Override
	public void cellStateChanged(Cell c) {
		this.setColor();
	}

}
