package ddg.ui.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ddg.Config;
import ddg.model.Fighter;
import ddg.model.FighterModel;
import ddg.model.ItemEditorModel;
import ddg.model.item.BaseItem;
import ddg.model.item.Item;
import ddg.ui.view.component.ListEntryCellRenderer;
import ddg.utils.UtilityStorage;
import ddg.utils.Utils;
/**
 * 
 * 
 * This class provides the interface to allow the user choose an item for the character
 * 
 * @author Fei Yu
 * @date Mar 3, 2017
 */
public class ItemSelection extends JDialog implements ActionListener, ListSelectionListener{
    private JButton selectBtn = new JButton("      Select      ");
    private JButton cancelBtn = new JButton("    Cancel  ");
    
    private  ItemEditorModel itemListModel = new ItemEditorModel(); 
    private JList itemJList = new JList();
    private JScrollPane itemListPane = new JScrollPane(itemJList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    
    private JLabel nameLabel = new JLabel(" Equipment name ");
    private JLabel typeLabel = new JLabel(" Equipment type ");
    private JLabel attributeLabel = new JLabel(" Attribute ");
    private JLabel bonusLabel = new JLabel(" Value ");
    
//    private JDialog errorMessageWindow= new JDialog(this, "Item selection error", true);

	ArrayList<BaseItem> al1 = new ArrayList<>();
	public String fighterKeyName = "fighter111";
    private static CharacterEditor owner;
    public Item selectedItem;
    private Fighter fighter;
	private String wearingType;
    
    /**
     * Create the window
     * @param wearingType 
     * @param currentFighter2 The owner window of this window
     */
    public static void createAndShowGUI(Fighter fighter, String wearingType) 
    {
    	ItemSelection frame1 = new ItemSelection(fighter, wearingType); 
        frame1.setBounds(260, 20, 0, 0);
        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);
    }

    public ItemSelection(Fighter fighter, String wearingType) {
    	this.fighter = fighter;
    	this.wearingType = wearingType;
		System.out.println("before inintial");
		initialization();
		System.out.println("after inintial");
	}
    
	/**
     * Constructor
     */
    public ItemSelection(){
		initialization();
    }

    protected void initialization() {
        setTitle("Select Item 2");
        setModal(true);
        
//		String g = Utils.readFile(Config.ITEM_FILE);
//		this.itemListModel = Utils.fromJson(g, ItemEditorModel.class);
		this.itemListModel = Utils.readObject(Config.ITEM_FILE, ItemEditorModel.class);
		if (this.itemListModel == null) {
			this.itemListModel = new ItemEditorModel();
		}
		
        setLayout(new BorderLayout());
        JPanel backPanel= new JPanel(new BorderLayout());
        JPanel characterPanel= new JPanel(new BorderLayout());
        JPanel attributesPanel= new JPanel(new GridLayout(8,3,5,5));
        JPanel backpackPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel= new JPanel(new GridLayout(8,1,5,5));
        JPanel backpackListPanel = new JPanel(new BorderLayout());
        ImageIcon icon = new ImageIcon("icon1.jpg");  
        icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),  
                icon.getIconHeight(), Image.SCALE_DEFAULT)); 

        add(backPanel, BorderLayout.NORTH);
        backPanel.add(characterPanel, BorderLayout.WEST);
        backPanel.add(attributesPanel, BorderLayout.CENTER);
        backPanel.add(backpackPanel, BorderLayout.EAST);
        backpackPanel.add(buttonsPanel, BorderLayout.SOUTH);
        characterPanel.add(backpackListPanel, BorderLayout.CENTER);
        attributesPanel.setPreferredSize(new Dimension(300,320));
        JLabel lb1 = new JLabel(" ");
        JLabel nameModiferL = new JLabel(" L ");
        JLabel levelModiferL = new JLabel(" L ");
        JLabel strengthModiferL = new JLabel(" L ");
        lb1.setBorder(new LineBorder(Color.BLACK));
        nameModiferL.setBorder(new LineBorder(Color.BLACK));
        levelModiferL.setBorder(new LineBorder(Color.BLACK));
        strengthModiferL.setBorder(new LineBorder(Color.BLACK));
        lb1.setPreferredSize(new Dimension(20,15));
        lb1.setBounds(0, 0, 20, 15);
        lb1.setIcon(icon);
        lb1.setText(" 2 ");
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Name: "));
        attributesPanel.add(nameLabel);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Type: "));
        attributesPanel.add(typeLabel);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Attribute: "));
        attributesPanel.add(attributeLabel);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Value  "));
        attributesPanel.add(bonusLabel);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        
        buttonsPanel.add(new JLabel("     "));
        buttonsPanel.add(selectBtn);
        buttonsPanel.add(cancelBtn);
        buttonsPanel.add(new JLabel("     "));
        buttonsPanel.setSize(300,500);
        selectBtn.setEnabled(false);
    	addListView();
    	
    	selectBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			Item tempItem = selectedItem;
    			if(tempItem == null){
    				
    			} else {

        			if (fighter == null){
        				JOptionPane.showMessageDialog(null, "Null Fighter");
        			}
        			
        			if (wearingType.equals(tempItem.getName())){
        				
        				fighter.equip(wearingType, tempItem);
	
        				System.out.println(fighter);
            			System.out.println(tempItem.getId() + " " + tempItem.getName() + " " + tempItem.getIncrease() + " " + tempItem.getBonus());
            			dispose();
        			}
        			else{
        				JOptionPane.showMessageDialog(null, "The equipment type must match what you chose.");
        			}
        			selectedItem = null;
    			}
    			
            }
        });
    	cancelBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			dispose();
            }
        });    	

        backpackListPanel.add(itemListPane, BorderLayout.CENTER);    			
	}

	/**
     * This method is to fill the list of items.
     */
	private void addListView() {
		DefaultListModel l = itemListModel.getListModel();
		itemJList.setModel(l);
		itemJList.setCellRenderer(new ListEntryCellRenderer());
		itemJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemJList.addListSelectionListener(this);
		itemJList.setVisibleRowCount(15);
		itemListPane.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT-3 * Config.BTN_HEIGHT));
	}
	@Override
	public void actionPerformed(ActionEvent e) {		
	}
	
	@Override
    public void valueChanged(ListSelectionEvent e)
    {
		if (e.getValueIsAdjusting() == false) {
			int index = itemJList.getSelectedIndex();
			if(index >= 0) {
				Item item = itemListModel.getItemByIndex(index);
				
				nameLabel.setText(item.getId());
				typeLabel.setText(item.getName());
				attributeLabel.setText(item.getIncrease());
				bonusLabel.setText(Integer.toString(item.getBonus()));
				selectedItem = item;
				UtilityStorage.setItem(item);
		        selectBtn.setEnabled(true);
			}
		}
        System.out.println("value changed");
    }
	
	/**
	 * Return this window to its caller
	 * @return This window
	 */
	public ItemSelection getThisFrame(){
		return this;
	}
	
 }
