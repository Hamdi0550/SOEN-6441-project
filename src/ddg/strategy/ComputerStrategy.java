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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructors
	 * 
	 */
	public ComputerStrategy(Game game) {
		this.game = game;
		initLocation();
//		System.out.println("11111"+locationOfExit.x+","+locationOfExit.y);
	}

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
//		int x = game.getXofplayer();
//		int y = game.getYofplayer();
//		int xOffset = game.getPlayingmap().getRow()-game.getXofplayer();
//		int yOffset = game.getPlayingmap().getColumn()-game.getYofplayer();
//		int looptimes = xOffset>yOffset?xOffset:yOffset;
		
//		for(int i=1;i<looptimes;i++){
//			if(x+i<game.getPlayingmap().getRow()){
//				if(game.getPlayingmap().getLocation()[x+i][y]=='c')
//					locationOfChests.add(new Point(x+i, y));
//				if(locationOfExit==null && game.getPlayingmap().getLocation()[x+i][y]=='o'){
//					locationOfExit = new Point(x+i, y);
//				}
//			}
//			if(x-i>=0){
//				if(game.getPlayingmap().getLocation()[x-i][y]=='c')
//					locationOfChests.add(new Point(x-i, y));
//				if(locationOfExit==null && game.getPlayingmap().getLocation()[x-i][y]=='o'){
//					locationOfExit = new Point(x-i, y);
//				}
//			}
//			if(y+i<game.getPlayingmap().getColumn()){
//				if(game.getPlayingmap().getLocation()[x][y+i]=='c')
//					locationOfChests.add(new Point(x, y+i));
//				if(locationOfExit==null && game.getPlayingmap().getLocation()[x][y+i]=='o'){
//					locationOfExit = new Point(x, y+i);
//				}
//			}
//			if(y-i>=0){
//				if(game.getPlayingmap().getLocation()[x][y-i]=='c')
//					locationOfChests.add(new Point(x, y-i));
//				if(locationOfExit==null && game.getPlayingmap().getLocation()[x][y-i]=='o'){
//					locationOfExit = new Point(x, y-i);
//				}
//			}
//			if(x+i<game.getPlayingmap().getRow()&&y+i<game.getPlayingmap().getColumn()){
//				if(game.getPlayingmap().getLocation()[x+i][y+i]=='c')
//					locationOfChests.add(new Point(x+i, y+i));
//				if(locationOfExit==null && game.getPlayingmap().getLocation()[x+i][y+i]=='o')
//					locationOfExit = new Point(x+i, y+i);
//			}
//			if(x+i<game.getPlayingmap().getRow()&&y-i>=0){
//				if(game.getPlayingmap().getLocation()[x+i][y-i]=='c')
//					locationOfChests.add(new Point(x+i, y-i));
//				if(locationOfExit==null && game.getPlayingmap().getLocation()[x+i][y-i]=='o')
//					locationOfExit = new Point(x+i, y-i);
//			}
//			if(x-i>=0&&y+i<game.getPlayingmap().getColumn()){
//				if(game.getPlayingmap().getLocation()[x-i][y+i]=='c')
//					locationOfChests.add(new Point(x-i, y+i));
//				if(locationOfExit==null && game.getPlayingmap().getLocation()[x-i][y+i]=='o')
//					locationOfExit = new Point(x-i, y+i);
//			}
//			if(x-i>=0&&y-i>=0){
//				if(game.getPlayingmap().getLocation()[x-i][y-i]=='c')
//					locationOfChests.add(new Point(x-i, y-i));
//				if(locationOfExit==null && game.getPlayingmap().getLocation()[x-i][y-i]=='o')
//					locationOfExit = new Point(x-i, y-i);
//			}
//		}
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
		if(locationOfChests.size()!=0){
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
		}
		game.getPlayingmap().notifyChange();
	}
	
	protected ArrayList<Point> searchWayToAim(Point aim) {
		ArrayList<Point> queueOfPath = new ArrayList<>();
		Map<String, Integer> locationAlreadySearch = new HashMap<>();
		int x = game.getXofplayer(), y= game.getYofplayer();
		while(!locationAlreadySearch.containsKey(aim.x+","+aim.y)){
			if(queueOfPath.size()==3){
				break;
			}
			int dist1,dist2,dist3,dist4;
			if(!locationAlreadySearch.containsKey(x-1+","+y)){
				dist1 = shortestDistance(x-1,y,aim);
				locationAlreadySearch.put(x-1+","+y, 0);
			}
			else
				dist1 = 1000;
			if(!locationAlreadySearch.containsKey(x+1+","+y)){
				dist2 = shortestDistance(x+1,y,aim);
				locationAlreadySearch.put(x+1+","+y, 0);
			}
			else
				dist2 = 1000;
			if(!locationAlreadySearch.containsKey(x+","+(y-1))){
				dist3 = shortestDistance(x,y-1,aim);
				locationAlreadySearch.put(x+","+(y-1), 0);
			}
			else
				dist3 = 1000;
			
			if(!locationAlreadySearch.containsKey(x+","+(y+1))){
				dist4 = shortestDistance(x,y+1,aim);
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
	
	public int shortestDistance(int x,int y, Point aim){
		if(x<0||x>=game.getPlayingmap().getRow()||y<0||y>=game.getPlayingmap().getColumn()||game.getPlayingmap().getLocation()[x][y]!='f'){
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
			if(p.x>0&&(game.getPlayingmap().getLocation()[p.x-1][p.y]=='f'
					||game.getPlayingmap().getLocation()[p.x-1][p.y]=='c'
					||game.getPlayingmap().getLocation()[p.x-1][p.y]=='c')
					&&!map.containsKey((p.x-1)+","+p.y)){
				order.add(new Point(p.x-1,p.y));
				map.put((p.x-1)+","+p.y, map.get(p.x+","+p.y).intValue()+1);
			}
			if(p.x<game.getPlayingmap().getRow()-1
					&&(game.getPlayingmap().getLocation()[p.x+1][p.y]=='f'||game.getPlayingmap().getLocation()[p.x+1][p.y]=='c')
					&&!map.containsKey((p.x+1)+","+p.y)){
				order.add(new Point(p.x+1,p.y));
				map.put((p.x+1)+","+p.y, map.get(p.x+","+p.y).intValue()+1);
			}
			if(p.y>0
					&&(game.getPlayingmap().getLocation()[p.x][p.y-1]=='f'||game.getPlayingmap().getLocation()[p.x][p.y-1]=='c')
					&&!map.containsKey(p.x+","+(p.y-1))){
				order.add(new Point(p.x,p.y-1));
				map.put(p.x+","+(p.y-1), map.get(p.x+","+p.y).intValue()+1);
			}
			if(p.y<game.getPlayingmap().getColumn()-1
					&&(game.getPlayingmap().getLocation()[p.x][p.y+1]=='f'||game.getPlayingmap().getLocation()[p.x][p.y+1]=='c')
					&&!map.containsKey(p.x+","+p.y+1)){
				order.add(new Point(p.x,p.y+1));
				map.put(p.x+","+(p.y+1), map.get(p.x+","+p.y).intValue()+1);
			}
		}
		return 1000;
	}
	
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

	private void attackCharacter(int x, int y) {
		Fighter attacter = game.getFighter();
		Fighter attactee = (Fighter)game.getPlayingmap().getCellsinthemap()[x][y].getContent();
		attacter.attackCaracter(attactee);
		if(!attactee.isAlive()){
				game.getPlayingmap().changeLocation(x, y, 'd');
			}
	}
}
