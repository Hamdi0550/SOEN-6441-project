package ddg.utils.builder;

import ddg.model.Fighter;

public class FighterExplorer {

	private FighterBuilder fighterBuilder;

	public void setBuilder(FighterBuilder newBuilder) {
		this.fighterBuilder = newBuilder;
	}

	public void constructFighter() {
		fighterBuilder.createFighter();
		fighterBuilder.generateScores();
		fighterBuilder.assignScores();
	}

	public Fighter getFighter() {
		return fighterBuilder.getFighter();
	}

}
