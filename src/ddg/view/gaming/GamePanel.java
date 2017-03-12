package ddg.view.gaming;

import java.awt.event.ActionListener;

import ddg.model.gaming.GameModel;
import ddg.view.MapEditor;
/**
 * 
 * Gaming panel, design to extends MapEditor, maybe need to refactor
 * 
 * @author Zhen Du
 * @date Mar 12, 2017
 */
public class GamePanel extends MapEditor {

	public GamePanel(GameModel model, ActionListener a) {
		super(a);
	}

}
