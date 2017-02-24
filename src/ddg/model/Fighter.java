package ddg.model;
import java.io.Serializable;
import java.util.Date;

import ddg.Config;
import ddg.utils.Dice;
//import ddg.utils.FileRW;
import ddg.utils.Utils;


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
	//Your attack roll is 1d20 + your ability modifier + your proficiency bonus(level) if you're proficient with the weapon you’re using.	
	private int damageBonus; 	//based on strength modifier, only for melee weapons
	//You roll the damage die or dice, add any modifiers, and apply the damage to your target. With a penalty, it is possible to deal 0 damage, but never negative damage.
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
//		hitPoints = Dice.d10Roll() + (constitution * this.level);
		armorClass = this.dexterity + 0;
		
	}
	public static void saveFighter(Fighter fighter){
//		FileRW.save2File(fighter, "Fighter");
		FighterModel fModel = new FighterModel();
//		this.model = Utils.fromJson(g, FighterModel.class);
		
		
		
		
		String dateString = new Date().toLocaleString();
		dateString = dateString + fighter.name;
		fModel.fightersHM.put(dateString, fighter);
//		Utils.saveToFile(fightersHM, Config.CHARACTOR_FILE);
//		Utils.save2File(f, json);
	}
	public static void deleteFighter(Fighter fighter){
//		Utils.deleteFromFile(fighter, Config.CHARACTOR_FILE);
	}
	public String getName(){
		return name;
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
	public void setName(String name){
		this.name = name;
	}
	public void setLevel(int level){
		this.level = level;
	}
	public void setStrength(int strength){
		this.level = strength;
	}
	public void setDexterity(int dexterity){
		this.level = dexterity;
	}
}
