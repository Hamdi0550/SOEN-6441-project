package model;

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
		this.location = new char[row][column];	// record the inform of location on the map
		this.cellsinthemap = new Cell[row][column];
		
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++)
				this.location[i][j] = 'f';
		}
		
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
	
	/**
	 * @param name	name of the map
	 * @param row	the number of row of the map
	 * @param column	the number of column of the map
	 */
	public Map(String name, int row, int column){
		this.name = name;
		this.row = row;
		this.column = column;
		this.location = new char[row][column];
		this.cellsinthemap = new Cell[row][column];
		
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++)
				this.location[i][j] = 'f';
		}
		
	}
	
	/** 
	 * @return get the number of the row of the map
	 */
	public int getRow(){
		return this.row;
	}
	/** 
	 * @return get the number of the column of the map
	 */
	public int getColumn(){
		return this.column;
	}

	
	/**
	 * @return return the detail location of the map
	 */
	public char[][] getLocation() {
		return location;
	}

	/**
	 * @param location 2-dimensional array of all location of the map
	 */
	public void setLocation(char[][] location) {
		this.location = location;
	}
	/**
	 * change content of one of the location on the map
	 * @param x row-coordinate of the location
	 * @param y column-coordinate of the location
	 * @param c the content you would like to change. 'c' = chest,'w'=wall, 'f'=floor, 'i' = indoor, 'o' = outdoor ...
	 */
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
	
	/**
	 * @return name of the map
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param x row-coordinate of the location
	 * @param y	column-coordinate of the location
	 * @param cell	the cell which you would like to put in the map
	 */
	public void changeCellsinthemap(int x,int y, Cell cell){
		this.cellsinthemap[x][y] = cell;
	}
	
	/**
	 * @param map map whose validation you would like to check
	 * @return	cell the same function on the ValidationTool, return true if validation is success, else return false.
	 */
	public boolean checkValidation(Map map){
		return new ValidationTool(map).checkValidation();
	}

}
