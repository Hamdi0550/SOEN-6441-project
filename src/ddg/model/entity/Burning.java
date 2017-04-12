/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.model.entity;

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
}
