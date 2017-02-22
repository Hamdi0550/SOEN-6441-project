package ddg.model;

import ddg.Config;
import ddg.utils.Utils;
import java.io.Serializable;

/**
 * 
 */

/**
 * @author yufei
 * @version v1.0
 */
public class Fighter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int level;
	private int strength;
	private int dexterity;
	private int constitution;
	private int intelligence;
	private int wisdom;
	private int charisma;
	private int armorClass;		//based on dexterity modifier and worn armor
	private int hitPoints; 		//based on constitution modifier and level
	private int attackBonus; 	//based on level and strength/dexterity modifiers
	private int damageBonus; 	//based on strength modifier, only for melee weapons
	
	private boolean armorIsOn = false;
	private boolean shieldIsOn = false;
	private boolean weaponIsOn = false;
	private boolean bootsIsOn = false;
	private boolean ringIsOn = false;
	private boolean beltIsOn = false;
	private boolean helmetIsOn = false;
	
	public Fighter(int level, int strength, int dexterity){
		this.level = level;
		this.strength = strength;
		this.dexterity = dexterity;
		
	}
	public static void saveFighter(Fighter fighter){
//		Utils.save2File(fighter, "Fighter");
//		Utils.save2File(f, json);
	}
	public int getLevel(){
		return level;
	}
	public int getStrength(){
		return strength;
	}
	public int getDexterity(){
		return dexterity;
	}
}
