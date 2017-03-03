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
public class InventoryView extends JDialog implements ActionListener, ListSelectionListener {

    private final JButton cancelBtn = new JButton("    Back  ");
    private final JButton equipBtn = new JButton("    Equip   ");
    private final JButton removeBtn = new JButton("   Take off  ");

    private final JButton helmetBtn = new JButton();
    private final JButton beltBtn = new JButton("  Belt ");
    private final JButton ringBtn = new JButton("  Ring  ");
    private final JButton armorBtn = new JButton("  Armor  ");
    private final JButton shieldBtn = new JButton("  Shield  ");
    private final JButton bootsBtn = new JButton("    Boots  ");
    private final JButton weaponBtn = new JButton("   Weapon  ");
    
    private final DefaultListModel<String> backpackItemModel = new DefaultListModel<String>(); 
    private final JList<String> backpackItemList = new JList<String>();
        
    private final JLabel nameTextF = new JLabel("   ");
    private final JLabel levelTextF = new JLabel("   ");
    private final JLabel strengthTextF = new JLabel("   ");
    private final JLabel dexterityTextF = new JLabel("   ");
    private final JLabel constitutionTextF = new JLabel("   ");
    private final JLabel intelligenceTextF = new JLabel("   ");
    private final JLabel wisdomTextF = new JLabel("   ");
    private final JLabel charismaTextF = new JLabel("   ");
    
    JLabel nameModiferL = new JLabel("   ");
    JLabel levelModiferL = new JLabel("   ");
    JLabel strengthModiferL = new JLabel("   ");
    JLabel dexModiferL = new JLabel("   ");
    JLabel conModiferL = new JLabel("   ");
    JLabel intelliModiferL = new JLabel("   ");
    JLabel wisModiferL = new JLabel("   ");
    JLabel chaModiferL = new JLabel("   ");

    private final JLabel equipmentTypeL = new JLabel("   ");
    private final JLabel attributeL = new JLabel("   ");
    private final JLabel valueL = new JLabel("   ");
    
    public String selectedWorn = null;
    public BaseItem selectedBackPackItem = null;
    public Fighter f2 = null;
    private static CharacterEditLayout owner;
   
    /**
     * 
     * @param ownerFrame
     */
    public static void createAndShowGUI(CharacterEditLayout ownerFrame) {
        owner = (CharacterEditLayout) ownerFrame;
    	InventoryView frame1 = new InventoryView(); 
        frame1.setBounds(260, 260, 0, 0);
        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);
    }

    /**
     * Constructor
     */
    InventoryView()
    {
        super();
        setTitle("Inventory");
        setModal(true);
        setLayout(new BorderLayout());
        JPanel backPanel= new JPanel(new BorderLayout());
        JPanel characterPanel= new JPanel(new BorderLayout());
        JPanel attributesPanel= new JPanel(new GridLayout(10,4,5,5));
        JPanel backpackPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel= new JPanel(new GridLayout(5,1,5,5));
        JPanel backpackListPanel = new JPanel(new BorderLayout());
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

        characterImagePanel.setBounds(0, 0, 500, 500);
        characterImagePanel.add(new JLabel("                                                                 "));
        backpackListPanel.setPreferredSize(new Dimension(200,260));
        characterLeftPanel.setPreferredSize(new Dimension(100,320));
        characterRightPanel.setPreferredSize(new Dimension(100,320));
        attributesPanel.setPreferredSize(new Dimension(600,320));
        characterImagePanel.setPreferredSize(new Dimension(230,320));

        JLabel lb1 = new JLabel(" ");
        lb1.setBorder(new LineBorder(Color.BLACK));
        nameModiferL.setBorder(new LineBorder(Color.BLACK));
        levelModiferL.setBorder(new LineBorder(Color.BLACK));
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
        lb1.setPreferredSize(new Dimension(20,15));
        lb1.setBounds(0, 0, 20, 15);
        lb1.setIcon(icon);
        lb1.setText(" 2 ");
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
        attributesPanel.add(new JLabel(" Item Type "));
        attributesPanel.add(new JLabel(" Strength "));
        attributesPanel.add(strengthTextF);
        attributesPanel.add(strengthModiferL);
        attributesPanel.add(equipmentTypeL);
        attributesPanel.add(new JLabel(" Dexterity "));
        attributesPanel.add(dexterityTextF);
        attributesPanel.add(dexModiferL);
        attributesPanel.add(new JLabel("  Attribute  "));
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
        buttonsPanel.add(cancelBtn);
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.setSize(300,500);

        focusManage();

        backpackItemList.addListSelectionListener(this);
        backpackItemList.setModel(backpackItemModel);
        JScrollPane itemListPane = new JScrollPane(backpackItemList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        backpackListPanel.add(itemListPane, BorderLayout.CENTER);
        backpackItemList.setPreferredSize(new Dimension(200,220));
        for(BaseItem i: owner.getOwner().fighter.getBackpack()){
        	backpackItemModel.addElement(i.getId());
        }      	
        
    	helmetBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.HELMET;
    			Fighter fighter1;
    			fighter1 = owner.getOwner().fighter;
    			System.out.println("fighter's inventory size is " + fighter1.getBackpack().size());
            }
        });
    	
    	beltBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.BELT;
    			Fighter fighter1;
    			fighter1 = owner.getOwner().fighter;
            }
        });

    	ringBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.RING;
    			Fighter fighter1;
    			fighter1 = owner.getOwner().fighter;
            }
        });
    	armorBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.ARMOR;
    			Fighter fighter1;
    			fighter1 = owner.getOwner().fighter;
            }
        });
    	shieldBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.SHIELD;
    			Fighter fighter1;
    			fighter1 = owner.getOwner().fighter;
            }
        });
    	bootsBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.BOOTS;
    			Fighter fighter1;
    			fighter1 = owner.getOwner().fighter;
            }
        });
    	weaponBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			selectedWorn = BaseItem.WEAPON;
    			Fighter fighter1;
    			fighter1 = owner.getOwner().fighter;
            }
        });
    	removeBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			try{
        			if (owner.getOwner().fighter.getBackpack().size() >= 10){
    					JOptionPane.showMessageDialog(null, "The backpack is full, there is no place for the item!", "Warning", JOptionPane.WARNING_MESSAGE);
        			} else {
            			try{
                			for (BaseItem i: owner.getOwner().fighter.getWorn()){
                				if (i.getName().equals(selectedWorn)){
                					owner.getOwner().fighter.gainBonus(i.getIncrease(), i.getBonus(), "-");
                					owner.getOwner().fighter.getBackpack().add(i);
                					owner.getOwner().fighter.getWorn().remove(i);
                        			owner.getOwner().fighter.setEquipOff(selectedWorn);
//                					System.out.println("backpack=========" + owner.getOwner().fighter.getBackpack());  
                					
                				} else {
                					JOptionPane.showMessageDialog(null, "The character is not wearing a " + selectedWorn.toLowerCase() + ".", "Warning", JOptionPane.WARNING_MESSAGE);
                				}
                			}
            			}
            			catch (ConcurrentModificationException e1) {
            				JOptionPane.showMessageDialog(null, "The equipment has been move to backpack!", "Message", JOptionPane.WARNING_MESSAGE);
            			}
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
    					for (BaseItem item: owner.getOwner().fighter.getWorn()){
	        				if (item.getName().equals(selectedBackPackItem.getName())){
	        					owner.getOwner().fighter.gainBonus(item.getIncrease(), item.getBonus(), "-");
	        					owner.getOwner().fighter.getBackpack().add(item);
	        					owner.getOwner().fighter.getWorn().remove(item);
//	        					System.out.println("backpack=========" + owner.getOwner().fighter.getBackpack());  
	        					
	        				}
    					}
						owner.getOwner().fighter.gainBonus(selectedBackPackItem.getIncrease(), selectedBackPackItem.getBonus(), "+");
						owner.getOwner().fighter.getBackpack().remove(selectedBackPackItem);
						owner.getOwner().fighter.getWorn().add(selectedBackPackItem);
						owner.getOwner().fighter.setEquipOn(selectedBackPackItem.getName());
						JOptionPane.showMessageDialog(null, "The item is worn.", "Message", JOptionPane.WARNING_MESSAGE); 
    				} else {
	    				JOptionPane.showMessageDialog(null, "This is not a wearable equipment.", "Warning",JOptionPane.WARNING_MESSAGE);
    				}    				
    			}
    			catch (NullPointerException ex){
    				JOptionPane.showMessageDialog(null, "You need to choose an item.", "Warning", JOptionPane.WARNING_MESSAGE);
    			}
				
	        }
		});
    	
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("cancel clicked");
				dispose();				
	        }
		});
        
    }

    /**
     * This method manage the actions of the window focus
     */
	private void focusManage() {        
		this.addWindowFocusListener(new WindowFocusListener() {          	
	        @Override  
	        public void windowGainedFocus(WindowEvent e) { 
	        	backpackItemModel.clear();
	        	for (BaseItem i: owner.getOwner().fighter.getBackpack()){
                	backpackItemModel.addElement(i.getId());
	        	}

				strengthTextF.setText(Integer.toString(owner.getOwner().fighter.getTotalStrength()));
				dexterityTextF.setText(Integer.toString(owner.getOwner().fighter.getTotalDexterity()));
				constitutionTextF.setText(Integer.toString(owner.getOwner().fighter.getTotalConstitution()));
				intelligenceTextF.setText(Integer.toString(owner.getOwner().fighter.getTotalIntelligence()));
				wisdomTextF.setText(Integer.toString(owner.getOwner().fighter.getTotalWisdom()));
				charismaTextF.setText(Integer.toString(owner.getOwner().fighter.getTotalCharisma()));
	    		strengthModiferL.setText(Integer.toString(owner.getOwner().fighter.getModifier(owner.getOwner().fighter.getTotalStrength())));
	    		dexModiferL.setText(Integer.toString(owner.getOwner().fighter.getModifier(owner.getOwner().fighter.getTotalDexterity())));
	    		conModiferL.setText(Integer.toString(owner.getOwner().fighter.getModifier(owner.getOwner().fighter.getTotalConstitution())));
	    		intelliModiferL.setText(Integer.toString(owner.getOwner().fighter.getModifier(owner.getOwner().fighter.getTotalIntelligence())));
	    		wisModiferL.setText(Integer.toString(owner.getOwner().fighter.getModifier(owner.getOwner().fighter.getTotalWisdom())));
	    		chaModiferL.setText(Integer.toString(owner.getOwner().fighter.getModifier(owner.getOwner().fighter.getTotalCharisma())));
	    		
	    		if (owner.getOwner().fighter.isHelmetOn){
	    			helmetBtn.setText("");
	    			helmetBtn.setIcon(Config.HELMET_ICON);
	    		} else{
	    			helmetBtn.setText("Helmet");
	    			helmetBtn.setIcon(null);	    			
	    		}
	    		if (owner.getOwner().fighter.isArmorOn){
	    			armorBtn.setText("");
	    			armorBtn.setIcon(Config.ARMOR_ICON);
	    		} else{
	    			armorBtn.setText("Armor");
	    			armorBtn.setIcon(null);	    			
	    		}
	    		if (owner.getOwner().fighter.isBeltOn){
	    			beltBtn.setText("");
	    			beltBtn.setIcon(Config.BELT_ICON);
	    		} else{
	    			beltBtn.setText("Belt");
	    			beltBtn.setIcon(null);	    			
	    		}
	    		if (owner.getOwner().fighter.isBootsOn){
	    			bootsBtn.setText("");
	    			bootsBtn.setIcon(Config.BOOTS_ICON);
	    		} else{
	    			bootsBtn.setText("Boots");
	    			bootsBtn.setIcon(null);	    			
	    		}
	    		if (owner.getOwner().fighter.isRingOn){
	    			ringBtn.setText("");
	    			ringBtn.setIcon(Config.RING_ICON);
	    		} else{
	    			ringBtn.setText("Ring");
	    			ringBtn.setIcon(null);	    			
	    		}
	    		if (owner.getOwner().fighter.IsShieldOn){
	    			shieldBtn.setText("");
	    			shieldBtn.setIcon(Config.SHIELD_ICON);
	    		} else{
	    			shieldBtn.setText("Shield");
	    			shieldBtn.setIcon(null);	    			
	    		}
	    		if (owner.getOwner().fighter.isWeaponOn){
	    			weaponBtn.setText("");
	    			weaponBtn.setIcon(Config.WEAPON_ICON);
	    		} else{
	    			weaponBtn.setText("Weapon");
	    			weaponBtn.setIcon(null);	    			
	    		}

		    	 Fighter f2 = owner.getOwner().fighter;

	    		System.out.println("===========================");
	    		System.out.println(f2);
	    		System.out.println(f2.getName());
	    		System.out.println("backpack now  has " + f2.getBackpack().size());
	    		System.out.println("worn now  has " +f2.getWorn().size());
	    		System.out.print(f2.isHelmetOn);
	    		System.out.print(" ");
	    		System.out.print(f2.isArmorOn);
	    		System.out.print(" ");
	    		System.out.print(f2.isBeltOn);
	    		System.out.print(" ");
	    		System.out.print(f2.isRingOn); 
	    		System.out.print(" ");
	    		System.out.print(f2.isBootsOn);
	    		System.out.print(" ");
	    		System.out.print(f2.isWeaponOn);
	    		System.out.print(" ");
	    		System.out.print(f2.IsShieldOn);
	    		System.out.print(" ");
	    		System.out.print(f2.IsShieldOn);
	    		System.out.println(f2.getBackpack());
	    		System.out.println(f2.getWorn());
	    		System.out.println("===========================");
	                        
	            	
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
	public CharacterEditLayout getOwner(){
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
				BaseItem item = owner.getOwner().fighter.getBackpack().get(index);
				
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
