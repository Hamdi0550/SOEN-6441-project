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
	
	@Before
	public void setUp() throws Exception {
		FighterModel fighterModel = Utils.readObject(Config.CHARACTER_FILE, FighterModel.class);
		player = fighterModel.getFighters().get("Tank3");
		npc = fighterModel.getFighters().get("Tank4");
	}

	@Test
	public void testFreezing() {
		Item i = player.getWearItemByName(Item.WEAPON);
		if (player.getWearItemByName(Item.WEAPON) instanceof MagicWeaponItem){
			((MagicWeaponItem)i).attack(npc);
		}
		assertTrue(npc.getMagicStrategy()[0] != null);
	}

	@Test
	public void testBuring() {
		Item i = player.getWearItemByName(Item.WEAPON);
		if (player.getWearItemByName(Item.WEAPON) instanceof MagicWeaponItem){
			((MagicWeaponItem)i).attack(npc);
		}
		assertFalse(npc.getMagicStrategy()[1] == null);
	}
	
	@Test
	public void testFrightening() {
		Item i = player.getWearItemByName(Item.WEAPON);
		if (player.getWearItemByName(Item.WEAPON) instanceof MagicWeaponItem){
			((MagicWeaponItem)i).attack(npc);
		}
		assertTrue(npc.getMagicStrategy()[2] instanceof FrighteningStrategy);
	}
	
	@Test
	public void testPacifying() {
		Item i = player.getWearItemByName(Item.WEAPON);
		if (player.getWearItemByName(Item.WEAPON) instanceof MagicWeaponItem){
			((MagicWeaponItem)i).attack(npc);
		}
		assertTrue(npc.getBehaviorStrategy() instanceof FriendlyStrategy);
	}
}
