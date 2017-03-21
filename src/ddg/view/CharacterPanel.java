package ddg.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import ddg.Config;
import ddg.item.entity.BaseItem;
import ddg.model.Fighter;
import ddg.model.FighterModel;
import ddg.utils.Dice;
import ddg.utils.Utils;
import ddg.view.CharacterEditor.EmbeddedPanel;

/**
 * 
 * This class is to display information of a character during a game
 * 
 * @author Fei Yu
 * @date Mar 12, 2017
 */
public class CharacterPanel extends JPanel {
	
	private final JButton inventoryBtn = new JButton(" Inventory ");

//	public final JButton helmetBtn = new JButton("  Helmet  ");
//	public final JButton beltBtn = new JButton("  Belt ");
//	public final JButton ringBtn = new JButton("  Ring  ");
//	public final JButton armorBtn = new JButton("  Armor  ");
//	public final JButton shieldBtn = new JButton("  Shield  ");
//	public final JButton bootsBtn = new JButton("    Boots  ");
//	public final JButton weaponBtn = new JButton("   Weapon  ");


	public JLabel iconL = new JLabel();
	private JLabel nameL = new JLabel();
	private JLabel levelL = new JLabel();
	private JLabel typeL = new JLabel();
	private JLabel hitponitL = new JLabel();
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
//	public String wearingType;
	public Fighter fighter = null;
	private MapPanelInGame owner1;
	
	public static void main(String[] args) {
		createAndShowGUI(null);
	}

	/**
	 * 
	 * @param ownerFrame
	 */
	public static void createAndShowGUI(CharacterSelection ownerFrame) {
		JFrame frame2 = new JFrame();
		frame2.setLayout(new FlowLayout());
		frame2.add(new CharacterPanel(null));
//		frame2.setBounds(230, 230, 0, 0);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.pack();
		frame2.setVisible(true);

	}

	/**
	 * 
	 */
	CharacterPanel(MapPanelInGame mapPanelInGame) {
		owner1 = mapPanelInGame;
		setLayout(new BorderLayout());
		JPanel backPanel = new JPanel(new BorderLayout());
		JPanel attributesPanel = new JPanel(new GridLayout(11, 3, 5, 5));
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		JPanel iconPanel = new JPanel(new FlowLayout());
		

		add(backPanel, BorderLayout.NORTH);
		backPanel.add(attributesPanel, BorderLayout.CENTER);
		backPanel.add(iconPanel, BorderLayout.NORTH);
		backPanel.add(buttonsPanel, BorderLayout.SOUTH);
		attributesPanel.setPreferredSize(new Dimension(270, 320));

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

//		iconL.setPreferredSize(new Dimension(50,50));
		iconL.setIcon(Config.NPC_ICON);
		iconPanel.add(iconL);
		attributesPanel.add(new JLabel(" Name "));
		attributesPanel.add(nameL);
		attributesPanel.add(new JLabel("     "));
		attributesPanel.add(new JLabel(" Type "));
		attributesPanel.add(typeL);
		attributesPanel.add(new JLabel("     "));
		attributesPanel.add(new JLabel(" Level "));
		attributesPanel.add(levelL);
		attributesPanel.add(new JLabel("     "));
		attributesPanel.add(new JLabel(" Hitpoints "));
		attributesPanel.add(hitponitL);
		attributesPanel.add(new JLabel("     "));
		attributesPanel.add(new JLabel("      "));
		attributesPanel.add(new JLabel(" Value "));
		attributesPanel.add(new JLabel(" Modifier "));
		attributesPanel.add(new JLabel(" Strength "));
		attributesPanel.add(strengthL);
		attributesPanel.add(strengthModiferL);
		attributesPanel.add(new JLabel(" Dexterity "));
		attributesPanel.add(dexterityL);
		attributesPanel.add(dexModiferL);
		attributesPanel.add(new JLabel(" Constitution "));
		attributesPanel.add(constitutionL);
		attributesPanel.add(conModiferL);
		attributesPanel.add(new JLabel(" Intelligence "));
		attributesPanel.add(intelligenceL);
		attributesPanel.add(intelliModiferL);
		attributesPanel.add(new JLabel(" Wisdom "));
		attributesPanel.add(wisdomL);
		attributesPanel.add(wisModiferL);
		attributesPanel.add(new JLabel(" Charisma "));
		attributesPanel.add(charismaL);
		attributesPanel.add(chaModiferL);

		buttonsPanel.add(inventoryBtn);
//		getOwnerInformation();
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
//			setLabels(owner.fighter);
//			setEquipmentIcon(owner.fighter);
		}
	}

//	/**
//	 * 
//	 * @param fighter
//	 */
//	private void setLabels(Fighter fighter) {
//		updateAttributes(fighter);
//
//	}

	/**
	 * This method refresh attribute values of a character during a game.
	 * @param fighter The selected character in the game
	 */
	protected void updateAttributes(Fighter fighter) {
		nameL.setText(fighter.getName());
		levelL.setText(Integer.toString(fighter.getLevel()));
		hitponitL.setText(Integer.toString(fighter.getHitPoints()));

		strengthL.setText(Integer.toString(fighter.getTotalStrength()));
		dexterityL.setText(Integer.toString(fighter.getTotalDexterity()));
		constitutionL.setText(Integer.toString(fighter.getTotalConstitution()));
		intelligenceL.setText(Integer.toString(fighter.getTotalIntelligence()));
		wisdomL.setText(Integer.toString(fighter.getTotalWisdom()));
		charismaL.setText(Integer.toString(fighter.getTotalCharisma()));
		
		strengthModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalStrength())));
		dexModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalDexterity())));
		conModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalConstitution())));
		intelliModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalIntelligence())));
		wisModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalWisdom())));
		chaModiferL.setText(Integer.toString(fighter.getModifier(fighter.getTotalCharisma())));

	}

	public void actionPerformed(ActionEvent e) {

	}

	/**
	 * This method defines actions of different buttons in this frame.
	 */
	private void buttonsManage() {
		
		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("opening 1 " + fighter);
				System.out.println("opening 2 " + owner1);
//				System.out.println(getThisFrame());
				if ( owner1.getSelectedCharacter() != null) {
					System.out.println("opening");
					InventoryView.createAndShowGUI(owner1.getSelectedCharacter());
				}
			}
		});

	}

//	/**
//	 *  
//	 * This internal class is to draw a background picture in the frame
//	 * 
//	 * @author Fei Yu
//	 * @date Mar 3, 2017
//	 */
//	class EmbeddedPanel extends JPanel {
//
//		private Image img;
//
//		public EmbeddedPanel() {
//			super();
//			setOpaque(true);
//
//			img = Toolkit.getDefaultToolkit().getImage("example.jpg");
//		}
//
//		/**
//		 * This method is override method to draw a picture
//		 */
//		public void paintComponent(Graphics g) {
//			super.paintComponent(g);
//			g.drawImage(img, 0, 0, 230, 320, this);
//		}
//	}

}