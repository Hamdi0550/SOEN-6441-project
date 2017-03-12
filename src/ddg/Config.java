package ddg;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import ddg.item.entity.BaseItem;
/**
 * This class include all the config data
 * 
 * @author Zhen Du, Fei Yu
 * @date Feb 5, 2017
 */
public class Config {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public static final int TOOLBAR_WIDTH = WIDTH-8;
	public static final int TOOLBAR_HEIGHT = HEIGHT/10;
	
	public static final int OPTION_WIDTH = WIDTH/5;
	public static final int OPTION_HEIGHT = HEIGHT;
	
	public static final int CENTER_WIDTH = WIDTH-2*OPTION_WIDTH;
	public static final int CENTER_HEIGHT = HEIGHT;
	
	public static final int BTN_WIDTH = WIDTH/6;
	public static final int BTN_HEIGHT = HEIGHT/18;
	
	public static final Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
			  new Color(45,92,162),
			  new Color(43,66,97),
			  new Color(45,92,162),
			  new Color(84,123,200));
	
	public static final String CHARACTOR_FILE = "./charactor.json";
	
	public static final String MAP_FILE = "./map.ser";

	public static final String CAMPAIGN_FILE = "./campaign.json";
	
	public static final String ITEM_FILE = "./item.json";
	
	public static final String GAME_FILE = "./game.json";
	
	public static final ImageIcon HELMET_ICON = new ImageIcon("res/Helmet.jpg");  
	public static final ImageIcon ARMOR_ICON = new ImageIcon("res/Armor.jpg");  
	public static final ImageIcon BOOTS_ICON = new ImageIcon("res/Boots.jpg");  
	public static final ImageIcon RING_ICON = new ImageIcon("res/Ring.jpg");  
	public static final ImageIcon SHIELD_ICON = new ImageIcon("res/Shield.jpg");  
	public static final ImageIcon WEAPON_ICON = new ImageIcon("res/Weapon.jpg");  
	public static final ImageIcon BELT_ICON = new ImageIcon("res/Belt.jpg"); 
	
	public static final String BULLY = "Bully";
	public static final String NIMBLE = "Nimble";
	public static final String TANK = "Tank";
	
	// set the size of map. it could be changed if click the S/M/L button  
	public static int  MAP_SIZE = 10;

	/**
	 * This method is to set icon of different equipment parts of a character
	 * @param wearingType
	 * @return item type based on different part
	 */
	public static Icon iconByType(String wearingType) {
		if (wearingType.equals(BaseItem.HELMET)){
			return HELMET_ICON;
		} else if (wearingType.equals(BaseItem.ARMOR)){
			return ARMOR_ICON;
		} else if (wearingType.equals(BaseItem.BOOTS)){
			return BOOTS_ICON;
		} else if (wearingType.equals(BaseItem.RING)){
			return RING_ICON;
		} else if (wearingType.equals(BaseItem.BELT)){
			return BELT_ICON;
		} else if (wearingType.equals(BaseItem.WEAPON)){
			return WEAPON_ICON;
		} else if (wearingType.equals(BaseItem.SHIELD)){
			return SHIELD_ICON;
		}
		return null;
	}
}
