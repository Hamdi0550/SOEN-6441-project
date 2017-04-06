package ddg.model.entity;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * This class define the variable of an item
 * 
 * @author Zhen Du
 * @date Feb 22, 2017
 */
public class BaseItem implements Item, java.io.Serializable{
	private static final long serialVersionUID = 1417331693668110660L;
	
	public static final String TYPE = "type";
	public static final String ABILITY = "ability";
	public static final String BONUS = "bonus";
	public static final String WEAPON_TYPE = "weaponType";
	
	public static final String HELMET = "Helmet";
	public static final String ARMOR = "Armor";
	public static final String SHIELD = "Shield";
	public static final String RING = "Ring";
	public static final String BELT = "Belt";
	public static final String BOOTS = "Boots";
	public static final String WEAPON = "Weapon";
	public static final String WEAPON_MELEE = "Melee";
	public static final String WEAPON_RANGED = "Ranged";
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
	private String weaponType = WEAPON_MELEE;//0:Melee 1:Ranged
	
	public ArrayList<Ability> magic = new ArrayList<Ability>();
	
	public void addAbility(Ability a) {
		magic.add(a);
	}
	
	public DefaultListModel getListModel() {
		DefaultListModel l = new DefaultListModel();
		for(Ability i : magic) {
			l.addElement(new ListEntry(i.getName()));
		}
		return l;
	}
	
	public String getWeaponType() {
		if(!WEAPON.equals(name))
			return null;
		return weaponType;
	}

	public void setWeaponType(String weaponType) {
		if(!WEAPON.equals(name))
			return;
		this.weaponType = weaponType;
	}
	
	private int level = 0;
	public int getLevel() {
		return level;
	}

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
	}
	
	/**
	 * This method is to get bonus of an item by its level
	 * @return the according attribute
	 */
	private int getBonusByLevel() {
		if(this.level <= 0)
			return 0;
		else if(this.level > 17)
			return 5;
		else
			return (this.level-1)/4 + 1;
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
		int weaponBonus = 0;
		if(WEAPON.equals(name)) {
			if(Ability.ATTACK_BONUS.equals(ability)) {
				
			} else if(Ability.DAMAGE_BONUS.equals(ability)) {
				
			}
			if(WEAPON_MELEE.equals(weaponType)) {
				
			} else if(WEAPON_RANGED.equals(weaponType)) {
				
			}
		}
		return bonus + getBonusByLevel();
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
	/**
	  * This method check the targetLevel equals to level 
	  * @param targetLevel
	 */
	public void updateLevel(int targetLevel){

		if(targetLevel != 0){
			this.level = targetLevel;
		}

		if(targetLevel != level){
			level = targetLevel;
		}
		System.out.println(level);

	}
}