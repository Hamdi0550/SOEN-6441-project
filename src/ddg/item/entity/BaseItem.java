package ddg.item.entity;

/**
 * @author jenkin
 *
 */
public class BaseItem {
//	protected EItem type;HELMET, ARMOR, SHIELD, RING, BELT, BOOTS, WEAPON
	public static final String HELMET = "Helmet";
	public static final String ARMOR = "Armor";
	public static final String SHIELD = "Shield";
	public static final String RING = "Ring";
	public static final String BELT = "Belt";
	public static final String BOOTS = "Boots";
	public static final String WEAPON = "Weapon";
	
	protected int id;
	protected String name;
	protected int bonus;
	protected String[] ability;
	protected String increate;
	
	public BaseItem(String name) {
		this.name = name;
		this.ability = getAbility(name);
	}
	
	public BaseItem(String name, int bonus, String increate) {
		this.name = name;
		this.bonus = bonus;
		this.increate = increate;
		this.ability = getAbility(name);
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public String[] getAbility() {
		return ability;
	}
//
//	public void setAbility(String[] ability) {
//		this.ability = ability;
//	}

	public String getIncreate() {
		return increate;
	}

	public void setIncreate(String increate) {
		this.increate = increate;
	}

	public String[] getAbility(String name) {
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
		}
		return null;
	}
}