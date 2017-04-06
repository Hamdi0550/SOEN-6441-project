/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.factory;

import ddg.model.entity.Ability;
import ddg.model.entity.Burning;
import ddg.model.entity.Freezing;
import ddg.model.entity.Frightening;
import ddg.model.entity.MagicItem;
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

	public static Ability getMagic(String magic) {
		return new Ability(magic);
//		if(MagicItem.FREEZING.equals(magic)) {
//			return new Freezing();
//		} else if(MagicItem.BURNING.equals(magic)) {
//			return new Burning();
//		} else if(MagicItem.SLAYING.equals(magic)) {
//			return new Slaying();
//		} else if(MagicItem.FRIGHTENING.equals(magic)) {
//			return new Frightening();
//		} else if(MagicItem.PACIFYING.equals(magic)) {
//			return new Pacifying();
//		}
//		return null;
	}
}
