package ddg.model;
import java.io.Serializable;
import java.util.*;

import ddg.Config;
import ddg.utils.Dice;
import ddg.utils.Utils;
import ddg.item.entity.*;


/**
 * 
 * 
 * This class define a character's features and actions
 * 
 * @author Fei Yu
 * @date Mar 3, 2017
 */
public class Fighter implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int level = 1;
	private int strength;
	private int dexterity;
	private int constitution;
	private int intelligence;
	private int wisdom;
	private int charisma;
	private int gainedStrength;
	private int gainedDexterity;
	private int gainedConstitution;
	private int gainedIntelligence;
	private int gainedWisdom;
	private int gainedCharisma;
	private int gainedArmorClass;
	private int armorClass;		//based on dexterity modifier and worn armor
	private int hitPoints; 		//based on constitution modifier and level
	private int attackBonus; 	//based on level and strength/dexterity modifiers
	//Your attack roll is 1d20 + your ability modifier + your proficiency bonus(level) if you're proficient with the weapon you’re using.	
	private int damageBonus; 	//based on strength modifier, only for melee weapons
	//You roll the damage die or dice, add any modifiers, and apply the damage to your target. With a penalty, it is possible to deal 0 damage, but never negative damage.
	public boolean isArmorOn = false;
	public boolean IsShieldOn = false;
	public boolean isWeaponOn = false;
	public boolean isBootsOn = false;
	public boolean isRingOn = false;
	public boolean isBeltOn = false;
	public boolean isHelmetOn = false;
	private ArrayList<BaseItem> backpack = new ArrayList<>();
	private BaseItem[] backpack1 = new BaseItem[10];
	private ArrayList<BaseItem> wornItems = new ArrayList<>();
	
	public Fighter(){
		
	}
	
	public Fighter(int level, int strength, int dexterity){
		this.level = level;
		this.strength = strength;
		this.dexterity = dexterity;
		hitPoints = Dice.d10Roll() + (constitution * this.level);
		armorClass = this.dexterity + 0;
		
	}
	/**
	 * 
	 * @param fighter
	 */
	public static void saveFighter(Fighter fighter){
		FighterModel fModel = new FighterModel();
		
		String dateString = new Date().toLocaleString();
		dateString = dateString + fighter.name;
		fModel.fightersHM.put(dateString, fighter);
	}
	/**
	 * 
	 * @param fighter
	 */
	public static void deleteFighter(Fighter fighter){
	}
	/**
	 * 
	 * @return
	 */
	public String getName(){
		return name;
	}
	/**
	 * 
	 * @return
	 */
	public int getLevel(){
		return level;
	}
	/**
	 * 
	 * @return
	 */
	public int getStrength(){
		return strength;
	}
	/**
	 * 
	 * @return
	 */
	public int getDexterity(){
		return dexterity;
	}
	/**
	 * 
	 * @return
	 */
	public int getConstitution(){
		return constitution;
	}
	/**
	 * 
	 * @return
	 */
	public int getIntelligence(){
		return intelligence;
	}
	/**
	 * 
	 * @return
	 */
	public int getWisdom(){
		return wisdom;
	}
	/**
	 * 
	 * @return
	 */
	public int getCharisma(){
		return charisma;
	}	
	/**
	 * 
	 * @return
	 */
	public int getGainedStrength(){
		return gainedStrength;
	}
	/**
	 * 
	 * @return
	 */
	public int getGainedDexterity(){
		return gainedDexterity;
	}
	/**
	 * 
	 * @return
	 */
	public int getGainedConstitution(){
		return gainedConstitution;
	}
	/**
	 * 
	 * @return
	 */
	public int getGainedIntelligence(){
		return gainedIntelligence;
	}
	/**
	 * 
	 * @return
	 */
	public int getGainedWisdom(){
		return gainedWisdom;
	}
	/**
	 * 
	 * @return
	 */
	public int getGainedCharisma(){
		return gainedCharisma;
	}
	/**
	 * 
	 * @return
	 */
	public int getGainedArmorClass(){
		return gainedArmorClass;
	}
	/**
	 * 
	 * @param inputValue
	 * @return
	 */
	public int getModifier(int inputValue){
		return (inputValue/ 2 - 5);
	}
	/**
	 * 
	 * @return
	 */
	public int getAttackBonus(){
		int baseAttackBonus = 0;
		if (level <= 5 && level >= 0){
			baseAttackBonus = level;			
		} else if (level <= 10 && level > 5){
			baseAttackBonus = level * 2 - 5;
		} else if (level <= 15 && level > 11){
			baseAttackBonus = level * 3 - 15;
		} else if (level <= 20 && level >= 15){
			baseAttackBonus = level * 4 - 30;
		} else if (level > 20){
			baseAttackBonus = 50;
		}
		attackBonus = baseAttackBonus + getDamageBonus();
		return attackBonus;
	}
	/**
	 * 
	 * @return
	 */
	public int getDamageBonus(){
		damageBonus = getModifier(getStrength() + getGainedStrength());
		return damageBonus;
	}
	/**
	 * 
	 * @return
	 */
	public int getArmorClass(){
		armorClass = getDexterity() + getGainedDexterity() + 10;
		return armorClass;
	}
	/**
	 * 
	 * @return
	 */
	public int getHitPoints(){
		return hitPoints;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<BaseItem> getBackpack(){
		return backpack;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<BaseItem> getWorn(){
		return wornItems;
	}
	/**
	 * 
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 
	 * @param level
	 */
	public void setLevel(int level){
		this.level = level;
	}
	/**
	 * 
	 * @param strength
	 */

	public void setStrength(int strength){
		this.strength = strength;
	}
	/**
	 * 
	 * @param dexterity
	 */
	public void setDexterity(int dexterity){
		this.dexterity = dexterity;
	}
	/**
	 * 
	 * @param constitution
	 */
	public void setConstitution(int constitution){
		this.constitution = constitution;
	}
	/**
	 * 
	 * @param intelligence
	 */
	public void setIntelligence(int intelligence){
		this.intelligence = intelligence;
	}
	/**
	 * 
	 * @param wisdom
	 */
	public void setWisdom(int wisdom){
		this.wisdom = wisdom;
	}
	/**
	 * 
	 * @param charisma
	 */
	public void setCharisma(int charisma){
		this.charisma = charisma;
	}
	/**
	 * 
	 * @param strength
	 */
	public void setGainedStrength(int strength){
		this.gainedStrength = strength;
	}
	/**
	 * 
	 * @param dexterity
	 */
	public void setGainedDexterity(int dexterity){
		this.gainedDexterity = dexterity;
	}
	/**
	 * 
	 * @param constitution
	 */
	public void setGainedConstitution(int constitution){
		this.gainedConstitution = constitution;
	}
	/**
	 * 
	 * @param intelligence
	 */
	public void setGainedIntelligence(int intelligence){
		this.gainedIntelligence = intelligence;
	}
	/**
	 * 
	 * @param wisdom
	 */
	public void setGainedWisdom(int wisdom){
		this.gainedWisdom = wisdom;
	}
	/**
	 * 
	 * @param charisma
	 */
	public void setGainedCharisma(int charisma){
		this.gainedCharisma = charisma;
	}
	
	/**
	 * 
	 * @param hitpoints
	 */
	public void setHitpoints(int hitpoints){
		this.hitPoints = hitpoints;
	}
	
	/**
	 * 
	 * @param ac
	 */

	public void setGainedArmorClass(int ac){
		this.gainedArmorClass = ac;
	}
	
	/**
	 * 
	 * @param backpack
	 */
	public void setBackpack(ArrayList<BaseItem> backpack){
		this.backpack = backpack;
	}
	
	/**
	 * 
	 * @param worn
	 */
	public void setWorn(ArrayList<BaseItem> worn){
		this.wornItems = worn;
	}
	
	/**
	 * 
	 * @param wearingType
	 */
	public void setEquipOn(String wearingType) {
		if (wearingType.equals(BaseItem.HELMET)){
			isHelmetOn = true;
		} else if (wearingType.equals(BaseItem.ARMOR)){
			isArmorOn = true;
		}else if (wearingType.equals(BaseItem.SHIELD)){
			IsShieldOn = true;
		}else if (wearingType.equals(BaseItem.WEAPON)){
			isWeaponOn = true;
		}else if (wearingType.equals(BaseItem.BOOTS)){
			isBootsOn = true;
		}else if (wearingType.equals(BaseItem.RING)){
			isRingOn = true;
		}else if (wearingType.equals(BaseItem.BELT)){
			isBeltOn = true;
		}
	}
	
	/**
	 * 
	 * @param wearingType
	 */
	public void setEquipOff(String wearingType) {
		if (wearingType.equals(BaseItem.HELMET)){
			isHelmetOn = false;
		} else if (wearingType.equals(BaseItem.ARMOR)){
			isArmorOn = false;
		}else if (wearingType.equals(BaseItem.SHIELD)){
			IsShieldOn = false;
		}else if (wearingType.equals(BaseItem.WEAPON)){
			isWeaponOn = false;
		}else if (wearingType.equals(BaseItem.BOOTS)){
			isBootsOn = false;
		}else if (wearingType.equals(BaseItem.RING)){
			isRingOn = false;
		}else if (wearingType.equals(BaseItem.BELT)){
			isBeltOn = false;
		}
	}
	
	/**
	 * 
	 * @param increase
	 * @param bonus
	 * @param string
	 */
	public void gainBonus(String increase, int bonus, String string) {
		if (string.equals("+")){
			if (increase.equals(Ability.STRENGTH)){
				strength = strength + bonus;
			} else if (increase.equals(Ability.DEXTERITY)){
				dexterity = dexterity + bonus;
			} else if (increase.equals(Ability.CONSTITUTION)){
				constitution = constitution + bonus;
			} else if (increase.equals(Ability.INTELLIGENCE)){
				intelligence = intelligence + bonus;
			} else if (increase.equals(Ability.WISDOM)){
				wisdom = wisdom + bonus;
			} else if (increase.equals(Ability.CHARISMA)){
				charisma = charisma + bonus;
			}			
		} else if (string.equals("-")){
			if (increase.equals(Ability.STRENGTH)){
				strength = strength - bonus;
			} else if (increase.equals(Ability.DEXTERITY)){
				dexterity = dexterity - bonus;
			} else if (increase.equals(Ability.CONSTITUTION)){
				constitution = constitution - bonus;
			} else if (increase.equals(Ability.INTELLIGENCE)){
				intelligence = intelligence - bonus;
			} else if (increase.equals(Ability.WISDOM)){
				wisdom = wisdom - bonus;
			} else if (increase.equals(Ability.CHARISMA)){
				charisma = charisma - bonus;
			}			
		}
	}
}
