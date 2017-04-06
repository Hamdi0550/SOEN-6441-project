/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.strategy;

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
public abstract class FriendlyStrategy implements IStrategy {

	/**
	 * Constructors
	 * 
	 */
	public FriendlyStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void turn(TurnCallback cb) {
		wander(cb);
	}
	protected abstract void wander(TurnCallback cb);
}
