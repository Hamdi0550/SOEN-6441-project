package ddg.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import ddg.Config;
import ddg.utils.Dice;
import ddg.utils.Utils;

public class FighterTest {

	static Fighter[] player = new Fighter[3];

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FighterModel fighterModel = Utils.readObject(Config.CHARACTER_FILE, FighterModel.class);
		player[0] = fighterModel.getFighters().get("Tank1");
		player[1] = fighterModel.getFighters().get("Tank2");
		player[2] = fighterModel.getFighters().get("Tank3");
	}

	@Test
	public void testAttackRollMelee() {
		assertEquals(player[1].getAttackBonus(), player[1].getStrModifier());
	}

	@Test
	public void testAttackRollRanged() {
		assertEquals(player[2].getAttackBonus(), player[2].getDexModifier());
	}
	
	@Test
	public void testAttackRollNoWeapon() {
		assertEquals(player[0].getAttackBonus(), player[0].getLevel());
	}
	
	@Test
	public void testAttackHits() {
		int attackRoll = Dice.d20Roll() + player[0].getAttackBonus();
		int origin = player[1].getHitPoints();
		if(attackRoll >= player[1].getArmorClass()){
			player[1].beAttacked(player[0].getDamageBonus());
			assertTrue(origin >= player[1].getHitPoints());
		} else {
			assertEquals(origin, player[1].getHitPoints());
		}
		
	}
	
	@Test
	public void testAttackDamage() {
		int attackRoll = Dice.d20Roll() + player[0].getAttackBonus();
		int origin = player[1].getHitPoints();
		if(attackRoll >= player[1].getArmorClass()){
			player[1].beAttacked(player[0].getDamageBonus());
			assertTrue(origin-player[1].getHitPoints() == player[0].getDamageBonus());
		} else {
			assertEquals(origin, player[1].getHitPoints());
		}
		
	}
}
