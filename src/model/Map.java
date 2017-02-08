package model;

import java.util.List;

import ddg.Config;
import ddg.utils.Utils;


/**
 * 
 * @author Bo
 * @date Feb 6,2017
 *
 * model for Map
 *
 */
public class Map {
	// record the location of things in the map
	private char[][] location;

	
	
	// recording the status of player.
//	private Character player;
	// recording the status of all monsters
//	private List<Charactor> monster;
	
	public Map(){
		this.location = new char[Config.MAP_SIZE][Config.MAP_SIZE];
	}
	public Map(char[][] loca){
		this.location = new char[Config.MAP_SIZE][Config.MAP_SIZE];
		this.location = loca;
	}
	
	
	public char[][] getLocation() {
		return location;
	}

	public void setLocation(char[][] location) {
		this.location = location;
	}
	
	static public void savemap(Map m){
		Utils.save2File(Config.MAP_FILE, Utils.toJson(m));
	}
}
