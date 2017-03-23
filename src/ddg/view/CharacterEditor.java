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
import ddg.utils.builder.BullyFighterBuilder;
import ddg.utils.builder.FighterBuilder;
import ddg.utils.builder.FighterExplorer;
import ddg.utils.builder.NimbleFighterBuilder;
import ddg.utils.builder.TankFighterBuilder;

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
public class CharacterEditor extends JDialog implements ActionListener, Observer {

	private JButton saveBtn = new JButton("      Save      ");
	private JButton cancelBtn = new JButton("      Cancel      ");
	private JButton randomBtn = new JButton("      Random      ");
	private JButton inventoryBtn = new JButton(" Inventory ");

	private JButton helmetBtn = new JButton("  Helmet  ");
	private JButton beltBtn = new JButton("  Belt ");
	private JButton ringBtn = new JButton("  Ring  ");
	private JButton armorBtn = new JButton("  Armor  ");
	private JButton shieldBtn = new JButton("  Shield  ");
	private JButton bootsBtn = new JButton("    Boots  ");
	private JButton weaponBtn = new JButton("   Weapon  ");

	private final DefaultListModel<String> model = new DefaultListModel<String>();

	private JTextField nameTextF = new JTextField();
	private JTextField levelTextF = new JTextField();
	private JTextField strengthTextF = new JTextField();
	private JTextField dexterityTextF = new JTextField();
	private JTextField constitutionTextF = new JTextField();
	private JTextField intelligenceTextF = new JTextField();
	private JTextField wisdomTextF = new JTextField();
	private JTextField charismaTextF = new JTextField();

	private JComboBox<String> typeList = new JComboBox();

	private JLabel typeL = new JLabel();
	private JLabel strengthL = new JLabel();
	private JLabel dexterityL = new JLabel();
	private JLabel constitutionL = new JLabel();
	private JLabel intelligenceL = new JLabel();
	private JLabel wisdomL = new JLabel();
	private JLabel charismaL = new JLabel();
    private JLabel armorClassL = new JLabel("    ");
    private JLabel hitPointsL = new JLabel("    ");
    private JLabel attackBonusL = new JLabel("    ");
    private JLabel damageBonusL = new JLabel("   ");
	
	private JLabel strengthModiferL = new JLabel();
	private JLabel dexModiferL = new JLabel();
	private JLabel conModiferL = new JLabel();
	private JLabel intelliModiferL = new JLabel();
	private JLabel wisModiferL = new JLabel();
	private JLabel chaModiferL = new JLabel();

	private static CharacterSelection owner;
	public BaseItem selectedItem = null;
	public String wearingType;
	public JDialog thisWindow = this;
	private FighterExplorer fighterExplorer = new FighterExplorer();
	private FighterBuilder fb = new BullyFighterBuilder();
	private Fighter fighter = new Fighter();

//	/**
//	 * This method build frame of the Character Selection Window
//	 * @param ownerFrame the object object get from owner window
//	 */
//	public static void createAndShowGUI(CharacterSelection ownerFrame) {
//		owner = (CharacterSelection) ownerFrame;
//
//		System.out.println("========" + owner);
//		CharacterEditor frame1 = new CharacterEditor();
//		frame1.setBounds(230, 230, 0, 0);
//		frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		frame1.pack();
//		frame1.setVisible(true);
//
//	}

	/**
	 * This method build frame of the Character Selection Window
	 * @param fighter the Fighter object get from owner window
	 */
	public static void createAndShowGUI(Fighter fighter) {
		CharacterEditor frame1 = new CharacterEditor(fighter);
		frame1.setBounds(230, 230, 0, 0);
		frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame1.pack();
		frame1.setVisible(true);
		
	}
	
	/**
	 * Constructor
	 * @param fighter the Fighter object get from owner window
	 */
	public CharacterEditor(Fighter fighter) {
    	super();
		this.fighter = fighter;
		initialization();
	}

	/**
	 * Constructor
	 */
	public CharacterEditor() {
		super();		
		initialization();
	}

	/**
	 * This method builds the window.
	 */
	private void initialization() {
		setTitle("Character Editor");
		setModal(true);
		setLayout(new BorderLayout());
		JPanel backPanel = new JPanel(new BorderLayout());
		JPanel characterPanel = new JPanel(new BorderLayout());
		JPanel attributesPanel = new JPanel(new GridLayout(15, 4, 5, 5));
		JPanel buttonsPanel = new JPanel(new GridLayout(15, 1, 5, 5));

		JPanel characterLeftPanel = new JPanel(new GridLayout(5, 1, 5, 5));
		JPanel characterRightPanel = new JPanel(new GridLayout(5, 1, 5, 5));
		JPanel characterImagePanel = new EmbeddedPanel();
		add(backPanel, BorderLayout.NORTH);
		backPanel.add(characterPanel, BorderLayout.WEST);
		backPanel.add(attributesPanel, BorderLayout.CENTER);
		backPanel.add(buttonsPanel, BorderLayout.EAST);
		characterPanel.add(characterLeftPanel, BorderLayout.WEST);
		characterPanel.add(characterImagePanel, BorderLayout.CENTER);
		characterPanel.add(characterRightPanel, BorderLayout.EAST);
		characterLeftPanel.add(helmetBtn);
		characterLeftPanel.add(armorBtn);
		characterLeftPanel.add(beltBtn);
		characterRightPanel.add(ringBtn);
		characterRightPanel.add(bootsBtn);
		characterRightPanel.add(weaponBtn);
		characterRightPanel.add(shieldBtn);
		characterImagePanel.setBounds(0, 0, 500, 500);
		characterImagePanel.add(new JLabel("                                                                 "));
		buttonsPanel.setPreferredSize(new Dimension(100, 450));
		characterLeftPanel.setPreferredSize(new Dimension(100, 450));
		characterRightPanel.setPreferredSize(new Dimension(100, 450));
		attributesPanel.setPreferredSize(new Dimension(400, 450));
		characterImagePanel.setPreferredSize(new Dimension(230, 450));

		strengthModiferL.setBorder(new LineBorder(Color.BLACK));
		dexModiferL.setBorder(new LineBorder(Color.BLACK));
		conModiferL.setBorder(new LineBorder(Color.BLACK));
		intelliModiferL.setBorder(new LineBorder(Color.BLACK));
		wisModiferL.setBorder(new LineBorder(Color.BLACK));
		chaModiferL.setBorder(new LineBorder(Color.BLACK));
		strengthL.setBorder(new LineBorder(Color.BLACK));
		dexterityL.setBorder(new LineBorder(Color.BLACK));
		constitutionL.setBorder(new LineBorder(Color.BLACK));
		intelligenceL.setBorder(new LineBorder(Color.BLACK));
		wisdomL.setBorder(new LineBorder(Color.BLACK));
		charismaL.setBorder(new LineBorder(Color.BLACK));
        armorClassL.setBorder(new LineBorder(Color.BLACK));
        hitPointsL.setBorder(new LineBorder(Color.BLACK));
        attackBonusL.setBorder(new LineBorder(Color.BLACK));
        damageBonusL.setBorder(new LineBorder(Color.BLACK));

		attributesPanel.add(new JLabel(" Name "));
		attributesPanel.add(nameTextF);
		attributesPanel.add(new JLabel("     "));
		attributesPanel.add(new JLabel("     "));
		attributesPanel.add(new JLabel(" Type "));		
		
		if (fighter == null){
			attributesPanel.add(typeList);
		} else {
			nameTextF.setEditable(false);
			nameTextF.setText(fighter.getName());
			attributesPanel.add(typeL);
			typeL.setText(fighter.getType());	
			levelTextF.setText(Integer.toString(fighter.getLevel()));
		}
		attributesPanel.add(new JLabel("     "));
		attributesPanel.add(new JLabel("     "));
		attributesPanel.add(new JLabel(" Level "));
		attributesPanel.add(levelTextF);
		attributesPanel.add(new JLabel("     "));
		attributesPanel.add(new JLabel("     "));
		attributesPanel.add(new JLabel("      "));
		attributesPanel.add(new JLabel("      "));
		attributesPanel.add(new JLabel(" Value "));
		attributesPanel.add(new JLabel(" Modifier "));
		attributesPanel.add(new JLabel(" Strength "));
		attributesPanel.add(strengthTextF);
		attributesPanel.add(strengthL);
		attributesPanel.add(strengthModiferL);
		attributesPanel.add(new JLabel(" Dexterity "));
		attributesPanel.add(dexterityTextF);
		attributesPanel.add(dexterityL);
		attributesPanel.add(dexModiferL);
		attributesPanel.add(new JLabel(" Constitution "));
		attributesPanel.add(constitutionTextF);
		attributesPanel.add(constitutionL);
		attributesPanel.add(conModiferL);
		attributesPanel.add(new JLabel(" Intelligence "));
		attributesPanel.add(intelligenceTextF);
		attributesPanel.add(intelligenceL);
		attributesPanel.add(intelliModiferL);
		attributesPanel.add(new JLabel(" Wisdom "));
		attributesPanel.add(wisdomTextF);
		attributesPanel.add(wisdomL);
		attributesPanel.add(wisModiferL);
		attributesPanel.add(new JLabel(" Charisma "));
		attributesPanel.add(charismaTextF);
		attributesPanel.add(charismaL);
		attributesPanel.add(chaModiferL);
        attributesPanel.add(new JLabel(" Hitpoints "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(hitPointsL);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Attack Bonus "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(attackBonusL);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Damage Bonus "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(damageBonusL);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Armor Class "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(armorClassL);
        attributesPanel.add(new JLabel("      "));
//		if (owner.isCreatingNew == false) {
//			nameTextF.setEditable(false);
//		}

		buttonsPanel.add(new JLabel("    "));
		buttonsPanel.add(new JLabel("    "));
		buttonsPanel.add(new JLabel("    "));
		buttonsPanel.add(new JLabel("    "));
		buttonsPanel.add(new JLabel("    "));
		buttonsPanel.add(new JLabel("    "));
		buttonsPanel.add(new JLabel("    "));
		buttonsPanel.add(new JLabel("    "));
		buttonsPanel.add(new JLabel("    "));
		buttonsPanel.add(inventoryBtn);
		buttonsPanel.add(randomBtn);
		buttonsPanel.add(saveBtn);
		buttonsPanel.add(cancelBtn);
		buttonsPanel.setSize(300, 500);
		
		if (fighter == null){
			inventoryBtn.setEnabled(false);
		}

		typeList.addItem(Config.BULLY);
		typeList.addItem(Config.NIMBLE);
		typeList.addItem(Config.TANK);
		typeList.addActionListener(this);
		System.out.println(typeList.getSelectedItem());
//		getOwnerInformation();
		focusManage();
		buttonsManage();

		fighterExplorer.setBuilder(fb);
		
		if (fighter == null){			
			helmetBtn.setEnabled(false);
			beltBtn.setEnabled(false);
			ringBtn.setEnabled(false);
			armorBtn.setEnabled(false);
			shieldBtn.setEnabled(false);
			bootsBtn.setEnabled(false);
			weaponBtn.setEnabled(false);
		}

	}

	/**
	 * This method refresh the character's information who is selected in
	 * Character Selection window.
	 */
//	private void getOwnerInformation() {
//		if (owner == null) {
//			System.out.println("No owner===============");
//		}
//		if (owner.isCreatingNew == false) {
//			System.out.println(owner.toString());
//			updateAttributes(owner.fighter);
//			setEquipmentIcon(owner.fighter);
//
//		}
//	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		updateView((Fighter)arg1);
		
	}

	/**
	 * This method call two method to update character information.
	 * @param fighter The character selected
	 */
	private void updateView(Fighter fighter) {
		updateAttributes(fighter);		
		setEquipmentIcon(fighter);
	}

	/**
	 * This method update information of the character when his information change.
	 * @param fighter The character selected
	 */
	protected void updateInformation(Fighter fighter) {
		nameTextF.setText(fighter.getName());
		levelTextF.setText(Integer.toString(fighter.getLevel()));
		updateAttributes(fighter);
	}

	/**
	 * This method update attribute values of the character when his attributes change.
	 * @param fighter The character selected
	 */
	protected void updateAttributes(Fighter fighter) {

		strengthTextF.setText(Integer.toString(fighter.getStrength()));
		dexterityTextF.setText(Integer.toString(fighter.getDexterity()));
		constitutionTextF.setText(Integer.toString(fighter.getConstitution()));
		intelligenceTextF.setText(Integer.toString(fighter.getIntelligence()));
		wisdomTextF.setText(Integer.toString(fighter.getWisdom()));
		charismaTextF.setText(Integer.toString(fighter.getCharisma()));

		strengthL.setText(Integer.toString(fighter.getTotalStrength()));
		dexterityL.setText(Integer.toString(fighter.getTotalDexterity()));
		constitutionL.setText(Integer.toString(fighter.getTotalConstitution()));
		intelligenceL.setText(Integer.toString(fighter.getTotalIntelligence()));
		wisdomL.setText(Integer.toString(fighter.getTotalWisdom()));
		charismaL.setText(Integer.toString(fighter.getTotalCharisma()));
		armorClassL.setText(Integer.toString(fighter.getTotalArmorClass()));
		attackBonusL.setText(Integer.toString(fighter.getAttackBonus()));
		damageBonusL.setText(Integer.toString(fighter.getDamageBonus()));
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO
		if (e.getSource() == typeList) {
			String s = (String) typeList.getSelectedItem();
			switch (s) {
			case Config.BULLY:
				fb = new BullyFighterBuilder();
				fighterExplorer.setBuilder(fb);
				randomBtn.setEnabled(true);
				System.out.println("Bully");
				break;
			case Config.NIMBLE:
				fb = new NimbleFighterBuilder();
				fighterExplorer.setBuilder(fb);
				randomBtn.setEnabled(true);
				System.out.println("Nimble");
				break;
			case Config.TANK:
				fb = new TankFighterBuilder();
				fighterExplorer.setBuilder(fb);
				randomBtn.setEnabled(true);
				System.out.println("Tank");
				break;
			default:
				System.out.println("Type error!");
			}			
		}

	}

	/**
	 * This method defines actions of different buttons in this frame.
	 */
	private void buttonsManage() {
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("cancel clicked");
				dispose();
			}
		});

		randomBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
//				fighterExplorer.constructFighter();
				if (fighter == null){
					if (fighterExplorer.getFighter() == null){
						fighterExplorer.constructFighter();
					} else {
						fighterExplorer.constructFighter(fighterExplorer.getFighter());
					}
					System.out.println(fighterExplorer.getFighter());
					fighter = fighterExplorer.getFighter();
					inventoryBtn.setEnabled(true);

					helmetBtn.setEnabled(true);
					beltBtn.setEnabled(true);
					ringBtn.setEnabled(true);
					armorBtn.setEnabled(true);
					shieldBtn.setEnabled(true);
					bootsBtn.setEnabled(true);
					weaponBtn.setEnabled(true);
					
					updateAttributes(fighterExplorer.getFighter());
				} else {
					if (fighter.getType().equals(Config.BULLY)){
						fb = new BullyFighterBuilder();
						fighterExplorer.setBuilder(fb);
						fb.setFighter(fighter);
						fighterExplorer.constructFighter(fighterExplorer.getFighter());
					} else if (fighter.getType().equals(Config.NIMBLE)){
						fb = new NimbleFighterBuilder();
						fighterExplorer.setBuilder(fb);
						fb.setFighter(fighter);
						fighterExplorer.constructFighter(fighterExplorer.getFighter());		
					} else if (fighter.getType().equals(Config.TANK)){
						fb = new TankFighterBuilder();
						fighterExplorer.setBuilder(fb);
						fb.setFighter(fighter);
						fighterExplorer.constructFighter(fighterExplorer.getFighter());				
					} else {
						fb = new BullyFighterBuilder();
						fighterExplorer.setBuilder(fb);
						fb.setFighter(fighter);
						fighterExplorer.constructFighter(fighterExplorer.getFighter());				
					}
					updateAttributes(fighterExplorer.getFighter());
				}
				
			}
		});

		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				System.out.println("save clicked");
				Fighter fighter1 = new Fighter();
//				if (owner.fighter != null && owner.isCreatingNew == false) {
//					System.out.println("&&&&&&&&&&&&&&&&&&&&&&&");
//					fighter1 = owner.fighter;
//				}
				if (nameTextF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "The character must have a name.", "Warning",
							JOptionPane.WARNING_MESSAGE);

				} else if (levelTextF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "You must input level.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						if (Integer.parseInt(levelTextF.getText()) < 1 || Integer.parseInt(levelTextF.getText()) > 20) {
							JOptionPane.showMessageDialog(null, "The level should be between 1 to 20.", "Warning",
									JOptionPane.WARNING_MESSAGE);
						} else {

							System.out.println("nametextF is not null ");
							fighter.setName(nameTextF.getText());
							fighter.setLevel(Integer.parseInt(levelTextF.getText()));
							fighter.setStrength(Integer.parseInt(strengthTextF.getText()));
							fighter.setDexterity(Integer.parseInt(dexterityTextF.getText()));
							fighter.setConstitution(Integer.parseInt(constitutionTextF.getText()));
							fighter.setIntelligence(Integer.parseInt(intelligenceTextF.getText()));
							fighter.setWisdom(Integer.parseInt(wisdomTextF.getText()));
							fighter.setCharisma(Integer.parseInt(charismaTextF.getText()));
							
//							fighter.setStrength(Integer.parseInt(strengthTextF.getText()) - fighter.getGainedStrength());
//							fighter.setDexterity(Integer.parseInt(dexterityTextF.getText()) - fighter.getGainedDexterity());
//							fighter.setConstitution(Integer.parseInt(constitutionTextF.getText()) - fighter.getGainedConstitution());
//							fighter.setIntelligence(Integer.parseInt(intelligenceTextF.getText()) - fighter.getGainedIntelligence());
//							fighter.setWisdom(Integer.parseInt(wisdomTextF.getText()) - fighter.getGainedWisdom());
//							fighter.setCharisma(Integer.parseInt(charismaTextF.getText()) - fighter.getGainedCharisma());
							if (fighter.getHitPoints() == 0) {
								fighter.setHitpoints(fighter.getLevel()
										* (Dice.d10Roll() + fighter.getModifier(fighter.getTotalConstitution())));
							}
							
							Utils.saveFighter(fighter);
							
//							FighterModel fm = new FighterModel();
//							String g = Utils.readFile(Config.CHARACTER_FILE);
//							fm = Utils.fromJson(g, FighterModel.class);
//
//							HashMap<String, Fighter> hm1 = new HashMap<>();
//							if (fm != null) {
//								System.out.println(fm);
//								try {
//									System.out.println("2" + fm);
//									if (null != fm.getFighters()) {
//										hm1 = fm.getFighters();
//										Set<String> keySet1 = hm1.keySet();
//										Iterator<String> it1 = keySet1.iterator();
//
//										while (it1.hasNext()) {
//											String keyName = it1.next();
//											if (keyName == fighter1.getName()) {
//												JOptionPane.showMessageDialog(null, "There already exists a character with this name, please change a name.", "Warning",
//														JOptionPane.WARNING_MESSAGE);												
//											}
//										}
//									}
//								} catch (NullPointerException ex) {
//									System.out.println("there is a NullPointerException");
//								}
//							}
//							hm1.put(fighter1.getName(), fighter1);
//							fm.setFighters(hm1);
//
//							String gSave = Utils.toJson(fm);
//							Utils.save2File(Config.CHARACTER_FILE, gSave);

//							owner.hm1 = hm1;
							dispose();
//							owner.setEnabled(true);
//							owner.setVisible(true);

						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "The attibute values is not valid.", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		helmetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.HELMET;
//				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(helmetBtn);
				System.out.println("============" + fighter);
				ItemSelection.createAndShowGUI(fighter, wearingType);
			}
		});

		armorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.ARMOR;
//				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(armorBtn);
				ItemSelection.createAndShowGUI(fighter, wearingType);
			}
		});

		beltBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.BELT;
//				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(beltBtn);
				ItemSelection.createAndShowGUI(fighter, wearingType);
			}
		});

		bootsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.BOOTS;
//				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(bootsBtn);
				ItemSelection.createAndShowGUI(fighter, wearingType);
			}
		});

		ringBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.RING;
//				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(ringBtn);
				ItemSelection.createAndShowGUI(fighter, wearingType);
			}
		});

		shieldBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.SHIELD;
//				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(shieldBtn);
				ItemSelection.createAndShowGUI(fighter, wearingType);
			}
		});

		weaponBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.WEAPON;
//				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(weaponBtn);
				ItemSelection.createAndShowGUI(fighter, wearingType);
			}
		});

		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
//				System.out.println(getThisFrame());
				System.out.println(fighter);
//				if (owner.fighter != null) {
				if (fighter != null) {
					System.out.println("there is a fighter");
//					InventoryView.createAndShowGUI(getThisFrame());
					InventoryView.createAndShowGUI(fighter);
				}
			}
		});
	}


	/**
	 * This method return the object of this frame
	 * @return This frame object
	 */
	public CharacterEditor getThisFrame() {
		return this;
	}

	/**
	 * Get the owner window
	 * @return owner
	 */
	public CharacterSelection getOwner() {
		return owner;
	}

	/**
	 * This method manage the actions of the window focus
	 */
	public void focusManage() {
		this.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent e) {
				System.out.println("The CE window is focused.");
				System.out.println("fighter at " + fighter);

				if (fighter != null ) {
					updateView(fighter);					
					Utils.displayFighterInfo(fighter);
				}
				
//				if (owner.fighter != null && owner.isCreatingNew == false) {
//				}
				
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				System.out.println("The CE window is not focused.");
			}

		});

		this.addWindowListener(new WindowListener() {
			@Override
			public void windowClosed(WindowEvent e) {
//				owner.setEnabled(true);
//				owner.setVisible(true);
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
	 * This internal class is to draw a background picture in the frame
	 * 
	 * @author Fei Yu
	 * @date Mar 3, 2017
	 */
	class EmbeddedPanel extends JPanel {

		private Image img;

		public EmbeddedPanel() {
			super();
			setOpaque(true);

			img = Toolkit.getDefaultToolkit().getImage("example.jpg");
		}

		/**
		 * This method is override method to draw a picture
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, 230, 320, this);
		}
	}


}
