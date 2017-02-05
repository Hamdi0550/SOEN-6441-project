package ddg.ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ddg.Config;
import ddg.framework.IObserver;
import ddg.model.MapModel;
import ddg.model.Model;
import ddg.view.CampaignEditor;
import ddg.view.CharactorEditor;
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
	private CharactorEditor charactorEditor;
	private CampaignEditor campaignEditor;
	private ItemEditor itemEditor;
	
	public DDGameMain() {
		init();
		this.contentPane = getContentPane();
		this.contentPane.setLayout(new CardLayout());
		this.mapEditor = new MapEditor(this);
		this.mainPage = new MainPage(this);
		this.charactorEditor = new CharactorEditor(this);
		this.campaignEditor = new CampaignEditor(this);
		this.itemEditor = new ItemEditor(this);
		this.contentPane.add(mainPage);
		this.contentPane.add(mapEditor);
		this.contentPane.add(charactorEditor);
		this.contentPane.add(campaignEditor);
		this.contentPane.add(itemEditor);
	}

	private void init() {
		setSize(Config.WIDTH, Config.HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(false);
	    this.setTitle("DDG");
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	DDGameMain gui = new DDGameMain();
        		gui.setVisible(true);
            }
        });
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("CHARACTOR")) {
			this.mainPage.setVisible(false);
			this.charactorEditor.setVisible(true);
		} else if(e.getActionCommand().equals("CAMPAIGN")) {
			this.mainPage.setVisible(false);
			this.campaignEditor.setVisible(true);
		} else if(e.getActionCommand().equals("MAP")) {
			this.mainPage.setVisible(false);
			this.mapEditor.setVisible(true);
		} else if(e.getActionCommand().equals("ITEM")) {
			this.mainPage.setVisible(false);
			this.itemEditor.setVisible(true);
		} else if(e.getActionCommand().equals("CONTINUE")) {
			System.out.println("OPEN CONTINUE");
		} else if(e.getActionCommand().equals("START")) {
			System.out.println("OPEN START");
		} else if(e.getActionCommand().equals("CHARACTOR-BACK")) {
			this.mainPage.setVisible(true);
			this.charactorEditor.setVisible(false);
		} else if(e.getActionCommand().equals("CAMPAIGN-BACK")) {
			this.mainPage.setVisible(true);
			this.campaignEditor.setVisible(false);
		} else if(e.getActionCommand().equals("MAP-BACK")) {
			this.mainPage.setVisible(true);
			this.mapEditor.setVisible(false);
		} else if(e.getActionCommand().equals("ITEM-BACK")) {
			this.mainPage.setVisible(true);
			this.itemEditor.setVisible(false);
		} 
	}
}
