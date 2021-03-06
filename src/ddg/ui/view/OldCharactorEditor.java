package ddg.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import ddg.Config;
import ddg.ui.view.component.DButton;
/**
 * This class is show charactor editor view
 * 
 * @author Zhen Du
 * @date Feb 5, 2017
 */
@Deprecated
public class OldCharactorEditor extends JPanel implements ActionListener {

	private ActionListener listener;
	
	public OldCharactorEditor(ActionListener a) {
		this.listener = a;
		initView();
	}

	private void initView() {
		BorderLayout l = new BorderLayout();
	    setLayout(l);
		JPanel contentPanel = new JPanel();
		JTextArea ddg = new JTextArea("CHARACTOR");
		ddg.setEditable(false);
		contentPanel.add(ddg);
	    add(contentPanel, BorderLayout.CENTER);
	    
	    addOption();
	}

	private void addOption() {
		JPanel optionPanel = new JPanel();
	    optionPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT));
	    optionPanel.setBorder(Config.border);
	    JTextArea optionTitle = new JTextArea("OPTION");
	    optionTitle.setEditable(false);

	    DButton saveBtn = new DButton("SAVE", this);
	    saveBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    DButton backBtn = new DButton("BACK", this);
	    backBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    optionPanel.add(optionTitle);
	    optionPanel.add(saveBtn);
	    optionPanel.add(backBtn);
	    add(optionPanel, BorderLayout.EAST);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("BACK")) {
			e = new ActionEvent(e.getSource(), e.getID(), "CHARACTOR-BACK");
		}
		listener.actionPerformed(e);
	}
}
