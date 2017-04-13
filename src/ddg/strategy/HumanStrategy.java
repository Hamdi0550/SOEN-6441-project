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

import ddg.ui.view.MapPanelInGame;

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
public class HumanStrategy implements IStrategy {
	private static final long serialVersionUID = 1L;
	private int movetimes = 0;
	private int attacktimes =0;
	/**
	 * Constructors
	 * 
	 */
	public HumanStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void turn() {
//		JOptionPane.showMessageDialog(null, "Is your Turn!!", "Your Turn!", JOptionPane.INFORMATION_MESSAGE);
		MapPanelInGame.printLog("Is your Turn, you can Move 3 times and Attack 1 time!!!");
		this.movetimes = 3;
		this.attacktimes = 1;
	}

	 /**
	  * as counter to check if play can move the character on the map
	 * @return true if it is possible to move on map, false if cannot
	 */
	public boolean moveCells(){
		if(movetimes>0){
			movetimes--;
			return true;
		}
		return false;
	}
	/**
	 * as counter to check if play can attack 
	 * @return true if it is possible to attack others, false if cannot
	 */
	public boolean attack(){
		if(attacktimes > 0){
			attacktimes--;
			return true;
		}
		return false;
	}
}
