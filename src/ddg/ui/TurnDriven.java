package ddg.ui;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import ddg.model.Fighter;
import ddg.strategy.HumanStrategy;
import ddg.strategy.IStrategy.TurnCallback;
import ddg.ui.view.MapPanelInGame;
import ddg.utils.Dice;

/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
public class TurnDriven {

	private ArrayList<Fighter> fighters = new ArrayList<Fighter>();
	ArrayList<Integer> mapOrderRecode;
	ArrayList<Integer> orderArr;
//	private boolean stop = false;
	public TurnDriven() {
		
	}

	/**
	 * @return fighters array list which recode all fighter in map
	 */
	public ArrayList<Fighter> getFighters() {
		return fighters;
	}
	
	/**
	 * add fighter into array list 
	 * @param fighter	fighter would like to join in
	 */
	public void addFighter(Fighter fighter) {
		if(!this.fighters.contains(fighter)) {
			this.fighters.add(fighter);
		}
	}
	
	/**
	 * begin Turn, calculate running order
	 */
	public void startTurn() {
		this.mapOrderRecode = new ArrayList<>(this.fighters.size()); 
		System.out.println("TurnDriven start");
		for(int i=0;i<this.fighters.size();i++){
			mapOrderRecode.add((Integer)(Dice.d20Roll()+fighters.get(i).getDexModifier()));
		}
		this.orderArr = (ArrayList<Integer>) mapOrderRecode.clone();
//		if(!stop){
//			next();
//		}
//		if(!stop) {
//			int sum = this.fighters.size();
//			if(sum == 0)
//				return;
//			int order[] = new int[sum];
//			for(int i=0;i<sum;i++) {
//				order[i] = i;
//			}
//			next(order, sum);
//		}
	}

	/**
	 * find which fighter is next and run turn() function
	 * @return	fighter who is the next
	 */
	public Fighter next() {
		
//		Fighter f = null;
//		boolean newTurn = false;
//		while(true) {
//			int d = Dice.d20Roll()%sum;//sum should < 20
//			if(order[d] != -1) {
//				order[d] = -1;
//				f = fighters.get(d);
//				break;
//			}
//			int total = 0;
//			for(int i=0;i<sum;i++) {
//				total +=order[i];
//			}
//			if(total == -sum) {
//				newTurn = true;
//				break;
//			}
//		}
		
		int maxlocation = 0;
		for(int i=1;i<this.fighters.size();i++){
			if(orderArr.get(i)>orderArr.get(maxlocation)){
				maxlocation = i;
			}
		}
		
		if(orderArr.get(maxlocation)==-1) {
			this.orderArr = (ArrayList<Integer>) mapOrderRecode.clone();

			MapPanelInGame.printLog("\n");
			return next();
		}
		
		if(fighters.get(maxlocation).isAlive()) {
			orderArr.set(maxlocation, -1);
			fighters.get(maxlocation).turn();
			
		}
		else{
			fighters.remove(maxlocation);
			orderArr.remove(maxlocation);
			MapPanelInGame.printLog("\n");
			return next();
		}

		MapPanelInGame.printLog("\n");
		return fighters.get(maxlocation);
	}
//	
//	public void finishTurn() {
//		System.out.println("TurnDriven finish");
//		stop = true;
//	}
}
