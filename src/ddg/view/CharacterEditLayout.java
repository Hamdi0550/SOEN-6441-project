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
import ddg.utils.Dice;
import ddg.utils.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.*;
/**
 * 
 * 
 * This class provides an interface to edit a character
 * 
 * @author Fei Yu
 * @date Mar 3, 2017
 */
public class CharacterEditLayout extends JDialog implements ActionListener {

    private final JButton saveBtn = new JButton("      Save      ");
    private final JButton cancelBtn = new JButton("      Cancel      ");
    private final JButton randomBtn = new JButton("      Random      ");
    private final JButton inventoryBtn = new JButton(" Inventory ");

    public final JButton helmetBtn = new JButton("  Helmet  ");
    public final JButton shoulderBtn = new JButton("  Shoulder  ");
    public final JButton beltBtn = new JButton("  Belt ");
    public final JButton ringBtn = new JButton("  Ring  ");
    public final JButton armorBtn = new JButton("  Armor  ");
    public final JButton shieldBtn = new JButton("  Shield  ");
    public final JButton bootsBtn = new JButton("    Boots  ");
    public final JButton weaponBtn = new JButton("   Weapon  ");

    public int id;
    
    private final DefaultListModel<String> model = new DefaultListModel<String>(); 
        
    private final JTextField nameTextF = new JTextField();
    private final JTextField levelTextF = new JTextField();
    private final JTextField strengthTextF = new JTextField();
    private final JTextField dexterityTextF = new JTextField();
    private final JTextField constitutionTextF = new JTextField();
    private final JTextField intelligenceTextF = new JTextField();
    private final JTextField wisdomTextF = new JTextField();
    private final JTextField charismaTextF = new JTextField();
    

    JLabel nameModiferL = new JLabel(" L ");
    JLabel levelModiferL = new JLabel(" L ");
    JLabel strengthModiferL = new JLabel(" L ");
    JLabel dexModiferL = new JLabel(" L ");
    JLabel conModiferL = new JLabel(" L ");
    JLabel intelliModiferL = new JLabel(" L ");
    JLabel wisModiferL = new JLabel(" L ");
    JLabel chaModiferL = new JLabel(" L ");
    
    private static CharacterSelection owner;
    public BaseItem selectedItem = null;
    public String wearingType; 
    public JDialog thisWindow = this;
    
//    private JDialog errorMessageWindow= new JDialog(this, "Error Message", true);
//	private JLabel messageL = new JLabel(" ");
//	private JButton okBtn = new JButton("   OK   ");

    /**
     * 
     * @param ownerFrame
     */
    public static void createAndShowGUI(CharacterSelection ownerFrame){
        owner = (CharacterSelection) ownerFrame;
        
        System.out.println("========"+owner);
        CharacterEditLayout frame1 = new CharacterEditLayout(); 
        frame1.setBounds(230, 230, 0, 0);
        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);
        
    }
    
    /**
     * 
     */
    CharacterEditLayout()
    {
        super();
        setTitle("Character Editor");
        setModal(true);
        setLayout(new BorderLayout());
        JPanel backPanel= new JPanel(new BorderLayout());
        JPanel characterPanel= new JPanel(new BorderLayout());
        JPanel attributesPanel= new JPanel(new GridLayout(10,4,5,5));
        JPanel buttonsPanel= new JPanel(new GridLayout(10,1,5,5));
//        Config.HELMET_ICON.setImage(Config.HELMET_ICON.getImage().getScaledInstance(Config.HELMET_ICON.getIconWidth(),  
//        		Config.HELMET_ICON.getIconHeight(), Image.SCALE_DEFAULT)); 
//        Config.WEAPON_ICON.setImage(Config.WEAPON_ICON.getImage().getScaledInstance(Config.WEAPON_ICON.getIconWidth(),  
//        		Config.WEAPON_ICON.getIconHeight(), Image.SCALE_DEFAULT)); 
        
        JPanel characterLeftPanel= new JPanel(new GridLayout(5,1,5,5));
        JPanel characterRightPanel= new JPanel(new GridLayout(5,1,5,5));
        JPanel characterImagePanel= new EmbeddedPanel();
        add(backPanel, BorderLayout.NORTH);
        backPanel.add(characterPanel, BorderLayout.WEST);
        backPanel.add(attributesPanel, BorderLayout.CENTER);
        backPanel.add(buttonsPanel, BorderLayout.EAST);
        characterPanel.add(characterLeftPanel, BorderLayout.WEST);
        characterPanel.add(characterImagePanel, BorderLayout.CENTER);
        characterPanel.add(characterRightPanel, BorderLayout.EAST);
//        helmetBtn.setIcon(Config.HELMET_ICON);
//        beltBtn.setIcon(Config.BELT_ICON);
//        weaponBtn.setIcon(Config.WEAPON_ICON);
//        ringBtn.setIcon(Config.RING_ICON);
//        bootsBtn.setIcon(Config.BOOTS_ICON);
//        armorBtn.setIcon(Config.ARMOR_ICON);
//        shieldBtn.setIcon(Config.SHIELD_ICON);
        characterLeftPanel.add(helmetBtn);
        characterLeftPanel.add(armorBtn);
        characterLeftPanel.add(beltBtn);
        characterRightPanel.add(ringBtn);
        characterRightPanel.add(bootsBtn);
        characterRightPanel.add(weaponBtn);
        characterRightPanel.add(shieldBtn);
        characterImagePanel.setBackground(Color.YELLOW);
        characterImagePanel.setBounds(0, 0, 500, 500);
        characterImagePanel.add(new JLabel("                                                                 "));
        buttonsPanel.setPreferredSize(new Dimension(100,320));
        characterLeftPanel.setPreferredSize(new Dimension(100,320));
        characterRightPanel.setPreferredSize(new Dimension(100,320));
        attributesPanel.setPreferredSize(new Dimension(600,320));
        characterImagePanel.setPreferredSize(new Dimension(350,320));
        
        characterImagePanel.setPreferredSize(new Dimension(350,320));
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
        lb1.setPreferredSize(new Dimension(20,15));
        lb1.setBounds(0, 0, 20, 15);
        lb1.setIcon(Config.HELMET_ICON);
        lb1.setText(" 2 ");
        attributesPanel.add(new JLabel("   1  "));
        attributesPanel.add(lb1);
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
        attributesPanel.add(new JLabel("   1  "));
        attributesPanel.add(new JLabel("   1  "));
        attributesPanel.add(new JLabel("   1  "));
        attributesPanel.add(new JLabel("   1  "));
        if (owner.isCreatingNew == false){
        	nameTextF.setEditable(false);
        }
        
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(inventoryBtn);
        buttonsPanel.add(randomBtn);
        buttonsPanel.add(saveBtn);
        buttonsPanel.add(cancelBtn);
        buttonsPanel.setSize(300,500);
        
        getOwnerInformation();
        focusManage();
        buttonsManage();
            
    }
    
    /**
     * This method refresh the character's information who is selected in Character Selection window.
     */
	private void getOwnerInformation() {
		if(owner == null){
			System.out.println("No owner===============");
		}
		if(owner.isCreatingNew == false){
			System.out.println(owner.toString());
			nameTextF.setText(owner.fighter.getName());
			levelTextF.setText(Integer.toString(owner.fighter.getLevel()));
			strengthTextF.setText(Integer.toString(owner.fighter.getStrength()));
			dexterityTextF.setText(Integer.toString(owner.fighter.getDexterity()));
			constitutionTextF.setText(Integer.toString(owner.fighter.getConstitution()));
			intelligenceTextF.setText(Integer.toString(owner.fighter.getIntelligence()));
			wisdomTextF.setText(Integer.toString(owner.fighter.getWisdom()));
			charismaTextF.setText(Integer.toString(owner.fighter.getCharisma()));
			
    		strengthModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getStrength())));
    		dexModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getDexterity())));
    		conModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getConstitution())));
    		intelliModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getIntelligence())));
    		wisModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getWisdom())));
    		chaModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getCharisma())));	
    		
    		if (owner.fighter.isHelmetOn){
    			helmetBtn.setText("");
    			helmetBtn.setIcon(Config.HELMET_ICON);
    		}
    		if (owner.fighter.isArmorOn){
    			armorBtn.setText("");
    			armorBtn.setIcon(Config.ARMOR_ICON);
    		}
    		if (owner.fighter.isBeltOn){
    			beltBtn.setText("");
    			beltBtn.setIcon(Config.BELT_ICON);
    		}
    		if (owner.fighter.isBootsOn){
    			bootsBtn.setText("");
    			bootsBtn.setIcon(Config.BOOTS_ICON);
    		}
    		if (owner.fighter.isRingOn){
    			ringBtn.setText("");
    			ringBtn.setIcon(Config.RING_ICON);
    		}
    		if (owner.fighter.IsShieldOn){
    			shieldBtn.setText("");
    			shieldBtn.setIcon(Config.SHIELD_ICON);
    		}
    		if (owner.fighter.isWeaponOn){
    			weaponBtn.setText("");
    			weaponBtn.setIcon(Config.WEAPON_ICON);
    		}
		}		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	/**
	 * This method defines actions of different buttons in this frame.
	 */
	private void buttonsManage(){
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("cancel clicked");
				dispose();				
	        }
	    });

		randomBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Fighter f1 = new Fighter();
				if (owner.fighter == null){
					owner.fighter = f1;
				} else {
					f1 = owner.fighter;
				}
				
				f1.setStrength(Dice.d46Roll());
				f1.setDexterity(Dice.d46Roll());
				f1.setConstitution(Dice.d46Roll());
				f1.setIntelligence(Dice.d46Roll());
				f1.setWisdom(Dice.d46Roll());
				f1.setCharisma(Dice.d46Roll());

				strengthTextF.setText(Integer.toString(owner.fighter.getStrength()));
				dexterityTextF.setText(Integer.toString(owner.fighter.getDexterity()));
				constitutionTextF.setText(Integer.toString(owner.fighter.getConstitution()));
				intelligenceTextF.setText(Integer.toString(owner.fighter.getIntelligence()));
				wisdomTextF.setText(Integer.toString(owner.fighter.getWisdom()));
				charismaTextF.setText(Integer.toString(owner.fighter.getCharisma()));
	    		strengthModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getStrength())));
	    		dexModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getDexterity())));
	    		conModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getConstitution())));
	    		intelliModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getIntelligence())));
	    		wisModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getWisdom())));
	    		chaModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getCharisma())));
//				System.out.println("I get the owner's id " + owner.id);
				System.out.println("I get the figher " + owner.fighterKeyName);
	        }
	    });
		
		saveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("save clicked");
				Fighter fighter1 = new Fighter();
				if (owner.fighter != null){
					System.out.println("owner.fighter is not null");
					fighter1 = owner.fighter;					
				}
//				JFrame rootframe = (JFrame) SwingUtilities.getWindowAncestor(this);
				if (nameTextF.getText().equals("")){
					JOptionPane.showMessageDialog(null, "The character must have a name.", "Warning", JOptionPane.WARNING_MESSAGE);
    				
//    				messageL.setText("The character must have a name!");
//					ShowErrorMessage();
				} else if(levelTextF.getText().equals("")){
					JOptionPane.showMessageDialog(null, "You must input level.", "Warning", JOptionPane.WARNING_MESSAGE);
				} else {
					try{
						if(Integer.parseInt(levelTextF.getText()) < 1 || Integer.parseInt(levelTextF.getText()) > 20){
						JOptionPane.showMessageDialog(null, "The level should be between 1 to 20.", "Warning", JOptionPane.WARNING_MESSAGE);
						}
						else {
						
							System.out.println("nametextF is not null ");
							fighter1.setName(nameTextF.getText());
							fighter1.setLevel(Integer.parseInt(levelTextF.getText()));
							fighter1.setStrength(Integer.parseInt(strengthTextF.getText()));
							fighter1.setDexterity(Integer.parseInt(dexterityTextF.getText()));
							fighter1.setConstitution(Integer.parseInt(constitutionTextF.getText()));
							fighter1.setIntelligence(Integer.parseInt(intelligenceTextF.getText()));
							fighter1.setWisdom(Integer.parseInt(wisdomTextF.getText()));
							fighter1.setCharisma(Integer.parseInt(charismaTextF.getText()));
							if (owner.fighter != null){
								
							} else {
								fighter1.setHitpoints(Dice.d10Roll() + (fighter1.getLevel() * fighter1.getModifier(fighter1.getConstitution())));									
							}
//							fighter1.setHitpoints(Dice.d10Roll() + (fighter1.getLevel() * 3));	
						
			                FighterModel fm = new FighterModel();
			        		String g = Utils.readFile(Config.CHARACTOR_FILE);
			        		fm = Utils.fromJson(g, FighterModel.class);
			        		
			        		HashMap<String, Fighter> hm1 = new HashMap<>();
			        		if(fm != null){
			            		System.out.println(fm);
			            		try{			
			                		System.out.println("2"+fm);
			                		if( null!=fm.getFighters() ){
			                            hm1 = fm.getFighters();
			                            Set<String> keySet1 = hm1.keySet();
			                            Iterator<String> it1 = keySet1.iterator();
			                            
			                            while(it1.hasNext()){
			                            	String keyName = it1.next();
			                            	if (keyName == fighter1.getName()){
			                            		System.out.println("There is a characater with same name==========");
			                            	}                            		
			                            }
			                		}        			
			            		}
			            		catch (NullPointerException ex){
			            			System.out.println("there is a NullPointerException");
			            		}        			
			        		}
			        		hm1.put(fighter1.getName(), fighter1);        		
			        		fm.setFighters(hm1);
			        		
			    			String gSave = Utils.toJson(fm);
			    			Utils.save2File(Config.CHARACTOR_FILE, gSave);
			        		
			    			owner.hm1 = hm1;
							dispose();
							owner.setEnabled(true);
							owner.setVisible(true);
	
						}
					}
					catch (NumberFormatException ex){
						JOptionPane.showMessageDialog(null, "The level should be between 1 to 20.", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
	    });
		
		helmetBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				wearingType = BaseItem.HELMET;
				CharacterEditLayout rootFrame = (CharacterEditLayout) SwingUtilities.getWindowAncestor(helmetBtn);
				ItemSelection.createAndShowGUI(rootFrame);				
			}
		});
		
		armorBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				wearingType = BaseItem.ARMOR;
				CharacterEditLayout rootFrame = (CharacterEditLayout) SwingUtilities.getWindowAncestor(armorBtn);
				ItemSelection.createAndShowGUI(rootFrame);				
			}
		});
		
		beltBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				wearingType = BaseItem.BELT;
				CharacterEditLayout rootFrame = (CharacterEditLayout) SwingUtilities.getWindowAncestor(beltBtn);
				ItemSelection.createAndShowGUI(rootFrame);				
			}
		});
		
		bootsBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				wearingType = BaseItem.BOOTS;
				CharacterEditLayout rootFrame = (CharacterEditLayout) SwingUtilities.getWindowAncestor(bootsBtn);
				ItemSelection.createAndShowGUI(rootFrame);				
			}
		});
		
		ringBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				wearingType = BaseItem.RING;
				CharacterEditLayout rootFrame = (CharacterEditLayout) SwingUtilities.getWindowAncestor(ringBtn);
				ItemSelection.createAndShowGUI(rootFrame);				
			}
		});
		
		shieldBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				wearingType = BaseItem.SHIELD;
				CharacterEditLayout rootFrame = (CharacterEditLayout) SwingUtilities.getWindowAncestor(shieldBtn);
				ItemSelection.createAndShowGUI(rootFrame);				
			}
		});
		
		weaponBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				wearingType = BaseItem.WEAPON;
				CharacterEditLayout rootFrame = (CharacterEditLayout) SwingUtilities.getWindowAncestor(weaponBtn);
				ItemSelection.createAndShowGUI(rootFrame);				
			}
		});
		
		inventoryBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(getThisFrame());
				InventoryView.createAndShowGUI(getThisFrame());
			}
		});    	
		
//    	okBtn.addActionListener(new ActionListener(){ 
//    		public void actionPerformed(ActionEvent e){
//    			errorMessageWindow.dispose();
//            }
//        }); 
	}
	
	/**
	 * 
	 * @return
	 */
	public CharacterEditLayout getThisFrame() {
		return this;
	}
	
	/**
	 * 
	 */
	public CharacterSelection getOwner(){
		return owner;
	}
	/**
	 * 
	 */
//    public void ShowErrorMessage(){
//    	errorMessageWindow.setLayout(new FlowLayout());
//    	errorMessageWindow.setBounds(400, 300, 250, 150);
//    	errorMessageWindow.add(messageL);
//    	errorMessageWindow.add(okBtn);
//    	errorMessageWindow.setVisible(true);
//
//    }
	/**
	 * This method manage the actions of the window focus
	 */
	public void focusManage() { 
	 this.addWindowFocusListener(new WindowFocusListener() {  
	 	
	     @Override  
	     public void windowGainedFocus(WindowEvent e) {
	    	 System.out.println("The CE window is focused.");  
	    	 if (owner.fighter != null && owner.isCreatingNew == false){
		    	 nameTextF.setText(owner.fighter.getName());
		    	 levelTextF.setText(Integer.toString(owner.fighter.getLevel()));
		    	 strengthTextF.setText(Integer.toString(owner.fighter.getStrength()));
		    	 dexterityTextF.setText(Integer.toString(owner.fighter.getDexterity()));
		    	 constitutionTextF.setText(Integer.toString(owner.fighter.getConstitution()));
		    	 intelligenceTextF.setText(Integer.toString(owner.fighter.getIntelligence()));
		    	 wisdomTextF.setText(Integer.toString(owner.fighter.getWisdom()));
		    	 charismaTextF.setText(Integer.toString(owner.fighter.getCharisma()));
				
		    	 strengthModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getStrength())));
		    	 dexModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getDexterity())));
		    	 conModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getConstitution())));
		    	 intelliModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getIntelligence())));
		    	 wisModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getWisdom())));
		    	 chaModiferL.setText(Integer.toString(owner.fighter.getModifier(owner.fighter.getCharisma())));
		    	 System.out.println(owner.fighter.getWorn().size() + "==========" + owner.fighter.getWorn());
		    	 Fighter f2 = owner.fighter;

		    		System.out.println(f2);
		    		System.out.println(f2.getName());
		    		System.out.println("backpack now  has " + f2.getBackpack().size());
		    		System.out.println("worn now  has " +f2.getWorn().size());
		    		System.out.print(f2.isArmorOn);
		    		System.out.print(" ");
		    		System.out.print(f2.isBeltOn);
		    		System.out.print(" ");
		    		System.out.print(f2.isRingOn); 
		    		System.out.print(" ");
		    		System.out.print(f2.isBootsOn);
		    		System.out.print(" ");
		    		System.out.print(f2.isHelmetOn);
		    		System.out.print(" ");
		    		System.out.print(f2.IsShieldOn);
		    		System.out.print(" ");
		    		System.out.print(f2.isWeaponOn);
		    		System.out.println(f2.getBackpack());
		    		System.out.println(f2.getWorn());
	    	 }
	     }  
	
	     @Override  
	     public void windowLostFocus(WindowEvent e){
	         System.out.println("The CE window is not focused.");  
	     }  
	       
	 });  
	 
	 this.addWindowListener(new WindowListener(){
		 @Override
		 public void windowClosed(WindowEvent e){
				owner.setEnabled(true);
				owner.setVisible(true);			 
		 }

		@Override
		public void windowActivated(WindowEvent arg0) {
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			
		}
	 });
	 
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
	        g.drawImage(img, 0, 0,300, 300, this); 
		}
	}
    
}
