package cellularAutomata;

import java.awt.Color;


public class CtrlCellDraw {
	
	private Cell cell;
	private CtrlCellDrawDesign ctrlCellDrawDesign;
	private int colorSeed = 0;
	
	public CtrlCellDraw(Cell cell, int colorSeed) {
		this.cell =  cell;
		this.colorSeed = colorSeed;
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

}
