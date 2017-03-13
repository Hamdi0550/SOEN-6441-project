package ddg.utils.builder;

public class NimbleFighterBuilder extends FighterBuilder {

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
