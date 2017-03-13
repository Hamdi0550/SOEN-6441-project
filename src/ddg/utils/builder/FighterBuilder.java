package ddg.utils.builder;

import java.util.ArrayList;
import java.util.Comparator;

import ddg.model.Fighter;
import ddg.utils.Dice;

public abstract class FighterBuilder {

	protected Fighter fighter;
	protected ArrayList<Integer> scores = new ArrayList<Integer>();
	
	public Fighter getFighter() {
		return fighter;
	}

	public void createFighter() {
		fighter = new Fighter();
	}

	public void generateScores() {
		scores.add(Dice.d46Roll());
		scores.add(Dice.d46Roll());
		scores.add(Dice.d46Roll());
		scores.add(Dice.d46Roll());
		scores.add(Dice.d46Roll());
		scores.add(Dice.d46Roll());
		scores.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		System.out.println("scores:"+scores.toString());
	}

	abstract void assignScores();
}
