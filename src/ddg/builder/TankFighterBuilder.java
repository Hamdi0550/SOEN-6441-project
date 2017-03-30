package ddg.builder;

import ddg.Config;

/**
 * 
 * This class is to build a character of Tank type
 * @author Zhen Du, Fei Yu
 * @date Mar 10, 2017
 *
 */
public class TankFighterBuilder extends FighterBuilder {

	/**
	 * This method assign different values to a Tank's attributes according to its type
	 */
	@Override
	void assignScores() {
		fighter.setType(Config.TANK);
		fighter.setConstitution(scores.get(0));
		fighter.setDexterity(scores.get(1));
		fighter.setStrength(scores.get(2));
		fighter.setIntelligence(scores.get(3));
		fighter.setCharisma(scores.get(4));
		fighter.setWisdom(scores.get(5));
	}

}
