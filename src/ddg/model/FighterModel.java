package ddg.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import ddg.item.entity.ListEntry;


/**
 * 
 * This class
 * 
 * @author Fei Yu
 * @date Feb 23, 2017
 */
public class FighterModel extends Observable {
	
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
		setChanged();
		notifyObservers(this);
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
	 * This method
	 * 
	 * @param name
	 */
	public void removeFighterByName(String name) {
		this.fightersHM.remove(name);
	}
	
	/**
	 * 
	 * This method getListModel
	 * 
	 * @return
	 */
	public DefaultListModel getListModel() {
		DefaultListModel l = new DefaultListModel();
		Set<String> keySet = this.fightersHM.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String name = it.next();
			if(name.length() > 0) {
				l.addElement(new ListEntry(name, new ImageIcon("res/"+"Helmet"+".jpg")));
//				l.addElement(name);
			}
		}
		return l;
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
