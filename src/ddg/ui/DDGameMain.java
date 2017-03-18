package ddg.ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ddg.Config;
import ddg.model.Fighter;
import ddg.view.CampaignEditor;
import ddg.view.CharacterEditor;
import ddg.view.CharacterSelection;
import ddg.view.OldCharactorEditor;
import ddg.view.ItemEditor;
import ddg.view.MainPage;
import ddg.view.MapEditor;
/**
 * This is the DDGame Main page
 * 
 * @author Zhen Du
 * @date Feb 5, 2017
 */
public class DDGameMain extends JFrame implements ActionListener {

	private Container contentPane;
	private MapEditor mapEditor;
	private MainPage mainPage;
	private OldCharactorEditor charactorEditor;
	private CampaignEditor campaignEditor;
	private ItemEditor itemEditor;
	
	/**
	 * 
	 * Constructors for DDGameMain
	 *
	 */
	public DDGameMain() {
		init();
		this.contentPane = getContentPane();
		this.contentPane.setLayout(new CardLayout());
		this.mapEditor = new MapEditor(this);
		this.mainPage = new MainPage(this);
		this.charactorEditor = new OldCharactorEditor(this);
		this.campaignEditor = new CampaignEditor(this);
		this.itemEditor = new ItemEditor(this);
		this.contentPane.add(mainPage);
		this.contentPane.add(mapEditor);
		this.contentPane.add(charactorEditor);
		this.contentPane.add(campaignEditor);
		this.contentPane.add(itemEditor);
	}

	/**
	 * 
	 * This method is init the page size
	 *
	 */
	private void init() {
		setSize(Config.WIDTH, Config.HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(false);
	    this.setTitle("DDG");
	    setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	DDGameMain gui = new DDGameMain();
        		gui.setVisible(true);
            }
        });
		
	}

	private void startGame() {
		DDGaming dialog = new DDGaming();
		dialog.popShow(this, "Gaming");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("CHARACTOR")) {
			CharacterSelection.createAndShowGUI();
		} else if(e.getActionCommand().equals("CAMPAIGN")) {
			this.mainPage.setVisible(false);
			this.campaignEditor.setVisible(true);
			this.setTitle("Campaign Editor");
		} else if(e.getActionCommand().equals("MAP")) {
			this.mainPage.setVisible(false);
			this.mapEditor.setVisible(true);
			this.setTitle("Map Editor");
		} else if(e.getActionCommand().equals("ITEM")) {
			this.mainPage.setVisible(false);
			this.itemEditor.setVisible(true);
			this.setTitle("Item Editor");
		} else if(e.getActionCommand().equals("LOAD")) {
			System.out.println("OPEN LOAD");
		} else if(e.getActionCommand().equals("NEW")) {
			System.out.println("OPEN NEW");
			startGame();
		} else if(e.getActionCommand().equals("CHARACTOR-BACK")) {
			this.mainPage.setVisible(true);
			this.charactorEditor.setVisible(false);
			this.setTitle("DDG");
		} else if(e.getActionCommand().equals("CAMPAIGN-BACK")) {
			this.mainPage.setVisible(true);
			this.campaignEditor.setVisible(false);
			this.setTitle("DDG");
		} else if(e.getActionCommand().equals("MAP-BACK")) {
			this.mainPage.setVisible(true);
			this.mapEditor.setVisible(false);
			this.setTitle("DDG");
		} else if(e.getActionCommand().equals("ITEM-BACK")) {
			this.mainPage.setVisible(true);
			this.itemEditor.setVisible(false);
			this.setTitle("DDG");
		} 
	}
	
	private Fighter selectedFighter = null;
	
	public Fighter getSelectedFighter(){
		return selectedFighter;
	}

	public void setSelectedFighter(Fighter fighter){
		selectedFighter = fighter;
	}
}
