package ddg.model.item;

import java.io.Serializable;

import ddg.IOwner;

/**
 * This class
 * 
 * @author Zhen Du
 * @date Mar 11, 2017
 */
public interface Item extends Serializable {
	
	public static final String TYPE = "type";
	public static final String ABILITY = "ability";
	public static final String BONUS = "bonus";
	
	public static final String HELMET = "Helmet";
	public static final String ARMOR = "Armor";
	public static final String SHIELD = "Shield";
	public static final String RING = "Ring";
	public static final String BELT = "Belt";
	public static final String BOOTS = "Boots";
	public static final String WEAPON = "Weapon";
	public static final String[] NAME = {HELMET, ARMOR, SHIELD, RING, BELT, BOOTS, WEAPON};
	
	String getId();
	void setId(String id);
	String getName();
	void setName(String name);
	int getBonus();
	void setBonus(int bonus);
	String getIncrease();
	void setIncrease(String increase);
	IOwner getOwner();
	void setOwner(IOwner owner);
}
