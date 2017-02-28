package ddg.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ddg.Config;
import ddg.item.entity.BaseItem;
import ddg.model.ItemEditorModel;
import ddg.utils.Utils;
import ddg.view.component.DButton;
import ddg.view.component.DComboBox;
import ddg.view.component.DComboBox.DItemListener;
import ddg.view.component.ListEntryCellRenderer;

/**
 * 
 * This class is show item editor view
 * 
 * @author Zhen Du
 * @date Feb 5, 2017
 */
public class ItemEditor extends JPanel implements ActionListener, DItemListener, ListSelectionListener {

	private ActionListener listener;
	private ItemEditorModel model;
	private JList list;
	private DComboBox<String> typeComboBox;
	private DComboBox<String> abilityComboBox;
	private DComboBox<String> bonusComboBox;
	
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

		addListView();
		addEditorView();
		addOptionView();
		list.setSelectedIndex(0);
	}
	
	private void addListView() {
		JPanel listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT));
		DefaultListModel l = model.getListModel();
		list = new JList(l);
		list.setCellRenderer(new ListEntryCellRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(15);
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT-3 * Config.BTN_HEIGHT));
        listPanel.add(listScrollPane);
        
        typeComboBox = new DComboBox<String>(BaseItem.TYPE);
        typeComboBox.addItem("ADD ITEM");
        for(String i : BaseItem.NAME) {
        	typeComboBox.addItem("  +  " + i);
        }
        typeComboBox.addDItemListener(this);
        listPanel.add(typeComboBox);
        
		add(listPanel, BorderLayout.WEST);
	}

	private void addEditorView() {
		JPanel contentPanel = new JPanel();
		
		abilityComboBox = new DComboBox<String>(BaseItem.ABILITY);
		abilityComboBox.addDItemListener(this);
		
		bonusComboBox = new DComboBox<String>(BaseItem.BONUS);
		for(int i=1;i<6;i++) {
			bonusComboBox.addItem(i);
		}
		bonusComboBox.addDItemListener(this);
		contentPanel.add(abilityComboBox);
		contentPanel.add(bonusComboBox);

		add(contentPanel, BorderLayout.CENTER);
	}

	private void addOptionView() {
		JPanel optionPanel = new JPanel();
		optionPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT));
		optionPanel.setBorder(Config.border);
		JTextArea optionTitle = new JTextArea("OPTION");
		optionTitle.setEditable(false);

		DButton saveBtn = new DButton("SAVE", this);
		DButton backBtn = new DButton("BACK", this);
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
		if(e.getStateChange() == ItemEvent.SELECTED) {
			System.out.println("ComboBox "+name+" choose " + e.getItem());
			if(BaseItem.TYPE.equals(name)) {
				int add = typeComboBox.getSelectedIndex();
				if(add > 0) {
					BaseItem i = new BaseItem(BaseItem.NAME[add-1]);
					model.addItem(i);
					DefaultListModel l = model.getListModel();
					list.setModel(l);
					list.setSelectedIndex(l.size()-1);
					list.ensureIndexIsVisible(l.size()-1);
					typeComboBox.setSelectedIndex(0);
				}
			} else {
				int index = list.getSelectedIndex();
				if(index >= 0) {
					BaseItem item = model.getItemByIndex(index);
					int i = 0;
					if(BaseItem.ABILITY.equals(name)) {
						item.setIncreate(item.getAbility()[abilityComboBox.getSelectedIndex()]);
					} else if(BaseItem.BONUS.equals(name)) {
						i = bonusComboBox.getSelectedIndex();
						item.setBonus(i+1);
					}
				}
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			int index = list.getSelectedIndex();
			if(index >= 0) {
				System.out.println("list select:"+index);
				BaseItem item = model.getItemByIndex(index);
				
				abilityComboBox.removeDItemListener(this);
				abilityComboBox.removeAllItems();
				
				for(String i : item.getAbility()) {
					abilityComboBox.addItem(i);
				}
				abilityComboBox.addDItemListener(this);
				abilityComboBox.setSelectedItem(item.getIncreate());
				bonusComboBox.setSelectedItem(item.getBonus());
			}
		}
	}
}
