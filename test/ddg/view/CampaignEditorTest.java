/**
 * 
 */
package ddg.view;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import ddg.model.CampaignEditorModel;
import ddg.model.entity.BaseCampaign;

/**
 * 
 * This class is test the campaign edit
 * 
 * @author Hamzah Hamdi
 * @date Mar 2, 2017
 */
public class CampaignEditorTest {

	public static String[] maps = new String[10];
	/**
	 * 
	 * This method
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		for(int i=0;i<10;i++) {
			maps[i] = "Map"+(i+1);
		}
	}

	@Test
	public void testCreate() {
		CampaignEditorModel model = new CampaignEditorModel();
		BaseCampaign c = new BaseCampaign("Campaign");
		for(int i=0;i<10;i++) {
			c.addMapToCampaign(maps[i]);
		}
		model.addCampaign(c);
		assertTrue(model.getItemByIndex(0).getMaps().size() == 10);
	}

	@Test
	public void testEdit() {
		CampaignEditorModel model = new CampaignEditorModel();
		BaseCampaign c = new BaseCampaign("Campaign");
		for(int i=0;i<10;i++) {
			c.addMapToCampaign(maps[i]);
		}
		model.addCampaign(c);
		
		BaseCampaign g = model.getItemByIndex(0);
		for(int i=0; i<6;i++) {
			g.addMapToCampaign(maps[i]);
		}
		assertTrue(model.getItemByIndex(0).getMaps().size() == 16);
	}

}
