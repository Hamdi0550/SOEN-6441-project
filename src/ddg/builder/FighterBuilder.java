package ddg.builder;

import java.util.ArrayList;
import java.util.Comparator;

import ddg.model.Fighter;
import ddg.utils.Dice;

/**
 * 
 * This class is to provide a pattern for building a character
 * @author Zhen Du, Fei Yu
 * @date Mar 10, 2017
 *
 */
public abstract class FighterBuilder {

	protected Fighter fighter;
	protected ArrayList<Integer> scores = new ArrayList<Integer>();
	
	/**
	 * This method is to return the character that is built.
	 * @return fighter The character object
	 */
	public Fighter getFighter() {
		return fighter;
	}
		
	/**
	 * This method is to set the character that is built.
	 * @param fighter The character object
	 */
	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}



	/**
	 * This method creates an object of Fighter class
	 */
	public void createFighter() {
		fighter = new Fighter();
		
	}

	/**
	 * This method generate 6 random numbers by 4d6 rules and put them into a list in descending order.
	 */
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
	
	/**
	 * This method clears the list of scores.
	 */
	public void clearScores(){
		scores.clear();
	}

	/**
	 * This method provides a definition for assigning scores to different types of characters.
	 */
	abstract void assignScores();
}
