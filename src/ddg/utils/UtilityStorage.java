package ddg.utils;

import ddg.model.Fighter;
import ddg.model.item.Item;

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
	private static Item item;
	
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
	public static Item getItem(){
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
	public static void setItem(Item newitem){
		item = newitem;
	}

}
