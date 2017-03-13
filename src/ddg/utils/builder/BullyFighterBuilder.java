package ddg.utils.builder;

public class BullyFighterBuilder extends FighterBuilder {

	@Override
	void assignScores() {
		fighter.setStrength(scores.get(0));
		fighter.setConstitution(scores.get(1));
		fighter.setDexterity(scores.get(2));
		fighter.setIntelligence(scores.get(3));
		fighter.setCharisma(scores.get(4));
		fighter.setWisdom(scores.get(5));
	}

}
