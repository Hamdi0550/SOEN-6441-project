package ddg.utils.builder;

/**
 * 
 * This class is to build a character of Nimble type
 * @author Zhen Du, Fei Yu
 * @version 1.1
 *
 */
public class NimbleFighterBuilder extends FighterBuilder {

	/**
	 * This method assign different values to a Nimble's attributes according to its type
	 */
	@Override
	void assignScores() {
		fighter.setDexterity(scores.get(0));
		fighter.setConstitution(scores.get(1));
		fighter.setStrength(scores.get(2));
		fighter.setIntelligence(scores.get(3));
		fighter.setCharisma(scores.get(4));
		fighter.setWisdom(scores.get(5));
	}

}
