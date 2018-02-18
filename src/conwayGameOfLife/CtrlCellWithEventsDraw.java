package conwayGameOfLife;

import java.util.Random;

import cellularAutomata.Cell;
import cellularAutomata.CellWithEvents;
import cellularAutomata.CellWithEvents.CellEventListener;
import cellularAutomata.CtrlCellDraw;


public class CtrlCellWithEventsDraw extends CtrlCellDraw implements CellEventListener {
	private boolean colorChange = false;

	public CtrlCellWithEventsDraw(CellWithEvents cell, int colorSeed, boolean colorChange) {
		super(cell, colorSeed);
		this.colorChange = colorChange;
		cell.addCellListener(this);
	}
	
	@Override
	public void cellStateChanged(Cell c) {
		if(colorChange)
			super.colorSeed = new Random().nextInt(0xFFFFFF);
		
		super.setColor();
	}

}
