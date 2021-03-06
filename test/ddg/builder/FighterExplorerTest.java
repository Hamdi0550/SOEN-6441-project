package ddg.builder;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import ddg.model.Fighter;

/**
 * This class is to test Builder pattern for creating different types of character.
 * 
 * @author Zhen Du
 * @date Mar 25, 2017
 *
 */
public class FighterExplorerTest {

	private FighterExplorer explorer;

	/**
	 * This method setup initial testing data.
	 */
	@Before
	public void setUp() throws Exception {
		explorer = new FighterExplorer();
	}

	/**
	 * This method tests if the Builder pattern correctly assign scores when creating a Bully.
	 */
	@Test
	public void testBullyBuilder() {
		explorer.setBuilder(new BullyFighterBuilder());
		explorer.constructFighter();
		Fighter f = explorer.getFighter();
		assertFalse(f.getStrength()<f.getConstitution()||f.getStrength()<f.getDexterity()
				|| f.getStrength()<f.getIntelligence()||f.getStrength()<f.getCharisma()
				||f.getStrength()<f.getWisdom());
	}

	/**
	 * This method tests if the Builder pattern correctly assign scores when creating a Nimble.
	 */
	@Test
	public void testNimbleBuilder() {
		explorer.setBuilder(new NimbleFighterBuilder());
		explorer.constructFighter();
		Fighter f = explorer.getFighter();
		assertFalse(f.getDexterity()<f.getConstitution()||f.getDexterity()<f.getStrength()
				|| f.getDexterity()<f.getIntelligence()||f.getDexterity()<f.getCharisma()
				||f.getDexterity()<f.getWisdom());
	}

	/**
	 * This method tests if the Builder pattern correctly assign scores when creating a Tank.
	 */
	@Test
	public void testTankBuilder() {
		explorer.setBuilder(new TankFighterBuilder());
		explorer.constructFighter();
		Fighter f = explorer.getFighter();
		assertFalse(f.getConstitution()<f.getDexterity()||f.getConstitution()<f.getStrength()
				|| f.getConstitution()<f.getIntelligence()||f.getConstitution()<f.getCharisma()
				||f.getConstitution()<f.getWisdom());
	}
}
