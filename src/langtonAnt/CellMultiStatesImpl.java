package langtonAnt;

public class CellMultiStatesImpl implements CellMultiStates {
	
	protected byte stateQty = 2; //==> default binary states
	protected byte state = 0; //==> default state
	protected char states[] = {' ','1'}; //==> default states are empty and '1'.
	
	public CellMultiStatesImpl(char[] statesSet) {
		this.states = statesSet.clone();
		this.stateQty = (byte) this.states.length;
	}

	@Override
	public void nextState() {
		this.state = (byte) ((this.state + stateQty + 1) % stateQty); 
	}

	@Override
	public void kill() {
		this.state = 0;
	}

	@Override
	public void setStatesSet(char[] statesSet) {
		this.states = statesSet.clone();
		this.stateQty = (byte) this.states.length;
		this.state = 0;
	}
	
	@Override
	public byte getStateQty() {
		return stateQty;
	}

	@Override
	public String toString(){
		return "StateQty : " + stateQty + "\nState : " + states[state];
	}
	
	public static void main(String args[]){
		//exemple
		char statesSet[] = {'0','1','2','3','4'};
		CellMultiStates cell = new CellMultiStatesImpl(statesSet);
		System.out.println(cell.toString());
		for(byte b=0; b<8; b++){
			cell.nextState();
			System.out.println(cell.toString());
		}
		
		System.out.println("------------------------\nStates set switch !");
		char otherStatesSet[] = {'0','1'};
		cell.setStatesSet(otherStatesSet);
		System.out.println(cell.toString());
		for(byte b=0; b<10; b++){
			cell.nextState();
			System.out.println(cell.toString());
		}
	}
	
}
