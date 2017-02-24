package ddg.model;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 
 * This class
 * 
 * @author Fei Yu
 * @date Feb 23, 2017
 */
public class FighterModel {
	
	public HashMap<String, Fighter> fightersHM;

	public FighterModel() {
		super();
		fightersHM = new HashMap<String, Fighter>();
	}
	
	public FighterModel(HashMap<String, Fighter> fightersHM) {
		super();
		this.fightersHM = fightersHM;
	}

	public HashMap<String, Fighter> getFighters() {
		return fightersHM;
	}

	public void setFighters(HashMap<String, Fighter> fightersHM) {
		this.fightersHM = fightersHM;
	}
	
//	public ArrayList<Fighter> fighters;
//
//	public FighterModel() {
//		super();
//		fighters = new ArrayList<Fighter>();
//	}
//	
//	public FighterModel(ArrayList<Fighter> fighters) {
//		super();
//		this.fighters = fighters;
//	}
//
//	public ArrayList<Fighter> getFighters() {
//		return fighters;
//	}
//
//	public void setItems(ArrayList<Fighter> fighters) {
//		this.fighters = fighters;
//	}
}
