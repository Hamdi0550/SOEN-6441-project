package ddg.view.gaming;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import ddg.Config;
import ddg.campaign.entity.BaseCampaign;
import ddg.map.entity.Map;
import ddg.model.CampaignEditorModel;
import ddg.model.Fighter;
import ddg.model.FighterModel;
import ddg.model.MapEditorModel;
import ddg.model.gaming.GameModel;
import ddg.utils.Utils;

public class GameInitDialogTest {
	GameModel gameModel = null;
	static FighterModel fighterModel = null;
	static CampaignEditorModel campaignEditorModel = null;
	static Fighter fighter = null;
	static BaseCampaign baseCampaign = null;
	Map map = null;
	static GameInitDialog test = null;
	
	/**
	 * 
	 * This method is to chose the campaign and character 
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String g = Utils.readFile(Config.CHARACTOR_FILE);
		fighterModel = Utils.fromJson(g, FighterModel.class);
		fighter = fighterModel.getFighterByName("Tank3");
		g = Utils.readFile(Config.CAMPAIGN_FILE);
		campaignEditorModel = Utils.fromJson(g, CampaignEditorModel.class);
		baseCampaign = campaignEditorModel.getItemByIndex(0);
		test = new GameInitDialog(null, null);
	}
	
	@Test
	public void testMapLoading(){
		gameModel = test.mapLoading(fighter, baseCampaign);
		assertNotNull(gameModel.getCampaign().getMaps().size());
	}
	
	@Test
	public void testFighterLoading(){
		gameModel = test.mapLoading(fighter, baseCampaign);
		assertNotNull(gameModel.getFighter());
	}
}
