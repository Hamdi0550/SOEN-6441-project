package ddg.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import com.google.gson.Gson;

import ddg.model.Fighter;
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
	 * @param fighter
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
}
