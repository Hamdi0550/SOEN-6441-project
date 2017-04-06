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
public abstract class DecoratorItem extends BaseItem {

	private Item item;
	public DecoratorItem(Item item) {
		super(item.getName(), item.getBonus(), item.getIncrease());
		this.item = item;
	}
	@Override
	public String getId() {
		return item.getId();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return item.getName();
	}
	@Override
	public int getBonus() {
		// TODO Auto-generated method stub
		return item.getBonus();
	}
	@Override
	public String getIncrease() {
		// TODO Auto-generated method stub
		return item.getIncrease();
	}
	
	
}
