package ddg.view.gaming;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.BeforeClass;
import org.junit.Test;

import ddg.Config;
import ddg.campaign.entity.BaseCampaign;
import ddg.model.CampaignEditorModel;
import ddg.model.FighterModel;
import ddg.model.gaming.GameModel;
import ddg.utils.Utils;

public class GameInitDialogTest {
	/**
	 * 
	 * This method is to chose the campaign and character 
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented"); // TODO
	}
	@Test
	public void testSelectTheCampaign(){
		String g = Utils.readFile(Config.CAMPAIGN_FILE);
		CampaignEditorModel campaignModel = Utils.fromJson(g, CampaignEditorModel.class);
		if (campaignModel != null) {
			for(String i : campaignModel.getCampaignList()) {
				
			}
		}
		
	}
	@Test
	public void testSelectTheCharacter(){
		FighterModel fighterModel = new FighterModel();
		String g = Utils.readFile(Config.CHARACTOR_FILE);
		fighterModel = Utils.fromJson(g, FighterModel.class);
		if (fighterModel != null) {
			for(String i : fighterModel.getFighterList()) {
			}
		}
	}
	

}
