package ddg.ui.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ddg.Config;
import ddg.model.Fighter;
import ddg.model.FighterModel;
import ddg.ui.DDGameMain;
import ddg.utils.Utils;

/**
 * for popUp a Dialog supporting user chose character
 * @author Bo
 * @date Mar 23, 2017
 * 
 */
public class PopUpForFighter extends JDialog implements ActionListener, ListSelectionListener{
    //define components in the frame

    private final JButton selectBtn = new JButton("      Select      ");
    private final JButton cancelBtn = new JButton("    Cancel  ");

    private final JButton helmetBtn = new JButton();
    
    //these 3 lines are for upper left jlist of games in a jscrollpanel
    private final DefaultListModel<String> jlistModel = new DefaultListModel<String>(); 
    private final JList<String> characterList = new JList<String>(jlistModel);
    private final JScrollPane characterListPane = new JScrollPane(characterList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    
    
    //Text Fields to show the game results
    private final JLabel nameTextF = new JLabel("0");
    private final JLabel levelTextF = new JLabel("0");
    private final JLabel strengthTextF = new JLabel("0");
    private final JLabel dexterityTextF = new JLabel("0");
    private final JLabel constitutionTextF = new JLabel("0");
    private final JLabel intelligenceTextF = new JLabel("0");
    private final JLabel wisdomTextF = new JLabel("0");
    private final JLabel charismaTextF = new JLabel("0");

    private final JLabel nameModiferL = new JLabel("");
    private final JLabel levelModiferL = new JLabel("");
    private final JLabel strengthModiferL = new JLabel("0");
    private final JLabel dexModiferL = new JLabel("0");
    private final JLabel conModiferL = new JLabel("0");
    private final JLabel intelliModiferL = new JLabel("0");
    private final JLabel wisModiferL = new JLabel("0");
    private final JLabel chaModiferL = new JLabel("0");


	HashMap<String, Fighter> hm1 = new HashMap<>();
	public int id = 100;
	public String fighterKeyName = "fighter111";
	public Fighter fighter = null;
	private boolean isfriendly = false;
	
	/**
	 * @return the character which user chose
	 */
	public Fighter getFighter() {
		return fighter;
	}

	/**
	 * @return true if this character is friendly, otherwise return false
	 */
	public boolean getIsfriendly() {
		return isfriendly;
	}

	public boolean isCreatingNew = true;
	
	private DDGameMain owner = null;
	private final JCheckBox checkboxfriendly = new JCheckBox(" isFriendly?");

	/**
	 * @param owner	the frame that own this dialog
	 * @param title title for this dialog
	 */
	public PopUpForFighter(JFrame owner, String title)
    {
        super(owner, title);
        this.owner = (DDGameMain)owner;
        setModal(true);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel backPanel= new JPanel(new BorderLayout());
        JPanel characterPanel= new JPanel(new BorderLayout());
        JPanel attributesPanel= new JPanel(new GridLayout(0,3,5,5));
        JPanel backpackPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel= new JPanel(new GridLayout(8,1,5,5));
        JPanel backpackListPanel = new JPanel(new BorderLayout());
        ImageIcon icon = new ImageIcon("icon1.jpg");  
        icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),  
                icon.getIconHeight(), Image.SCALE_DEFAULT)); 

        getContentPane().add(backPanel, BorderLayout.NORTH);
        backPanel.add(characterPanel, BorderLayout.WEST);
        backPanel.add(attributesPanel, BorderLayout.CENTER);
        backPanel.add(backpackPanel, BorderLayout.EAST);
        backpackPanel.add(buttonsPanel, BorderLayout.SOUTH);
        characterList.setPreferredSize(new Dimension(200,560));
        characterPanel.add(backpackListPanel, BorderLayout.CENTER);
        backpackListPanel.add(characterListPane, BorderLayout.CENTER);
        helmetBtn.setIcon(icon);
        
        backpackListPanel.setPreferredSize(new Dimension(200,260));
        attributesPanel.setPreferredSize(new Dimension(600,320));

        JLabel lb1 = new JLabel(" ");
        lb1.setBorder(new LineBorder(Color.BLACK));
        strengthModiferL.setBorder(new LineBorder(Color.BLACK));
        dexModiferL.setBorder(new LineBorder(Color.BLACK));
        conModiferL.setBorder(new LineBorder(Color.BLACK));
        intelliModiferL.setBorder(new LineBorder(Color.BLACK));
        wisModiferL.setBorder(new LineBorder(Color.BLACK));
        chaModiferL.setBorder(new LineBorder(Color.BLACK));
        lb1.setPreferredSize(new Dimension(20,15));
        lb1.setBounds(0, 0, 20, 15);
        lb1.setIcon(icon);
        lb1.setText(" 2 ");
        attributesPanel.add(new JLabel("   1  "));
        attributesPanel.add(lb1);
        attributesPanel.add(levelModiferL);
        attributesPanel.add(new JLabel(" Name "));
        attributesPanel.add(nameTextF);
        attributesPanel.add(nameModiferL);
        attributesPanel.add(new JLabel(" Level "));
        attributesPanel.add(levelTextF);
        JLabel label = new JLabel(" Modifier ");
        attributesPanel.add(label);
        attributesPanel.add(new JLabel(" Strength "));
        attributesPanel.add(strengthTextF);
        attributesPanel.add(strengthModiferL);
        attributesPanel.add(new JLabel(" Dexterity "));
        attributesPanel.add(dexterityTextF);
        attributesPanel.add(dexModiferL);
        attributesPanel.add(new JLabel(" Constitution "));
        attributesPanel.add(constitutionTextF);
        attributesPanel.add(conModiferL);
        attributesPanel.add(new JLabel(" Intelligence "));
        attributesPanel.add(intelligenceTextF);
        attributesPanel.add(intelliModiferL);
        attributesPanel.add(new JLabel(" Wisdom "));
        attributesPanel.add(wisdomTextF);
        attributesPanel.add(wisModiferL);
        attributesPanel.add(new JLabel(" Charisma "));
        attributesPanel.add(charismaTextF);
        attributesPanel.add(chaModiferL);
        attributesPanel.add(new JLabel(""));
        
        buttonsPanel.add(checkboxfriendly);
        buttonsPanel.add(selectBtn);
        buttonsPanel.add(cancelBtn);
        buttonsPanel.setSize(300,500);
        
        characterList.addListSelectionListener(this);


    	selectBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			if (!characterList.isSelectionEmpty()){
        			fighter = hm1.get((String) characterList.getSelectedValue());
        			isfriendly = checkboxfriendly.isSelected();
    	        }
    			dispose();
            }
        });
    	
    	
    	cancelBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){    			
                dispose();                
            }
        });

        focusManage();
    }//end of constructor
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		if (e.getSource() == selectBtn){
//			owner.setSelectedFighter(new Fighter(3,6,6));			
//        }
	}
	@Override
    public void valueChanged(ListSelectionEvent e){
        if (!characterList.isSelectionEmpty()){
            String key = (String) characterList.getSelectedValue();
//            Fighter f1 = hm1.get(key);
            fighter = hm1.get(key);
            nameTextF.setText(fighter.getName());
            levelTextF.setText(Integer.toString(fighter.getLevel()));
            strengthTextF.setText(Integer.toString(fighter.getStrength()));
            dexterityTextF.setText(Integer.toString(fighter.getDexterity()));
    		constitutionTextF.setText(Integer.toString(fighter.getConstitution()));
    		intelligenceTextF.setText(Integer.toString(fighter.getIntelligence()));
    		wisdomTextF.setText(Integer.toString(fighter.getWisdom()));
    		charismaTextF.setText(Integer.toString(fighter.getCharisma()));
    		strengthModiferL.setText(Integer.toString(fighter.getModifier(fighter.getStrength())));
    		dexModiferL.setText(Integer.toString(fighter.getModifier(fighter.getDexterity())));
    		conModiferL.setText(Integer.toString(fighter.getModifier(fighter.getConstitution())));
    		intelliModiferL.setText(Integer.toString(fighter.getModifier(fighter.getIntelligence())));
    		wisModiferL.setText(Integer.toString(fighter.getModifier(fighter.getWisdom())));
    		chaModiferL.setText(Integer.toString(fighter.getModifier(fighter.getCharisma())));
    		System.out.println("Helmet is on? " + fighter.isHelmetOn());
    		System.out.println(fighter.getWorn());
    		System.out.println("backpack now  has " + fighter.getBackpack().size());
    		System.out.println(fighter.getBackpack());
        }
        System.out.println("value changed");
    }
	
    public void focusManage() {
        // TODO Auto-generated constructor stub  
        this.addWindowFocusListener(new WindowFocusListener() {  
        	
            @Override  
            public void windowGainedFocus(WindowEvent e) {  
                // TODO Auto-generated method stub  
            	jlistModel.clear();
                System.out.println("The CS window is focused.");  
                FighterModel fm = new FighterModel();

//        		String g = Utils.readFile(Config.CHARACTER_FILE);
//        		fm = Utils.fromJson(g, FighterModel.class);
        		fm = Utils.readObject(Config.CHARACTER_FILE, FighterModel.class);
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
        		System.out.println("continue");
        		System.out.println("character list: " + characterList);
            }  
  
            @Override  
            public void windowLostFocus(WindowEvent e) {  
                // TODO Auto-generated method stub  
                System.out.println("The CS window is not focused.");
                System.out.println(isfriendly);
            }  
              
        });  
        
    }

}
