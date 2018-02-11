package cellularAutomata;


public class CellImpl implements Cell {

	protected boolean isAlive = false;
	protected char isAliveSymbol = '\u2589';
	
	public CellImpl(boolean isAlive, char isAliveSymbol) {
		this.isAlive = isAlive;
		this.isAliveSymbol = isAliveSymbol;
	}

	@Override
	public boolean isAlive() {
		return this.isAlive;
	}
	
	@Override
	public void setIsAlive(boolean isAlive){
		this.isAlive = isAlive;
	}
	
	@Override
	public void invertState() {
		this.isAlive = !this.isAlive;
	}
	
	@Override
	public void kill() {
		this.isAlive = false;
	}

	@Override
	public void resurrect() {
		this.isAlive = true;
	}
	
	@Override
	public String toString(){
		return ((this.isAlive)? String.valueOf(this.isAliveSymbol) :" ");
	}

}
