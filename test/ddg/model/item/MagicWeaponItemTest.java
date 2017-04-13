package ddg.model.item;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ddg.Config;
import ddg.model.Fighter;
import ddg.model.FighterModel;
import ddg.strategy.FriendlyStrategy;
import ddg.strategy.FrighteningStrategy;
import ddg.utils.Utils;

public class MagicWeaponItemTest {

	Fighter player;
	Fighter npc;
	
	/**
	 * 
	 * This method setUp before running
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		FighterModel fighterModel = Utils.readObject(Config.CHARACTER_FILE, FighterModel.class);
		player = fighterModel.getFighters().get("Tank3");
		npc = fighterModel.getFighters().get("Tank4");
	}

	/**
	 * 
	 * This method is testFreezing
	 *
	 */
	@Test
	public void testFreezing() {
		Item i = player.getWearItemByName(Item.WEAPON);
		if (i instanceof MagicWeaponItem){
			((MagicWeaponItem)i).attack(npc);
			assertTrue(npc.getMagicStrategy()[0] != null);
		} else {
			assertFalse(npc.getMagicStrategy()[0] != null);			
		}
	}

	/**
	 * 
	 * This method is testBuring
	 *
	 */
	@Test
	public void testBuring() {
		Item i = player.getWearItemByName(Item.WEAPON);
		if (i instanceof MagicWeaponItem){
			((MagicWeaponItem)i).attack(npc);
			assertFalse(npc.getMagicStrategy()[1] == null);
		} else {
			assertTrue(npc.getMagicStrategy()[1] == null);
		}
	}
	
	/**
	 * 
	 * This method is testFrightening
	 *
	 */
	@Test
	public void testFrightening() {
		Item i = player.getWearItemByName(Item.WEAPON);
		if (i instanceof MagicWeaponItem){
			((MagicWeaponItem)i).attack(npc);
			assertTrue(npc.getMagicStrategy()[2] instanceof FrighteningStrategy);
		} else {
			assertFalse(npc.getMagicStrategy()[2] instanceof FrighteningStrategy);
		}
	}
	
	/**
	 * 
	 * This method is testPacifying
	 *
	 */
	@Test
	public void testPacifying() {
		Item i = player.getWearItemByName(Item.WEAPON);
		if (i instanceof MagicWeaponItem){
			((MagicWeaponItem)i).attack(npc);
			assertTrue(npc.getBehaviorStrategy() instanceof FriendlyStrategy);
		} else {
			assertFalse(npc.getBehaviorStrategy() instanceof FriendlyStrategy);
		}
	}
	
	/**
	 * 
	 * This method is testWeaponRang
	 *
	 */
	@Test
	public void testWeaponRang() {
		Item i = player.getWearItemByName(Item.WEAPON);
		if (((MagicWeaponItem)i).getWeaponType().equals(WeaponItem.WEAPON_MELEE)){
			assertTrue(((MagicWeaponItem)i).getRange() == 1);
		} else {
			assertTrue(((MagicWeaponItem)i).getRange() == 3);
		}
	}
}
