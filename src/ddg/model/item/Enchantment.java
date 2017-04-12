package ddg.model.item;

import java.io.Serializable;

/**
 * 
 * This class define the name for increase
 * 
 * @author Zhen Du
 * @date Feb 22, 2017
 */
public class Enchantment implements Serializable {
	private static final long serialVersionUID = -6350068786148679730L;
	public static final String STRENGTH = "Strength";
	public static final String DEXTERITY = "Dexterity";
	public static final String CONSTITUTION = "Constitution";
	public static final String INTELLIGENCE = "Intelligence";
	public static final String WISDOM = "Wisdom";
	public static final String CHARISMA = "Charisma";
	public static final String ARMOR_CLASS = "Armor class";
	public static final String ATTACK_BONUS = "Attack bonus";
	public static final String DAMAGE_BONUS = "Damage bonus";
	
	protected String name;
	/**
	 * Constructors
	 * 
	 */
	public Enchantment(String name) {
		this.name = name;
	}

	String getName(){return name;}
}
