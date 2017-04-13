package ddg.view;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ddg.model.entity.Chest;
import ddg.model.item.BaseItem;

/**
 * This class is to test features about chests.
 * 
 * @author Yi Qin
 * @date Mar 20, 2017
 */
public class ChestTest {
	Chest chestnotemp;
	Chest chestempty;

	/**
	 * This method setup the initial testing data.
	 */
	@Before
	public void testBefore(){
		BaseItem i = new BaseItem(BaseItem.HELMET);
		chestnotemp = new Chest(i);
		chestempty = new Chest();
	}

	/**
	 * This method tests if empty attribute can turn correctly.
	 */
	@Test
	public void testBecameEmpty(){
		assertTrue(chestempty.isEmpty());
		assertFalse(chestnotemp.isEmpty());
		
		chestnotemp.becameEmpty();
		assertTrue(chestnotemp.isEmpty());
	}
}
