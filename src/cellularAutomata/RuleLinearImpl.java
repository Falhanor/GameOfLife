package cellularAutomata;

public class RuleLinearImpl implements Rule {
	private short number = 0;
	private boolean[] result = new boolean[8];
	/*
		111 110 101 100 011 010 001 000	
	*/
	public RuleLinearImpl(short number) {
		this.number = number;
		
		for (byte cas=0; cas<8; cas++)
			result[cas] = ruleBinary().toCharArray()[cas]=='1'?true:false;
	}

	@Override
	public short ruleNumber() {
		return this.number;
	}

	@Override
	public String ruleBinary() {
		return String.format("%08d", Integer.parseInt(Integer.toBinaryString(0xFFFFF & this.number)));
	}
	
	public String toString(){
		return Short.toString(this.number) + " : " + this.ruleBinary() + " > " + resultString() + "\n";
	}
	
	private String resultString(){
		StringBuffer str = new StringBuffer();
		for (byte cas=0; cas<8;cas++)
			str.append(result[cas] + ".");
		
		return str.toString();
	}

	@Override
	public boolean apply(boolean stateCellPrev, boolean stateCellSame, boolean stateCellNext) throws Exception {
		if(!stateCellPrev && !stateCellSame && !stateCellNext )
			return result[7];
		if(!stateCellPrev && !stateCellSame && stateCellNext )
			return result[6];
		if(!stateCellPrev && stateCellSame && !stateCellNext )
			return result[5];
		if(!stateCellPrev && stateCellSame && stateCellNext )
			return result[4];
		if(stateCellPrev && !stateCellSame && !stateCellNext )
			return result[3];
		if(stateCellPrev && !stateCellSame && stateCellNext )
			return result[2];
		if(stateCellPrev && stateCellSame && !stateCellNext )
			return result[1];
		if(stateCellPrev && stateCellSame && stateCellNext )
			return result[0];
		
		throw new Exception();
		
	}

}
