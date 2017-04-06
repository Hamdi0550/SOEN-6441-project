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
public abstract class AgressiveStrategy implements IStrategy {

	/**
	 * Constructors
	 * 
	 */
	public AgressiveStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void turn(TurnCallback cb) {
		searchPlayer(cb);
	}

	protected abstract void searchPlayer(TurnCallback cb);
}
