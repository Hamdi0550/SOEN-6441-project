/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.factory;

import ddg.model.item.Burning;
import ddg.model.item.Enchantment;
import ddg.model.item.Freezing;
import ddg.model.item.Frightening;
import ddg.model.item.Magic;
import ddg.model.item.MagicWeaponItem;
import ddg.model.item.Pacifying;
import ddg.model.item.Slaying;

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

	/**
	 * This method is to get the enchantment instance.
	 * @param magic
	 */
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
