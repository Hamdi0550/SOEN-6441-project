package ddg.ui.view.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JDialog;

import ddg.Config;
import ddg.model.Game;
import ddg.model.GameModel;
import ddg.model.Map;
import ddg.model.MapEditorModel;
import ddg.ui.view.GamePanel;
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
	
	public void resumeGame(Frame owner, String title){
		try{
			//read maps files import maps
			FileInputStream fileIn = new FileInputStream(Config.GAME_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Game game = (Game) in.readObject();
			in.close();
			fileIn.close();
			
			if (game == null) {
				popShow(owner, title);
			}
			else{
				GamePanel panel = new GamePanel(game);
				setTitle(title);
				getContentPane().add(panel);
				pack();
				setLocationRelativeTo(null);
				setVisible(true);
			}
		}catch(IOException i){
			i.printStackTrace();
		}catch(ClassNotFoundException c){
			c.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("MAP-BACK")) {
			setVisible(false);
		}
	}
}
