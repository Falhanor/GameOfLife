package cellularAutomata;

public class Cell implements ICell {

	private boolean isAlive = false;
	
	public Cell(boolean isAlive) {
		this.isAlive=isAlive;
	}

	@Override
	public boolean isAlive() {
		return this.isAlive;
	}
	
	@Override
	public void setIsAlive(boolean isAlive){
		this.isAlive=isAlive;
	}

	@Override
	public void killCell() {
		this.isAlive=false;
	}

	@Override
	public void resurrectCell() {
		this.isAlive=true;
	}
	
	@Override
	public String toString(){
		return (this.isAlive)? "O":" ";
	}

}
