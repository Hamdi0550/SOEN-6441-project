/**
 * 
 */
package ddg.view;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ddg.Config;
import ddg.model.Fighter;
import ddg.model.FighterModel;
import ddg.model.item.Item;
import ddg.utils.Utils;

/**
 * This class
 * 
 * @author Hamzah Hamdi
 * @date Mar 22, 2017
 */
public class BackpackTradeTest {

	private static FighterModel fighterModel;
	private Fighter player;
	private Fighter npc;
	/**
	 * This method
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		String g = Utils.readFile(Config.CHARACTER_FILE);
//		fighterModel = Utils.fromJson(g, FighterModel.class);
		fighterModel = Utils.readObject(Config.CHARACTER_FILE, FighterModel.class);
	}

	/**
	 * This method setup the initial testing data
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Iterator<Fighter> iterator = fighterModel.getFighters().values().iterator();
		while (iterator.hasNext()) {
			Fighter next = iterator.next();
			if (next.getBackpack().size() > 0) {
				if (player == null) {
					player = next;
				} else if (npc == null) {
					npc = next;
				}
			}
		}
	}

	/**
	 * This method tests if the player and NPC are null
	 */
	@Test
	public void testExitsPlayer() {
		assertNotNull(player);
		assertNotNull(npc);
	}

	/**
	 * This method tests a trade picking random items from player's and npc's backpack
	 */
	@Test
	public void testRandomTrade() {
		testExitsPlayer();
		int pSize = player.getBackpack().size();
		assertFalse(pSize <= 0);
		
		int size = npc.getBackpack().size();
		assertFalse(size <= 0);
		
		int r = Utils.getRadom(npc.getBackpack().size());
		Item i = npc.getBackpack().get(r);
		player.trade(player.getBackpack().get(0), npc, i);
		
		assertTrue(pSize == player.getBackpack().size());
	}
	
	/**
	 * This method tests a specific trade
	 */
	@Test
	public void testSelectTrade() {
		testExitsPlayer();
		int pSize = player.getBackpack().size();
		assertFalse(pSize <= 0);
		
		int size = npc.getBackpack().size();
		assertFalse(size <= 0);
		
		
		Item i = npc.getBackpack().get(0);
		player.trade(player.getBackpack().get(0), npc, i);
		
		assertTrue(pSize == player.getBackpack().size());
	}
	
	/**
	 * This method tests the trade if npc item is not chosen.
	 */
	@Test
	public void testNpcNull() {
		testExitsPlayer();
		int pSize = player.getBackpack().size();
		assertFalse(pSize <= 0);
		
		int size = npc.getBackpack().size();
		assertFalse(size <= 0);
		
		player.trade(player.getBackpack().get(0), npc, null);	
		assertTrue(pSize-1 == player.getBackpack().size());
	}
	
	/**
	 * This method tests the trade if player's item is not chosen.
	 */
	@Test
	public void testPlayerNull() {
		testExitsPlayer();
		int pSize = player.getBackpack().size();
		assertFalse(pSize <= 0);
		
		int size = npc.getBackpack().size();
		assertFalse(size <= 0);
		
		Item i = npc.getBackpack().get(0);
		player.trade(null, npc, i);
		
		assertTrue(pSize+1 == player.getBackpack().size());
	}
}