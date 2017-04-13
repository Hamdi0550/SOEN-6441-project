/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.strategy;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import ddg.model.Fighter;
import ddg.model.Game;
import ddg.model.entity.Chest;
import ddg.model.item.BaseItem;
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
public class AgressiveStrategy implements IStrategy, Serializable{
	private static final long serialVersionUID = 1L;
	
	private Game game;
	private int xOfCharacter;
	private int yOfCharacter;

	/**
	 * Constructors
	 * 
	 */
	public AgressiveStrategy(Game game, int x, int y) {
		this.game = game;
		this.xOfCharacter = x;
		this.yOfCharacter = y;
	}

	@Override
	public void turn() {
		System.out.println("Agressive !!!!");
		ArrayList<Point> queue = searchWayAttactPlayer();
		for (Point point : queue) {
			System.out.print(point.x+","+point.y+"\t");
		}
		int attacktimes = 1;
		int walktimes = 3;
		findChest();
		if(attacktimes>0){
			if(findEnemy()){
				attacktimes --;
			}
		}
		while(walktimes>0){
			if(queue.size()==0){
				break;
			}
			game.getPlayingmap().npcMove(xOfCharacter,yOfCharacter,queue.get(0).x,queue.get(0).y);
			xOfCharacter = queue.get(0).x;
			yOfCharacter = queue.get(0).y;
			queue.remove(0);
			walktimes--;
			if(attacktimes>0){
				if(findEnemy()){
					attacktimes --;
				}
			}
			findChest();
		}
	}

	private boolean findEnemy() {
		Fighter fighter = (Fighter) game.getPlayingmap().getCellsinthemap()[xOfCharacter][yOfCharacter].getContent();
		WeaponItem weapon = (WeaponItem) fighter.getWearItemByName("Weapon");
		int range = weapon.getRange();		// need to know the range
		for(int i=1;i<=range;i++){
			if(xOfCharacter<=game.getPlayingmap().getRow()-1-i&&
					((xOfCharacter+i==game.getXofplayer()&&yOfCharacter==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xOfCharacter+i][yOfCharacter]=='p')){
				attackCharacter(xOfCharacter+i,yOfCharacter);
				return true;
			}
			else if(xOfCharacter>=i&&
					((xOfCharacter-i==game.getXofplayer()&&yOfCharacter==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xOfCharacter-i][yOfCharacter]=='p')){
				attackCharacter(xOfCharacter-i,yOfCharacter);
				return true;
			}
			else if(yOfCharacter<=game.getPlayingmap().getColumn()-1-i&&
					((xOfCharacter==game.getXofplayer()&&yOfCharacter+i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xOfCharacter][yOfCharacter+i]=='p')){
				attackCharacter(xOfCharacter,yOfCharacter+i);
				return true;
			}
			else if(yOfCharacter>=i&&
					((xOfCharacter==game.getXofplayer()&&yOfCharacter-i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xOfCharacter][yOfCharacter-i]=='p')){
				attackCharacter(xOfCharacter,yOfCharacter-i);
				return true;
			}
			else if(xOfCharacter>=i&&yOfCharacter>=i&&
					((xOfCharacter-i==game.getXofplayer()&&yOfCharacter-i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xOfCharacter-i][yOfCharacter-i]=='p')){
				attackCharacter(xOfCharacter-i,yOfCharacter-i);
				return true;
			}
			else if(xOfCharacter<=game.getPlayingmap().getRow()-1-i&&yOfCharacter<=game.getPlayingmap().getColumn()-1-i&&
					((xOfCharacter+i==game.getXofplayer()&&yOfCharacter+i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xOfCharacter+i][yOfCharacter+i]=='p')){
				attackCharacter(xOfCharacter+i,yOfCharacter+i);
				return true;
			}
			else if(xOfCharacter>=i&&yOfCharacter<=game.getPlayingmap().getColumn()-1-i&&
					((xOfCharacter-i==game.getXofplayer()&&yOfCharacter+i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xOfCharacter-i][yOfCharacter+i]=='p')){
				attackCharacter(xOfCharacter-i,yOfCharacter+i);
				return true;
			}
			else if(xOfCharacter<=game.getPlayingmap().getRow()-1-i&&yOfCharacter>=i&&
					((xOfCharacter+i==game.getXofplayer()&&yOfCharacter-i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xOfCharacter+i][yOfCharacter-i]=='p')){
				attackCharacter(xOfCharacter+i,yOfCharacter-i);
				return true;
			}
		}
		return false;
	}

	private void attackCharacter(int x, int y) {
		Fighter attacter = (Fighter) game.getPlayingmap().getCellsinthemap()[xOfCharacter][yOfCharacter].getContent();
		if(x==game.getXofplayer()&&y==game.getYofplayer()){
			Fighter attactee = game.getFighter();
			attacter.attackCaracter(attactee);
//			if(!attactee.isAlive()){
//				
//			}
		}
		else{
			Fighter attactee = (Fighter)game.getPlayingmap().getCellsinthemap()[x][y].getContent();
			attacter.attackCaracter(attactee);
			if(!attactee.isAlive()){
				game.getPlayingmap().changeLocation(x, y, 'd');
			}
		}
		
		
	}

	protected ArrayList<Point> searchWayAttactPlayer() {
		ArrayList<Point> queueOfPath = new ArrayList<>();
		Map<String, Integer> locationAlreadySearch = new HashMap<>();
		int x = xOfCharacter, y= yOfCharacter;
		while(!locationAlreadySearch.containsKey(game.getXofplayer()+","+game.getYofplayer())){
			if(queueOfPath.size()==3){
				break;
			}
			int dist1,dist2,dist3,dist4;
			if(!locationAlreadySearch.containsKey(x-1+","+y)){
				dist1 = shortestDistance(x-1,y);
				locationAlreadySearch.put(x-1+","+y, 0);
			}
			else
				dist1 = 1000;
			if(!locationAlreadySearch.containsKey(x+1+","+y)){
				dist2 = shortestDistance(x+1,y);
				locationAlreadySearch.put(x+1+","+y, 0);
			}
			else
				dist2 = 1000;
			if(!locationAlreadySearch.containsKey(x+","+(y-1))){
				dist3 = shortestDistance(x,y-1);
				locationAlreadySearch.put(x+","+(y-1), 0);
			}
			else
				dist3 = 1000;
			
			if(!locationAlreadySearch.containsKey(x+","+(y+1))){
				dist4 = shortestDistance(x,y+1);
				locationAlreadySearch.put(x+","+(y+1), 0);
			}
			else
				dist4 = 1000;
			
			if(dist1==0||dist2==0||dist3==0||dist4==0){
				return queueOfPath;
			}
			else if(dist1<=dist2&&dist1<=dist3&&dist1<=dist4){
				x=x-1;
				queueOfPath.add(new Point(x, y));
			}
			else if(dist2<=dist1&&dist2<=dist3&&dist2<=dist4){
				x=x+1;
				queueOfPath.add(new Point(x, y));
			}
			else if(dist3<=dist1&&dist3<=dist2&&dist3<=dist4){
				y=y-1;
				queueOfPath.add(new Point(x, y));
			}
			else if(dist4<=dist1&&dist4<=dist2&&dist4<=dist3){
				y=y+1;
				queueOfPath.add(new Point(x, y));
			}
		}
		return queueOfPath;
	}
	
	public int shortestDistance(int x,int y){
		if(x<0||x>=game.getPlayingmap().getRow()||y<0||y>=game.getPlayingmap().getColumn()||game.getPlayingmap().getLocation()[x][y]!='f'){
			return 1000;
		}
		Map<String, Integer> map = new HashMap<>();
		Queue<Point> order = new LinkedList<Point>();
		order.add(new Point(x, y));
		map.put(x+","+y, 0);
		while(!(order.size()==0)){
			Point p = order.poll();
			if(map.containsKey(game.getXofplayer()+","+game.getYofplayer())){
				return map.get(game.getXofplayer()+","+game.getYofplayer()).intValue();
			}
			if(p.x>0&&game.getPlayingmap().getLocation()[p.x-1][p.y]=='f'&&!map.containsKey((p.x-1)+","+p.y)){
				order.add(new Point(p.x-1,p.y));
				map.put((p.x-1)+","+p.y, map.get(p.x+","+p.y).intValue()+1);
			}
			if(p.x<game.getPlayingmap().getRow()-1&&game.getPlayingmap().getLocation()[p.x+1][p.y]=='f'&&!map.containsKey((p.x+1)+","+p.y)){
				order.add(new Point(p.x+1,p.y));
				map.put((p.x+1)+","+p.y, map.get(p.x+","+p.y).intValue()+1);
			}
			if(p.y>0&&game.getPlayingmap().getLocation()[p.x][p.y-1]=='f'&&!map.containsKey(p.x+","+(p.y-1))){
				order.add(new Point(p.x,p.y-1));
				map.put(p.x+","+(p.y-1), map.get(p.x+","+p.y).intValue()+1);
			}
			if(p.y<game.getPlayingmap().getColumn()-1&&game.getPlayingmap().getLocation()[p.x][p.y+1]=='f'&&!map.containsKey(p.x+","+p.y+1)){
				order.add(new Point(p.x,p.y+1));
				map.put(p.x+","+(p.y+1), map.get(p.x+","+p.y).intValue()+1);
			}
		}
		return 1000;
	}
	
	private void findChest() {
		char[][] location = game.getPlayingmap().getLocation();
		Fighter fighter = (Fighter)game.getPlayingmap().getCellsinthemap()[xOfCharacter][yOfCharacter].getContent();

		if(xOfCharacter-1>=0&&location[xOfCharacter-1][yOfCharacter]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[xOfCharacter-1][yOfCharacter].getContent();
			fighter.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(xOfCharacter-1, yOfCharacter, 'e');
			}
		}
		else if(xOfCharacter+1<game.getPlayingmap().getRow()&&location[xOfCharacter+1][yOfCharacter]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[xOfCharacter+1][yOfCharacter].getContent();
			fighter.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(xOfCharacter+1, yOfCharacter, 'e');
			}
		}
		else if(yOfCharacter-1>=0&&location[xOfCharacter][yOfCharacter-1]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[xOfCharacter][yOfCharacter-1].getContent();
			fighter.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(xOfCharacter, yOfCharacter-1, 'e');
			}
		}
		else if(yOfCharacter+1<game.getPlayingmap().getColumn()&&location[xOfCharacter][yOfCharacter+1]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[xOfCharacter][yOfCharacter+1].getContent();
			fighter.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(xOfCharacter, yOfCharacter+1, 'e');
			}
		}
	}
}
