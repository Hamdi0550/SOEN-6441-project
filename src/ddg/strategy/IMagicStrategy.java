package ddg.strategy;

import java.io.Serializable;

import ddg.model.Fighter;

/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 12, 2017
 */
public interface IMagicStrategy extends Serializable {
	public int getTurns();
	public boolean enchantNext(Fighter npc);
	public int getIndex();
}
