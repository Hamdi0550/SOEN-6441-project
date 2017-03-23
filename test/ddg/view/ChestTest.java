package ddg.view;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ddg.Config;
import ddg.item.entity.BaseItem;
import ddg.map.entity.Chest;
import ddg.model.ItemEditorModel;
import ddg.utils.Utils;

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
