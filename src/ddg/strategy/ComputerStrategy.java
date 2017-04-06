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
public abstract class ComputerStrategy implements IStrategy {

	/**
	 * Constructors
	 * 
	 */
	public ComputerStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void turn(TurnCallback cb) {
		searchExit(cb);
	}

	protected abstract void searchExit(TurnCallback cb);
}
