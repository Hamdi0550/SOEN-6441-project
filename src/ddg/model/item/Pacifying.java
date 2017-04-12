/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.model.item;

import ddg.model.Fighter;
import ddg.model.Game;
import ddg.strategy.FriendlyStrategy;

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
public class Pacifying extends Magic {

	private static final long serialVersionUID = -7180171488475488152L;

	/**
	 * Constructors
	 * 
	 */
	public Pacifying() {
		super(MagicWeaponItem.PACIFYING);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void attack(Fighter npc) {
		npc.setStrategy(new FriendlyStrategy((Game)npc.getOwner(), npc));
	}
}
