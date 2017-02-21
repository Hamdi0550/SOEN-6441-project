package ddg.item.entity;

/**
 * 
 * @author jenkin
 *
 */
public class Ability {
	//STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA,
//	ARMOR_CLASS, ATTACK_BONUS, DAMAGE_BONUS
	public static final String STRENGTH = "Strength";
	public static final String DEXTERITY = "Dexterity";
	public static final String CONSTITUTION = "Constitution";
	public static final String INTELLIGENCE = "Intelligence";
	public static final String WISDOM = "Wisdom";
	public static final String CHARISMA = "Charisma";
	public static final String ARMOR_CLASS = "armor";
	public static final String ATTACK_BONUS = "attack";
	public static final String DAMAGE_BONUS = "damage";
	
	private EAbility type;
	private String name;
	
	public Ability(EAbility type, String name) {
		super();
		this.type = type;
		this.name = name;
	}
}
