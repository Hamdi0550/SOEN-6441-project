/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.model.entity;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

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
public class MagicItem extends DecoratorItem {

	public static final String FREEZING = "Freezing";
	public static final String BURNING = "Burning";
	public static final String SLAYING = "Slaying";
	public static final String FRIGHTENING = "Frightening";
	public static final String PACIFYING = "Pacifying";
	
	public static final String WEAPON_MAGIC_ABILITY = "weaponMagicAbility";
	public static final String[] MAGIC = {FREEZING, BURNING, SLAYING, FRIGHTENING, PACIFYING};
	
	public ArrayList<Ability> ability = new ArrayList<Ability>();
	/**
	 * Constructors
	 * 
	 * @param name
	 */
	public MagicItem(Item item) {
		super(item);
	}

	@Override
	public String getId() {
		return super.getId();
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public int getBonus() {
		return super.getBonus();
	}

	@Override
	public String getIncrease() {
		return super.getIncrease();
	}
	
	public void addAbility(Ability a) {
		ability.add(a);
	}
	
	public DefaultListModel getListModel() {
		DefaultListModel l = new DefaultListModel();
		for(Ability i : ability) {
			l.addElement(i.getClass());
		}
		return l;
	}
}
