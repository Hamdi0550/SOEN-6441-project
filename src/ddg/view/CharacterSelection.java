package ddg.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

import ddg.Config;
import ddg.model.Fighter;
import ddg.model.FighterModel;
import ddg.ui.DDGameMain;
import ddg.utils.Utils;
/**
 * 
 * 
 * This class provides the interface to choose an existing character.
 * 
 * @author Fei Yu
 * @date Mar 3, 2017
 */
public class CharacterSelection extends JDialog implements ActionListener, ListSelectionListener{

    private JButton selectBtn = new JButton("      OK      ");
    private JButton cancelBtn = new JButton("    Cancel  ");
    private JButton editBtn = new JButton("    Edit... ");
    private JButton deleteBtn = new JButton("    Delete ");
    private JButton createBtn = new JButton("   Create...  ");

    private JButton helmetBtn = new JButton();
    
    private final DefaultListModel<String> jlistModel = new DefaultListModel<String>(); 
    private final JList<String> characterList = new JList<String>(jlistModel);
    private final JScrollPane characterListPane = new JScrollPane(characterList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);     
    
    private JLabel nameL = new JLabel("   ");
    private JLabel levelL = new JLabel("   ");
    private JLabel typeL = new JLabel("   ");
    private JLabel strengthL = new JLabel("   ");
    private JLabel dexterityL = new JLabel("   ");
    private JLabel constitutionL = new JLabel("   ");
    private JLabel intelligenceL = new JLabel("   ");
    private JLabel wisdomL = new JLabel("   ");
    private JLabel charismaL = new JLabel("   ");
    private JLabel armorClassL = new JLabel("    ");
    private JLabel hitPointsL = new JLabel("    ");
    private JLabel attackBonusL = new JLabel("    ");
    private JLabel damageBonusL = new JLabel("   ");

    private JLabel strengthModiferL = new JLabel("   ");
    private JLabel dexModiferL = new JLabel("   ");
    private JLabel conModiferL = new JLabel("   ");
    private JLabel intelliModiferL = new JLabel("   ");
    private JLabel wisModiferL = new JLabel("   ");
    private JLabel chaModiferL = new JLabel("   ");


	HashMap<String, Fighter> fighterHM = new HashMap<>();
	public String fighterKeyName = "fighter111";
	public Fighter fighter = null;
	public boolean isCreatingNew = true;
	
	private DDGameMain owner = null;
    
    /**
     * This method build frame of the Character Selection Window
     */
    public static void createAndShowGUI() 
    {
    	CharacterSelection frame1 = new CharacterSelection(null, "Character Selection"); 
    	frame1.setBounds(200, 15, 0, 0);
        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);
    }
    
    /**
     * Constructor
     * @param owner
     * @param title
     */
	public CharacterSelection(JFrame owner, String title)
    {
        super(owner, title);
        this.owner = (DDGameMain)owner;
        setModal(true);
        setLayout(new BorderLayout());
        JPanel backPanel= new JPanel(new BorderLayout());
        JPanel characterPanel= new JPanel(new BorderLayout());
        JPanel attributesPanel= new JPanel(new GridLayout(15,4,5,5));
        JPanel backpackPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel= new JPanel(new GridLayout(8,1,5,5));
        JPanel backpackListPanel = new JPanel(new BorderLayout());

        add(backPanel, BorderLayout.NORTH);
        backPanel.add(characterPanel, BorderLayout.WEST);
        backPanel.add(attributesPanel, BorderLayout.CENTER);
        backPanel.add(backpackPanel, BorderLayout.EAST);
        backpackPanel.add(buttonsPanel, BorderLayout.SOUTH);
        characterList.setPreferredSize(new Dimension(200,560));
        characterPanel.add(backpackListPanel, BorderLayout.CENTER);
        backpackListPanel.add(characterListPane, BorderLayout.CENTER);
        helmetBtn.setIcon(Config.HELMET_ICON);
        
        backpackListPanel.setPreferredSize(new Dimension(200,260));
        attributesPanel.setPreferredSize(new Dimension(400,450));
        strengthModiferL.setBorder(new LineBorder(Color.BLACK));
        dexModiferL.setBorder(new LineBorder(Color.BLACK));
        conModiferL.setBorder(new LineBorder(Color.BLACK));
        intelliModiferL.setBorder(new LineBorder(Color.BLACK));
        wisModiferL.setBorder(new LineBorder(Color.BLACK));
        chaModiferL.setBorder(new LineBorder(Color.BLACK));
        nameL.setBorder(new LineBorder(Color.BLACK));
        levelL.setBorder(new LineBorder(Color.BLACK));
        typeL.setBorder(new LineBorder(Color.BLACK));
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
        
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Modifier "));
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Name "));
        attributesPanel.add(nameL);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Level "));
        attributesPanel.add(levelL);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Type "));
        attributesPanel.add(typeL);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Strength "));
        attributesPanel.add(strengthL);
        attributesPanel.add(strengthModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Dexterity "));
        attributesPanel.add(dexterityL);
        attributesPanel.add(dexModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Constitution "));
        attributesPanel.add(constitutionL);
        attributesPanel.add(conModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Intelligence "));
        attributesPanel.add(intelligenceL);
        attributesPanel.add(intelliModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Wisdom "));
        attributesPanel.add(wisdomL);
        attributesPanel.add(wisModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Charisma "));
        attributesPanel.add(charismaL);
        attributesPanel.add(chaModiferL);
        attributesPanel.add(new JLabel("     "));
        attributesPanel.add(new JLabel(" Hitpoints "));
        attributesPanel.add(hitPointsL);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Attack Bonus "));
        attributesPanel.add(attackBonusL);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Damage Bonus "));
        attributesPanel.add(damageBonusL);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel(" Armor Class "));
        attributesPanel.add(armorClassL);
        attributesPanel.add(new JLabel("      "));
        attributesPanel.add(new JLabel("      "));
        
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(createBtn);        
        buttonsPanel.add(editBtn);
        buttonsPanel.add(deleteBtn);
        buttonsPanel.add(selectBtn);
        buttonsPanel.add(cancelBtn);
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.setSize(300,500);
        deleteBtn.setEnabled(false);
        
        characterList.addListSelectionListener(this);

    	createBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			isCreatingNew = true;
    			fighter = null;
    			CharacterEditor.createAndShowGUI(fighter);
            }
        });
    	
    	selectBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			if (!characterList.isSelectionEmpty()){
                    dispose();
    	        }               
            }
        });
    	
    	editBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			if (!characterList.isSelectionEmpty()){
        			isCreatingNew = false;
	    			CharacterEditor.createAndShowGUI(fighter);
    	        }               
            }
        });
    	
    	deleteBtn.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
                String key = (String) characterList.getSelectedValue();
    			fighterHM = Utils.deleteFighter(fighterHM, key, jlistModel); 
    			
    			deleteBtn.setEnabled(false);
            }
        });
    	
    	cancelBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){    			
                dispose();                
            }
        });

        focusManage();
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	@Override
    public void valueChanged(ListSelectionEvent e){
        if (!characterList.isSelectionEmpty()){
        	deleteBtn.setEnabled(true);
            String key = (String) characterList.getSelectedValue();
            fighter = fighterHM.get(key);
            
            updateAttributes(fighter);
    		Utils.displayFighterInfo(fighter);
        }
        System.out.println("value changed");
        
    }
	
	private void updateAttributes(Fighter fighter) {
        nameL.setText(fighter.getName());
        levelL.setText(Integer.toString(fighter.getLevel()));
		typeL.setText(fighter.getType());
        
        strengthL.setText(Integer.toString(fighter.getTotalStrength()));
        dexterityL.setText(Integer.toString(fighter.getTotalDexterity()));
		constitutionL.setText(Integer.toString(fighter.getTotalConstitution()));
		intelligenceL.setText(Integer.toString(fighter.getTotalIntelligence()));
		wisdomL.setText(Integer.toString(fighter.getTotalWisdom()));
		charismaL.setText(Integer.toString(fighter.getTotalCharisma()));
		armorClassL.setText(Integer.toString(fighter.getTotalArmorClass()));
		hitPointsL.setText(Integer.toString(fighter.getHitPoints()));
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
	 * This method manage the actions of the window focus
	 */
    private void focusManage() {
        this.addWindowFocusListener(new WindowFocusListener() {          	
            @Override  
            public void windowGainedFocus(WindowEvent e) {  
            	jlistModel.clear();
                System.out.println("The CS window is focused.");  
                
                fighterHM = Utils.updateFighterList(jlistModel, fighterHM);
                
                System.out.println("jm = " + jlistModel);
                System.out.println("hm = " + fighterHM);

        		if (fighter != null){
        			System.out.println(fighter.isHelmetOn() + " " + fighter.getWorn().size() + " " + fighter.getWorn());
        		}
            }  
  
            @Override  
            public void windowLostFocus(WindowEvent e) { 
                System.out.println("The CS window is not focused.");  
            }                
        });  
        
    }

}