package ddg.item.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * This class define the variable of an item
 * 
 * @author Zhen Du
 * @date Feb 22, 2017
 */
public class BaseItem implements java.io.Serializable{
	private static final long serialVersionUID = 1417331693668110660L;
	
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
	
	@SerializedName(value = "id")
	private String id;
	@SerializedName(value = "name")
	private String name;
	@SerializedName(value = "bonus")
	private int bonus;
	@SerializedName(value = "ability")
	private String[] ability;
	@SerializedName(value = "increase")
	private String increase;
	
	/**
	 * 
	 * Constructors for BaseItem
	 * 
	 * @param name
	 */
	public BaseItem(String name) {
		this.bonus = 1;
		this.name = name;
		this.ability = getAbility(name);
		this.increase = this.ability[0];
	}
	
	/**
	 * 
	 * Constructors for BaseItem
	 * 
	 * @param name item name
	 * @param bonus bonus to set
	 * @param increate increase 
	 */
	public BaseItem(String name, int bonus, String increase) {
		this.name = name;
		this.bonus = bonus;
		this.increase = increase;
		this.ability = getAbility(name);
		this.increase = this.ability[0];
	}
	
	/**
	 * 
	 * This method is for get item id
	 * 
	 * @return String id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * This method is set the item id
	 * 
	 * @param id item id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * This method is get the item name
	 * 
	 * @return String item name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * This method is set the item name
	 * 
	 * @param name item name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * This method is get the bonus of this item
	 * 
	 * @return int bonus value
	 */
	public int getBonus() {
		return bonus;
	}

	/**
	 * 
	 * This method is set the bonus value
	 * 
	 * @param bonus bonus value
	 */
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	/**
	 * 
	 * This method get the abilities of this item
	 * 
	 * @return String[]	abilities
	 */
	public String[] getAbility() {
		return ability;
	}

//	public void setAbility(String[] ability) {
//		this.ability = ability;
//	}
	
	/**
	 * 
	 * This method is get the increase of this item
	 * 
	 * @return String increase
	 */
	public String getIncrease() {
		return increase;
	}

	/**
	 * 
	 * This method is set the increase value
	 * 
	 * @param increase String increase value
	 */
	public void setIncrease(String increase) {
		this.increase = increase;
	}

	/**
	 * 
	 * This method is get the ability scale of one specific item
	 * 
	 * @param name item name
	 * @return String[] abilities of this item
	 */
	private String[] getAbility(String name) {
		if(BaseItem.HELMET.equals(name)) {
			return new String[]{Ability.INTELLIGENCE, Ability.WISDOM, Ability.ARMOR_CLASS};
		} else if(BaseItem.ARMOR.equals(name)) {
			return new String[]{Ability.ARMOR_CLASS};
		} else if(BaseItem.SHIELD.equals(name)) {
			return new String[]{Ability.ARMOR_CLASS};
		} else if(BaseItem.RING.equals(name)) {
			return new String[]{Ability.ARMOR_CLASS, Ability.STRENGTH, Ability.CONSTITUTION, Ability.WISDOM, Ability.CHARISMA};
		} else if(BaseItem.BELT.equals(name)) {
			return new String[]{Ability.CONSTITUTION, Ability.STRENGTH};
		} else if(BaseItem.BOOTS.equals(name)) {
			return new String[]{Ability.ARMOR_CLASS, Ability.DEXTERITY};
		} else if(BaseItem.WEAPON.equals(name)) {
			return new String[]{Ability.ATTACK_BONUS, Ability.DAMAGE_BONUS};
		}else if("key".equals(name)){
			return new String[]{Ability.WISDOM};
		}
		return null;
	}
}