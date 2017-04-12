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
}
