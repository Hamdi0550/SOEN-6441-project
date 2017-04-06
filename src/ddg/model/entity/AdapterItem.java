package ddg.model.entity;

import java.io.Serializable;

/**
 * This class
 * 
 * @author Zhen Du
 * @date Mar 11, 2017
 */
@Deprecated
public class AdapterItem implements Item, Serializable {
	
	private static final long serialVersionUID = -8157381198311896595L;
	private int level = 0;
	private BaseItem item;
	
	/**
	 * 
	 * Constructors for AdapterItem
	 * 
	 * @param item
	 * @param level
	 */
	public AdapterItem(BaseItem item, int level) {
		this.item = item;
		this.level = level;
	}
	
	/**
	 * 
	 * This method getBonusByLevel
	 * 
	 * @return
	 */
	private int getBonusByLevel() {
		if(this.level <= 0)
			return 0;
		else if(this.level > 17)
			return 5;
		else
			return (this.level-1)/4 + 1;
	}
	
	/**
	 * 
	 * This method getAdapterBonus
	 * 
	 * @return
	 */
	public int getAdapterBonus() {
		return this.item.getBonus() + getBonusByLevel();
	}
	@Override
	public String getId() {
		return this.item.getId();
	}
	@Override
	public String getName() {
		return this.item.getName();
	}
	@Override
	public int getBonus() {
		return this.item.getBonus();
	}
	@Override
	public String getIncrease() {
		return this.item.getIncrease();
	}
}
