package ddg.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

import ddg.Config;
import ddg.item.entity.BaseItem;
import ddg.model.Fighter;
import ddg.model.FighterModel;
import ddg.model.ItemEditorModel;
import ddg.utils.UtilityStorage;
import ddg.utils.Utils;
import ddg.view.component.DComboBox;
import ddg.view.component.ListEntryCellRenderer;
/**
 * 
 * 
 * This class provides the interface to allow the user choose an item for the character
 * 
 * @author Fei Yu
 * @date Mar 3, 2017
 */
public class ItemSelection extends JDialog implements ActionListener, ListSelectionListener{
    private final JButton selectBtn = new JButton("      Select      ");
    private final JButton cancelBtn = new JButton("    Cancel  ");
    
    private  ItemEditorModel itemListModel = new ItemEditorModel(); 
    private final JList itemJList = new JList();
    private final JScrollPane itemListPane = new JScrollPane(itemJList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    
    private final JLabel nameLabel = new JLabel(" Equipment name ");
    private final JLabel typeLabel = new JLabel(" Equipment type ");
    private final JLabel attributeLabel = new JLabel(" Attribute ");
    private final JLabel bonusLabel = new JLabel(" Value ");
    
    private JDialog errorMessageWindow= new JDialog(this, "Item selection error", true);
	private JButton okBtn = new JButton("   OK   ");

	ArrayList<BaseItem> al1 = new ArrayList<>();
	public int id = 100;
	public String fighterKeyName = "fighter111";
    private static CharacterEditLayout owner;
    public BaseItem selectedItem;
    
    /**
     * Create the window
     * @param ownerFrame The owner window of this window
     */
    public static void createAndShowGUI(CharacterEditLayout ownerFrame) 
    {

        owner = (CharacterEditLayout) ownerFrame;
        System.out.println("========"+owner);
    	ItemSelection frame1 = new ItemSelection(); 
        frame1.setBounds(260, 260, 0, 0);
        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);
    }

    /**
     * Constructor
     */
    ItemSelection()
    {
        super();
        setTitle("Select Item 2");
        setModal(true);
        
		String g = Utils.readFile(Config.ITEM_FILE);
		this.itemListModel = Utils.fromJson(g, ItemEditorModel.class);
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
        itemJList.setPreferredSize(new Dimension(200,560));
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
    	
    	okBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			errorMessageWindow.dispose();
            }
        }); 

        
    	
    	selectBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			BaseItem tempItem = selectedItem;
    			if(tempItem == null){
    				
    			} else {

        			if (owner.getOwner().fighter == null){
        				owner.getOwner().fighter = new Fighter();
        			}
        			if (owner.wearingType.equals(tempItem.getName())){
            			owner.getOwner().fighter.setEquipOn(owner.wearingType);
            			
            			try{
                			for (BaseItem i: owner.getOwner().fighter.getWorn()){
                				if (i.getName().equals(owner.wearingType)){
                					owner.getOwner().fighter.gainBonus(i.getIncrease(), i.getBonus(), "-");
                					owner.getOwner().fighter.getWorn().remove(i);
                				}
                			}
            			}
            			catch (ConcurrentModificationException e1) {        				
            			}        			
            			
    					owner.getOwner().fighter.gainBonus(tempItem.getIncrease(), tempItem.getBonus(), "+");
            			owner.getOwner().fighter.getWorn().add(tempItem);
            			if (owner.wearingType.equals(BaseItem.HELMET)){
            				owner.helmetBtn.setText("");
                			owner.helmetBtn.setIcon(Config.iconByType(owner.wearingType));
            			} else if (owner.wearingType.equals(BaseItem.ARMOR)){
                			owner.armorBtn.setText("");
                			owner.armorBtn.setIcon(Config.iconByType(owner.wearingType));
            			} else if (owner.wearingType.equals(BaseItem.BOOTS)){
                			owner.bootsBtn.setText("");
                			owner.bootsBtn.setIcon(Config.iconByType(owner.wearingType));
            			} else if (owner.wearingType.equals(BaseItem.RING)){
                			owner.ringBtn.setText("");
                			owner.ringBtn.setIcon(Config.iconByType(owner.wearingType));
            			} else if (owner.wearingType.equals(BaseItem.BELT)){
                			owner.beltBtn.setText("");
                			owner.beltBtn.setIcon(Config.iconByType(owner.wearingType));
            			} else if (owner.wearingType.equals(BaseItem.WEAPON)){
                			owner.weaponBtn.setText("");
                			owner.weaponBtn.setIcon(Config.iconByType(owner.wearingType));
            			} else if (owner.wearingType.equals(BaseItem.SHIELD)){
                			owner.shieldBtn.setText("");
                			owner.shieldBtn.setIcon(Config.iconByType(owner.wearingType));
            			}		
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
//				System.out.println("list select:"+index);
				BaseItem item = itemListModel.getItemByIndex(index);
				
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
