package ddg.view;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

import ddg.Config;
import ddg.model.CampaignEditorModel;
import ddg.model.Fighter;
import ddg.model.FighterModel;
import ddg.model.GameModel;
import ddg.model.entity.BaseCampaign;
import ddg.ui.view.MapPanelInGame;
import ddg.utils.Utils;

/**
 * Test class for testing MapPanelInGame class
 * @author Bo
 * @date Mar 22, 2017
 * 
 */
public class MapPanelInGameTest {
	Fighter player;
	BaseCampaign campaign;
	MapPanelInGame mapPanelInGame;
	
	
	
	/**
	 * initialize data before all test case
	 * @throws Exception
	 */
	@Before
	public void testBefore() throws Exception {
		// FighterModel fighterModel = new FighterModel();
		// String g = Utils.readFile(Config.CHARACTER_FILE);
		// fighterModel = Utils.fromJson(g, FighterModel.class);
		FighterModel fighterModel = Utils.readObject(Config.CHARACTER_FILE, FighterModel.class);
		player = fighterModel.getFighters().get("Tank3");

		String g = Utils.readFile(Config.CAMPAIGN_FILE);
		CampaignEditorModel campaignModel = Utils.fromJson(g, CampaignEditorModel.class);
		campaign = campaignModel.getItemByIndex(0);

		mapPanelInGame = new MapPanelInGame(new GameModel(player, campaign));
	}
	
	/**
	 * test When thisPanel receive a KeyEvent, if the play character would move correctly.
	 */
	@Test
	public void testMoveOnMap(){
		Point point = mapPanelInGame.getPlayerLocation();
		mapPanelInGame.characterThisTurn = mapPanelInGame.getGame().getFighter();
		mapPanelInGame.getGame().getFighter().turn();
		mapPanelInGame.keyPressed(new KeyEvent(mapPanelInGame, 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
		point.y++;
		System.out.println(point);
		assertEquals(point, mapPanelInGame.getPlayerLocation());
		
		mapPanelInGame.keyPressed(new KeyEvent(mapPanelInGame, 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
		point.y--;
		assertEquals(point, mapPanelInGame.getPlayerLocation());
		
		mapPanelInGame.keyPressed(new KeyEvent(mapPanelInGame, 0, 0, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED));
		point.x--;
		assertEquals(point, mapPanelInGame.getPlayerLocation());
		
		mapPanelInGame.getGame().getFighter().turn();
		mapPanelInGame.keyPressed(new KeyEvent(mapPanelInGame, 0, 0, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED));
		point.x++;
		assertEquals(point, mapPanelInGame.getPlayerLocation());
		
	}
	
}
