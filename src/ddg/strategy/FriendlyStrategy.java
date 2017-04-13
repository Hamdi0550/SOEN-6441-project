/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.strategy;

import java.io.Serializable;

import ddg.model.Fighter;
import ddg.model.Game;
import ddg.model.Map;
import ddg.model.entity.Chest;

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
public class FriendlyStrategy implements IStrategy {
	private static final long serialVersionUID = 1L;
	
	private Game game;
	private Fighter character;
	/**
	 * Constructors
	 * 
	 */
	public FriendlyStrategy(Game game, int x, int y) {
		this.game = game;
		
	}

	public FriendlyStrategy(Game game, Fighter npc) {
		this.game = game;
		this.character = npc;
//		Map playingMap = game.getPlayingmap();
//		if(this.xofcharactor == -1 || this.yofcharactor == -1) {
//			for(int i=0;i< playingMap.getRow();i++){
//	            for(int j=0;j< playingMap.getColumn();j++){
//	            	if(playingMap.getLocation()[i][j]=='p'){
//	            		Fighter fighter = (Fighter)playingMap.getCellsinthemap()[i][j].getContent();
//	            		if(fighter.equals(npc)) {
//	            			this.xofcharactor = i;
//	            			this.yofcharactor = j;
//	            			return;
//	            		}
//	            	}
//	            }
//			}
//		}
	}
	
	@Override
	public void turn() {
		System.out.println("this is friendly strategy, turn function!!!");
		wander();
	}
	protected void wander() {
		for(int i=3;i>0;){
			findChest();
			int num = ((int) (Math.random()*100)) % 4;
			switch (num+1){
				case 1 :
					if(!(character.xOfFighter-1==game.getXofplayer()&&character.yOfFighter==game.getYofplayer())
							&&game.getPlayingmap().npcMove(character.xOfFighter,character.yOfFighter,character.xOfFighter-1,character.yOfFighter)){
						character.xOfFighter = character.xOfFighter-1;
						i--;
					}
					break;
				case 2 :
					if(!(character.xOfFighter==game.getXofplayer()&&character.yOfFighter-1==game.getYofplayer())&&
							game.getPlayingmap().npcMove(character.xOfFighter,character.yOfFighter,character.xOfFighter,character.yOfFighter-1)){
						character.yOfFighter = character.yOfFighter-1;
						i--;
					}
					break;
				case 3 :
					if(!(character.xOfFighter+1==game.getXofplayer()&&character.yOfFighter==game.getYofplayer())&&
							game.getPlayingmap().npcMove(character.xOfFighter,character.yOfFighter,character.xOfFighter+1,character.yOfFighter)){
						character.xOfFighter = character.xOfFighter+1;
						i--;
					}
					break;
				case 4 :
					if(!(character.xOfFighter==game.getXofplayer()&&character.yOfFighter+1==game.getYofplayer())&&
							game.getPlayingmap().npcMove(character.xOfFighter,character.yOfFighter,character.xOfFighter,character.yOfFighter+1)){
						character.yOfFighter = character.yOfFighter+1;
						i--;
					}
					break;
			}
		}
		findChest();
	}

	private void findChest() {
		char[][] location = game.getPlayingmap().getLocation();
//		Fighter fighter = (Fighter)game.getPlayingmap().getCellsinthemap()[character.xOfFighter][character.yOfFighter].getContent();

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
