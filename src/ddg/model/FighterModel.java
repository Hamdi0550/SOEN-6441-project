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

	/**
	 * Constructor
	 */
	public FighterModel() {
		super();
		fightersHM = new HashMap<String, Fighter>();
	}
	
	/**
	 * Constructor
	 * @param fightersHM
	 */
	public FighterModel(HashMap<String, Fighter> fightersHM) {
		super();
		this.fightersHM = fightersHM;
	}

	/**
	 * Return a hashmap table contains characters
	 * @return
	 */
	public HashMap<String, Fighter> getFighters() {
		return fightersHM;
	}
	
	/**
	 * Set the hashmap table storing characters
	 * @param fightersHM
	 */
	public void setFighters(HashMap<String, Fighter> fightersHM) {
		this.fightersHM = fightersHM;
	}

}
