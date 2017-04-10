package ddg.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * This class define the variable of an item
 * 
 * @author Zhen Du
 * @date Feb 22, 2017
 */
public class BaseItem implements Item {
	private static final long serialVersionUID = 1417331693668110660L;
	
	@SerializedName(value = "id")
	private String id;
	@SerializedName(value = "name")
	protected String name;
	@SerializedName(value = "bonus")
	private int bonus;
//	@SerializedName(value = "ability")
//	private String[] ability;
	@SerializedName(value = "increase")
	protected String increase;
	
	protected IOwner owner;

	/**
	 * 
	 * Constructors for BaseItem
	 * 
	 * @param name
	 */
	public BaseItem(String name) {
		this.bonus = 1;
		this.name = name;
//		this.ability = getAbility(name);
		this.increase = getAbility(name)[0];
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
//		this.ability = getAbility(name);
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
	 * This method is to get bonus of an item by its level
	 * @return the according attribute
	 */
	private int getBonusByLevel(int level) {
		if(level <= 0)
			return 0;
		else if(level > 17)
			return 5;
		else
			return (level-1)/4 + 1;
	}
	
	/**
	 * 
	 * This method is get the bonus of this item
	 * 
	 * @return int bonus value
	 */
	public int getBonus() {
		if(this.owner == null) {
			return bonus;
		} else {
			return getBonusByLevel(this.owner.getLevel());
		}
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
//	public String[] getAbility() {
//		return ability;
//	}

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
	public static String[] getAbility(String name) {
		if(BaseItem.HELMET.equals(name)) {
			return new String[]{Enchantment.INTELLIGENCE, Enchantment.WISDOM, Enchantment.ARMOR_CLASS};
		} else if(BaseItem.ARMOR.equals(name)) {
			return new String[]{Enchantment.ARMOR_CLASS};
		} else if(BaseItem.SHIELD.equals(name)) {
			return new String[]{Enchantment.ARMOR_CLASS};
		} else if(BaseItem.RING.equals(name)) {
			return new String[]{Enchantment.ARMOR_CLASS, Enchantment.STRENGTH, Enchantment.CONSTITUTION, Enchantment.WISDOM, Enchantment.CHARISMA};
		} else if(BaseItem.BELT.equals(name)) {
			return new String[]{Enchantment.CONSTITUTION, Enchantment.STRENGTH};
		} else if(BaseItem.BOOTS.equals(name)) {
			return new String[]{Enchantment.ARMOR_CLASS, Enchantment.DEXTERITY};
		} else if(BaseItem.WEAPON.equals(name)) {
			return new String[]{Enchantment.ATTACK_BONUS, Enchantment.DAMAGE_BONUS};
		}else if("key".equals(name)){
			return new String[]{Enchantment.WISDOM};
		}
		return null;
	}
//	/**
//	  * This method check the targetLevel equals to level 
//	  * @param targetLevel
//	 */
//	public void updateLevel(int targetLevel){
//
//		if(targetLevel != 0){
//			this.level = targetLevel;
//		}
//
//		if(targetLevel != level){
//			level = targetLevel;
//		}
//		System.out.println(level);
//
//	}
	
//	private int level = 0;
//	@Override
//	public int getLevel() {
//		return this.owner.getLevel();
//	}
	@Override
	public void setOwner(IOwner owner) {
		this.owner = owner;
	}
	@Override
	public IOwner getOwner() {
		return this.owner;
	}
}