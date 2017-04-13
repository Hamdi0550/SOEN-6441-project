/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.strategy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import ddg.model.Fighter;
import ddg.model.Game;
import ddg.model.entity.Chest;
import ddg.model.item.Item;
import ddg.model.item.WeaponItem;

/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
public class ComputerStrategy implements IStrategy {
	private Game game;
	private ArrayList<Point> locationOfChests = new ArrayList<>();
	private ArrayList<Fighter> NPCs = new ArrayList<>();
	private ArrayList<Fighter> Corpse = new ArrayList<>();
	private Point locationOfExit;
	private TurnCallback turnCallBack;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructors
	 * 
	 */
	public ComputerStrategy(Game game, TurnCallback cb) {
		this.game = game;
		this.turnCallBack = cb;
		initLocation();
	}

	/**
	 * traverse the map, recode all chest location, NPC location and exit door
	 */
	private void initLocation() {
		for(int i=0;i<game.getPlayingmap().getRow();i++){
			for(int j=0;j<game.getPlayingmap().getColumn();j++){
				if(game.getPlayingmap().getLocation()[i][j]=='c')
					locationOfChests.add(new Point(i,j));
				else if(game.getPlayingmap().getLocation()[i][j]=='p'){
					NPCs.add((Fighter)game.getPlayingmap().getCellsinthemap()[i][j].getContent());
				}
				if(locationOfExit==null && game.getPlayingmap().getLocation()[i][j]=='o')
					locationOfExit = new Point(i,j);
			}
		}
	}

	@Override
	public void turn() {
		ArrayList<Point> queueOfPath = new ArrayList<>();
		Fighter fighter = game.getFighter();
		while(locationOfChests.size()!=0){
			if(game.getPlayingmap().getLocation()[locationOfChests.get(0).x][locationOfChests.get(0).y]!='e'){
				break;
			}
			else
				locationOfChests.remove(0);
		}
		while(NPCs.size()!=0){
			if(NPCs.get(0).isAlive()){
				break;
			}
			else{
				Corpse.add(NPCs.get(0));
				NPCs.remove(0);
			}
		}
		boolean haskey = false;
		for (Item item : game.getFighter().getBackpack()) {
			if(item.getName().equals("key")){
				haskey = true;
			}
		}
		
		
		if(haskey){
			queueOfPath = searchWayToAim(locationOfExit);
		}
		else if(locationOfChests.size()!=0){
			queueOfPath = searchWayToAim(locationOfChests.get(0));
		}
		else if(NPCs.size()!=0){
			queueOfPath = searchWayToAim(new Point(NPCs.get(0).xOfFighter,NPCs.get(0).yOfFighter));
		}
		for (Point point : queueOfPath) {
			System.out.print(point.x+","+point.y+"\t");
		}
		int attacktimes = 1;
		int walktimes = 3;
		findChest();
		findExit();
		if(attacktimes>0){
			if(findEnemy()){
				attacktimes --;
			}
		}
		while(walktimes>0){
			if(queueOfPath.size()==0){
				break;
			}
			fighter.xOfFighter = queueOfPath.get(0).x;
			fighter.yOfFighter = queueOfPath.get(0).y;
			queueOfPath.remove(0);
			walktimes--;
			if(attacktimes>0){
				if(findEnemy()){
					attacktimes --;
				}
			}
			findChest();
			findExit();
		}
		if(game.getPlayingmap()!=null)
			game.getPlayingmap().notifyChange();
	}
	
	/**
	 * check if it has a out door around character
	 */
	private void findExit() {
		char[][] location = game.getPlayingmap().getLocation();
		Fighter fighter = game.getFighter();

		if(fighter.xOfFighter-1>=0&&location[fighter.xOfFighter-1][fighter.yOfFighter]=='o'){
			turnCallBack.finish();
		}
		else if(fighter.xOfFighter+1<game.getPlayingmap().getRow()&&location[fighter.xOfFighter+1][fighter.yOfFighter]=='o'){
			turnCallBack.finish();
		}
		else if(fighter.yOfFighter-1>=0&&location[fighter.xOfFighter][fighter.yOfFighter-1]=='o'){
			turnCallBack.finish();
		}
		else if(fighter.yOfFighter+1<game.getPlayingmap().getColumn()&&location[fighter.xOfFighter][fighter.yOfFighter+1]=='o'){
			turnCallBack.finish();
		}
	}

	/**
	 * 
	 * @param aim Point location of a chest, NPC or Exit door
	 * @return	return 3 Point type location which is best way to arrive aim
	 */
	protected ArrayList<Point> searchWayToAim(Point aim) {
		ArrayList<Point> queueOfPath = new ArrayList<>();
		Map<String, Integer> locationAlreadySearch = new HashMap<>();
		int x = game.getXofplayer(), y= game.getYofplayer();
		while(!locationAlreadySearch.containsKey(aim.x+","+aim.y)){
			if(queueOfPath.size()==3){
				break;
			}
			int distance1,distance2,distance3,distance4;
			if(!locationAlreadySearch.containsKey(x-1+","+y)){
				distance1 = shortestDistance(x-1,y,aim);
				locationAlreadySearch.put(x-1+","+y, 0);
			}
			else
				distance1 = 1000;
			if(!locationAlreadySearch.containsKey(x+1+","+y)){
				distance2 = shortestDistance(x+1,y,aim);
				locationAlreadySearch.put(x+1+","+y, 0);
			}
			else
				distance2 = 1000;
			if(!locationAlreadySearch.containsKey(x+","+(y-1))){
				distance3 = shortestDistance(x,y-1,aim);
				locationAlreadySearch.put(x+","+(y-1), 0);
			}
			else
				distance3 = 1000;
			
			if(!locationAlreadySearch.containsKey(x+","+(y+1))){
				distance4 = shortestDistance(x,y+1,aim);
				locationAlreadySearch.put(x+","+(y+1), 0);
			}
			else
				distance4 = 1000;
			
			if(distance1==0||distance2==0||distance3==0||distance4==0){
				return queueOfPath;
			}
			else if(distance1<=distance2&&distance1<=distance3&&distance1<=distance4){
				x=x-1;
				queueOfPath.add(new Point(x, y));
			}
			else if(distance2<=distance1&&distance2<=distance3&&distance2<=distance4){
				x=x+1;
				queueOfPath.add(new Point(x, y));
			}
			else if(distance3<=distance1&&distance3<=distance2&&distance3<=distance4){
				y=y-1;
				queueOfPath.add(new Point(x, y));
			}
			else if(distance4<=distance1&&distance4<=distance2&&distance4<=distance3){
				y=y+1;
				queueOfPath.add(new Point(x, y));
			}
		}
		return queueOfPath;
	}
	
	/**
	 * find shortestDIstance from starting point to the aim
	 * @param x x coordinate of starting point 
	 * @param y y coordinate of starting point
	 * @param aim	Point type of aim as ending point
	 * @return shortestDistance for starting point to ending point
	 */
	public int shortestDistance(int x,int y, Point aim){
		
		if(x==aim.x&&y==aim.y){
			return 0;
		}
		else if(x<0||x>=game.getPlayingmap().getRow()||y<0||y>=game.getPlayingmap().getColumn()
				||(game.getPlayingmap().getLocation()[x][y]!='f'
				&&game.getPlayingmap().getLocation()[x][y]!='d')){
			return 1000;
		}
		Map<String, Integer> map = new HashMap<>();
		Queue<Point> order = new LinkedList<Point>();
		order.add(new Point(x, y));
		map.put(x+","+y, 0);
		while(!(order.size()==0)){
			Point p = order.poll();
			if(map.containsKey(aim.x+","+aim.y)){
				return map.get(aim.x+","+aim.y).intValue();
			}
			if(p.x>0&&!map.containsKey((p.x-1)+","+p.y)){
				if(p.x-1==aim.x&&p.y==aim.y){
					map.put((p.x-1)+","+p.y, map.get(p.x+","+p.y).intValue()+1);
					continue;
				}
				else if(game.getPlayingmap().getLocation()[p.x-1][p.y]=='f'||game.getPlayingmap().getLocation()[p.x-1][p.y]=='d'){
					order.add(new Point(p.x-1,p.y));
					map.put((p.x-1)+","+p.y, map.get(p.x+","+p.y).intValue()+1);
				}
			}
			if(p.x<game.getPlayingmap().getRow()-1&&!map.containsKey((p.x+1)+","+p.y)){
				
				if(p.x+1==aim.x&&p.y==aim.y){
					map.put((p.x+1)+","+p.y, map.get(p.x+","+p.y).intValue()+1);
					continue;
				}
				else if(game.getPlayingmap().getLocation()[p.x+1][p.y]=='f'||game.getPlayingmap().getLocation()[p.x+1][p.y]=='d'){
					order.add(new Point(p.x+1,p.y));
					map.put((p.x+1)+","+p.y, map.get(p.x+","+p.y).intValue()+1);
				}
			}
			if(p.y>0&&!map.containsKey(p.x+","+(p.y-1))){
				if(p.x==aim.x&&p.y-1==aim.y){
					map.put(p.x+","+(p.y-1), map.get(p.x+","+p.y).intValue()+1);
					continue;
				}
				else if(game.getPlayingmap().getLocation()[p.x][p.y-1]=='f'||game.getPlayingmap().getLocation()[p.x][p.y-1]=='d'){
					order.add(new Point(p.x,p.y-1));
					map.put(p.x+","+(p.y-1), map.get(p.x+","+p.y).intValue()+1);
				}
			}
			if(p.y<game.getPlayingmap().getColumn()-1&&!map.containsKey(p.x+","+p.y+1)){
				
				if(p.x==aim.x&&p.y+1==aim.y){
					map.put(p.x+","+(p.y+1), map.get(p.x+","+p.y).intValue()+1);
					continue;
				}
				else if(game.getPlayingmap().getLocation()[p.x][p.y+1]=='f'||game.getPlayingmap().getLocation()[p.x][p.y+1]=='d'){
					order.add(new Point(p.x,p.y+1));
					map.put(p.x+","+(p.y+1), map.get(p.x+","+p.y).intValue()+1);
				}
			}
		}
		return 1000;
	}
	
	/**
	 * check and interact with chests which are around character
	 */
	private void findChest() {
		char[][] location = game.getPlayingmap().getLocation();
		Fighter fighter = game.getFighter();

		if(fighter.xOfFighter-1>=0&&location[fighter.xOfFighter-1][fighter.yOfFighter]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[fighter.xOfFighter-1][fighter.yOfFighter].getContent();
			fighter.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(fighter.xOfFighter-1, fighter.yOfFighter, 'e');
			}
		}
		else if(fighter.xOfFighter+1<game.getPlayingmap().getRow()&&location[fighter.xOfFighter+1][fighter.yOfFighter]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[fighter.xOfFighter+1][fighter.yOfFighter].getContent();
			fighter.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(fighter.xOfFighter+1, fighter.yOfFighter, 'e');
			}
		}
		else if(fighter.yOfFighter-1>=0&&location[fighter.xOfFighter][fighter.yOfFighter-1]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[fighter.xOfFighter][fighter.yOfFighter-1].getContent();
			fighter.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(fighter.xOfFighter, fighter.yOfFighter-1, 'e');
			}
		}
		else if(fighter.yOfFighter+1<game.getPlayingmap().getColumn()&&location[fighter.xOfFighter][fighter.yOfFighter+1]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[fighter.xOfFighter][fighter.yOfFighter+1].getContent();
			fighter.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(fighter.xOfFighter, fighter.yOfFighter+1, 'e');
			}
		}
	}
	
	/**
	 * check if there are enemies in character attach range
	 * @return true if launch a attack, or false nobody in attack range
	 */
	private boolean findEnemy() {
		int range = 1;
		Fighter fighter = game.getFighter();
		if((WeaponItem) fighter.getWearItemByName("Weapon")!=null)
			range = ((WeaponItem) fighter.getWearItemByName("Weapon")).getRange();		// need to know the range
		for(int i=1;i<=range;i++){
			if(fighter.xOfFighter<=game.getPlayingmap().getRow()-1-i&&
					((fighter.xOfFighter+i==game.getXofplayer()&&fighter.yOfFighter==game.getYofplayer())
							||game.getPlayingmap().getLocation()[fighter.xOfFighter+i][fighter.yOfFighter]=='p')){
				attackCharacter(fighter.xOfFighter+i,fighter.yOfFighter);
				return true;
			}
			else if(fighter.xOfFighter>=i&&
					((fighter.xOfFighter-i==game.getXofplayer()&&fighter.yOfFighter==game.getYofplayer())
							||game.getPlayingmap().getLocation()[fighter.xOfFighter-i][fighter.yOfFighter]=='p')){
				attackCharacter(fighter.xOfFighter-i,fighter.yOfFighter);
				return true;
			}
			else if(fighter.yOfFighter<=game.getPlayingmap().getColumn()-1-i&&
					((fighter.xOfFighter==game.getXofplayer()&&fighter.yOfFighter+i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[fighter.xOfFighter][fighter.yOfFighter+i]=='p')){
				attackCharacter(fighter.xOfFighter,fighter.yOfFighter+i);
				return true;
			}
			else if(fighter.yOfFighter>=i&&
					((fighter.xOfFighter==game.getXofplayer()&&fighter.yOfFighter-i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[fighter.xOfFighter][fighter.yOfFighter-i]=='p')){
				attackCharacter(fighter.xOfFighter,fighter.yOfFighter-i);
				return true;
			}
			else if(fighter.xOfFighter>=i&&fighter.yOfFighter>=i&&
					((fighter.xOfFighter-i==game.getXofplayer()&&fighter.yOfFighter-i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[fighter.xOfFighter-i][fighter.yOfFighter-i]=='p')){
				attackCharacter(fighter.xOfFighter-i,fighter.yOfFighter-i);
				return true;
			}
			else if(fighter.xOfFighter<=game.getPlayingmap().getRow()-1-i&&fighter.yOfFighter<=game.getPlayingmap().getColumn()-1-i&&
					((fighter.xOfFighter+i==game.getXofplayer()&&fighter.yOfFighter+i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[fighter.xOfFighter+i][fighter.yOfFighter+i]=='p')){
				attackCharacter(fighter.xOfFighter+i,fighter.yOfFighter+i);
				return true;
			}
			else if(fighter.xOfFighter>=i&&fighter.yOfFighter<=game.getPlayingmap().getColumn()-1-i&&
					((fighter.xOfFighter-i==game.getXofplayer()&&fighter.yOfFighter+i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[fighter.xOfFighter-i][fighter.yOfFighter+i]=='p')){
				attackCharacter(fighter.xOfFighter-i,fighter.yOfFighter+i);
				return true;
			}
			else if(fighter.xOfFighter<=game.getPlayingmap().getRow()-1-i&&fighter.yOfFighter>=i&&
					((fighter.xOfFighter+i==game.getXofplayer()&&fighter.yOfFighter-i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[fighter.xOfFighter+i][fighter.yOfFighter-i]=='p')){
				attackCharacter(fighter.xOfFighter+i,fighter.yOfFighter-i);
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * attack the Character locate in map with below coordinate 
	 * @param x	x coordinate of attactee
	 * @param y y coordinate of attactee
	 */
	private void attackCharacter(int x, int y) {
		Fighter attacter = game.getFighter();
		Fighter attactee = (Fighter)game.getPlayingmap().getCellsinthemap()[x][y].getContent();
		attacter.attackCaracter(attactee);
		if(!attactee.isAlive()){
				game.getPlayingmap().changeLocation(x, y, 'd');
			}
	}
}
