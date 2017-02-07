package model;

import java.util.List;

import ddg.Config;


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
//	private Charactor player;
	// recording the status of all monsters
//	private List<Charactor> monster;
	
	public Map(){
		this.location = new char[Config.MAP_SIZE][Config.MAP_SIZE];
		
	}
	
	
	public char[][] getLocation() {
		return location;
	}

	public void setLocation(char[][] location) {
		this.location = location;
	}
}
