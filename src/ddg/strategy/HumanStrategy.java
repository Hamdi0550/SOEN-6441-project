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
public abstract class HumanStrategy implements IStrategy {

	/**
	 * Constructors
	 * 
	 */
	public HumanStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void turn(TurnCallback cb) {
		moveCells(new TurnCallback() {

			@Override
			public void finish() {
				attack(new TurnCallback() {

					@Override
					public void finish() {
						interaction(cb);
					}
					
				});
			}
			
		});
	}

	protected abstract void moveCells(TurnCallback cb);
	protected abstract void attack(TurnCallback cb);
	protected abstract void interaction(TurnCallback cb);
}
