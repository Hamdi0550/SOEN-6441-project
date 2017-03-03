package ddg.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import com.google.gson.Gson;
/**
 * This class include some static method which often used
 * 
 * @author Zhen Du
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
}
