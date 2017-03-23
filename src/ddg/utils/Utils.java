package ddg.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import ddg.Config;
import ddg.model.Fighter;
import ddg.model.FighterModel;
/**
 * This class include some static method which often used
 * 
 * @author Zhen Du, Fei Yu
 * @date Feb 5, 2017
 */
public class Utils {
	
	/**
	 * 
	 * This method convert entity data type to json style
	 * 
	 * @param o
	 * @return String json strong
	 */
	public static String toJson(Object o) {
		Gson gson = new Gson();
		return gson.toJson(o);
	}
	
	/**
	 * 
	 * This method convert json value to Object
	 * 
	 * @param g for json value
	 * @param type Object type
	 * @return type of Object
	 */
	public static <T> T fromJson(String g, Class<T> type) {
		Gson gson = new Gson();
		return gson.fromJson(g, type);
	}
	
	/**
	 * 
	 * This method save String the file g
	 * 
	 * @param f file name
	 * @param g value for save
	 */
	public static void save2File(String f, String g) {
		FileWriter fw;
		try {
			fw = new FileWriter(f, false);
			fw.write(g);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * This method read the file
	 * 
	 * @param f file name
	 * @return String
	 */
	public static String readFile(String f) {
		String lines = "";
		try {
			File file = new File(f);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					lines+=line;
				}
				read.close();
			} else {
				System.out.println("can not find the file!");
			}
		} catch (Exception e) {
			System.out.println("unknow error when open file!");
			e.printStackTrace();
		}
		return lines;
	}
	
	/**
	 * 
	 * This method is get a radom value <= max
	 * 
	 * @param max the max value
	 * @return int radom value
	 */
	public static int getRadom(int max) {
		Random r = new Random();
		return r.nextInt(max);
	}

	/**
	 * This method displays the character's equipment information
	 * @param fighter The character
	 */
	public static void displayFighterInfo(Fighter fighter) {
		System.out.println("===========================");
		System.out.println("fighter is at " + fighter);
		System.out.println("name: " + fighter.getName());
		System.out.println("backpack " + fighter.getBackpack().size() + " worn " + fighter.getWorn().size());
		System.out.println("Strength = " + fighter.getTotalStrength());
		System.out.print(fighter.isHelmetOn());
		System.out.print(" ");
		System.out.print(fighter.isArmorOn());
		System.out.print(" ");
		System.out.print(fighter.isBeltOn());
		System.out.print(" ");
		System.out.print(fighter.isRingOn());
		System.out.print(" ");
		System.out.print(fighter.isBootsOn());
		System.out.print(" ");
		System.out.print(fighter.isWeaponOn());
		System.out.print(" ");
		System.out.print(fighter.isShieldOn());
		System.out.println(fighter.getBackpack());
		System.out.println(fighter.getWorn());
		System.out.println("===========================");		
	}

	/**
	 * This method is to save a Fighter object to the file. Put the object in to a hashmap, then put
	 * the hashmap into a model object, then save the model to the file.
	 * @param fighter the character
	 */
	public static void saveFighter(Fighter fighter) {
		FighterModel fm = new FighterModel();
		String g = Utils.readFile(Config.CHARACTER_FILE);
		fm = Utils.fromJson(g, FighterModel.class);

		HashMap<String, Fighter> hm1 = new HashMap<>();
//		if (fm != null) {
			System.out.println(fm);
			try {
				System.out.println("2   " + fm);
				if (null != fm.getFighters()) {
					hm1 = fm.getFighters();
					Set<String> keySet1 = hm1.keySet();
					Iterator<String> it1 = keySet1.iterator();

					while (it1.hasNext()) {
						String keyName = it1.next();
						if (keyName == fighter.getName()) {
							JOptionPane.showMessageDialog(null, "There already exists a character with this name, please change a name.", "Warning",
									JOptionPane.WARNING_MESSAGE);												
						}
					}
				} else {
					hm1.put(fighter.getName(), fighter);
					fm.setFighters(hm1);

					String gSave = Utils.toJson(fm);
					Utils.save2File(Config.CHARACTER_FILE, gSave);					
				}
				
			} catch (NullPointerException ex) {
				System.out.println("there is a NullPointerException");
			}
//		}
		
	}

	/**
	 * This method is to read Fighter objects from the file and put them to a hashmap structure
	 * @param jlistModel
	 * @param hm The HashMap of character list
	 * @return hm The HashMap of character list
	 */
	public static HashMap<String, Fighter> updateFighterList(DefaultListModel<String> jlistModel, HashMap<String, Fighter> hm) {
        FighterModel fm = new FighterModel();
        
		String g = Utils.readFile(Config.CHARACTER_FILE);
		fm = Utils.fromJson(g, FighterModel.class);
//		if(fm != null){        			
    		System.out.println(fm);
    		try{
    			System.out.println("2"+fm);
        		if( null!=fm.getFighters() ){
                    hm = fm.getFighters();
                    Set<String> keySet1 = hm.keySet();
                    Iterator<String> it1 = keySet1.iterator();
                    
                    while(it1.hasNext()){
                    	String keyName = it1.next();
                        jlistModel.addElement(keyName);
                    }
        		}
    		}
    		catch (NullPointerException ex){
    			System.out.println("there is a NullPointerException");
    		}        			
//		}
		return hm;
		
	}
}
