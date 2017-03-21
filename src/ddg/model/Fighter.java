package ddg.model;
import java.io.Serializable;
import java.util.*;

import javax.swing.JOptionPane;

import ddg.Config;
import ddg.utils.Dice;
import ddg.utils.Utils;
import ddg.item.entity.*;
import ddg.map.entity.Chest;


/**
 * 
 * 
 * This class define a character's features and actions
 * 
 * @author Fei Yu
 * @date Mar 3, 2017
 */
public class Fighter extends Observable implements Cloneable, Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int level = 1;
	private boolean isalive = true;
	
	private int strength;
	private int dexterity;
	private int constitution;
	private int intelligence;
	private int wisdom;
	private int charisma;
	private int armorClass;
	
	private int gainedStrength;
	private int gainedDexterity ;
	private int gainedConstitution;
	private int gainedIntelligence ;
	private int gainedWisdom ;
	private int gainedCharisma ;
	private int gainedArmorClass ;
	
	private int totalStrength;
	private int totalDexterity;
	private int totalConstitution;
	private int totalIntelligence;
	private int totalWisdom;
	private int totalCharisma;
	private int totalArmorClass;
	
	private int hitPoints; 
	private int attackBonus; 		
	private int damageBonus; 	
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
	
	
	/**
	 * Constructor
	 */
	public Fighter(){
		
	}
	/**
	 * Constructor
	 * @param level
	 * @param strength
	 * @param dexterity
	 */
	public Fighter(int level, int strength, int dexterity){
		this.level = level;
		this.strength = strength;
		this.dexterity = dexterity;
		hitPoints = Dice.d10Roll() + (constitution * this.level);
		armorClass = this.dexterity + 0;		
	}
	
	/**
	 * Save a character to the file
	 * @param fighter
	 */
	public static void saveFighter(Fighter fighter){
		FighterModel fModel = new FighterModel();
		
		String dateString = new Date().toLocaleString();
		dateString = dateString + fighter.name;
		fModel.fightersHM.put(dateString, fighter);
	}
	/**
	 * Delete a character from the file
	 * @param fighter
	 */
	public static void deleteFighter(Fighter fighter){
	}
	/**
	 * Get the name of the character
	 * @return
	 */
	public String getName(){
		return name;
	}
	/**
	 * Get the level of the character
	 * @return level
	 */
	public int getLevel(){
		return level;
	}
	/**
	 * Get the strength of the character
	 * @return strength
	 */
	public int getStrength(){
		return strength;
	}
	/**
	 * Get the dexterity of the character
	 * @return dexterity
	 */
	public int getDexterity(){
		return dexterity;
	}
	/**
	 * Get the constitution of the character
	 * @return constitution
	 */
	public int getConstitution(){
		return constitution;
	}
	/**
	 * Get the constitution of the character
	 * @return constitution
	 */
	public int getIntelligence(){
		return intelligence;
	}
	/**
	 * Get the wisdom of the character
	 * @return wisdom
	 */
	public int getWisdom(){
		return wisdom;
	}
	/**
	 * Get the charisma of the character
	 * @return charisma
	 */
	public int getCharisma(){
		return charisma;
	}	
	/**
	 * Get the gainedStrength of the character
	 * @return gainedStrength
	 */
	public int getGainedStrength(){
		return gainedStrength;
	}
	/**
	 * Get the gainedDexterity of the character
	 * @return gainedDexterity
	 */
	public int getGainedDexterity(){
		return gainedDexterity;
	}
	/**
	 * Get the gainedConstitution of the character
	 * @return gainedConstitution
	 */
	public int getGainedConstitution(){
		return gainedConstitution;
	}
	/**
	 * Get the gainedIntelligence of the character
	 * @return gainedIntelligence
	 */
	public int getGainedIntelligence(){
		return gainedIntelligence;
	}
	/**
	 * Get the gainedWisdom of the character
	 * @return gainedWisdom
	 */
	public int getGainedWisdom(){
		return gainedWisdom;
	}
	/**
	 * Get the gainedCharisma of the character
	 * @return gainedCharisma
	 */
	public int getGainedCharisma(){
		return gainedCharisma;
	}
	/**
	 * Get the gainedArmorClass of the character
	 * @return gainedArmorClass
	 */
	public int getGainedArmorClass(){
		return gainedArmorClass;
	}
	/**
	 * Return the total Strength of the character
	 * @return totalStrength
	 */
	public int getTotalStrength(){
		totalStrength = strength + gainedStrength;
		return totalStrength;
	}
	
	/**
	 * Return the total Dexterity of the character
	 * @return totalDexterity
	 */
	public int getTotalDexterity(){
		totalDexterity = dexterity + gainedDexterity;
		return totalDexterity;
	}
	
	/**
	 * Return the total Constitution of the character
	 * @return totalConstitution
	 */
	public int getTotalConstitution(){
		totalConstitution = constitution + gainedConstitution;
		return totalConstitution;
	}
	
	/**
	 * Return the total Intelligence of the character
	 * @return totalIntelligence
	 */
	public int getTotalIntelligence(){
		totalIntelligence = intelligence + gainedIntelligence;
		return totalIntelligence;
	}
	
	/**
	 * Return the total Wisdom of the character
	 * @return totalWisdom
	 */
	public int getTotalWisdom(){
		totalWisdom = wisdom + gainedWisdom;
		return totalWisdom;
	}
	
	/**
	 * Return the total Charisma of the character
	 * @return totalCharisma
	 */
	public int getTotalCharisma(){
		totalCharisma = charisma + gainedCharisma;
		return totalCharisma;
	}
	
	/**
	 * Return the total ArmorClass of the character
	 * @return
	 */
	public int getTotalArmorClass(){
		totalArmorClass = armorClass + gainedArmorClass;
		return totalArmorClass;
	}
	
	/**
	 * Get the Modifier of the character
	 * @param inputValue
	 * @return Modifier
	 */
	public int getModifier(int inputValue){
		return (inputValue/ 2 - 5);
	}
	/**
	 * Get the attack bonus of the character
	 * @return attackBonus
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
	 * Get the damage bonus of the character
	 * @return damageBonus
	 */
	public int getDamageBonus(){
		damageBonus = getModifier(getStrength() + getGainedStrength());
		return damageBonus;
	}
	/**
	 * Get the armor class of the character
	 * @return armorClass
	 */
	public int getArmorClass(){
		armorClass = getDexterity() + getGainedDexterity() + 10;
		return armorClass + gainedArmorClass;
	}
	/**
	 * Get the hitPoints of the character
	 * @return hitPoints
	 */
	public int getHitPoints(){
		return hitPoints;
	}
	/**
	 * Get the backpack of the character
	 * @return backpack
	 */
	public ArrayList<BaseItem> getBackpack(){
		return backpack;
	}
	/**
	 * Get the wornItems of the character
	 * @return wornItems
	 */
	public ArrayList<BaseItem> getWorn(){
		return wornItems;
	}
	/**
	 * Set the name of the character
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * Set the level of the character
	 * @param level
	 */
	public void setLevel(int level){
		this.level = level;
	}
	/**
	 * Set the strength of the character
	 * @param strength
	 */

	public void setStrength(int strength){
		this.strength = strength;
	}
	/**
	 * Set the dexterity of the character
	 * @param dexterity
	 */
	public void setDexterity(int dexterity){
		this.dexterity = dexterity;
	}
	/**
	 * Set the constitution of the character
	 * @param constitution
	 */
	public void setConstitution(int constitution){
		this.constitution = constitution;
	}
	/**
	 * Set the intelligence of the character
	 * @param intelligence
	 */
	public void setIntelligence(int intelligence){
		this.intelligence = intelligence;
	}
	/**
	 * Set the wisdom of the character
	 * @param wisdom
	 */
	public void setWisdom(int wisdom){
		this.wisdom = wisdom;
	}
	/**
	 * Set the charisma of the character
	 * @param charisma
	 */
	public void setCharisma(int charisma){
		this.charisma = charisma;
	}
	/**
	 * Set the gainedStrength of the character
	 * @param strength
	 */
	public void setGainedStrength(int strength){
		this.gainedStrength = strength;
	}
	/**
	 * Set the gainedDexterity of the character
	 * @param dexterity
	 */
	public void setGainedDexterity(int dexterity){
		this.gainedDexterity = dexterity;
	}
	/**
	 * Set the gainedConstitution of the character
	 * @param constitution
	 */
	public void setGainedConstitution(int constitution){
		this.gainedConstitution = constitution;
	}
	/**
	 * Set the gainedIntelligence of the character
	 * @param intelligence
	 */
	public void setGainedIntelligence(int intelligence){
		this.gainedIntelligence = intelligence;
	}
	/**
	 * Set the gainedWisdom of the character
	 * @param wisdom
	 */
	public void setGainedWisdom(int wisdom){
		this.gainedWisdom = wisdom;
	}
	/**
	 * Set the gainedCharisma of the character
	 * @param charisma
	 */
	public void setGainedCharisma(int charisma){
		this.gainedCharisma = charisma;
	}
	
	/**
	 * Set the hitPoints of the character
	 * @param hitpoints
	 */
	public void setHitpoints(int hitpoints){
		this.hitPoints = hitpoints;
	}
	
	/**
	 * Set the gainedArmorClass of the character
	 * @param ac
	 */

	public void setGainedArmorClass(int ac){
		this.gainedArmorClass = ac;
	}
	
	/**
	 * Set the backpack of the character
	 * @param backpack
	 */
	public void setBackpack(ArrayList<BaseItem> backpack){
		this.backpack = backpack;
	}
	
	/**
	 * Set the wornItems of the character
	 * @param worn
	 */
	public void setWorn(ArrayList<BaseItem> worn){
		this.wornItems = worn;
	}
	
	/**
	 * Set the character is wearing a type of equipment
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
	 * Set the character is not wearing a type of equipment
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
	 * Set the bonus attributes of the character
	 * @param increase
	 * @param bonus
	 * @param string
	 */
	public void gainBonus(String increase, int bonus, String string) {
		if (string.equals("+")){
			if (increase.equals(Ability.STRENGTH)){
				setGainedStrength(gainedStrength + bonus);
			} else if (increase.equals(Ability.DEXTERITY)){
				setGainedDexterity(gainedDexterity + bonus);
			} else if (increase.equals(Ability.CONSTITUTION)){
				setGainedConstitution(gainedConstitution + bonus);
			} else if (increase.equals(Ability.INTELLIGENCE)){
				setGainedIntelligence(gainedIntelligence + bonus);
			} else if (increase.equals(Ability.WISDOM)){
				setGainedWisdom(gainedWisdom + bonus);
			} else if (increase.equals(Ability.CHARISMA)){
				setGainedCharisma(gainedCharisma + bonus);
			} else if (increase.equals(Ability.ARMOR_CLASS)){
				setGainedArmorClass(gainedArmorClass + bonus);
			}			
		} else if (string.equals("-")){
			if (increase.equals(Ability.STRENGTH)){
				setGainedStrength(gainedStrength - bonus);
			} else if (increase.equals(Ability.DEXTERITY)){
				setGainedDexterity(gainedDexterity - bonus);
			} else if (increase.equals(Ability.CONSTITUTION)){
				setGainedConstitution(gainedConstitution - bonus);
			} else if (increase.equals(Ability.INTELLIGENCE)){
				setGainedIntelligence(gainedIntelligence - bonus);
			} else if (increase.equals(Ability.WISDOM)){
				setGainedWisdom(gainedWisdom - bonus);
			} else if (increase.equals(Ability.CHARISMA)){
				setGainedCharisma(gainedCharisma - bonus);
			} else if (increase.equals(Ability.ARMOR_CLASS)){
				setGainedArmorClass(gainedArmorClass - bonus);
			}			
		}
	}
	
//	public boolean wearItem(BaseItem i) {
//		Iterator<BaseItem> iterator = getWorn().iterator();
//		while(iterator.hasNext()) {
//			BaseItem next = iterator.next();
//			if(next.getName().equals(i.getName())) {
//				gainBonus(next.getIncrease(), next.getBonus(), "-");
//				iterator.remove();
//			}
//		}
//
//		gainBonus(i.getIncrease(), i.getBonus(), "+");
//		getWorn().add(i);
//		
//		return true;
//    }
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public boolean wearItem(BaseItem i) {

		for (BaseItem item: this.getWorn()){
			if (item.getName().equals(i.getName())){
				this.gainBonus(item.getIncrease(), item.getBonus(), "-");
				this.getBackpack().add(item);
				this.getWorn().remove(item);
				System.out.println("backpack=========" + getBackpack());  				
			}
		}
		this.gainBonus(i.getIncrease(), i.getBonus(), "+");
		this.getBackpack().remove(i);
		this.getWorn().add(i);
		this.setEquipOn(i.getName());
		JOptionPane.showMessageDialog(null, "The item is worn.", "Message", JOptionPane.WARNING_MESSAGE); 
		
		return true;
    }
	
	public void openChest(Chest chest) {
		if(this.backpack.size()<10){
			backpack.add(chest.getItem());
			chest.becameEmpty();
		}
		else{
			// can add inform on Textarea
			return;
		}
	}
	public void attackCaracter(Fighter npc) {
//		int harm = ;
// here 15 should be the harm values for npc
		npc.beAttacked(15);
	}
	public void beAttacked(int i) {
		this.hitPoints -= i;
		if(this.hitPoints<=0){
			System.out.println("going dead!!!!!");
			die();
		}
		setChanged();
		notifyObservers(this);
	}
	public boolean isAlive() {
		return isalive;
	}
	private void die() {
		this.isalive = false;
	}
	public void levelUp() {
		this.level++;
		
	}
   /**
     * This method adapter fighter level to map
     * @param targetLevel
    */
	// scores values have to be changed
	public void updateLevel(int targetLevel){
		if(targetLevel != level){
			this.level = targetLevel;
		}
		System.out.println(level);
	}
	public Fighter clone(){
		Fighter newfighter = null;
		try{
			newfighter=(Fighter)super.clone();
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		return newfighter;
	}
	public void trade(Fighter fighter, BaseItem selectedBackPackItem, String selectedWorn) {
		if (selectedBackPackItem != null && selectedWorn != null){
			
		} else if (selectedBackPackItem == null && selectedWorn != null){
			
		} else if (selectedBackPackItem != null && selectedWorn == null){
			
		}
	}
	public void takeoffEquipment(String selectedWorn) {
		try{
			for (BaseItem i: this.getWorn()){
				if (i.getName().equals(selectedWorn)){
					this.gainBonus(i.getIncrease(), i.getBonus(), "-");
					this.getBackpack().add(i);
					this.getWorn().remove(i);
					this.setEquipOff(selectedWorn);
//					System.out.println("backpack=========" + fighter.getBackpack());  
					
				} else {
					JOptionPane.showMessageDialog(null, "The character is not wearing a " + selectedWorn.toLowerCase() + ".", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		catch (ConcurrentModificationException e1) {
			JOptionPane.showMessageDialog(null, "The equipment has been move to backpack!", "Message", JOptionPane.WARNING_MESSAGE);
		}
	}
}
