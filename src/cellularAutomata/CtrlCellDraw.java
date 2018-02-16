package cellularAutomata;

import java.awt.Color;

import cellularAutomata.CellWithEvents.CellEventListener;


public class CtrlCellDraw implements CellEventListener {
	
	private CellWithEvents cell;
	private CtrlCellDrawDesign ctrlCellDrawDesign;
	private int colorSeed = 0;
	
	public CtrlCellDraw(CellWithEvents cell, int colorSeed) {
		this.cell =  cell;
		this.colorSeed = colorSeed;
		this.cell.addCellListener(this);
		ctrlCellDrawDesign = new CtrlCellDrawDesign();
			
		initialize();	
	}
	
	public CtrlCellDrawDesign getDesign(){
		return ctrlCellDrawDesign;
	}
	
	private void initialize() {	
		setColor();
	}

	////////////////////////////////////////////////////////
	// Function linked with design
	//
	private void setColor(){
		if(cell.isAlive()){
			this.ctrlCellDrawDesign.setBackground(new Color(colorSeed));
		}else{
			this.ctrlCellDrawDesign.setBackground(Color.black);
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
