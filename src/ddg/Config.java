package ddg;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
/**
 * This class include all the config data
 * 
 * @author Zhen Du
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
	
	// set the size of map. it could be changed if click the S/M/L button  
	public static int  MAP_SIZE = 10;
}
