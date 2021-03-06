package ddg.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ddg.Config;
import ddg.ui.view.component.DButton;
/**
 * This class is show main page
 * 
 * @author Zhen Du
 * @date Feb 5, 2017
 */
public class MainPage extends JPanel implements ActionListener {

	private ActionListener listener;
	
	/**
	 * 
	 * Constructors for MainPage
	 * 
	 * @param a ActionListener for perform event
	 */
	public MainPage(ActionListener a) {
		this.listener = a;
		initView();
	}

	/**
	 * 
	 * This method is initView
	 *
	 */
	private void initView() {
		BorderLayout l = new BorderLayout();
	    setLayout(l);
		JPanel contentPanel = new JPanel();
		JLabel ddg = new JLabel();
		ImageIcon icon = new ImageIcon("./res/logo.jpg");
		ddg.setIcon(icon);
		ddg.setSize(Config.CENTER_WIDTH, Config.CENTER_HEIGHT);
		contentPanel.add(ddg);
	    add(contentPanel, BorderLayout.CENTER);
	    
	    addOption();
	}

	/**
	 * 
	 * This method is add option button
	 *
	 */
	private void addOption() {
		JPanel optionPanel = new JPanel();
	    optionPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT));
	    optionPanel.setBorder(Config.border);
	    JTextArea optionTitle = new JTextArea("OPTION");
	    optionTitle.setEditable(false);
	    DButton charactorBtn = new DButton("CHARACTOR", this);
	    charactorBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    DButton campaignBtn = new DButton("CAMPAIGN", this);
	    campaignBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    DButton mapBtn = new DButton("MAP", this);
	    mapBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    DButton itemBtn = new DButton("ITEM", this);
	    itemBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    DButton continueBtn = new DButton("LOAD", this);
	    continueBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    DButton startBtn = new DButton("NEW", this);
	    startBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));

	    optionPanel.add(optionTitle);
	    optionPanel.add(charactorBtn);
	    optionPanel.add(campaignBtn);
	    optionPanel.add(mapBtn);
	    optionPanel.add(itemBtn);
	    optionPanel.add(continueBtn);
	    optionPanel.add(startBtn);
	    add(optionPanel, BorderLayout.EAST);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("CHARACTOR")) {
			System.out.println("OPEN CHARACTOR");
		} else if(e.getActionCommand().equals("CAMPAIGN")) {
			System.out.println("OPEN CAMPAIGN");
		} else if(e.getActionCommand().equals("MAP")) {
			System.out.println("OPEN MAP");
		} else if(e.getActionCommand().equals("ITEM")) {
			System.out.println("OPEN ITEM");
		} else if(e.getActionCommand().equals("LOAD")) {
			System.out.println("OPEN CONTINUE");
		} else if(e.getActionCommand().equals("NEW")) {
			System.out.println("OPEN START");
		}
		listener.actionPerformed(e);
	}
}
