package ddg.view;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ddg.builder.FighterExplorerTest;
import ddg.model.FighterTest;
import ddg.model.item.MagicWeaponItemTest;
import ddg.utils.DiceTest;

@RunWith(Suite.class)
@SuiteClasses({ CampaignEditorTest.class, ItemEditorTest.class, MapEditorTest.class, CharactorEditorTest.class,
		MapPanelInGameTest.class, FighterExplorerTest.class, GameInitDialogTest.class, BackpackTradeTest.class,
		ChestTest.class, DiceTest.class, MagicWeaponItemTest.class, FighterTest.class})
public class AllTests {

}
