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

import ddg.model.Game;

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
	private ArrayList<Point> locationOfChests;
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
		System.out.println("11111");
	}

	private void initLocation() {
		int x = game.getXofplayer();
		int y = game.getYofplayer();
		int xOffset = game.getPlayingmap().getRow()-game.getXofplayer();
		int yOffset = game.getPlayingmap().getColumn()-game.getYofplayer();
		int looptimes = xOffset>yOffset?xOffset:yOffset;
		
		for(int i=1;i<looptimes;i++){
			if(x+i<game.getPlayingmap().getRow()){
				if(game.getPlayingmap().getLocation()[x+i][y]=='c')
					locationOfChests.add(new Point(x+i, y));
				if(locationOfExit==null && game.getPlayingmap().getLocation()[x+i][y]=='o'){
					locationOfExit = new Point(x+i, y);
				}
			}
			if(x-i>=0){
				if(game.getPlayingmap().getLocation()[x-i][y]=='c')
					locationOfChests.add(new Point(x-i, y));
				if(locationOfExit==null && game.getPlayingmap().getLocation()[x-i][y]=='o'){
					locationOfExit = new Point(x-i, y);
				}
			}
			if(y+i<game.getPlayingmap().getColumn()){
				if(game.getPlayingmap().getLocation()[x][y+i]=='c')
					locationOfChests.add(new Point(x, y+i));
				if(locationOfExit==null && game.getPlayingmap().getLocation()[x][y+i]=='o'){
					locationOfExit = new Point(x, y+i);
				}
			}
			if(y-i>=0){
				if(game.getPlayingmap().getLocation()[x][y-i]=='c')
					locationOfChests.add(new Point(x, y-i));
				if(locationOfExit==null && game.getPlayingmap().getLocation()[x][y-i]=='o'){
					locationOfExit = new Point(x, y-i);
				}
			}
			if(x+i<game.getPlayingmap().getRow()&&y+i<game.getPlayingmap().getColumn()){
				if(game.getPlayingmap().getLocation()[x+i][y+i]=='c')
					locationOfChests.add(new Point(x+i, y+i));
				if(locationOfExit==null && game.getPlayingmap().getLocation()[x+i][y+i]=='o')
					locationOfExit = new Point(x+i, y+i);
			}
			if(x+i<game.getPlayingmap().getRow()&&y-i>=0){
				if(game.getPlayingmap().getLocation()[x+i][y-i]=='c')
					locationOfChests.add(new Point(x+i, y-i));
				if(locationOfExit==null && game.getPlayingmap().getLocation()[x+i][y-i]=='o')
					locationOfExit = new Point(x+i, y-i);
			}
			if(x-i>=0&&y+i<game.getPlayingmap().getColumn()){
				if(game.getPlayingmap().getLocation()[x-i][y+i]=='c')
					locationOfChests.add(new Point(x-i, y+i));
				if(locationOfExit==null && game.getPlayingmap().getLocation()[x-i][y+i]=='o')
					locationOfExit = new Point(x-i, y+i);
			}
			if(x-i>=0&&y-i>=0){
				if(game.getPlayingmap().getLocation()[x-i][y-i]=='c')
					locationOfChests.add(new Point(x-i, y-i));
				if(locationOfExit==null && game.getPlayingmap().getLocation()[x-i][y-i]=='o')
					locationOfExit = new Point(x-i, y-i);
			}
		}
	}

	@Override
	public void turn() {
		
	}
}
