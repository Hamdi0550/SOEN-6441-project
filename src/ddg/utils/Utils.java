package ddg.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
/**
 * This class include some static method which often used
 * 
 * @author Zhen Du
 * @date Feb 5, 2017
 */
public class Utils {
	
	public static String toJson(Object o) {
		Gson gson = new Gson();
		return gson.toJson(o);
	}
	
	public static <T> Object fromJson(String g, T t) {
		Gson gson = new Gson();
		return gson.fromJson(g, t.getClass());
	}
	
	public static void save2File(String f, String json) {
		FileWriter fw;
		try {
			fw = new FileWriter(f, false);
			fw.write(json);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readFileLines(String f) {
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
}
