package ddg.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ddg.Config;
import ddg.view.component.OButton;
/**
 * This class is show map editor view
 * 
 * @author 
 * @date Feb 5, 2017
 */
public class MapEditor extends JPanel implements ActionListener {

	private ActionListener listener;
	
	public MapEditor(ActionListener a) {
		this.listener = a;
		initView();
	}

	private void initView() {
		BorderLayout l = new BorderLayout();
		setLayout(l);

		GridLayout layout = new GridLayout();
		JPanel contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(620, 380));
		contentPanel.setLayout(layout);
		layout.setColumns(10);
		layout.setRows(10);

		for (int i = 0; i < 100; i++) {
			JTextField t = new JTextField(i + "");
			t.setName(i + "");
			t.setBorder(null);
			t.setSize(new Dimension(100, 100));
			t.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(e.getActionCommand());
					// observer.stateChanged(model);
				}
			});
			contentPanel.add(i + "", t);
		}
		add(contentPanel, BorderLayout.CENTER);

		addOption();
	}
	/**
	 * This method is used for add option panel
	 * 
	 */
	private void addOption() {
		JPanel optionPanel = new JPanel();
	    optionPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT));
	    optionPanel.setBorder(Config.border);
	    JTextArea optionTitle = new JTextArea("OPTION");
	    optionTitle.setEditable(false);
	    OButton sizeBtn = new OButton("S/M/L", this);
	    sizeBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    OButton clearBtn = new OButton("CLEAR", this);
	    clearBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    OButton validateBtn = new OButton("VALIDATE", this);
	    validateBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    OButton saveBtn = new OButton("SAVE", this);
	    saveBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    OButton backBtn = new OButton("BACK", this);
	    backBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    optionPanel.add(optionTitle);
	    optionPanel.add(sizeBtn);
	    optionPanel.add(clearBtn);
	    optionPanel.add(validateBtn);
	    optionPanel.add(saveBtn);
	    optionPanel.add(backBtn);
	    add(optionPanel, BorderLayout.EAST);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("BACK")) {
			e = new ActionEvent(e.getSource(), e.getID(), "MAP-BACK");
		}
		listener.actionPerformed(e);
	}
}
