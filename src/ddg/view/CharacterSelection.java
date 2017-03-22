package ddg.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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

    private final JButton selectBtn = new JButton("      OK      ");
    private final JButton cancelBtn = new JButton("    Cancel  ");
    private final JButton editBtn = new JButton("    Edit... ");
    private final JButton deleteBtn = new JButton("    Delete ");
    private JButton createBtn = new JButton("   Create...  ");

    private final JButton helmetBtn = new JButton();
    
    private final DefaultListModel<String> jlistModel = new DefaultListModel<String>(); 
    private final JList<String> characterList = new JList<String>(jlistModel);
    private final JScrollPane characterListPane = new JScrollPane(characterList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);     
    
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


	HashMap<String, Fighter> hm1 = new HashMap<>();
	public String fighterKeyName = "fighter111";
	public Fighter fighter = null;
	public boolean isCreatingNew = true;
	
	private DDGameMain owner = null;

    public static void main(String[] args) 
    {
        createAndShowGUI();
    }
    
    /**
     * 
     */
    public static void createAndShowGUI() 
    {
    	CharacterSelection frame1 = new CharacterSelection(null, "Character Selection"); 
    	frame1.setBounds(200, 200, 0, 0);
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
        Config.HELMET_ICON.setImage(Config.HELMET_ICON.getImage().getScaledInstance(Config.HELMET_ICON.getIconWidth(),  
        		Config.HELMET_ICON.getIconHeight(), Image.SCALE_DEFAULT)); 

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
        attributesPanel.setPreferredSize(new Dimension(600,450));
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
    			CharacterSelection rootframe = (CharacterSelection) SwingUtilities.getWindowAncestor(createBtn);
    			CharacterEditor.createAndShowGUI(rootframe);
    			setEnabled(false);
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
					System.out.println(getThisFrame());
					CharacterEditor.createAndShowGUI(getThisFrame());
					setEnabled(false);
    	        }               
            }
        });
    	
    	deleteBtn.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
                String key = (String) characterList.getSelectedValue();
                FighterModel fm = new FighterModel();
                
                hm1.remove(key);
        		fm.setFighters(hm1);
        		
    			String gSave = Utils.toJson(fm);
    			Utils.save2File(Config.CHARACTOR_FILE, gSave);

    			
            	jlistModel.clear();
        		String g = Utils.readFile(Config.CHARACTOR_FILE);
        		fm = Utils.fromJson(g, FighterModel.class);

        		if( null!=fm.getFighters() ){
                    hm1 = fm.getFighters();
                    Set<String> keySet1 = hm1.keySet();
                    Iterator<String> it1 = keySet1.iterator();
                    
                    while(it1.hasNext()){
                    	String keyName = it1.next();
                        jlistModel.addElement(keyName);
                    }
        		}
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
            fighter = hm1.get(key);
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

    		System.out.println("===========================");
    		System.out.println(fighter);
    		System.out.println(fighter.getName());
    		System.out.println("backpack now  has " + fighter.getBackpack().size());
    		System.out.println("worn now  has " +fighter.getWorn().size());
    		System.out.print(fighter.isHelmetOn);
    		System.out.print(" ");
    		System.out.print(fighter.isArmorOn);
    		System.out.print(" ");
    		System.out.print(fighter.isBeltOn);
    		System.out.print(" ");
    		System.out.print(fighter.isRingOn); 
    		System.out.print(" ");
    		System.out.print(fighter.isBootsOn);
    		System.out.print(" ");
    		System.out.print(fighter.isWeaponOn);
    		System.out.print(" ");
    		System.out.print(fighter.isShieldOn);
    		System.out.println(fighter.getBackpack());
    		System.out.println(fighter.getWorn());
    		System.out.println("===========================");
        }
        System.out.println("value changed");
        
    }
	
	/**
	 * Return the object of this window
	 * @return
	 */
	public CharacterSelection getThisFrame(){
		return this;
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
                FighterModel fm = new FighterModel();
                
        		String g = Utils.readFile(Config.CHARACTOR_FILE);
        		fm = Utils.fromJson(g, FighterModel.class);
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
                                jlistModel.addElement(keyName);
                            }
                		}
            		}
            		catch (NullPointerException ex){
            			System.out.println("there is a NullPointerException");
            		}        			
        		}
        		if (fighter != null){
        			System.out.println(fighter.isHelmetOn + " " + fighter.getWorn().size() + " " + fighter.getWorn());
        		}
            }  
  
            @Override  
            public void windowLostFocus(WindowEvent e) { 
                System.out.println("The CS window is not focused.");  
            }                
        });  
        
    }

}