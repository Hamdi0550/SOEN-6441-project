package ddg.ui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import ddg.model.gaming.GameModel;
import ddg.view.gaming.GameInitDialog;
import ddg.view.gaming.GamePanel;
/**
 * 
 * This is a dialog style gaming UI
 * 
 * @author Zhen Du
 * @date Mar 12, 2017
 */
public class DDGaming extends JDialog implements ActionListener {
	
	/**
	 * 
	 * Constructors for DDGaming
	 *
	 */
	public DDGaming() {
//		setSize(Config.WIDTH+200, Config.HEIGHT+200);
		setModal(true);
		
	}
	
	/**
	 * 
	 * This method is popShow
	 * 
	 * @param owner
	 * @param title
	 */
	public void popShow(Frame owner, String title) {
		GameInitDialog d = new GameInitDialog(owner, "Choose Character&Campaign");
		d.showSelectView();
		GameModel model = d.getGameModel();
		if(model != null) {
			GamePanel panel = new GamePanel(model, this);
			setTitle(title);
			getContentPane().add(panel);
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("MAP-BACK")) {
			setVisible(false);
		}
	}
}
