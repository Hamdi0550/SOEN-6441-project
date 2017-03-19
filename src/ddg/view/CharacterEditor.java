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
public class CharacterEditor extends JDialog implements ActionListener {

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

	private JComboBox<String> typeList = new JComboBox();

	private JLabel strengthL = new JLabel();
	private JLabel dexterityL = new JLabel();
	private JLabel constitutionL = new JLabel();
	private JLabel intelligenceL = new JLabel();
	private JLabel wisdomL = new JLabel();
	private JLabel charismaL = new JLabel();
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

	/**
	 * 
	 * @param ownerFrame
	 */
	public static void createAndShowGUI(CharacterSelection ownerFrame) {
		owner = (CharacterSelection) ownerFrame;

		System.out.println("========" + owner);
		CharacterEditor frame1 = new CharacterEditor();
		frame1.setBounds(230, 230, 0, 0);
		frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame1.pack();
		frame1.setVisible(true);

	}

	/**
	 * 
	 */
	CharacterEditor() {
		super();
		setTitle("Character Editor");
		setModal(true);
		setLayout(new BorderLayout());
		JPanel backPanel = new JPanel(new BorderLayout());
		JPanel characterPanel = new JPanel(new BorderLayout());
		JPanel attributesPanel = new JPanel(new GridLayout(10, 4, 5, 5));
		JPanel buttonsPanel = new JPanel(new GridLayout(10, 1, 5, 5));

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
		buttonsPanel.setPreferredSize(new Dimension(100, 320));
		characterLeftPanel.setPreferredSize(new Dimension(100, 320));
		characterRightPanel.setPreferredSize(new Dimension(100, 320));
		attributesPanel.setPreferredSize(new Dimension(360, 320));
		characterImagePanel.setPreferredSize(new Dimension(230, 320));

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

		attributesPanel.add(new JLabel(" Name "));
		attributesPanel.add(nameTextF);
		attributesPanel.add(new JLabel("     "));
		attributesPanel.add(new JLabel("     "));
		attributesPanel.add(new JLabel(" Type "));
		attributesPanel.add(typeList);
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
		if (owner.isCreatingNew == false) {
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
		buttonsPanel.setSize(300, 500);

		typeList.addItem(Config.BULLY);
		typeList.addItem(Config.NIMBLE);
		typeList.addItem(Config.TANK);
		typeList.addActionListener(this);

		getOwnerInformation();
		focusManage();
		buttonsManage();

	}

	/**
	 * This method refresh the character's information who is selected in
	 * Character Selection window.
	 */
	private void getOwnerInformation() {
		if (owner == null) {
			System.out.println("No owner===============");
		}
		if (owner.isCreatingNew == false) {
			System.out.println(owner.toString());
			setLabels(owner.fighter);
			setEquipmentIcon(owner.fighter);

		}
	}

	/**
	 * 
	 * @param fighter
	 */
	private void setLabels(Fighter fighter) {
		nameTextF.setText(fighter.getName());
		levelTextF.setText(Integer.toString(fighter.getLevel()));
		updateAttributes(fighter);

	}

	protected void updateAttributes(Fighter fighter) {

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
	 * 
	 * @param fighter
	 */
	private void setEquipmentIcon(Fighter fighter) {
		
		if (fighter.isHelmetOn) {
			helmetBtn.setText("");
			helmetBtn.setIcon(Config.HELMET_ICON);
		}
		if (fighter.isArmorOn) {
			armorBtn.setText("");
			armorBtn.setIcon(Config.ARMOR_ICON);
		}
		if (fighter.isBeltOn) {
			beltBtn.setText("");
			beltBtn.setIcon(Config.BELT_ICON);
		}
		if (fighter.isBootsOn) {
			bootsBtn.setText("");
			bootsBtn.setIcon(Config.BOOTS_ICON);
		}
		if (fighter.isRingOn) {
			ringBtn.setText("");
			ringBtn.setIcon(Config.RING_ICON);
		}
		if (fighter.IsShieldOn) {
			shieldBtn.setText("");
			shieldBtn.setIcon(Config.SHIELD_ICON);
		}
		if (fighter.isWeaponOn) {
			weaponBtn.setText("");
			weaponBtn.setIcon(Config.WEAPON_ICON);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == typeList) {
			String s = (String) typeList.getSelectedItem();
			switch (s) {
			case Config.BULLY:
				System.out.println("Bully");
				break;
			case Config.NIMBLE:
				System.out.println("Nimble");
				break;
			case Config.TANK:
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
				Fighter f1 = new Fighter();
				if (owner.fighter == null) {
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

				updateAttributes(owner.fighter);
				System.out.println("I get the figher " + owner.fighterKeyName);
			}
		});

		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("save clicked");
				Fighter fighter1 = new Fighter();
				if (owner.fighter != null && owner.isCreatingNew == false) {
					System.out.println("&&&&&&&&&&&&&&&&&&&&&&&");
					fighter1 = owner.fighter;
				}
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
							fighter1.setName(nameTextF.getText());
							fighter1.setLevel(Integer.parseInt(levelTextF.getText()));
							fighter1.setStrength(
									Integer.parseInt(strengthTextF.getText()) - fighter1.getGainedStrength());
							fighter1.setDexterity(
									Integer.parseInt(dexterityTextF.getText()) - fighter1.getGainedDexterity());
							fighter1.setConstitution(
									Integer.parseInt(constitutionTextF.getText()) - fighter1.getGainedConstitution());
							fighter1.setIntelligence(
									Integer.parseInt(intelligenceTextF.getText()) - fighter1.getGainedIntelligence());
							fighter1.setWisdom(Integer.parseInt(wisdomTextF.getText()) - fighter1.getGainedWisdom());
							fighter1.setCharisma(
									Integer.parseInt(charismaTextF.getText()) - fighter1.getGainedCharisma());
							if (fighter1.getHitPoints() == 0) {
								fighter1.setHitpoints(fighter1.getLevel()
										* (Dice.d10Roll() + fighter1.getModifier(fighter1.getTotalConstitution())));
							}
							FighterModel fm = new FighterModel();
							String g = Utils.readFile(Config.CHARACTOR_FILE);
							fm = Utils.fromJson(g, FighterModel.class);

							HashMap<String, Fighter> hm1 = new HashMap<>();
							if (fm != null) {
								System.out.println(fm);
								try {
									System.out.println("2" + fm);
									if (null != fm.getFighters()) {
										hm1 = fm.getFighters();
										Set<String> keySet1 = hm1.keySet();
										Iterator<String> it1 = keySet1.iterator();

										while (it1.hasNext()) {
											String keyName = it1.next();
											if (keyName == fighter1.getName()) {
											}
										}
									}
								} catch (NullPointerException ex) {
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
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "The level should be between 1 to 20.", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		helmetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.HELMET;
				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(helmetBtn);
				ItemSelection.createAndShowGUI(rootFrame);
			}
		});

		armorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.ARMOR;
				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(armorBtn);
				ItemSelection.createAndShowGUI(rootFrame);
			}
		});

		beltBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.BELT;
				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(beltBtn);
				ItemSelection.createAndShowGUI(rootFrame);
			}
		});

		bootsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.BOOTS;
				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(bootsBtn);
				ItemSelection.createAndShowGUI(rootFrame);
			}
		});

		ringBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.RING;
				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(ringBtn);
				ItemSelection.createAndShowGUI(rootFrame);
			}
		});

		shieldBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.SHIELD;
				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(shieldBtn);
				ItemSelection.createAndShowGUI(rootFrame);
			}
		});

		weaponBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wearingType = BaseItem.WEAPON;
				CharacterEditor rootFrame = (CharacterEditor) SwingUtilities.getWindowAncestor(weaponBtn);
				ItemSelection.createAndShowGUI(rootFrame);
			}
		});

		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(getThisFrame());
				if (owner.fighter != null) {
					InventoryView.createAndShowGUI(getThisFrame());
				}
			}
		});

	}


	/**
	 * 
	 * @return
	 */
	public CharacterEditor getThisFrame() {
		return this;
	}

	/**
	 * Get the owner window
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
				if (owner.fighter != null && owner.isCreatingNew == false) {

					nameTextF.setText(owner.fighter.getName());
					levelTextF.setText(Integer.toString(owner.fighter.getLevel()));

					updateAttributes(owner.fighter);
					
					System.out.println(owner.fighter.getWorn().size() + "==========" + owner.fighter.getWorn());
					Fighter f2 = owner.fighter;

					System.out.println("===========================");
					System.out.println(f2);
					System.out.println(f2.getName());
					System.out.println("backpack now  has " + f2.getBackpack().size());
					System.out.println("worn now  has " + f2.getWorn().size());
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
					System.out.println(f2.getBackpack());
					System.out.println(f2.getWorn());
					System.out.println("===========================");

				}
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				System.out.println("The CE window is not focused.");
			}

		});

		this.addWindowListener(new WindowListener() {
			@Override
			public void windowClosed(WindowEvent e) {
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
