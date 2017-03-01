package model;

import java.util.ArrayList;
import java.util.List;

import ddg.Config;
import ddg.model.MapEditorModel;
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
	private String name;
	private char[][] location;
	private Cell[][] cellsinthemap;
	private int row;
	private int column;
	
	
	// recording the status of player.
//	private Character player;
	// recording the status of all monsters
//	private List<Charactor> monster;
	
	public Map(){
		this.row = 10;
		this.column = 10;
		this.location = new char[row][column];
		this.cellsinthemap = new Cell[row][column];
		
	}

//	public Map(char[][] loca, Cell[][] cells){
//		this.height = 10;
//		this.width = 10;
//		this.location = new char[Config.MAP_SIZE][Config.MAP_SIZE];
//		this.location = loca;
//		this.cellsinthemap = new Cell[Config.MAP_SIZE][Config.MAP_SIZE];
//		this.cellsinthemap = cells;
//		
//	}

//	public Map(String name, char[][] location, Cell[][] cellsinthemap) {
//		this.name = name;
//		this.location = location;
//		this.cellsinthemap = cellsinthemap;
//		this.height = 10;
//		this.width = 10;
//	}
	
	public Map(String name, int height, int width){
		this.name = name;
		this.row = height;
		this.column = width;
		this.location = new char[height][width];
		this.cellsinthemap = new Cell[height][width];
		
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getColumn(){
		return this.column;
	}

	public char[][] getLocation() {
		return location;
	}

	public void setLocation(char[][] location) {
		this.location = location;
	}
	public void changeLocation(int x,int y, char c){
		this.location[x][y] = c;
	}
	/**
	 * 
	 * @param mapEditorModel detail location of map.
	 * @return true if the map have indoor, outdoor and can find a valid path to success.
	 */
	static public void savemap(MapEditorModel mapEditorModel){
		Utils.save2File(Config.MAP_FILE, Utils.toJson(mapEditorModel));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void changeCellsinthemap(int x,int y, Cell cell){
		this.cellsinthemap[x][y] = cell;
	}

}
