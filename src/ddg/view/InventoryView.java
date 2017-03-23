package ddg.view;
import java.util.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import ddg.Config;
import ddg.item.entity.BaseItem;
import ddg.model.Fighter;
import ddg.model.FighterModel;
import ddg.utils.UtilityStorage;
import ddg.utils.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.*;
/**
 * 
 * 
 * This class provides an interface to allow user to check a character's attributes, worn equipments and the backpack.
 * 
 * @author Fei Yu
 * @date Mar 3, 2017
 */
public class InventoryView extends JPanel implements ActionListener, ListSelectionListener {

    private final JButton backBtn = new JButton("    Back  ");
    protected final JButton equipBtn = new JButton("    Equip   ");
    protected final JButton removeBtn = new JButton("   Take off  ");

    protected final JButton helmetBtn = new JButton();
    protected final JButton beltBtn = new JButton("  Belt ");
    protected final JButton ringBtn = new JButton("  Ring  ");
    protected final JButton armorBtn = new JButton("  Armor  ");
    protected final JButton shieldBtn = new JButton("  Shield  ");
    protected final JButton bootsBtn = new JButton("    Boots  ");
    protected final JButton weaponBtn = new JButton("   Weapon  ");
    
    private final DefaultListModel<String> backpackItemModel = new DefaultListModel<String>(); 
    protected final JList<String> backpackItemList = new JList<String>();
        
    private JLabel nameTextF = new JLabel("   ");
    private JLabel levelTextF = new JLabel("   ");
    private JLabel typeTextF = new JLabel("   ");
    private JLabel strengthTextF = new JLabel("   ");
    private JLabel dexterityTextF = new JLabel("   ");
    private JLabel constitutionTextF = new JLabel("   ");
    private JLabel intelligenceTextF = new JLabel("   ");
    private JLabel wisdomTextF = new JLabel("   ");
    private JLabel charismaTextF = new JLabel("   ");
    
    private JLabel strengthModiferL = new JLabel("   ");
    private JLabel dexModiferL = new JLabel("   ");
    private JLabel conModiferL = new JLabel("   ");
    private JLabel intelliModiferL = new JLabel("   ");
    private JLabel wisModiferL = new JLabel("   ");
    private JLabel chaModiferL = new JLabel("   ");
    private JPanel backpackListPanel = new JPanel(new BorderLayout());
    static JDialog dialog;
    
    private JLabel equipmentTypeL = new JLabel("   ");
    private JLabel attributeL = new JLabel("   ");
    private JLabel valueL = new JLabel("   ");
    
    public String selectedWorn = null;
    public BaseItem selectedBackPackItem = null;
    public Fighter fighter = new Fighter();
    private static CharacterEditor owner;
   
    /**
     * 
     * @param ownerFrame
     */
    public static void createAndShowGUI(CharacterEditor ownerFrame) {
        owner = (CharacterEditor) ownerFrame;
        dialog = new JDialog(owner);
        InventoryView frame1 = new InventoryView(owner.getOwner().fighter); 
        dialog.add(frame1);
        dialog.setBounds(260, 260, 0, 0);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setTitle("Inventory");
        dialog.setModal(true);
        dialog.setVisible(true);
//        
//    	InventoryView frame1 = new InventoryView(); 
//        frame1.setBounds(260, 260, 0, 0);
//        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        frame1.pack();
//        frame1.setVisible(true);
    }

    /**
     * 
     * @param fighter
     */
	public static void createAndShowGUI(Fighter fighter) {

        dialog = new JDialog(owner);
        InventoryView frame1 = new InventoryView(fighter); 
        dialog.add(frame1);
        dialog.setBounds(260, 260, 0, 0);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setTitle("Inventory");
        dialog.setModal(true);
        dialog.setVisible(true);
        
//    	InventoryView frame1 = new InventoryView(fighter); 
//        frame1.setBounds(260, 260, 0, 0);
////        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
////        frame1.pack();
//        frame1.setVisible(true);
		
	}    
	InventoryView(Fighter fighter){
    	super();
		this.fighter = fighter;
		System.out.println("before inintial");
		initialization();

		System.out.println("after inintial");
	}

    /**
     * Constructor
     */
    InventoryView()
    {
    	super();
//    	this.fighter = owner.getOwner().fighter;
    	initialization();        
    }
    
    /**
     * Initialize the inventory view window.
     */
    protected void initialization() {
//        setTitle("Inventory");
//        setModal(true);
        setLayout(new BorderLayout());
        JPanel backPanel= new JPanel(new BorderLayout());
        JPanel characterPanel= new JPanel(new BorderLayout());
        JPanel attributesPanel= new JPanel(new GridLayout(11,4,5,5));
        JPanel backpackPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel= new JPanel(new GridLayout(5,1,5,5));
//        backpackListPanel = new JPanel(new BorderLayout());
        ImageIcon icon = new ImageIcon("icon1.jpg");  
        icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),  
                icon.getIconHeight(), Image.SCALE_DEFAULT));         

        JPanel characterLeftPanel= new JPanel(new GridLayout(5,1,5,5));
        JPanel characterRightPanel= new JPanel(new GridLayout(5,1,5,5));
        JPanel characterImagePanel= new EmbeddedPanel();
        JPanel characterButtomPanel= new JPanel(new FlowLayout());
        add(backPanel, BorderLayout.NORTH);
        backPanel.add(characterPanel, BorderLayout.WEST);
        backPanel.add(attributesPanel, BorderLayout.CENTER);
        backPanel.add(backpackPanel, BorderLayout.EAST);
        backpackPanel.add(buttonsPanel, BorderLayout.SOUTH);
        backpackPanel.add(backpackListPanel, BorderLayout.CENTER);
        characterPanel.add(characterLeftPanel, BorderLayout.WEST);
        characterPanel.add(characterImagePanel, BorderLayout.CENTER);
        characterPanel.add(characterRightPanel, BorderLayout.EAST);
        characterPanel.add(characterButtomPanel, BorderLayout.SOUTH);
        characterButtomPanel.add(removeBtn);
        helmetBtn.setIcon(icon);
        characterLeftPanel.add(helmetBtn);
        characterLeftPanel.add(armorBtn);
        characterLeftPanel.add(beltBtn);
        characterRightPanel.add(ringBtn);
        characterRightPanel.add(bootsBtn);
        characterRightPanel.add(weaponBtn);
        characterRightPanel.add(shieldBtn);
        
        helmetBtn.setMargin(new Insets(1,1,1,1));
        armorBtn.setMargin(new Insets(1,1,1,1));
        beltBtn.setMargin(new Insets(1,1,1,1));
        ringBtn.setMargin(new Insets(1,1,1,1));
        bootsBtn.setMargin(new Insets(1,1,1,1));
        weaponBtn.setMargin(new Insets(1,1,1,1));
        shieldBtn.setMargin(new Insets(1,1,1,1));

        characterImagePanel.setBounds(0, 0, 500, 500);
        characterImagePanel.add(new JLabel("                                                                 "));
        backpackListPanel.setPreferredSize(new Dimension(200,260));
        characterLeftPanel.setPreferredSize(new Dimension(70,320));
        characterRightPanel.setPreferredSize(new Dimension(70,320));
        attributesPanel.setPreferredSize(new Dimension(360,320));
        characterImagePanel.setPreferredSize(new Dimension(230,320));

        strengthModiferL.setBorder(new LineBorder(Color.BLACK));
        dexModiferL.setBorder(new LineBorder(Color.BLACK));
        conModiferL.setBorder(new LineBorder(Color.BLACK));
        intelliModiferL.setBorder(new LineBorder(Color.BLACK));
        wisModiferL.setBorder(new LineBorder(Color.BLACK));
        chaModiferL.setBorder(new LineBorder(Color.BLACK));
        nameTextF.setBorder(new LineBorder(Color.BLACK));
        levelTextF.setBorder(new LineBorder(Color.BLACK));
        typeTextF.setBorder(new LineBorder(Color.BLACK));
        strengthTextF.setBorder(new LineBorder(Color.BLACK));
        dexterityTextF.setBorder(new LineBorder(Color.BLACK));
        constitutionTextF.setBorder(new LineBorder(Color.BLACK));
        intelligenceTextF.setBorder(new LineBorder(Color.BLACK));
        wisdomTextF.setBorder(new LineBorder(Color.BLACK));
        charismaTextF.setBorder(new LineBorder(Color.BLACK));
        
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Modifier "));
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Name "));
        attributesPanel.add(nameTextF);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Level "));
        attributesPanel.add(levelTextF);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Type "));
        attributesPanel.add(typeTextF);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Item Type "));
        attributesPanel.add(new JLabel(" Strength "));
        attributesPanel.add(strengthTextF);
        attributesPanel.add(strengthModiferL);
        attributesPanel.add(equipmentTypeL);
        attributesPanel.add(new JLabel(" Dexterity "));
        attributesPanel.add(dexterityTextF);
        attributesPanel.add(dexModiferL);
        attributesPanel.add(new JLabel(" Attribute  "));
        attributesPanel.add(new JLabel(" Constitution "));
        attributesPanel.add(constitutionTextF);
        attributesPanel.add(conModiferL);
        attributesPanel.add(attributeL);
        attributesPanel.add(new JLabel(" Intelligence "));
        attributesPanel.add(intelligenceTextF);
        attributesPanel.add(intelliModiferL);
        attributesPanel.add(new JLabel(" Value   "));
        attributesPanel.add(new JLabel(" Wisdom "));
        attributesPanel.add(wisdomTextF);
        attributesPanel.add(wisModiferL);
        attributesPanel.add(valueL);
        attributesPanel.add(new JLabel(" Charisma "));
        attributesPanel.add(charismaTextF);
        attributesPanel.add(chaModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(equipBtn);
        buttonsPanel.add(backBtn);
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.setSize(300,500);
        
        JScrollPane itemListPane = new JScrollPane(backpackItemList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        backpackListPanel.add(itemListPane, BorderLayout.CENTER);
        backpackItemList.setPreferredSize(new Dimension(200,220));
        initMethod();
	}

	protected void initMethod() {
		for(BaseItem i: fighter.getBackpack()){
			backpackItemModel.addElement(i.getId());
		}      	
		backpackItemList.addListSelectionListener(this);
		backpackItemList.setModel(backpackItemModel);
		
		focusManage();
		
    	helmetBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.HELMET;
    			Fighter fighter1;
    			fighter1 = fighter;
    			System.out.println("fighter's inventory size is " + fighter1.getBackpack().size());
            }
        });
    	
    	beltBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.BELT;
            }
        });

    	ringBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.RING;
            }
        });
    	armorBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.ARMOR;
            }
        });
    	shieldBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.SHIELD;
            }
        });
    	bootsBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.BOOTS;
            }
        });
    	weaponBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.WEAPON;
            }
        });
    	removeBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			try{
        			if (fighter.getBackpack().size() >= Config.BACKPACK_SIZE){
    					JOptionPane.showMessageDialog(null, "The backpack is full, there is no place for the item!", "Warning", JOptionPane.WARNING_MESSAGE);
        			} else {
        				fighter.takeoffEquipment(selectedWorn);
        			}    				
    			}
    			catch (NullPointerException ex){
    				JOptionPane.showMessageDialog(null, "You need to choose an equipment", "Warning", JOptionPane.WARNING_MESSAGE);
    			}    			
            }
        });
    	
    	equipBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
    			try{
    				boolean isWearable = false;
    				for (int i = 0; i< BaseItem.NAME.length; i++){
    					if (BaseItem.NAME[i].equals(selectedBackPackItem.getName())){
    						isWearable = true;
            			}
    				}
    				if (isWearable == true){
    					

        				fighter.wearItem(selectedBackPackItem);
        				

						equipmentTypeL.setText("");
						attributeL.setText("");
						valueL.setText("");
    				} else {
	    				JOptionPane.showMessageDialog(null, "This is not a wearable equipment.", "Warning",JOptionPane.WARNING_MESSAGE);
    				}    				
    			}
    			catch (NullPointerException ex){
    				JOptionPane.showMessageDialog(null, "You need to choose an item.", "Warning", JOptionPane.WARNING_MESSAGE);
    			}
    			catch (ConcurrentModificationException ex){
    				JOptionPane.showMessageDialog(null, "The equipment has been equiped!", "Message", JOptionPane.WARNING_MESSAGE);
    			}
				
	        }
		});
    	
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("cancel clicked");
//				dispose();
				if(dialog!=null)
					dialog.dispose();
	        }
		});
	}
	
	
	protected void updateView() {
		updateView(fighter);
	}

	/**
	 * This method call two method to update character information.
	 * @param fighter The character selected
	 */
	protected void updateView(Fighter f) {
		fighter = f;
		backpackItemModel.clear();
		for (BaseItem i: fighter.getBackpack()){
			backpackItemModel.addElement(i.getId());
		}		
		updateAttributes(fighter);		
		setEquipmentIcon(fighter);
	}

	/**
	 * This method update attribute values of the character when his attributes change.
	 * @param fighter The character selected
	 */
	protected void updateAttributes(Fighter fighter) {

		nameTextF.setText(fighter.getName());
		levelTextF.setText(Integer.toString(fighter.getLevel()));
		typeTextF.setText(fighter.getType());
		
		strengthTextF.setText(Integer.toString(fighter.getTotalStrength()));
		dexterityTextF.setText(Integer.toString(fighter.getTotalDexterity()));
		constitutionTextF.setText(Integer.toString(fighter.getTotalConstitution()));
		intelligenceTextF.setText(Integer.toString(fighter.getTotalIntelligence()));
		wisdomTextF.setText(Integer.toString(fighter.getTotalWisdom()));
		charismaTextF.setText(Integer.toString(fighter.getTotalCharisma()));
		
		strengthModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalStrength())));
		dexModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalDexterity())));
		conModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalConstitution())));
		intelliModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalIntelligence())));
		wisModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalWisdom())));
		chaModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalCharisma())));

	}
	
	/**
	 * This method update the icon of the worn equipments.
	 * @param fighter The character selected
	 */
	private void setEquipmentIcon(Fighter fighter) {
		
		if (fighter.isHelmetOn()){
			helmetBtn.setText("");
			helmetBtn.setIcon(Config.HELMET_ICON);
		} else{
			helmetBtn.setText("Helmet");
			helmetBtn.setIcon(null);	    			
		}
		if (fighter.isArmorOn()){
			armorBtn.setText("");
			armorBtn.setIcon(Config.ARMOR_ICON);
		} else{
			armorBtn.setText("Armor");
			armorBtn.setIcon(null);	    			
		}
		if (fighter.isBeltOn()){
			beltBtn.setText("");
			beltBtn.setIcon(Config.BELT_ICON);
		} else{
			beltBtn.setText("Belt");
			beltBtn.setIcon(null);	    			
		}
		if (fighter.isBootsOn()){
			bootsBtn.setText("");
			bootsBtn.setIcon(Config.BOOTS_ICON);
		} else{
			bootsBtn.setText("Boots");
			bootsBtn.setIcon(null);	    			
		}
		if (fighter.isRingOn()){
			ringBtn.setText("");
			ringBtn.setIcon(Config.RING_ICON);
		} else{
			ringBtn.setText("Ring");
			ringBtn.setIcon(null);	    			
		}
		if (fighter.isShieldOn()){
			shieldBtn.setText("");
			shieldBtn.setIcon(Config.SHIELD_ICON);
		} else{
			shieldBtn.setText("Shield");
			shieldBtn.setIcon(null);	    			
		}
		if (fighter.isWeaponOn()){
			weaponBtn.setText("");
			weaponBtn.setIcon(Config.WEAPON_ICON);
		} else{
			weaponBtn.setText("Weapon");
			weaponBtn.setIcon(null);	    			
		}

	}

    /**
     * This method manage the actions of the window focus
     */
	private void focusManage() {
		if(dialog==null)
			return;
		dialog.addWindowFocusListener(new WindowFocusListener() {          	
	        @Override  
	        public void windowGainedFocus(WindowEvent e) { 
	        	updateView();				
	        	Utils.displayFighterInfo(fighter);
	                        
	            	
	        }

	
	        @Override  
	        public void windowLostFocus(WindowEvent e) {
	            System.out.println("The CS window is not focused.");  
	        }                
		});  
		
	}


	/**
	 * 
	 * @return
	 */
	public InventoryView getThisFrame() {
		return this;
	}
	
	/**
	 * 
	 */
	public CharacterEditor getOwner(){
		return owner;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	/**
	 * 
	 * 
	 * This internal class is to draw a background picture in the frame
	 * 
	 * @author Fei Yu
	 * @date Mar 3, 2017
	 */
	class EmbeddedPanel extends JPanel{

	    private ImageIcon icon;  
	    private Image img;  
	    public EmbeddedPanel() {  
	    	super();
	    	setOpaque(true);
	    	img = Toolkit.getDefaultToolkit().getImage( "example.jpg"); 
	    }  
	    
	    /**
	     * This method is override method to draw a picture
	     */
	    public void paintComponent(Graphics g) {  
	        super.paintComponent(g);  
	        g.drawImage(img, 0, 0,230, 320, this);  
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			int index = backpackItemList.getSelectedIndex();
			if(index >= 0) {
				System.out.println("list select:"+index);
				BaseItem item = fighter.getBackpack().get(index);
				
				equipmentTypeL.setText(item.getName());
				attributeL.setText(item.getIncrease());
				valueL.setText(Integer.toString(item.getBonus()));
				selectedWorn = item.getName();
				selectedBackPackItem = item;
				
				UtilityStorage.setItem(item);
			}
		}
        System.out.println("value changed");
		
	}

}
