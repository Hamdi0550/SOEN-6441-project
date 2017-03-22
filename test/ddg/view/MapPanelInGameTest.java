package ddg.view;

import ddg.Config;
import ddg.campaign.entity.BaseCampaign;
import ddg.map.entity.Map;
import ddg.model.CampaignEditorModel;
import ddg.model.Fighter;
import ddg.model.FighterModel;
import ddg.model.MapEditorModel;
import ddg.utils.Utils;
import ddg.utils.ValidationTool;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.channels.FileChannel.MapMode;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapPanelInGameTest {
	Fighter player;
	BaseCampaign campaign;
	MapPanelInGame mappanelingame;
	
	@Before
	public void testBefore() throws Exception {
		 FighterModel fighterModel = new FighterModel();
		 String g = Utils.readFile(Config.CHARACTOR_FILE);
		 fighterModel = Utils.fromJson(g, FighterModel.class);
		 player = fighterModel.getFighters().get("Tank");
		
		 g = Utils.readFile(Config.CAMPAIGN_FILE);
		 CampaignEditorModel campaignModel = Utils.fromJson(g, CampaignEditorModel.class);
		 campaign = campaignModel.getItemByIndex(0);
		 
		 mappanelingame = new MapPanelInGame(player,campaign);
	}
	
	@Test
	public void testMoveOnMap(){
		Point point = mappanelingame.getPlayerLocation();
		mappanelingame.keyPressed(new KeyEvent(mappanelingame, 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
		point.y++;
		assertEquals(point, mappanelingame.getPlayerLocation());
		
		mappanelingame.keyPressed(new KeyEvent(mappanelingame, 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
		point.y--;
		assertEquals(point, mappanelingame.getPlayerLocation());
		
		mappanelingame.keyPressed(new KeyEvent(mappanelingame, 0, 0, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED));
		point.x--;
		assertEquals(point, mappanelingame.getPlayerLocation());
		
		mappanelingame.keyPressed(new KeyEvent(mappanelingame, 0, 0, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED));
		point.x++;
		assertEquals(point, mappanelingame.getPlayerLocation());
		
	}
	
}
