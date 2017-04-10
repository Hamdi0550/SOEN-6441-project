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
import ddg.model.entity.BaseItem;
import ddg.model.entity.Chest;
import ddg.model.entity.Game;

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
	private int xofcharactor;
	private int yofcharactor;

	/**
	 * Constructors
	 * 
	 */
	public AgressiveStrategy(Game game, int x, int y) {
		this.game = game;
		this.xofcharactor = x;
		this.yofcharactor = y;
	}

	@Override
	public void turn(TurnCallback cb) {
		System.out.println("Agressive !!!!");
		ArrayList<Point> queue = searchWayAttactPlayer(cb);
		for (Point point : queue) {
			System.out.print(point.x+","+point.y+"\t");
		}
		int attacktimes = 1;
		int walktimes = 3;
		while(walktimes>0){
			findChest();
			if(attacktimes>0){
				if(findEnemy(cb)){
					attacktimes --;
				}
			}
			if(queue.size()==0){
				break;
			}
			game.getPlayingmap().npcMove(xofcharactor,yofcharactor,queue.get(0).x,queue.get(0).y);
			xofcharactor = queue.get(0).x;
			yofcharactor = queue.get(0).y;
			queue.remove(0);
			walktimes--;
		}
	}

	private boolean findEnemy(TurnCallback cb) {
		Fighter fighter = (Fighter) game.getPlayingmap().getCellsinthemap()[xofcharactor][yofcharactor].getContent();
		BaseItem weapon = fighter.getWearItemByName("Weapon");
		int range = 2; //weapon.getRange();		// need to know the range
		for(int i=1;i<=range;i++){
			if(xofcharactor<=game.getPlayingmap().getRow()-1-i&&
					((xofcharactor+i==game.getXofplayer()&&yofcharactor==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xofcharactor+i][yofcharactor]=='p')){
				attackCharacter(xofcharactor+i,yofcharactor,cb);
				return true;
			}
			else if(xofcharactor>=i&&
					((xofcharactor-i==game.getXofplayer()&&yofcharactor==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xofcharactor-i][yofcharactor]=='p')){
				attackCharacter(xofcharactor-i,yofcharactor,cb);
				return true;
			}
			else if(yofcharactor<=game.getPlayingmap().getColumn()-1-i&&
					((xofcharactor==game.getXofplayer()&&yofcharactor+i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xofcharactor][yofcharactor+i]=='p')){
				attackCharacter(xofcharactor,yofcharactor+i,cb);
				return true;
			}
			else if(yofcharactor>=i&&
					((xofcharactor==game.getXofplayer()&&yofcharactor-i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xofcharactor][yofcharactor-i]=='p')){
				attackCharacter(xofcharactor,yofcharactor-i,cb);
				return true;
			}
			else if(xofcharactor>=i&&yofcharactor>=i&&
					((xofcharactor-i==game.getXofplayer()&&yofcharactor-i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xofcharactor-i][yofcharactor-i]=='p')){
				attackCharacter(xofcharactor-i,yofcharactor-i,cb);
				return true;
			}
			else if(xofcharactor<=game.getPlayingmap().getRow()-1-i&&yofcharactor<=game.getPlayingmap().getColumn()-1-i&&
					((xofcharactor+i==game.getXofplayer()&&yofcharactor+i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xofcharactor+i][yofcharactor+i]=='p')){
				attackCharacter(xofcharactor+i,yofcharactor+i,cb);
				return true;
			}
			else if(xofcharactor>=i&&yofcharactor<=game.getPlayingmap().getColumn()-1-i&&
					((xofcharactor-i==game.getXofplayer()&&yofcharactor+i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xofcharactor-i][yofcharactor+i]=='p')){
				attackCharacter(xofcharactor-i,yofcharactor+i,cb);
				return true;
			}
			else if(xofcharactor<=game.getPlayingmap().getRow()-1-i&&yofcharactor>=i&&
					((xofcharactor+i==game.getXofplayer()&&yofcharactor-i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[xofcharactor+i][yofcharactor-i]=='p')){
				attackCharacter(xofcharactor+i,yofcharactor-i,cb);
				return true;
			}
		}
		return false;
	}

	private void attackCharacter(int x, int y,TurnCallback cb) {
		Fighter attacter = (Fighter) game.getPlayingmap().getCellsinthemap()[xofcharactor][yofcharactor].getContent();
		if(x==game.getXofplayer()&&y==game.getYofplayer()){
			Fighter attactee = game.getFighter();
			attacter.attackCaracter(attactee);
			if(!attactee.isAlive()){
				cb.finish();
			}
		}
		else{
			Fighter attactee = (Fighter)game.getPlayingmap().getCellsinthemap()[x][y].getContent();
			attacter.attackCaracter(attactee);
			if(!attactee.isAlive()){
				game.getPlayingmap().changeLocation(x, y, 'd');
			}
		}
		
		
	}

	protected ArrayList<Point> searchWayAttactPlayer(TurnCallback cb) {
		ArrayList<Point> queue = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		int x = xofcharactor, y= yofcharactor;
		while(!map.containsKey(game.getXofplayer()+","+game.getYofplayer())){
			if(queue.size()==3){
				break;
			}
			int dist1,dist2,dist3,dist4;
			if(!map.containsKey(x-1+","+y)){
				dist1 = shortestDistance(x-1,y);
				map.put(x-1+","+y, 0);
			}
			else
				dist1 = 1000;
			if(!map.containsKey(x+1+","+y)){
				dist2 = shortestDistance(x+1,y);
				map.put(x+1+","+y, 0);
			}
			else
				dist2 = 1000;
			if(!map.containsKey(x+","+(y-1))){
				dist3 = shortestDistance(x,y-1);
				map.put(x+","+(y-1), 0);
			}
			else
				dist3 = 1000;
			
			if(!map.containsKey(x+","+(y+1))){
				dist4 = shortestDistance(x,y+1);
				map.put(x+","+(y+1), 0);
			}
			else
				dist4 = 1000;
			
			if(dist1==0||dist2==0||dist3==0||dist4==0){
				return queue;
			}
			else if(dist1<=dist2&&dist1<=dist3&&dist1<=dist4){
				x=x-1;
				queue.add(new Point(x, y));
			}
			else if(dist2<=dist1&&dist2<=dist3&&dist2<=dist4){
				x=x+1;
				queue.add(new Point(x, y));
			}
			else if(dist3<=dist1&&dist3<=dist2&&dist3<=dist4){
				y=y-1;
				queue.add(new Point(x, y));
			}
			else if(dist4<=dist1&&dist4<=dist2&&dist4<=dist3){
				y=y+1;
				queue.add(new Point(x, y));
			}
		}
		return queue;
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
		Fighter fighter = (Fighter)game.getPlayingmap().getCellsinthemap()[xofcharactor][yofcharactor].getContent();

		if(xofcharactor-1>=0&&location[xofcharactor-1][yofcharactor]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[xofcharactor-1][yofcharactor].getContent();
			fighter.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(xofcharactor-1, yofcharactor, 'e');
			}
		}
		else if(xofcharactor+1<game.getPlayingmap().getRow()&&location[xofcharactor+1][yofcharactor]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[xofcharactor+1][yofcharactor].getContent();
			fighter.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(xofcharactor+1, yofcharactor, 'e');
			}
		}
		else if(yofcharactor-1>=0&&location[xofcharactor][yofcharactor-1]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[xofcharactor][yofcharactor-1].getContent();
			fighter.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(xofcharactor, yofcharactor-1, 'e');
			}
		}
		else if(yofcharactor+1<game.getPlayingmap().getColumn()&&location[xofcharactor][yofcharactor+1]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[xofcharactor][yofcharactor+1].getContent();
			fighter.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(xofcharactor, yofcharactor+1, 'e');
			}
		}
	}
}
