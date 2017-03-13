package ddg.utils.builder;

import ddg.model.Fighter;

public class FighterExplorer {

	private FighterBuilder builder;

	public void setBuilder(FighterBuilder builder) {
		this.builder = builder;
	}

	public void constructFighter() {
		builder.createFighter();
		builder.generateScores();
		builder.assignScores();
	}

	public Fighter getFighter() {
		return builder.getFighter();
	}

}
