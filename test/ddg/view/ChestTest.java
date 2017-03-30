package ddg.view;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ddg.model.entity.BaseItem;
import ddg.model.entity.Chest;

public class ChestTest {
	Chest chestnotemp;
	Chest chestempty;
	
	@Before
	public void testBefore(){
		BaseItem i = new BaseItem(BaseItem.HELMET);
		chestnotemp = new Chest(i);
		chestempty = new Chest();
	}
	
	@Test
	public void testBecameEmpty(){
		assertTrue(chestempty.isEmpty());
		assertFalse(chestnotemp.isEmpty());
		
		chestnotemp.becameEmpty();
		assertTrue(chestnotemp.isEmpty());
	}
}
