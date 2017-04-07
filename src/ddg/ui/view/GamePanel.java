package ddg.ui.view;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ddg.model.GameModel;
import ddg.model.entity.Game;
/**
 * 
 * Gaming panel, design to extends MapEditor, maybe need to refactor
 * 
 * @author Zhen Du
 * @date Mar 12, 2017
 */
public class GamePanel extends JPanel {
	private MapPanelInGame mapPanel;

	/**
	 * 
	 * Constructors for GamePanel
	 * 
	 * @param model
	 * @param a
	 */
	public GamePanel(GameModel model, ActionListener a) {
//		setPreferredSize(new Dimension(1000, 800));
		mapPanel = new MapPanelInGame(model.getFighter(), model.getCampaign());
		mapPanel.addKeyListener(mapPanel);
		add(mapPanel);
	}
	public GamePanel(Game game){
		mapPanel = new MapPanelInGame(game);
		mapPanel.addKeyListener(mapPanel);
		add(mapPanel);
	}

}
