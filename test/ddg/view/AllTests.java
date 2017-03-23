package ddg.view;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ddg.utils.builder.FighterExplorerTest;
import ddg.view.gaming.GameInitDialogTest;

@RunWith(Suite.class)
@SuiteClasses({ CampaignEditorTest.class, ItemEditorTest.class, MapEditorTest.class, CharactorEditorTest.class,
		MapPanelInGameTest.class, FighterExplorerTest.class, GameInitDialogTest.class, BackpackTradeTest.class,
		ChestTest.class})
public class AllTests {

}
