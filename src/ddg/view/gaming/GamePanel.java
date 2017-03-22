package ddg.view.gaming;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ddg.model.gaming.GameModel;
import ddg.view.MapPanelInGame;
/**
 * 
 * Gaming panel, design to extends MapEditor, maybe need to refactor
 * 
 * @author Zhen Du
 * @date Mar 12, 2017
 */
public class GamePanel extends JPanel {
	private MapPanelInGame mapPanel;

	public GamePanel(GameModel model, ActionListener a) {
//		setPreferredSize(new Dimension(1000, 800));
		mapPanel = new MapPanelInGame(model.getFighter(), model.getCampaign());
		mapPanel.addKeyListener(mapPanel);
		add(mapPanel);
	}

}
