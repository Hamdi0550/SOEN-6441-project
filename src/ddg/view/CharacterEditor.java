package ddg.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ddg.Config;
import ddg.item.entity.ListEntry;
import ddg.model.Fighter;
import ddg.model.FighterModel;
import ddg.utils.Utils;
import ddg.view.component.DButton;
import ddg.view.component.ListEntryCellRenderer;
/**
 * This class is show charactor editor view
 * 
 * @author Fei Yu
 * @author Zhen Du
 * @date Feb 5, 2017
 */
public class CharacterEditor extends JPanel implements Observer, ActionListener, ListSelectionListener {

	private ActionListener listener;
	private FighterModel model;
	private JList list;
	private DefaultListModel listModel;
	private DButton deleteBtn;
	private DButton editBtn;
	
	private final JLabel nameTextF = new JLabel("   ");
    private final JLabel levelTextF = new JLabel("   ");
    private final JLabel strengthTextF = new JLabel("   ");
    private final JLabel dexterityTextF = new JLabel("   ");
    private final JLabel constitutionTextF = new JLabel("   ");
    private final JLabel intelligenceTextF = new JLabel("   ");
    private final JLabel wisdomTextF = new JLabel("   ");
    private final JLabel charismaTextF = new JLabel("   ");
    private final JLabel armorClassTextF = new JLabel("    ");
    private final JLabel hitPointsTextF = new JLabel("    ");
    private final JLabel attackBonusTextF = new JLabel("    ");
    private final JLabel damageBonusTextF = new JLabel("   ");

    private final JLabel nameModiferL = new JLabel("   ");
    private final JLabel levelModiferL = new JLabel("   ");
    private final JLabel strengthModiferL = new JLabel("   ");
    private final JLabel dexModiferL = new JLabel("   ");
    private final JLabel conModiferL = new JLabel("   ");
    private final JLabel intelliModiferL = new JLabel("   ");
    private final JLabel wisModiferL = new JLabel("   ");
    private final JLabel chaModiferL = new JLabel("   ");
    
	public CharacterEditor(ActionListener a) {
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
		String g = Utils.readFile(Config.CHARACTOR_FILE);
		this.model = Utils.fromJson(g, FighterModel.class);
		if (this.model == null) {
			this.model = new FighterModel();
		}
	}
	
	private void initView() {
		BorderLayout l = new BorderLayout();
	    setLayout(l);
		
	    addListView();
	    addEditorView();
	    addOption();
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
		listModel = model.getListModel();
		list = new JList(listModel);
		list.setCellRenderer(new ListEntryCellRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(15);
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT-3 * Config.BTN_HEIGHT));
        listPanel.add(listScrollPane);
        
		add(listPanel, BorderLayout.WEST);
	}
	
	private void addEditorView() {
		JPanel attributesPanel= new JPanel(new GridLayout(15,4,5,5));
		strengthModiferL.setBorder(new LineBorder(Color.BLACK));
        dexModiferL.setBorder(new LineBorder(Color.BLACK));
        conModiferL.setBorder(new LineBorder(Color.BLACK));
        intelliModiferL.setBorder(new LineBorder(Color.BLACK));
        wisModiferL.setBorder(new LineBorder(Color.BLACK));
        chaModiferL.setBorder(new LineBorder(Color.BLACK));
        nameTextF.setBorder(new LineBorder(Color.BLACK));
        levelTextF.setBorder(new LineBorder(Color.BLACK));
        strengthTextF.setBorder(new LineBorder(Color.BLACK));
        dexterityTextF.setBorder(new LineBorder(Color.BLACK));
        constitutionTextF.setBorder(new LineBorder(Color.BLACK));
        intelligenceTextF.setBorder(new LineBorder(Color.BLACK));
        wisdomTextF.setBorder(new LineBorder(Color.BLACK));
        charismaTextF.setBorder(new LineBorder(Color.BLACK));
        armorClassTextF.setBorder(new LineBorder(Color.BLACK));
        hitPointsTextF.setBorder(new LineBorder(Color.BLACK));
        attackBonusTextF.setBorder(new LineBorder(Color.BLACK));
        damageBonusTextF.setBorder(new LineBorder(Color.BLACK));
        
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Modifier "));
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Name "));
        attributesPanel.add(nameTextF);
        attributesPanel.add(nameModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Level "));
        attributesPanel.add(levelTextF);
        attributesPanel.add(levelModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Strength "));
        attributesPanel.add(strengthTextF);
        attributesPanel.add(strengthModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Dexterity "));
        attributesPanel.add(dexterityTextF);
        attributesPanel.add(dexModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Constitution "));
        attributesPanel.add(constitutionTextF);
        attributesPanel.add(conModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Intelligence "));
        attributesPanel.add(intelligenceTextF);
        attributesPanel.add(intelliModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Wisdom "));
        attributesPanel.add(wisdomTextF);
        attributesPanel.add(wisModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Charisma "));
        attributesPanel.add(charismaTextF);
        attributesPanel.add(chaModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Hitpoints "));
        attributesPanel.add(hitPointsTextF);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Attack Bonus "));
        attributesPanel.add(attackBonusTextF);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Damage Bonus "));
        attributesPanel.add(damageBonusTextF);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Armor Class "));
        attributesPanel.add(armorClassTextF);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        
        add(attributesPanel, BorderLayout.CENTER);
	}
	
	private void addOption() {
		JPanel optionPanel = new JPanel();
	    optionPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT));
	    optionPanel.setBorder(Config.border);
	    JTextArea optionTitle = new JTextArea("OPTION");
	    optionTitle.setEditable(false);

	    DButton createBtn = new DButton("CREATE", this);
	    createBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    editBtn = new DButton("EDIT", this);
	    editBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    deleteBtn = new DButton("DELETE", this);
	    deleteBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    deleteBtn.setEnabled(false);
	    DButton selectBtn = new DButton("SELECT", this);
	    selectBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    selectBtn.setEnabled(false);
	    DButton cancelBtn = new DButton("BACK", this);
	    cancelBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    
	    optionPanel.add(optionTitle);
	    optionPanel.add(createBtn);
	    optionPanel.add(editBtn);
	    optionPanel.add(deleteBtn);
	    optionPanel.add(selectBtn);
	    optionPanel.add(cancelBtn);
	    add(optionPanel, BorderLayout.EAST);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("CREATE")) {
			CharacterEditLayout.createAndShowGUI(null);
		} else if(e.getActionCommand().equals("EDIT")) {
			CharacterEditLayout.createAndShowGUI(null);
		} else if(e.getActionCommand().equals("DELETE")) {
			int index = list.getSelectedIndex();
			ListEntry key = (ListEntry) list.getSelectedValue();
			model.removeFighterByName(key.getValue());
			String gSave = Utils.toJson(model);
			Utils.save2File(Config.CHARACTOR_FILE, gSave);
			listModel.remove(index);
			list.setSelectedIndex(index-1<0?0:index-1);
			if(listModel.isEmpty()) {
				deleteBtn.setEnabled(false);
				editBtn.setEnabled(false);
			}
		} else if(e.getActionCommand().equals("SELECT")) {
			
		} else if(e.getActionCommand().equals("BACK")) {
			e = new ActionEvent(e.getSource(), e.getID(), "CHARACTOR-BACK");
		}
		listener.actionPerformed(e);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
        if (!list.isSelectionEmpty()){
        	deleteBtn.setEnabled(true);
            updateAttributes();
        }
	}

	private void updateAttributes() {
		if(!listModel.isEmpty()) {
			deleteBtn.setEnabled(true);
			editBtn.setEnabled(true);
		}
		ListEntry key = (ListEntry) list.getSelectedValue();
        Fighter fighter = model.getFighterByName(key.getValue());
		if(fighter == null)
			return;
        nameTextF.setText(fighter.getName());
		levelTextF.setText(Integer.toString(fighter.getLevel()));
		strengthTextF.setText(Integer.toString(fighter.getTotalStrength()));
		dexterityTextF.setText(Integer.toString(fighter.getTotalDexterity()));
		constitutionTextF.setText(Integer.toString(fighter.getTotalConstitution()));
		intelligenceTextF.setText(Integer.toString(fighter.getTotalIntelligence()));
		wisdomTextF.setText(Integer.toString(fighter.getTotalWisdom()));
		charismaTextF.setText(Integer.toString(fighter.getTotalCharisma()));
		armorClassTextF.setText(Integer.toString(fighter.getTotalArmorClass()));
		hitPointsTextF.setText(Integer.toString(fighter.getHitPoints()));
		attackBonusTextF.setText(Integer.toString(fighter.getAttackBonus()));
		damageBonusTextF.setText(Integer.toString(fighter.getDamageBonus()));
		
		strengthModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalStrength())));
		dexModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalDexterity())));
		conModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalConstitution())));
		intelliModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalIntelligence())));
		wisModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalWisdom())));
		chaModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalCharisma())));
	}

	@Override
	public void update(Observable o, Object arg) {
		updateAttributes();
	}
}
