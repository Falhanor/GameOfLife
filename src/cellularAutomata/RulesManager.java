package cellularAutomata;

import java.util.LinkedList;

public class RulesManager implements IRulesManager {
	
	private LinkedList<IRule> lstRules;

	public RulesManager() {
		lstRules = new LinkedList<IRule>();
		initRules();
	}

	private void initRules(){
		for (int i=0;i<256;i++){
			IRule r= new Rule((short)i);
			lstRules.add(r);
		}
		
	}
	
	public IRule getRule(short number) {
		try{
			return lstRules.get(number);	
		}catch (Exception e){
			throw e;
		}
	}
	
	public String toString(){
		StringBuffer stb =new StringBuffer();
		for (IRule r : this.lstRules){
			stb.append(r.toString());
		}
		return stb.toString();
	}

}
