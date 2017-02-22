package ddg.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import ddg.Config;
import ddg.item.entity.BaseItem;
import ddg.model.ItemEditorModel;
import ddg.utils.Utils;
import ddg.view.component.DButton;
import ddg.view.component.DComboBox;
import ddg.view.component.DComboBox.DItemListener;

/**
 * This class is show item editor view
 * 
 * @author
 * @date Feb 5, 2017
 */
public class ItemEditor extends JPanel implements ActionListener, DItemListener {

	private ActionListener listener;
	private ItemEditorModel model;

	public ItemEditor(ActionListener a) {
		this.listener = a;
		initData();
		initView();
	}

	private void initData() {
		String g = Utils.readFile(Config.ITEM_FILE);
		this.model = Utils.fromJson(g, ItemEditorModel.class);
		if (this.model == null) {
			this.model = new ItemEditorModel();
		}
	}

	private void initView() {
		BorderLayout l = new BorderLayout();
		setLayout(l);

		addEditor();
		addOption();
	}

	private void addEditor() {
		JPanel contentPanel = new JPanel();
		DComboBox<String> comboBox = new DComboBox<String>("type");
		for(String i : BaseItem.NAME) {
			comboBox.addItem(i);
		}
		comboBox.setName("Please");
		comboBox.addDItemListener(this);
		contentPanel.add(comboBox);

		JTextArea ddg = new JTextArea("ITEM NAME");
		// ddg.setEditable(false);
		contentPanel.add(ddg);

		JTextArea ability = new JTextArea("ITEM");
		// ddg.setEditable(false);
		contentPanel.add(ability);
		add(contentPanel, BorderLayout.CENTER);
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
		if (e.getActionCommand().equals("BACK")) {
			e = new ActionEvent(e.getSource(), e.getID(), "ITEM-BACK");
		} else if (e.getActionCommand().equals("SAVE")) {
			String g = Utils.toJson(this.model);
			Utils.save2File(Config.ITEM_FILE, g);
		}
		listener.actionPerformed(e);
	}

	@Override
	public void itemChanged(ItemEvent e, String name) {
		// TODO Auto-generated method stub
		
	}
}
