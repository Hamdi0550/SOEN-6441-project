/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.strategy;

import java.io.Serializable;

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
public abstract class FriendlyStrategy implements IStrategy,Serializable {
	private static final long serialVersionUID = 1L;
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
