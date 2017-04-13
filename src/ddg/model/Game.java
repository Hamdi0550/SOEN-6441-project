package ddg.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ddg.Config;
import ddg.IOwner;
import ddg.model.entity.BaseCampaign;
import ddg.strategy.ComputerStrategy;
import ddg.strategy.HumanStrategy;
import ddg.strategy.IStrategy.TurnCallback;

/**
 * 
 * @author Bo
 * @date Apr 13, 2017
 * 
 */
public class Game implements IOwner, java.io.Serializable{
	private static final long serialVersionUID = -1424213104639818704L;
	
	private BaseCampaign campaign;
	private Map playingMap;
	private Fighter fighter;
	
	/**
	 * Constructor
	 * @param model	game model which contains fighter and campaign
	 * @param cb	callback attribute
	 */
	public Game(GameModel model, TurnCallback cb){
		this.fighter = model.getFighter().clone();
		this.campaign = model.getCampaign();
		this.fighter.setOwner(this.fighter);
 		if(playingMap==null){
 			initData();
 		}
 		else
 			initMapData();
		if(model.isComputer()) {
			this.fighter.setStrategy(new ComputerStrategy(this,cb));
		} else {
			this.fighter.setStrategy(new HumanStrategy());
		}
 	}
	
	/**
	 * initial data of this game
	 */
	private void initData(){
		if(campaign.getMaps().size()==0){
			playingMap=null;
			return;
		}
		
		try{
			//read maps files import maps
			FileInputStream fileIn = new FileInputStream(Config.MAP_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			MapEditorModel mapsmodel = (MapEditorModel) in.readObject();
			in.close();
			fileIn.close();
			
			if (mapsmodel == null) {
				mapsmodel = new MapEditorModel();
				mapsmodel.add(new Map());
				System.out.println("map files was wrong!!!");
				System.exit(0);
			}
			for(Map map : mapsmodel.getMaps()){
				if(map.getName().equals(campaign.getMaps().get(0))){
					this.playingMap = map;
					initMapData();
					break;
				}
			}
			
		}catch(IOException i){
			i.printStackTrace();
		}catch(ClassNotFoundException c){
			c.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * initial map data
	 */
	private void initMapData() {
		// TODO Auto-generated method stub
//		this.playingmap.adaptedLevel(fighter.getLevel());
		this.playingMap.setOwner(this);
		
		for(int i=0;i<playingMap.getRow();i++){
			for(int j=0;j<playingMap.getColumn();j++){
				if(playingMap.getLocation()[i][j] == 'i'){
					if(i>0 && playingMap.getLocation()[i-1][j]=='f'){
						 fighter.setXOfFighter(i-1);
						 fighter.setYOfFighter(j);
					}
					else if(i<playingMap.getRow()-1 && playingMap.getLocation()[i+1][j]=='f'){
						fighter.setXOfFighter(i+1);
						fighter.setYOfFighter(j);
					}
					else if(j>0 && playingMap.getLocation()[i][j-1]=='f'){
						fighter.setXOfFighter(i);
						fighter.setYOfFighter(j-1);
					}
					else if(j<playingMap.getColumn()-1 && playingMap.getLocation()[i][j+1]=='f'){
						fighter.setXOfFighter(i);
						fighter.setYOfFighter(j+1);
					}
				}
			}
		}
	}

	/**
	 * @return the x coordinate of character in this game
	 */
	public int getXofplayer() {
		return fighter.getXOfFighter();
	}

	/**
	 * @param xofplayer x coordinate of character user appoint
	 */
	public void setXofplayer(int xofplayer) {
		this.fighter.setXOfFighter(xofplayer);
	}

	/**
	 * @return the y coordinate of character in this game
	 */
	public int getYofplayer() {
		return fighter.getYOfFighter();
	}

	/**
	 * @param yofplayer y  coordinate of character user appoint
	 */
	public void setYofplayer(int yofplayer) {
		this.fighter.setYOfFighter(yofplayer);
	}

	/**
	 * @return	campaign of this game
	 */
	public BaseCampaign getCampaign() {
		return campaign;
	}

	/**
	 * @return the map which is playing
	 */
	public Map getPlayingmap() {
		return playingMap;
	}

	/**
	 * @return Character in the game
	 */
	public Fighter getFighter() {
		return fighter;
	}

	/**
	 * @param cb callback attribute, when one map finish call function 
	 * 				to initial next map in campaign
	 */
	public void nextMap(TurnCallback cb) {
		this.campaign.getMaps().remove(0);
		initData();
		if(playingMap!=null && (fighter.getBehaviorStrategy() instanceof ComputerStrategy)){
			fighter.setStrategy(new ComputerStrategy(this,cb));
		}
	}
	
	/**
	 * @param game game which you would like to save
	 * @return true if save success, or false if fail
	 */
	public static boolean saveGame(Game game){
		try{
			FileOutputStream fileOut =
	        new FileOutputStream(Config.GAME_FILE);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(game);
	        out.close();
	        fileOut.close();
	        System.out.printf("Serialized data is saved");
	        return true;
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getLevel() {
		return fighter.getLevel();
	}
}
