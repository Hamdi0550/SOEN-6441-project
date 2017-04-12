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

	private static final long serialVersionUID = -888475640284563800L;

	public FrighteningStrategy(Game game, int x, int y) {
		super(game, x, y);
	}

	@Override
	public boolean enchantNext(Fighter npc) {
		turn(null);
		return true;
	}

	@Override
	public int getIndex() {
		return 2;
	}

}
