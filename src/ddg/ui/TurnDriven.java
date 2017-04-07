package ddg.ui;

import java.util.ArrayList;

import ddg.model.Fighter;
import ddg.strategy.IStrategy.TurnCallback;
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
	

	private boolean stop = false;
	public TurnDriven() {
		
	}

	public ArrayList<Fighter> getFighters() {
		return fighters;
	}
	
	public void addFighter(Fighter fighter) {
		if(!this.fighters.contains(fighter)) {
			this.fighters.add(fighter);
		}
	}
	
	public void startTurn() {
		System.out.println("TurnDriven start");
		if(!stop) {
			int sum = this.fighters.size();
			if(sum == 0)
				return;
			int order[] = new int[sum];
			for(int i=0;i<sum;i++) {
				order[i] = i;
			}
			next(order, sum);
		}
	}

	private void next(int order[], int sum) {
		
		Fighter f = null;
		boolean newTurn = false;
		while(true) {
			int d = Dice.d20Roll()%sum;//sum should < 20
			if(order[d] != -1) {
				order[d] = -1;
				f = fighters.get(d);
				break;
			}
			int total = 0;
			for(int i=0;i<sum;i++) {
				total +=order[i];
			}
			if(total == -sum) {
				newTurn = true;
				break;
			}
		}
		if(newTurn) {
			startTurn();
			return;
		}
		if(f.isAlive()) {
			f.turn(new TurnCallback() {
				
				@Override
				public void finish() {
					next(order, sum);
				}
				
			});
		}
	}
	
	public void finishTurn() {
		System.out.println("TurnDriven finish");
		stop = true;
	}
}
