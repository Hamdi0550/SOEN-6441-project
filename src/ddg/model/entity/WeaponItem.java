package ddg.model.entity;

import ddg.model.Fighter;

public abstract class WeaponItem implements Item {
	
	private static final long serialVersionUID = -1835239647971587899L;
	public static final String WEAPON_TYPE = "weaponType";
	public static final String WEAPON_MELEE = "Melee";
	public static final String WEAPON_RANGED = "Ranged";
	private String weaponType = WEAPON_MELEE;
	
	
	private Item item;
	public WeaponItem(Item item) {
//		super(item.getName(), item.getBonus(), item.getIncrease());
		this.item = item;
	}
	
	public String getWeaponType() {
//		if(!WEAPON.equals(name))
//			return null;
		return weaponType;
	}

	public void setWeaponType(String weaponType) {
//		if(!WEAPON.equals(name))
//			return;
		this.weaponType = weaponType;
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
		if(getOwner()!=null) {
			if(Enchantment.ATTACK_BONUS.equals(item.getIncrease())) {
				return item.getBonus()+((Fighter)item.getOwner()).getStrModifier();
			} else if(Enchantment.DAMAGE_BONUS.equals(item.getIncrease())) {
				if(WEAPON_MELEE.equals(weaponType)) {
					return ((Fighter)item.getOwner()).getStrModifier();
				} else if(WEAPON_RANGED.equals(weaponType)) {
					return 0;
				}
			}
		}
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
