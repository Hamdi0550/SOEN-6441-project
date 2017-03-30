package ddg.builder;

import ddg.model.Fighter;

/**
 * 
 * This class is to provide a tool to generate different types of characters.
 * @author Zhen Du, Fei Yu
 * @date Mar 10, 2017
 *
 */
public class FighterExplorer {

	private FighterBuilder fighterBuilder;

	/**
	 * This method set a builder type.
	 * @param newBuilder The builder type
	 */
	public void setBuilder(FighterBuilder newBuilder) {
		this.fighterBuilder = newBuilder;
	}

	/**
	 * This method construct a Fighter object according to the builder type.
	 */
	public void constructFighter() {
		fighterBuilder.createFighter();
		fighterBuilder.generateScores();
		fighterBuilder.assignScores();
		fighterBuilder.clearScores();
	}
	
	public void constructFighter(Fighter fighter) {
		fighterBuilder.generateScores();
		fighterBuilder.assignScores();
		fighterBuilder.clearScores();
	}

	/**
	 * This method return the Fighter object
	 * @return The fighter object
	 */
	public Fighter getFighter() {
		return fighterBuilder.getFighter();
	}

}
