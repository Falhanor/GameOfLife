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
		for (Rule r : this.lstRules){
			stb.append(r.toString());
		}
		return stb.toString();
	}

}
