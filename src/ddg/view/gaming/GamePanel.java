package ddg.view.gaming;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ddg.model.gaming.GameModel;
import ddg.view.MapEditor;
import ddg.view.MapPartInGame;
/**
 * 
 * Gaming panel, design to extends MapEditor, maybe need to refactor
 * 
 * @author Zhen Du
 * @date Mar 12, 2017
 */
public class GamePanel extends JPanel {
	private MapPartInGame mapPanel;

	public GamePanel(GameModel model, ActionListener a) {
		setPreferredSize(new Dimension(1000, 800));
		mapPanel = new MapPartInGame(model.getFighter(), model.getCampaign());
		mapPanel.addKeyListener(mapPanel);
		add(mapPanel);
	}

}
