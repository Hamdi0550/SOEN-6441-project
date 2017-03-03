package ddg.utils;

import ddg.item.entity.BaseItem;
import ddg.model.Fighter;

/**
 * 
 * 
 * This class is to store some temporary object when a game is running
 * 
 * @author Fei Yu
 * @date Mar 3, 2017
 */
public class UtilityStorage {
	private static Fighter fighter;
	private static BaseItem item;
	
	/**
	 * Return the character object
	 * @return fighter
	 */
	public static Fighter getFighter(){
		return fighter;
	}
	
	/**
	 * Return the item object
	 * @return item
	 */
	public static BaseItem getItem(){
		return item;
	}
	
	/**
	 * Store a fighter
	 * @param newfighter
	 */
	public static void setFighter(Fighter newfighter){
		fighter = newfighter;
	}
	
	/**
	 * Store an item
	 * @param newitem
	 */
	public static void setItem(BaseItem newitem){
		item = newitem;
	}

}
