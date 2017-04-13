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
import ddg.ui.view.MapPanelInGame;

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
public class Burning extends Magic {

	private static final long serialVersionUID = -6221328594501953658L;

	/**
	 * Constructors
	 * 
	 */
	public Burning() {
		super(MagicWeaponItem.BURNING);
	}

	@Override
	public String getName() {
		return name;
	}

	public int getDamage() {
		return weapon==null?0:5*weapon.getBonus();
	}

	@Override
	public void attack(Fighter npc) {
		int turns[] = {3};
		npc.setMagicStrategy(new IMagicStrategy() {
			private static final long serialVersionUID = 5208777579557355918L;

			@Override
			public int getTurns() {
				return turns[0];
			}

			@Override
			public boolean enchantNext(Fighter npc) {
				turns[0]--;
				MapPanelInGame.printLog(" " + npc.getName() + " is burning by: " + getDamage() + " this is round " + turns[0] + " ");
				return npc.beAttacked(getDamage());
			}

			@Override
			public int getIndex() {
				return 1;
			}
			
		});
	}
}
