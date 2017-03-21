package ddg.view.gaming;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ddg.model.gaming.GameModel;
import ddg.view.MapEditor;
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
		setPreferredSize(new Dimension(1000, 650));
		mapPanel = new MapPanelInGame(model.getFighter(), model.getCampaign());
		mapPanel.addKeyListener(mapPanel);
		add(mapPanel);
	}

}
