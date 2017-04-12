/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.model.entity;

import ddg.model.Fighter;

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
public class Slaying extends Magic {

	private static final long serialVersionUID = -217973515946560710L;

	/**
	 * Constructors
	 * 
	 */
	public Slaying() {
		super(MagicWeaponItem.SLAYING);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void attack(Fighter npc) {
		npc.die();
	}
}
