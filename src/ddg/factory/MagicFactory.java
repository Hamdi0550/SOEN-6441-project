/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.factory;

import ddg.model.entity.Enchantment;
import ddg.model.entity.Burning;
import ddg.model.entity.Freezing;
import ddg.model.entity.Frightening;
import ddg.model.entity.Magic;
import ddg.model.entity.MagicWeaponItem;
import ddg.model.entity.Pacifying;
import ddg.model.entity.Slaying;

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
public class MagicFactory {

	/**
	 * Constructors
	 * 
	 */
	public MagicFactory() {
		// TODO Auto-generated constructor stub
	}

	public static Magic getMagic(String magic) {
//		return new Ability(magic);
		if(MagicWeaponItem.FREEZING.equals(magic)) {
			return new Freezing();
		} else if(MagicWeaponItem.BURNING.equals(magic)) {
			return new Burning();
		} else if(MagicWeaponItem.SLAYING.equals(magic)) {
			return new Slaying();
		} else if(MagicWeaponItem.FRIGHTENING.equals(magic)) {
			return new Frightening();
		} else if(MagicWeaponItem.PACIFYING.equals(magic)) {
			return new Pacifying();
		}
		return null;
	}
}
