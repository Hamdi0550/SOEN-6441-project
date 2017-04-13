package ddg.strategy;

import ddg.model.Fighter;
import ddg.model.Game;

/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 12, 2017
 */
public abstract class FrighteningStrategy extends AgressiveStrategy implements IMagicStrategy {
	private Fighter npc;
	private static final long serialVersionUID = -888475640284563800L;

	public FrighteningStrategy(Game game, Fighter npc) {
		super(game, npc);
	}

	@Override
	public boolean enchantNext(Fighter npc) {
		this.npc = npc;
		int turns = getTurns();
		turns--;
		turn();
		return false;
	}
	
	@Override
	public void turn() {
		int xOfNextforNpc = npc.xOfFighter;
		int yOfNextforNpc = npc.yOfFighter;
		for(int i=1;i<=3;i++){
			if(game.getXofplayer()-npc.xOfFighter>0&&xOfNextforNpc-1>=0){
				xOfNextforNpc = xOfNextforNpc- 1;
			}
			else if(game.getXofplayer()-npc.xOfFighter<0&&xOfNextforNpc+1<game.getPlayingmap().getRow()){
				xOfNextforNpc = xOfNextforNpc + 1;
			}
			
			else if(game.getYofplayer()-npc.yOfFighter>0&&yOfNextforNpc-1>=0){
				yOfNextforNpc = yOfNextforNpc -1;
			}
			else if(game.getYofplayer()-npc.yOfFighter<0&&yOfNextforNpc+1<game.getPlayingmap().getColumn()){
				yOfNextforNpc = yOfNextforNpc +1;
			}
			
			if(game.getPlayingmap().npcMove(npc.xOfFighter, npc.yOfFighter, xOfNextforNpc, yOfNextforNpc)){
				npc.xOfFighter = xOfNextforNpc;
				npc.yOfFighter = yOfNextforNpc;
			}
		}
	}

	@Override
	public int getIndex() {
		return 2;
	}

}
