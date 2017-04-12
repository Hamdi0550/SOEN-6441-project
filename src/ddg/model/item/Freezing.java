/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.model.item;

import ddg.model.Fighter;
import ddg.strategy.IMagicStrategy;

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
public class Freezing extends Magic {

	private static final long serialVersionUID = -6387511701613860418L;

	/**
	 * Constructors
	 * 
	 */
	public Freezing() {
		super(MagicWeaponItem.FREEZING);
	}

	@Override
	public String getName() {
		return name;
	}
	
	public int getFreezingTurns() {
		return weapon==null?0:weapon.getBonus();
	}

	@Override
	public void attack(Fighter npc) {
		int turns[] = {getFreezingTurns()};
		npc.setMagicStrategy(new IMagicStrategy() {
			private static final long serialVersionUID = -4587647564027450860L;

			@Override
			public int getTurns() {
				return turns[0];
			}

			@Override
			public boolean enchantNext(Fighter npc) {
				turns[0]--;
				return false;
			}

			@Override
			public int getIndex() {
				return 0;
			}
			
		});
	}
}
