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
public class MagicWeaponItem extends WeaponItem {

	private static final long serialVersionUID = -4252729232240057434L;
	public static final String FREEZING = "Freezing";
	public static final String BURNING = "Burning";
	public static final String SLAYING = "Slaying";
	public static final String FRIGHTENING = "Frightening";
	public static final String PACIFYING = "Pacifying";
	
	public static final String WEAPON_MAGIC_ABILITY = "weaponMagicAbility";
	public static final String[] MAGIC = {FREEZING, BURNING, SLAYING, FRIGHTENING, PACIFYING};
	
	public ArrayList<Magic> magic = new ArrayList<Magic>();
	/**
	 * Constructors
	 * 
	 * @param name
	 */
	public MagicWeaponItem(Item item) {
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
//		if(getOwner()!=null) {
//			int burn = 0;
//			for(Magic i : magic) {
//				if(i instanceof Burning) {
//					burn++;
//				}
//			}
//			if(burn > 0) {
//				return 5*burn*super.getBonus();
//			}
//		}
		return super.getBonus();
	}
	
	@Override
	public String getIncrease() {
		return super.getIncrease();
	}
	
	public void addAbility(Magic a) {
		a.setWeapon(this);
		magic.add(a);
	}
	
	public ArrayList<Magic> getEnchantment() {
		return magic;
	}
	
	public DefaultListModel getListModel() {
		DefaultListModel l = new DefaultListModel();
		for(Magic i : magic) {
			l.addElement(i.getName());
		}
		return l;
	}
	
	public void attack(Fighter npc) {
		for(Magic m : magic) {
			m.attack(npc);
		}
	}
}
