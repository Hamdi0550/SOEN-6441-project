package ddg.model.item;

import ddg.IOwner;

public abstract class WeaponItem implements Item {
	
	private static final long serialVersionUID = -1835239647971587899L;
	public static final String WEAPON_TYPE = "weaponType";
	public static final String WEAPON_MELEE = "Melee";
	public static final String WEAPON_RANGED = "Ranged";
	private String weaponType = WEAPON_MELEE;
	private int range;
	
	private Item item;
	public WeaponItem(Item item) {
		this.item = item;
		if(weaponType.equals(WEAPON_MELEE))
			this.range = 1;
	}
	
	public String getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}
	
	public int getRange(){
		return range;
	}
	@Override
	public String getId() {
		return item.getId();
	}
	
	@Override
	public String getName() {
		return item.getName();
	}
	
	@Override
	public int getBonus() {
//		if(getOwner()!=null) {
//			if(Enchantment.ATTACK_BONUS.equals(item.getIncrease())) {
//				return item.getBonus()+((Fighter)item.getOwner()).getStrModifier();
//			} else if(Enchantment.DAMAGE_BONUS.equals(item.getIncrease())) {
//				if(WEAPON_MELEE.equals(weaponType)) {
//					return ((Fighter)item.getOwner()).getStrModifier();
//				} else if(WEAPON_RANGED.equals(weaponType)) {
//					return 0;
//				}
//			}
//		}
		return item.getBonus();
	}

	@Override
	public String getIncrease() {
		return item.getIncrease();
	}
	
	@Override
	public void setId(String id) {
		item.setId(id);
	}

	@Override
	public void setName(String name) {
		item.setName(name);
	}

	@Override
	public void setBonus(int bonus) {
		item.setBonus(bonus);
	}

	@Override
	public void setIncrease(String increase) {
		item.setIncrease(increase);
	}

	@Override
	public IOwner getOwner() {
		return item.getOwner();
	}

	@Override
	public void setOwner(IOwner owner) {
		item.setOwner(owner);
	}
}
