/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.strategy;

import java.io.Serializable;

import javax.swing.JOptionPane;

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
	private static final long serialVersionUID = 1L;
	/**
	 * Constructors
	 * 
	 */
	public HumanStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void turn() {
		JOptionPane.showMessageDialog(null, "Is your Turn!!", "Your Turn!", JOptionPane.INFORMATION_MESSAGE);
		moveCells();
		attack();
		interaction();
	}

	protected abstract void moveCells();
	protected abstract void attack();
	protected abstract void interaction();
}
