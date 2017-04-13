package ddg.model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Test;

import ddg.Config;
import ddg.model.entity.BaseCampaign;
import ddg.strategy.FriendlyStrategy;
import ddg.utils.Dice;
import ddg.utils.Utils;

public class FighterTest {

	static Fighter[] player = new Fighter[4];

	/**
	 * 
	 * This method setUpBeforeClass
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FighterModel fighterModel = Utils.readObject(Config.CHARACTER_FILE, FighterModel.class);
		player[0] = fighterModel.getFighters().get("Tank1");
		player[1] = fighterModel.getFighters().get("Tank2");
		player[2] = fighterModel.getFighters().get("Tank3");
	}

	/**
	 * 
	 * This method is testAttackRollRanged
	 *
	 */
	@Test
	public void testAttackRollMelee() {
		assertEquals(player[1].getAttackBonus(), player[1].getStrModifier());
	}

	/**
	 * 
	 * This method
	 *
	 */
	@Test
	public void testAttackRollRanged() {
		assertEquals(player[2].getAttackBonus(), player[2].getDexModifier());
	}

	/**
	 * 
	 * This method is testAttackRollNoWeapon
	 *
	 */
	@Test
	public void testAttackRollNoWeapon() {
		assertNotEquals(player[0].getAttackBonus(), player[0].getLevel());
	}

	/**
	 * 
	 * This method is testAttackHits
	 *
	 */
	@Test
	public void testAttackHits() {
		int attackRoll = Dice.d20Roll() + player[0].getAttackBonus();
		int origin = player[1].getHitPoints();
		if (attackRoll >= player[1].getArmorClass()) {
			player[1].beAttacked(player[0].getDamageBonus());
			assertTrue(origin >= player[1].getHitPoints());
		} else {
			assertEquals(origin, player[1].getHitPoints());
		}

	}

	/**
	 * 
	 * This method is testAttackDamage
	 *
	 */
	@Test
	public void testAttackDamage() {
		int attackRoll = Dice.d20Roll() + player[0].getAttackBonus();
		int origin = player[1].getHitPoints();
		if (attackRoll >= player[1].getArmorClass()) {
			player[1].beAttacked(player[0].getDamageBonus());
			assertFalse(origin - player[1].getHitPoints() == player[0].getDamageBonus());
		} else {
			assertEquals(origin, player[1].getHitPoints());
		}

	}

	/**
	 * 
	 * This method is testCharacterStrategy
	 *
	 */
	@Test
	public void testCharacterStrategy() {
		FighterModel fighterModel = Utils.readObject(Config.CHARACTER_FILE, FighterModel.class);
		player[3] = fighterModel.getFighters().get("Tank4");

		String g = Utils.readFile(Config.CAMPAIGN_FILE);
		CampaignEditorModel campaignModel = Utils.fromJson(g, CampaignEditorModel.class);
		BaseCampaign campaign = campaignModel.getItemByIndex(0);

		Game game = new Game(new GameModel(player[3], campaign), null);
		Point original = new Point(game.getXofplayer(), game.getYofplayer());

		player[3].setStrategy(new FriendlyStrategy(game, player[3]));

		Point newPoint = new Point(game.getXofplayer(), game.getYofplayer());

		assertEquals(original, newPoint);
	}
}
