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
import ddg.item.entity.BaseItem;
import ddg.model.Fighter;
import ddg.model.FighterModel;
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
		String g = Utils.readFile(Config.CHARACTER_FILE);
		fighterModel = Utils.fromJson(g, FighterModel.class);
	}

	/**
	 * This method
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

	@Test
	public void testExitsPlayer() {
		assertNotNull(player);
		assertNotNull(npc);
	}

	@Test
	public void testRandomTrade() {
		testExitsPlayer();
		int pSize = player.getBackpack().size();
		assertFalse(pSize <= 0);
		
		int size = npc.getBackpack().size();
		assertFalse(size <= 0);
		
		int r = Utils.getRadom(npc.getBackpack().size());
		BaseItem i = npc.getBackpack().get(r);
		player.trade(player.getBackpack().get(0), npc, i);
		
		assertTrue(pSize == player.getBackpack().size());
	}
	
	@Test
	public void testSelectTrade() {
		testExitsPlayer();
		int pSize = player.getBackpack().size();
		assertFalse(pSize <= 0);
		
		int size = npc.getBackpack().size();
		assertFalse(size <= 0);
		
		
		BaseItem i = npc.getBackpack().get(0);
		player.trade(player.getBackpack().get(0), npc, i);
		
		assertTrue(pSize == player.getBackpack().size());
	}
	
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
	
	@Test
	public void testPlayerNull() {
		testExitsPlayer();
		int pSize = player.getBackpack().size();
		assertFalse(pSize <= 0);
		
		int size = npc.getBackpack().size();
		assertFalse(size <= 0);
		
		BaseItem i = npc.getBackpack().get(0);
		player.trade(null, npc, i);
		
		assertTrue(pSize+1 == player.getBackpack().size());
	}
}