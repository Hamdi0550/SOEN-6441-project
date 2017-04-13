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
	private Fighter character;

	/**
	 * Constructors
	 * 
	 */
	public AgressiveStrategy(Game game, Fighter npc) {
		this.game = game;
		this.character = npc;
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
			game.getPlayingmap().npcMove(character.xOfFighter,character.yOfFighter,queue.get(0).x,queue.get(0).y);
			character.xOfFighter = queue.get(0).x;
			character.yOfFighter = queue.get(0).y;
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
		int range = 1;
		if((WeaponItem) character.getWearItemByName("Weapon")!=null)
			range = ((WeaponItem) character.getWearItemByName("Weapon")).getRange();		// need to know the range
		for(int i=1;i<=range;i++){
			if(character.xOfFighter<=game.getPlayingmap().getRow()-1-i&&
					((character.xOfFighter+i==game.getXofplayer()&&character.yOfFighter==game.getYofplayer())
							||game.getPlayingmap().getLocation()[character.xOfFighter+i][character.yOfFighter]=='p')){
				attackCharacter(character.xOfFighter+i,character.yOfFighter);
				return true;
			}
			else if(character.xOfFighter>=i&&
					((character.xOfFighter-i==game.getXofplayer()&&character.yOfFighter==game.getYofplayer())
							||game.getPlayingmap().getLocation()[character.xOfFighter-i][character.yOfFighter]=='p')){
				attackCharacter(character.xOfFighter-i,character.yOfFighter);
				return true;
			}
			else if(character.yOfFighter<=game.getPlayingmap().getColumn()-1-i&&
					((character.xOfFighter==game.getXofplayer()&&character.yOfFighter+i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[character.xOfFighter][character.yOfFighter+i]=='p')){
				attackCharacter(character.xOfFighter,character.yOfFighter+i);
				return true;
			}
			else if(character.yOfFighter>=i&&
					((character.xOfFighter==game.getXofplayer()&&character.yOfFighter-i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[character.xOfFighter][character.yOfFighter-i]=='p')){
				attackCharacter(character.xOfFighter,character.yOfFighter-i);
				return true;
			}
			else if(character.xOfFighter>=i&&character.yOfFighter>=i&&
					((character.xOfFighter-i==game.getXofplayer()&&character.yOfFighter-i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[character.xOfFighter-i][character.yOfFighter-i]=='p')){
				attackCharacter(character.xOfFighter-i,character.yOfFighter-i);
				return true;
			}
			else if(character.xOfFighter<=game.getPlayingmap().getRow()-1-i&&character.yOfFighter<=game.getPlayingmap().getColumn()-1-i&&
					((character.xOfFighter+i==game.getXofplayer()&&character.yOfFighter+i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[character.xOfFighter+i][character.yOfFighter+i]=='p')){
				attackCharacter(character.xOfFighter+i,character.yOfFighter+i);
				return true;
			}
			else if(character.xOfFighter>=i&&character.yOfFighter<=game.getPlayingmap().getColumn()-1-i&&
					((character.xOfFighter-i==game.getXofplayer()&&character.yOfFighter+i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[character.xOfFighter-i][character.yOfFighter+i]=='p')){
				attackCharacter(character.xOfFighter-i,character.yOfFighter+i);
				return true;
			}
			else if(character.xOfFighter<=game.getPlayingmap().getRow()-1-i&&character.yOfFighter>=i&&
					((character.xOfFighter+i==game.getXofplayer()&&character.yOfFighter-i==game.getYofplayer())
							||game.getPlayingmap().getLocation()[character.xOfFighter+i][character.yOfFighter-i]=='p')){
				attackCharacter(character.xOfFighter+i,character.yOfFighter-i);
				return true;
			}
		}
		return false;
	}

	private void attackCharacter(int x, int y) {
		Fighter attacter = character;
		if(x==game.getXofplayer()&&y==game.getYofplayer()){
			Fighter attactee = game.getFighter();
			attacter.attackCaracter(attactee);
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
		int x = character.xOfFighter, y= character.yOfFighter;
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

		if(character.xOfFighter-1>=0&&location[character.xOfFighter-1][character.yOfFighter]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[character.xOfFighter-1][character.yOfFighter].getContent();
			character.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(character.xOfFighter-1, character.yOfFighter, 'e');
			}
		}
		else if(character.xOfFighter+1<game.getPlayingmap().getRow()&&location[character.xOfFighter+1][character.yOfFighter]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[character.xOfFighter+1][character.yOfFighter].getContent();
			character.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(character.xOfFighter+1, character.yOfFighter, 'e');
			}
		}
		else if(character.yOfFighter-1>=0&&location[character.xOfFighter][character.yOfFighter-1]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[character.xOfFighter][character.yOfFighter-1].getContent();
			character.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(character.xOfFighter, character.yOfFighter-1, 'e');
			}
		}
		else if(character.yOfFighter+1<game.getPlayingmap().getColumn()&&location[character.xOfFighter][character.yOfFighter+1]=='c'){
			Chest chest = (Chest)game.getPlayingmap().getCellsinthemap()[character.xOfFighter][character.yOfFighter+1].getContent();
			character.openChest(chest);
			if(chest.isEmpty()){
				game.getPlayingmap().changeLocation(character.xOfFighter, character.yOfFighter+1, 'e');
			}
		}
	}
}
