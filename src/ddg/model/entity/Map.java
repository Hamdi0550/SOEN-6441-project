package ddg.model.entity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observable;

import ddg.Config;
import ddg.model.Fighter;
import ddg.model.MapEditorModel;
import ddg.utils.ValidationTool;


/**
 * 
 * @author Bo
 * @date Feb 6,2017
 *
 * model for Map
 *
 */
public class Map extends Observable implements IOwner, Cloneable, java.io.Serializable{
	private static final long serialVersionUID = -8908299320533978891L;
	
	// record the location of things in the map
	private String name;
	private char[][] location;
	private Cell<?>[][] cellsinthemap;
	private int row;
	private int column;
	private IOwner owner;
	
	// recording the status of player.
//	private Character player;
	// recording the status of all monsters
//	private List<Charactor> monster;
	
	public Map(){
		this.name ="Map1";
		this.row = 10;
		this.column = 10;
		this.location = new char[row][column];	// record the inform of location on the map
		this.cellsinthemap = new Cell[row][column];
		
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++)
				this.location[i][j] = 'f';
		}
		
	}

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
	 * Constructor create new Map from a exist map
	 * @param map the old map your would like to use
	 */
	public Map(Map map) {
		this.name = map.getName();
		this.row = map.getRow();
		this.column = map.getColumn();
		this.location = new char[row][column];
		this.cellsinthemap = new Cell[row][column];
		
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				this.location[i][j] = map.getLocation()[i][j];
				if(this.location[i][j] == 'c' || this.location[i][j] == 'e'){
					this.cellsinthemap[i][j]=new Cell<>(((Chest)map.getCellsinthemap()[i][j].getContent()).clone());
				}
				else if(this.location[i][j] == 'p' || this.location[i][j] == 'd'){
					if(map.getCellsinthemap()[i][j].getIsfriendly())
						this.cellsinthemap[i][j] = new Cell<>(((Fighter)map.getCellsinthemap()[i][j].getContent()).clone());
					else
						this.cellsinthemap[i][j] = new Cell<>(((Fighter)map.getCellsinthemap()[i][j].getContent()).clone(),false);
				}
			}
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
		setChanged();
		notifyObservers(this);
	}
	/**
	 * 
	 * @param mapEditorModel detail location of map.
	 * @return true if the map have indoor, outdoor and can find a valid path to success.
	 */
	static public void savemap(MapEditorModel mapEditorModel){
//		Utils.save2File(Config.MAP_FILE, Utils.toJson(mapEditorModel));
		try{
			FileOutputStream fileOut =
	        new FileOutputStream(Config.MAP_FILE);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(mapEditorModel);
	        out.close();
	        fileOut.close();
	        System.out.printf("Serialized data is saved");
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * @return name of the map
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return all cells in this map
	 */
	public Cell<?>[][] getCellsinthemap() {
		return cellsinthemap;
	}

	/**
	 * @param x row-coordinate of the location
	 * @param y	column-coordinate of the location
	 * @param cell	the cell which you would like to put in the map
	 */
	public void changeCellsinthemap(int x,int y, Cell<?> cell){
		this.cellsinthemap[x][y] = cell;
		setChanged();
		notifyObservers(this);
	}
	
	
	/**
	 * @param map map whose validation you would like to check
	 * @return	cell the same function on the ValidationTool, return true if validation is success, else return false.
	 */
	public boolean checkValidation(Map map){
		return new ValidationTool(map).checkValidation();
	}
	
//	/**
//	 * adapting all item and non-player Characters and Items basing on level of player
//	 * @param level	level of player
//	 */
//	public void adaptedLevel(int level){
//		System.out.println("adaptedLevel()!!!!!" + level);
//		for(int i=0; i<row ;i++){
//			for(int j=0; j<column ;j++){
//				if(location[i][j]=='p'){
//				 ((Fighter)cellsinthemap[i][j].getContent()).updateLevel(level);
//				}
//				else if(location[i][j]=='c'){
//					((Chest) cellsinthemap[i][j].getContent()).getItem().updateLevel(level);
//				}
//			}
//		}
//	}
	
	public void setOwner(IOwner l){
		this.owner = l;
		for(int i=0; i<row ;i++){
			for(int j=0; j<column ;j++){
				if(location[i][j]=='p'){
					((Fighter)cellsinthemap[i][j].getContent()).setOwner(owner);
				}
				else if(location[i][j]=='c'){
					((Chest) cellsinthemap[i][j].getContent()).getItem().setOwner(owner);
				}
			}
		}
	}

	public boolean npcMove(int xofcharactor, int yofcharactor, int newx, int newy) {
		if(newx>=getRow()||newx<0||newy>=getColumn()||newy<0)
			return false;
		char temp = getLocation()[newx][newy];
		if(temp =='f'){
			this.location[xofcharactor][yofcharactor]='f';
			this.location[newx][newy]='p';
			this.cellsinthemap[newx][newy]=this.cellsinthemap[xofcharactor][yofcharactor];
			this.cellsinthemap[xofcharactor][yofcharactor]=null;
			setChanged();
			notifyObservers(this);
			return true;
		}
		return false;
	}

	@Override
	public int getLevel() {
		return this.owner==null?0:this.owner.getLevel();
	}
}
