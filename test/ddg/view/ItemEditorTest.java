/**
 * 
 */
package ddg.view;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import ddg.item.entity.BaseItem;

/**
 * This class is test item editor
 * 
 * @author Zhen Du
 * @date Mar 2, 2017
 */
public class ItemEditorTest {

//	private ItemD
	/**
	 * This method set up test environment
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] items = new String[] {BaseItem.HELMET, BaseItem.ARMOR, BaseItem.SHIELD, BaseItem.RING,
				BaseItem.BELT, BaseItem.BOOTS, BaseItem.WEAPON};
	}

	@Test
	public void testCreate() {
		for(int i=0;i<10;i++) {
			
		}
		fail("Not yet implemented");
	}

	@Test
	public void testEdit() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSave() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testLoad() {
		fail("Not yet implemented");
	}
}
