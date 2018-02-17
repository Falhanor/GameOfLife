package cellularAutomata;

import java.util.LinkedList;

public class RulesLinearManagerImpl implements RulesManager {
	
	private LinkedList<Rule> lstRules;

	public RulesLinearManagerImpl() {
		lstRules = new LinkedList<Rule>();
		initRules();
	}

	private void initRules(){
		for (int i=0;i<256;i++){
			Rule r= new RuleLinearImpl((short)i);
			lstRules.add(r);
		}
		
	}
	
	public Rule getRule(short number) {
		try{
			return lstRules.get(number);	
		}catch (Exception e){
			throw e;
		}
	}
	
	public String toString(){
		StringBuffer stb =new StringBuffer();
		stb.append("==============================================================================\n"); 
		stb.append("=======================|         List Of Rules         |======================\n"); 
		stb.append("==============================================================================\n"); 
		stb.append(" N° :  binary  >  111  |  110  |  101  |  100  |  011  |  010  |  001  |  000 \n"); 
		stb.append("==============================================================================\n"); 
		for (Rule r : this.lstRules){
			stb.append(r.toString());
		}
		return stb.toString();
	}

}
