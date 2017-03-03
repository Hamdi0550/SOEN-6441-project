package ddg.model;
import java.io.Serializable;
import java.util.*;

import ddg.Config;
import ddg.utils.Dice;
import ddg.utils.Utils;
import ddg.item.entity.*;


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
	public int getConstitution(){
		return constitution;
	}
	public int getIntelligence(){
		return intelligence;
	}
	public int getWisdom(){
		return wisdom;
	}
	public int getCharisma(){
		return charisma;
	}
	public int getModifier(int inputValue){
		return (inputValue/ 2 - 5);
	}
	public ArrayList<BaseItem> getBackpack(){
		return backpack;
	}
	public ArrayList<BaseItem> getWorn(){
		return wornItems;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setLevel(int level){
		this.level = level;
	}

	public void addStrength(int strength){
		this.strength += strength;
	}
	public void addDexterity(int dexterity){
		this.dexterity += dexterity;
	}
	public void addConstitution(int constitution){
		this.constitution += constitution;
	}
	public void addIntelligence(int intelligence){
		this.intelligence += intelligence;
	}
	public void addWisdom(int wisdom){
		this.wisdom += wisdom;
	}
	public void addCharisma(int charisma){
		this.charisma += charisma;
	}
//	public void setStrength(int strength){
//		this.strength = strength;
//	}
//	public void setDexterity(int dexterity){
//		this.dexterity = dexterity;
//	}
//	public void setConstitution(int constitution){
//		this.constitution = constitution;
//	}
//	public void setIntelligence(int intelligence){
//		this.intelligence = intelligence;
//	}
//	public void setWisdom(int wisdom){
//		this.wisdom = wisdom;
//	}
//	public void setCharisma(int charisma){
//		this.charisma = charisma;
//	}
	public void setBackpack(ArrayList<BaseItem> backpack){
		this.backpack = backpack;
	}
	public void setWorn(ArrayList<BaseItem> worn){
		this.wornItems = worn;
	}

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
