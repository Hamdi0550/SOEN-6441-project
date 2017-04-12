/**
 * 
 */
package ddg.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ddg.Config;
import ddg.model.FighterModel;
import ddg.model.ItemEditorModel;
import ddg.model.item.BaseItem;
import ddg.utils.Utils;

/**
 * This class is test item editor
 * 
 * @author Zhen Du
 * @date Mar 2, 2017
 */
public class ItemEditorTest {

	private static String[] items;
	private ItemEditorModel model;
	/**
	 * This method set up test environment
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		items = new String[] {BaseItem.HELMET, BaseItem.ARMOR, BaseItem.SHIELD, BaseItem.RING,
				BaseItem.BELT, BaseItem.BOOTS, BaseItem.WEAPON};
	}
	
	@Before
	public void initItem() {
		model = new ItemEditorModel();
		for(int i=0;i<10;i++) {
			model.addItem(new BaseItem(items[Utils.getRadom(6)]));
		}
	}

	@Test
	public void testCreate() {
		assertTrue(model.getListModel().getSize() == 10);
	}

	@Test
	public void testEdit() {
		for(int i=0;i<10;i++) {
			model.addItem(new BaseItem(items[Utils.getRadom(6)]));
		}
		assertTrue(model.getListModel().getSize() == 20);
	}
	
	@Test
	public void testSaveLoad() {
//		String g = Utils.toJson(this.model);
//		Utils.save2File(Config.ITEM_FILE, g);
		Utils.saveObject(Config.ITEM_FILE, this.model);
		
//		String s = Utils.readFile(Config.ITEM_FILE);
		ItemEditorModel s = Utils.readObject(Config.CHARACTER_FILE, ItemEditorModel.class);
		assertEquals(this.model, s);
	}
}
