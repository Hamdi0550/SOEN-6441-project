package ddg.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Observable;

import javax.swing.JOptionPane;

import ddg.Config;
import ddg.IOwner;
import ddg.model.entity.Chest;
import ddg.model.item.BaseItem;
import ddg.model.item.Enchantment;
import ddg.model.item.Item;
import ddg.model.item.MagicWeaponItem;
import ddg.strategy.AgressiveStrategy;
import ddg.strategy.FriendlyStrategy;
import ddg.strategy.IMagicStrategy;
import ddg.strategy.IStrategy;
import ddg.strategy.IStrategy.TurnCallback;
import ddg.ui.view.MapPanelInGame;
import ddg.utils.Dice;
import ddg.utils.Utils;


/**
 * 
 * 
 * This class define a character's features and actions
 * 
 * @author Fei Yu
 * @date Mar 3, 2017
 */
public class Fighter extends Observable implements IOwner, Cloneable, Serializable{

	private static final long serialVersionUID = 7049746086056480628L;
	private String name;
	private int level = 1;
	private boolean isalive = true;
	private String type;
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
	private int gainedAttackBonus ;
	private int gainedDamageBonus ;
	
	private int totalStrength;
	private int totalDexterity;
	private int totalConstitution;
	private int totalIntelligence;
	private int totalWisdom;
	private int totalCharisma;
	private int totalArmorClass;
	private int totalAttackBonus ;
	private int totalDamageBonus ;

	private int dexModifier;
	private int strModifier;
	
	private int hitPoints; 
	private int attackBonus; 		
	private int damageBonus; 	
	private boolean isArmorOn = false;
	private boolean isShieldOn = false;
	private boolean isWeaponOn = false;
	private boolean isBootsOn = false;
	private boolean isRingOn = false;
	private boolean isBeltOn = false;
	private boolean isHelmetOn = false;
	private ArrayList<Item> backpack = new ArrayList<>();
	private ArrayList<Item> wornItems = new ArrayList<>();
	
	private IOwner owner;
	
	private IMagicStrategy[] magicStrategy = new IMagicStrategy[3];
	private IStrategy behaviorStrategy;
	
	public int xOfFighter = -1;
	public int yOfFighter = -1;
	
	public void setStrategy(IStrategy strategy) {
		behaviorStrategy = strategy;
	}
	public IStrategy getBehaviorStrategy(){
		return behaviorStrategy;
	}
	
	public IMagicStrategy[] getMagicStrategy(){
		return magicStrategy;
	}
	
	public void turn() {
		boolean contine = true;
		for(IMagicStrategy s : magicStrategy) {
			if(s!=null) {
				if(s.getTurns()==0) {
					s = null;
				} else {
					contine = s.enchantNext(this);
				}
			}
		}
		if(contine) {
			behaviorStrategy.turn();
		}
	}
	
	public void setMagicStrategy(IMagicStrategy strategy) {
		if(strategy == null || strategy.getIndex() < 0 || strategy.getIndex() >= magicStrategy.length)
			return;
		magicStrategy[strategy.getIndex()] = strategy;
	}
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
		hitPoints = (Dice.d10Roll() + getModifier(getTotalConstitution())) * this.level;
		armorClass = this.dexterity + 0;
	}
	
	/**
	 * This method clear backpack list
	 */
	public void clearBackpack() {
		backpack.clear();
		observerNotify();
	}
	
	/**
	 * This method clear list of worn items
	 */
	public void clearWornItems() {
		wornItems.clear();
		isArmorOn = false;
		isShieldOn = false;
		isWeaponOn = false;
		isBootsOn = false;
		isRingOn = false;
		isBeltOn = false;
		isHelmetOn = false;
		observerNotify();
	}
	
	/**
	 * Save a character to the file
	 * @param fighter
	 */
//	public static void saveFighter(Fighter fighter){
//		FighterModel fModel = new FighterModel();
//		
//		String dateString = new Date().toLocaleString();
//		dateString = dateString + fighter.name;
//		fModel.fightersHM.put(dateString, fighter);
//	}
	
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
	 * Get the type of the character
	 * @return type
	 */
	public String getType() {
		return type;
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
	 * Get the gainedAttackBonus of the character
	 * @return gainedAttackBonus
	 */
	public int getGainedAttackBonus(){
		return gainedAttackBonus;
	}
	
	/**
	 * Get the gainedDamageBonus of the character
	 * @return gainedDamageBonus
	 */
	public int getGaineDamageBonus(){
		return gainedDamageBonus;
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
		totalArmorClass = getArmorClass() + gainedArmorClass;
		return totalArmorClass;
	}
	
	/**
	 * Return the total Attack Bonus of the character
	 * @return totalAttackBonus
	 */
	public int getTotalAttackBonus() {
		totalAttackBonus = getAttackBonus() + gainedAttackBonus;
		return totalAttackBonus;
	}
	
	/**
	 * Return the total Damage Bonus of the character
	 * @return totaldDamageBonus
	 */
	public int getTotaldDamageBonus() {
		totalDamageBonus = getDamageBonus() + gainedDamageBonus;
		return totalDamageBonus;
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
//		for (Item wornItem : wornItems) {
//			if (wornItem.getName().equals("Weapon")){
//				return wornItem.getBonus();
//			}
//		}
//		return level;
		int attackBonus = 0;
		boolean hasWeapon = false;
		boolean isMelee = false;
		boolean isRanged = false;
		for (Item wornItem : wornItems) {
			if (wornItem instanceof MagicWeaponItem){
				hasWeapon = true;
				if (((MagicWeaponItem)wornItem).getWeaponType().equals("Melee")){
					isMelee = true;
				}else if(((MagicWeaponItem)wornItem).getWeaponType().equals("Ranged")){
					isRanged = true;
				}
			}
		}
		if (hasWeapon){
			System.out.println("This is a weapon");
			if (isMelee){
//				attackBonus = level +  getStrModifier();
				attackBonus = getStrModifier();
				MapPanelInGame.printLog(" attack bonus: Strength Modifier " + attackBonus);
				System.out.println("this weanpon is melee weapon attackBonus: "+attackBonus);
			}else if (isRanged){
//				attackBonus = level + getDexModifier();
				attackBonus = getDexModifier();
				MapPanelInGame.printLog(" attack bonus: Dexterity Modifier " + attackBonus);
				System.out.println("this weanpon is ranged weapon attackBonus: "+attackBonus);
			}
		} else {
			attackBonus = getStrModifier();
			MapPanelInGame.printLog(" attack bonus: Strength Modifier" + attackBonus);
		}
		return attackBonus;
	}
	
	/**
	 * Get the damage bonus of the character
	 * @return damageBonus
	 */
	public int getDamageBonus(){
		Dice dice = new Dice();
		int i = dice.d6Roll();
		MapPanelInGame.printLog(" Damage Bonus = d6 roll: " + i + " ");
		damageBonus = i + getStrModifier();
		MapPanelInGame.printLog(" + Strength Modifier " + getStrModifier() + " ");
		return damageBonus;
	}
	
	/**
	 * Get the armor class of the character
	 * @return armorClass
	 */
	public int getArmorClass(){
		armorClass = getDexModifier() + 10;
		MapPanelInGame.printLog("Dexterity modifier: " + getDexModifier() + " + 10 ");
		return armorClass;
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
	public ArrayList<Item> getBackpack(){
		return backpack;
	}
	
	/**
	 * Get the wornItems of the character
	 * @return wornItems
	 */
	public ArrayList<Item> getWorn(){
		return wornItems;
	}

	/**
	 * Get the dexModifier of the character
	 * @return dexModifier
	 */
	public int getDexModifier() {
		return getTotalDexterity() / 2 - 5;
	}

//	/**
//	 * set dexModifier
//	 * @param dexModifier
//	 */
//	public void setDexModifier(int dexModifier) {
//		this.dexModifier = dexModifier;
//	}

	/**
	 * Get the strModifier of the character
	 * @return strModifier
	 */
	public int getStrModifier() {
		return getTotalStrength() / 2 - 5;
	}	

//	/**
//	 * set StrModifier
//	 * @param strModifier
//	 */
//	public void setStrModifier(int strModifier) {
//		this.strModifier = strModifier;
//	}

	/**
	 * This method get the boolean value of isArmorOn
	 * @return isArmorOn Ture means the character is wearing an armor
	 */
	public boolean isArmorOn() {
		return isArmorOn;
	}
	
	/**
	 * This method get the boolean value of isShieldOn
	 * @return isShieldOn Ture means the character is wearing a shield
	 */
	public boolean isShieldOn() {
		return isShieldOn;
	}
	
	/**
	 * This method get the boolean value of isWeaponOn
	 * @return isWeaponOn Ture means the character is wearing a weapon
	 */
	public boolean isWeaponOn() {
		return isWeaponOn;
	}
	
	/**
	 * This method get the boolean value of isBootsOn
	 * @return isBootsOn Ture means the character is wearing boots
	 */
	public boolean isBootsOn() {
		return isBootsOn;
	}
	
	/**
	 * This method get the boolean value of isRingOn
	 * @return isRingOn Ture means the character is wearing a ring
	 */
	public boolean isRingOn() {
		return isRingOn;
	}
	
	/**
	 * This method get the boolean value of isBeltOn
	 * @return isBeltOn Ture means the character is wearing a belt
	 */
	public boolean isBeltOn() {
		return isBeltOn;
	}
	
	/**
	 * This method get the boolean value of isHelmetOn
	 * @return isHelmetOn Ture means the character is wearing a helmet
	 */
	public boolean isHelmetOn() {
		return isHelmetOn;
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
	 * Set the type of the character
	 * @param type the type of the character
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Set the strength of the character
	 * @param strength the strength of the character
	 */
	public void setStrength(int strength){
		this.strength = strength;
	}
	
	/**
	 * Set the dexterity of the character
	 * @param dexterity the dexterity of the character
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
	 * Set the gainedAttackBonus of the character
	 * @param gainedAttackBonus
	 */
	public void setGainedAttackBonus(int gainedAttackBonus){
		this.gainedAttackBonus = gainedAttackBonus;
	}	
	
	/**
	 * Set the gainedDamageBonus of the character
	 * @param gainedDamageBonus
	 */
	public void setGainedDamageBonus(int gainedDamageBonus){
		this.gainedDamageBonus = gainedDamageBonus;
	}
	
	/**
	 * Set the backpack of the character
	 * @param backpack
	 */
	public void setBackpack(ArrayList<Item> backpack){
		this.backpack = backpack;
	}
	
	/**
	 * Set the wornItems of the character
	 * @param worn
	 */
	public void setWorn(ArrayList<Item> worn){
		this.wornItems = worn;
	}

	/**
	 * This method set the value of isArmorOn
	 * @param isArmorOn the boolean value of isArmorOn
	 */
	public void setArmorOn(boolean isArmorOn) {
		this.isArmorOn = isArmorOn;
	}

	/**
	 * This method set the value of isShieldOn
	 * @param isShieldOn the boolean value of isShieldOn
	 */
	public void setShieldOn(boolean isShieldOn) {
		this.isShieldOn = isShieldOn;
	}

	/**
	 * This method set the value of isWeaponOn
	 * @param isWeaponOn the boolean value of isWeaponOn
	 */
	public void setWeaponOn(boolean isWeaponOn) {
		this.isWeaponOn = isWeaponOn;
	}

	/**
	 * This method set the value of isBootsOn
	 * @param isBootsOn the boolean value of isBootsOn
	 */
	public void setBootsOn(boolean isBootsOn) {
		this.isBootsOn = isBootsOn;
	}

	/**
	 * This method set the value of isRingOn
	 * @param isRingOn the boolean value of isRingOn
	 */
	public void setRingOn(boolean isRingOn) {
		this.isRingOn = isRingOn;
	}

	/**
	 * This method set the value of isBeltOn
	 * @param isBeltOn the boolean value of isBeltOn
	 */
	public void setBeltOn(boolean isBeltOn) {
		this.isBeltOn = isBeltOn;
	}

	/**
	 * This method set the value of isHelmetOn
	 * @param isHelmetOn the boolean value of isHelmetOn
	 */
	public void setHelmetOn(boolean isHelmetOn) {
		this.isHelmetOn = isHelmetOn;
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
		} else if (wearingType.equals(BaseItem.SHIELD)){
			isShieldOn = true;
		} else if (wearingType.equals(BaseItem.WEAPON)){
			isWeaponOn = true;
		} else if (wearingType.equals(BaseItem.BOOTS)){
			isBootsOn = true;
		} else if (wearingType.equals(BaseItem.RING)){
			isRingOn = true;
		} else if (wearingType.equals(BaseItem.BELT)){
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
		} else if (wearingType.equals(BaseItem.SHIELD)){
			isShieldOn = false;
		} else if (wearingType.equals(BaseItem.WEAPON)){
			isWeaponOn = false;
		} else if (wearingType.equals(BaseItem.BOOTS)){
			isBootsOn = false;
		} else if (wearingType.equals(BaseItem.RING)){
			isRingOn = false;
		} else if (wearingType.equals(BaseItem.BELT)){
			isBeltOn = false;
		}
	}
	
	/**
	 * Set the bonus attributes of the character
	 * @param increase
	 * @param bonus
	 * @param string
	 */
	public void updateGainedAttribute(String increase, int bonus, String string) {
		if (string.equals("+")){
			if (increase.equals(Enchantment.STRENGTH)){
				setGainedStrength(gainedStrength + bonus);
			} else if (increase.equals(Enchantment.DEXTERITY)){
				setGainedDexterity(gainedDexterity + bonus);
			} else if (increase.equals(Enchantment.CONSTITUTION)){
				setGainedConstitution(gainedConstitution + bonus);
			} else if (increase.equals(Enchantment.INTELLIGENCE)){
				setGainedIntelligence(gainedIntelligence + bonus);
			} else if (increase.equals(Enchantment.WISDOM)){
				setGainedWisdom(gainedWisdom + bonus);
			} else if (increase.equals(Enchantment.CHARISMA)){
				setGainedCharisma(gainedCharisma + bonus);
			} else if (increase.equals(Enchantment.ARMOR_CLASS)){
				setGainedArmorClass(gainedArmorClass + bonus);
			}			
		} else if (string.equals("-")){
			if (increase.equals(Enchantment.STRENGTH)){
				setGainedStrength(gainedStrength - bonus);
			} else if (increase.equals(Enchantment.DEXTERITY)){
				setGainedDexterity(gainedDexterity - bonus);
			} else if (increase.equals(Enchantment.CONSTITUTION)){
				setGainedConstitution(gainedConstitution - bonus);
			} else if (increase.equals(Enchantment.INTELLIGENCE)){
				setGainedIntelligence(gainedIntelligence - bonus);
			} else if (increase.equals(Enchantment.WISDOM)){
				setGainedWisdom(gainedWisdom - bonus);
			} else if (increase.equals(Enchantment.CHARISMA)){
				setGainedCharisma(gainedCharisma - bonus);
			} else if (increase.equals(Enchantment.ARMOR_CLASS)){
				setGainedArmorClass(gainedArmorClass - bonus);
			}			
		}
	}
	
	/**
	 * This method allows a character wear an item
	 * @param i
	 * @return
	 */
	public boolean wearItem(Item i) {
		return wearItem(i, true);
    }
	
	/**
	 * 
	 * This method allows a character wear an item
	 * 
	 * @param i
	 * @param show
	 * @return
	 */
	public boolean wearItem(Item i, boolean show) {
		for (Item item: this.getWorn()){
			if (item.getName().equals(i.getName())){
				this.updateGainedAttribute(item.getIncrease(), item.getBonus(), "-");
				this.getBackpack().add(item);
				this.getWorn().remove(item);
				System.out.println("backpack=========" + getBackpack());
				break;
			}
		}
		this.updateGainedAttribute(i.getIncrease(), i.getBonus(), "+");
		this.getBackpack().remove(i);
		this.getWorn().add(i);
		this.setEquipOn(i.getName());
		if(show)
			JOptionPane.showMessageDialog(null, "The item is worn.", "Message", JOptionPane.WARNING_MESSAGE); 
		observerNotify();
		return true;
	}
	
	/**
	 * This method allows a character open a chest
	 * @param chest Chest object
	 */
	public void openChest(Chest chest) {
		if(this.backpack.size()<Config.BACKPACK_SIZE){
			if(!chest.isEmpty()){
				Item i = chest.getItem();
				i.setOwner(this);
				backpack.add(i);
				System.out.println("get"+ chest.getItem().getOwner().getLevel()+"------bouns--"+chest.getItem().getBonus());
				chest.becameEmpty();
				observerNotify();
			}
			else
				return;
		}
		else{
			// can add inform on Textarea
			return;
		}
	}
	
	/**
	 * 
	  * This method loot all items from a corpse
	  * 
	  * @param corpse
	 */
	public void lootCorpseItems(Fighter corpse){
		if(corpse.backpack.size() > 0){
			for(Item item: corpse.backpack){
				item.setOwner(this);
				this.backpack.add(item);
				System.out.println(item.getOwner().getLevel()+"----get a item from corpse backpack items" + item.getName());
			}
			corpse.clearBackpack();
		}
		if(corpse.wornItems.size() > 0){
			for(Item item : corpse.wornItems){
				item.setOwner(this);
				this.backpack.add(item);
				System.out.println(item.getOwner().getLevel()+"---get a item from corpse worn items" + item.getName() );
			}
			corpse.clearWornItems();
		}
		observerNotify();
	}
	
	/**
	 * This method show a character being attacked
	 * @param npc the npc is attacked
	 */
	public void attackCaracter(Fighter npc) {
		int d20Roll = Dice.d20Roll();		
		int attackRoll =  d20Roll + this.getAttackBonus();
		String s = "Attack Roll = d20 roll: " + d20Roll + " + attack bonus of the character: " + this.getAttackBonus();		
		MapPanelInGame.printLog(s);
		if(npc.getBehaviorStrategy() instanceof FriendlyStrategy){
			Game game = ((FriendlyStrategy)npc.getBehaviorStrategy()).getGame();
			npc.setStrategy(new AgressiveStrategy(game, npc));
		}
		if(attackRoll >= npc.getArmorClass()){
			s = "Attack Roll: " + attackRoll + " >= Armor Class " + npc.getArmorClass() + ", Attack Success!";
			MapPanelInGame.printLog(s);
			System.out.println("Attack Success!");
			if(npc.beAttacked(this.getDamageBonus())) {
				for (Item wornItem : wornItems) {
					if (wornItem instanceof MagicWeaponItem){
						((MagicWeaponItem)wornItem).attack(npc);
						break;
					}
				}
			}
		} else {
			s = "Attack Roll: " + attackRoll + " < Armor Class " + npc.getArmorClass() + ", Attack Fail!";
			MapPanelInGame.printLog(s);
			System.out.println("Attack Fail!");
		}
	}

	/**
	 * call this function if the character is attacked
	 * @param i harm values that this character will be hurt
	 */
	public boolean beAttacked(int i) {
		this.hitPoints -= i;
		if(this.hitPoints<=0){
			MapPanelInGame.printLog(" HP <= 0, dead! ");
			System.out.println("going dead!!!!!");
			die();
		}
		observerNotify();
		return isAlive();
	}
	
	/**
	 * @return true if this character is  alive, else return false
	 */
	public boolean isAlive() {
		return isalive;
	}
	
	/**
	 * call this function if character's hit points down to 0
	 */
	public void die() {
		this.hitPoints=0;
		this.isalive = false;
		this.behaviorStrategy=null;
		observerNotify();
	}
	
	/**
	 * level up and add hit points
	 */
	public void levelUp() {
		this.level++;
		MapPanelInGame.printLog(" current HP: " + this.hitPoints + " + ");
		this.hitPoints = this.hitPoints + d10RollAndTimes(1);
		observerNotify();
	}
	
   /**
     * This method adapter fighter level to map
     * @param targetLevel
    */
	// scores values have to be changed
//	public void updateLevel(int targetLevel){
//		
//		for(BaseItem item : this.wornItems){
//			this.updateGainedAttribute(item.getIncrease(), item.getBonus(), "-");
//			item.updateLevel(targetLevel);
//			this.updateGainedAttribute(item.getIncrease(), item.getBonus(), "+");
//		}
//		
//		for(BaseItem item : this.backpack){
//			item.updateLevel(targetLevel);
//		}
//		
//		if(targetLevel != level){
//			this.hitPoints = this.hitPoints + d10RollAndTimes(targetLevel - this.level);
//			this.level = targetLevel;
//		}
//		
//	}
	
	/**
	 * Get the owner of the character
	 * @return owner
	 */
	public IOwner getOwner() {
		return this.owner;
	}
	
	/**
	 * Set the owner of the character
	 * @param owner
	 */
	public void setOwner(IOwner owner) {
		this.owner = owner;
		for(Item item : this.wornItems){
			this.updateGainedAttribute(item.getIncrease(), item.getBonus(), "-");
//			item.updateLevel(targetLevel);
			item.setOwner(this);
			this.updateGainedAttribute(item.getIncrease(), item.getBonus(), "+");
		}
		
		for(Item item : this.backpack){
//			item.updateLevel(targetLevel);
			item.setOwner(this);
			System.out.println(item.getOwner().getLevel());
		}
		
		if(owner.getLevel() != level){
			this.hitPoints = this.hitPoints + d10RollAndTimes(owner.getLevel() - this.level);
			this.level = owner.getLevel();
		}
	}
	
	/**
	 * This method caculates the hit points and return it
	 * @param times
	 * @return sum
	 */
	private int d10RollAndTimes(int times){
		int sum = 0;
		while(times>0){
			int i = Dice.d10Roll();
			sum += i + getModifier(getTotalConstitution());
			MapPanelInGame.printLog(" a d10 roll: " + i + " + constitution modifier: " + getModifier(getTotalConstitution()));
			times--;
		}
		return sum;
	}
	
	/**
	 * This method clones a Fighter object and return it
	 * @return newfighter
	 */
	public Fighter clone(){
		Fighter newfighter = null;
		try{
			newfighter=(Fighter)super.clone();
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		return newfighter;
	}
	
	/**
	 * This method allows a player trade with a npc
	 * @param playeritem Player's item
	 * @param npc NPC
	 * @param npcitem NPC's item
	 */
	public void trade(Item playeritem, Fighter npc, Item npcitem) {
		if (npc != null && npcitem != null && playeritem!=null){
			npc.backpack.remove(npcitem);
			npcitem.setOwner(this);
			this.backpack.add(npcitem);
			this.backpack.remove(playeritem);
			playeritem.setOwner(npc);
			npc.backpack.add(playeritem);	
		} else if (npc != null && npcitem == null && playeritem != null){
			this.backpack.remove(playeritem);
			playeritem.setOwner(npc);
			npc.backpack.add(playeritem);
		} else if (npc != null && npcitem != null && playeritem == null){
			npc.backpack.remove(npcitem);
			npcitem.setOwner(this);
			this.backpack.add(npcitem);
		}
		observerNotify();
	}
	
	/**
	 * This method allow the character move an item from worn to backpack
	 * @param selectedWorn
	 */
	public void takeoffEquipment(String selectedWorn) {
		if(this.getWorn().size()==0) {
			JOptionPane.showMessageDialog(null, "The character is not wearing a " + selectedWorn.toLowerCase() + ".", "Warning", JOptionPane.WARNING_MESSAGE);
		} else {
			try{
				Iterator<Item> it = this.getWorn().iterator();
				boolean wearing = false;
				while (it.hasNext()) {
					Item i = it.next();
					if (i.getName().equals(selectedWorn)){
						this.updateGainedAttribute(i.getIncrease(), i.getBonus(), "-");
						this.getBackpack().add(i);
						this.getWorn().remove(i);
						this.setEquipOff(selectedWorn);
						wearing = true;
						break;
					} else {
						wearing = false;
					}
				}
				if(!wearing) {
					JOptionPane.showMessageDialog(null, "The character is not wearing a " + selectedWorn.toLowerCase() + ".", "Warning", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "The equipment has been move to backpack!", "Message", JOptionPane.WARNING_MESSAGE);
					observerNotify();
				}
//				for (BaseItem i: this.getWorn()){
//					if (i.getName().equals(selectedWorn)){
//						this.gainBonus(i.getIncrease(), i.getBonus(), "-");
//						this.getBackpack().add(i);
//						this.getWorn().remove(i);
//						this.setEquipOff(selectedWorn);
////					System.out.println("backpack=========" + fighter.getBackpack());  
//						
//					} else {
//						JOptionPane.showMessageDialog(null, "The character is not wearing a " + selectedWorn.toLowerCase() + ".", "Warning", JOptionPane.WARNING_MESSAGE);
//					}
//				}
			}
			catch (ConcurrentModificationException e1) {
				JOptionPane.showMessageDialog(null, "The equipment has been move to backpack!", "Message", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	/**
	 * This method save a Fighter object to the file
	 * @param fighter The character
	 */
	public static void saveFighter(Fighter fighter){
		System.out.println("save fighter======1");
		
		FighterModel fm = Utils.readObject(Config.CHARACTER_FILE, FighterModel.class);
		if (fm == null) {
			fm = new FighterModel();
		}
		System.out.println("save fighter======2");
			
		if(fm.getFighters().containsKey(fighter.name)){
			fm.getFighters().put(fighter.name, fighter);
			Utils.saveObject(Config.CHARACTER_FILE, fm);
			System.out.println("save fighter======success");
		}
		else{
			System.out.println("wrong !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}
	
	/**
	 * This method refer to observerNotify method of Observerable class.
	 */
	private void observerNotify() {
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * This method lets the fighter equip the selected item
	 * @param wearingType Type of the item
	 * @param tempItem SelectedItem
	 */
	public void equip(String wearingType, Item tempItem) {    			
		try{
			for (Item i: getWorn()){
				if (i.getName().equals(wearingType)){
					updateGainedAttribute(i.getIncrease(), i.getBonus(), "-");
					wornItems.remove(i);
				}
			}
		}		
		catch (ConcurrentModificationException e1) {
			System.out.println("ConcurrentModificationException");
		}        			
		
		updateGainedAttribute(tempItem.getIncrease(), tempItem.getBonus(), "+");
		wornItems.add(tempItem);
		setEquipOn(wearingType);        
		
		observerNotify();
	}
	
	/**
	 * 
	 * This method get the item by its type
	 * 
	 * @param name type of the item
	 * @return i the item
	 */
	public Item getWearItemByName(String name) {
		Iterator<Item> it = this.getWorn().iterator();
		while (it.hasNext()) {
			Item i = it.next();
			if (i.getName().equals(name)){
				return i;
			}
		}
		return null;
	}
	
	/**
	 * Get the X coordinate of the character
	 * @return xOfFighter X coordinate of the character
	 */
	public int getXOfFighter() {
		return xOfFighter;
	}
	
	/**
	 * Get the Y coordinate of the character
	 * @return yOfFighter Y coordinate of the character
	 */
	public int getYOfFighter() {
		return yOfFighter;
	}
	
	/**
	 * Set the X coordinate of the character
	 * @param xOfFighter X coordinate of the character
	 */
	public void setXOfFighter(int xOfFighter) {
		this.xOfFighter = xOfFighter;
	}
	
	/**
	 * Set the Y coordinate of the character
	 * @param yOfFighter Y coordinate of the character
	 */
	public void setYOfFighter(int yOfFighter) {
		this.yOfFighter = yOfFighter;
	}
}
