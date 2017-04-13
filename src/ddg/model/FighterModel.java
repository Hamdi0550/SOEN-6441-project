package ddg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * This class
 * 
 * @author Fei Yu
 * @date Feb 23, 2017
 */
public class FighterModel implements Serializable {
	
	private static final long serialVersionUID = -8867154362135278354L;
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
	 * @return fightersHM
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

	/**
	 * 
	 * This method getFighterByName
	 * 
	 * @param name
	 * @return
	 */
	public Fighter getFighterByName(String name) {
		return this.fightersHM.get(name);
	}
	
	/**
	 * 
	 * This method get fighter list
	 * 
	 * @return
	 */
	public ArrayList<String> getFighterList() {
		ArrayList<String> list = new ArrayList<String>();
		Set<String> keySet = this.fightersHM.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String name = it.next();
			if(name.length() > 0) {
				list.add(name);
			}
		}
		
		return list;
	}
}
