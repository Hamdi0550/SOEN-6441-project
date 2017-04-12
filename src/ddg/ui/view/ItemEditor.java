package ddg.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ddg.Config;
import ddg.factory.MagicFactory;
import ddg.model.FighterModel;
import ddg.model.ItemEditorModel;
import ddg.model.item.BaseItem;
import ddg.model.item.Item;
import ddg.model.item.MagicWeaponItem;
import ddg.model.item.WeaponItem;
import ddg.ui.view.component.DButton;
import ddg.ui.view.component.DComboBox;
import ddg.ui.view.component.DComboBox.DItemListener;
import ddg.ui.view.component.ListEntryCellRenderer;
import ddg.utils.Utils;

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
	private DComboBox<String> weaponTypeComboBox;
	private DComboBox<String> magicWeaponComboBox;
	private JList magicList;
	private JScrollPane listScrollPane;
	
	/**
	 * 
	 * Constructors for eidtor view
	 * 
	 * @param a action listener for option button
	 */
	public ItemEditor(ActionListener a) {
		this.listener = a;
		initData();
		initView();
	}

	/**
	 * 
	 * This method init view data
	 *
	 */
	private void initData() {
//		String g = Utils.readFile(Config.ITEM_FILE);
//		this.model = Utils.fromJson(g, ItemEditorModel.class);
		this.model = Utils.readObject(Config.ITEM_FILE, ItemEditorModel.class);
		if (this.model == null) {
			this.model = new ItemEditorModel();
		}
	}

	/**
	 * 
	 * This method init view show
	 *
	 */
	private void initView() {
		BorderLayout l = new BorderLayout();
		setLayout(l);

		addListView();
		addEditorView();
		addOptionView();
		list.setSelectedIndex(0);
	}
	
	/**
	 * 
	 * This method add list view in the left area
	 *
	 */
	private void addListView() {
		JPanel listPanel = new JPanel();
		BorderLayout layout = new BorderLayout();
		listPanel.setLayout(layout);
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
        listPanel.add(typeComboBox, BorderLayout.SOUTH);
		add(listPanel, BorderLayout.WEST);
	}

	/**
	 * 
	 * This method add editor area
	 *
	 */
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
		
		weaponTypeComboBox = new DComboBox<String>(WeaponItem.WEAPON_TYPE);
		weaponTypeComboBox.addItem(WeaponItem.WEAPON_MELEE);
		weaponTypeComboBox.addItem(WeaponItem.WEAPON_RANGED);
		weaponTypeComboBox.addDItemListener(this);
		contentPanel.add(weaponTypeComboBox);
		//Add for weapon
		magicWeaponComboBox = new DComboBox<String>(MagicWeaponItem.WEAPON_MAGIC_ABILITY);
		magicWeaponComboBox.addItem("Magic");
		for(String i : MagicWeaponItem.MAGIC) {
			magicWeaponComboBox.addItem("  +  " + i);
		}
		magicWeaponComboBox.addDItemListener(this);
		contentPanel.add(magicWeaponComboBox);
	        
	        
		JPanel listPanel = new JPanel();
		BorderLayout layout = new BorderLayout();
		listPanel.setLayout(layout);
		listPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT - 5*Config.BTN_HEIGHT));
		DefaultListModel l = new DefaultListModel();
		magicList = new JList(l);
		magicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		magicList.addListSelectionListener(this);
		magicList.setVisibleRowCount(10);
        listScrollPane = new JScrollPane(magicList);
        listScrollPane.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT - 5*Config.BTN_HEIGHT));
       
        listPanel.add(listScrollPane, BorderLayout.SOUTH);
        listPanel.add(contentPanel, BorderLayout.CENTER);
		
        add(listPanel, BorderLayout.CENTER);
	}

	/**
	 * 
	 * This method is add option area
	 *
	 */
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
//			String g = Utils.toJson(this.model);
//			Utils.save2File(Config.ITEM_FILE, g);
			Utils.saveObject(Config.ITEM_FILE, this.model);
			JOptionPane.showMessageDialog(this, "Save Success!");
		}
		listener.actionPerformed(e);
	}

	@Override
	public void itemChanged(ItemEvent e, String name) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			System.out.println("ComboBox "+name+" choose " + e.getItem());
			if(BaseItem.TYPE.equals(name)) {
				int add = typeComboBox.getSelectedIndex();
				Item item = null;
				if(add > 0) {
					item = new BaseItem(BaseItem.NAME[add-1]);
					if(BaseItem.NAME[add-1].equals(BaseItem.WEAPON)) {
						item = new MagicWeaponItem(item);
					}
					model.addItem(item);
					DefaultListModel l = model.getListModel();
					list.setModel(l);
					list.setSelectedIndex(l.size()-1);
					list.ensureIndexIsVisible(l.size()-1);
					typeComboBox.setSelectedIndex(0);
				}
			} else if(MagicWeaponItem.WEAPON_MAGIC_ABILITY.equals(name)) {
				int add = magicWeaponComboBox.getSelectedIndex();
				if(add > 0) {
					int index = list.getSelectedIndex();
					if(index >= 0) {
						Item item = model.getItemByIndex(index);
						if(item instanceof MagicWeaponItem) {
							MagicWeaponItem magic = (MagicWeaponItem)item;
							magic.addAbility(MagicFactory.getMagic(MagicWeaponItem.MAGIC[add-1]));
							DefaultListModel l = magic.getListModel();
							magicList.setModel(l);
							magicList.setSelectedIndex(l.size()-1);
							magicList.ensureIndexIsVisible(l.size()-1);
							magicWeaponComboBox.setSelectedIndex(0);
						}
					}
				}
			} else {
				int index = list.getSelectedIndex();
				if(index >= 0) {
					Item item = model.getItemByIndex(index);
					int i = 0;
					if(BaseItem.ABILITY.equals(name)) {
						item.setIncrease(BaseItem.getAbility(item.getName())[abilityComboBox.getSelectedIndex()]);
					} else if(BaseItem.BONUS.equals(name)) {
						i = bonusComboBox.getSelectedIndex();
						item.setBonus(i+1);
					} else if(WeaponItem.WEAPON_TYPE.equals(name)) {
						((MagicWeaponItem)item).setWeaponType((String)weaponTypeComboBox.getSelectedItem());
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
				Item item = model.getItemByIndex(index);
				if(BaseItem.WEAPON.equals(item.getName())) {
					weaponTypeComboBox.setVisible(true);
					weaponTypeComboBox.setSelectedItem(((MagicWeaponItem)item).getWeaponType());
					magicWeaponComboBox.setVisible(true);
					listScrollPane.setVisible(true);
					DefaultListModel l = ((MagicWeaponItem)item).getListModel();
					magicList.setModel(l);
					magicList.ensureIndexIsVisible(l.size()-1);
				} else {
					weaponTypeComboBox.setVisible(false);
					magicWeaponComboBox.setVisible(false);
					listScrollPane.setVisible(false);
				}
				abilityComboBox.removeDItemListener(this);
				abilityComboBox.removeAllItems();
				
				for(String i : BaseItem.getAbility(item.getName())) {
					abilityComboBox.addItem(i);
				}
				abilityComboBox.addDItemListener(this);
				abilityComboBox.setSelectedItem(item.getIncrease());
				bonusComboBox.setSelectedItem(item.getBonus());
			}
		}
	}
}
