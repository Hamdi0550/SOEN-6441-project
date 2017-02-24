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
import ddg.utils.Utils;

public class CharacterSelection extends JFrame implements ActionListener, ListSelectionListener{
    //define components in the frame

    private final JButton saveBtn = new JButton("      Select      ");
    private final JButton cancelBtn = new JButton("    Cancel  ");
    private final JButton editBtn = new JButton("    Edit... ");
    private final JButton deleteBtn = new JButton("    Delete ");
    private JButton createBtn = new JButton("   Create...  ");
    private final JButton randomBtn = new JButton("    Random ");

    private final JButton helmetBtn = new JButton();
    
    //these 3 lines are for upper left jlist of games in a jscrollpanel
    private final DefaultListModel<String> jlistModel = new DefaultListModel<String>(); 
    private final JList<String> characterList = new JList<String>(jlistModel);
    private final JScrollPane characterListPane = new JScrollPane(characterList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    
    
    //Text Fields to show the game results
    private final JLabel nameTextF = new JLabel(" L ");
    private final JLabel levelTextF = new JLabel(" L ");
    private final JLabel strengthTextF = new JLabel(" L ");
    private final JLabel dexterityTextF = new JLabel(" L ");
    private final JLabel constitutionTextF = new JLabel(" L ");
    private final JLabel intelligenceTextF = new JLabel(" L ");
    private final JLabel wisdomTextF = new JLabel(" L ");
    private final JLabel charismaTextF = new JLabel(" L ");


	HashMap<String, Fighter> hm1 = new HashMap<>();
    //define 2 colors that are used frequently
    private final Color dustYellow = new Color(211,211,55);
    private final Color lightPink = new Color(255,230,230);
	public int id = 100;
	public String fighterKeyName = "fighter111";

    public static void main(String[] args) 
    {
        //call the method to build the frame
        createAndShowGUI();
    }//end of main()
    ///////////////////////////////////////////////////////////////////////////
    public static void createAndShowGUI() 
    {
        //new up  this class, & call constructor, --due to extends, it is a frame
    	CharacterSelection frame1 = new CharacterSelection(); 
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.pack();
//        frame1.setResizable(false);
        frame1.setVisible(true);
    }//end of createAndShowGUI()
    ///////////////////////////////////////////////////////////////////////////
    CharacterSelection()
    {
        //build the frame with a title and define layout
        super("Select Character");
        setLayout(new BorderLayout());
        JPanel backPanel= new JPanel(new BorderLayout());
        JPanel characterPanel= new JPanel(new BorderLayout());
        JPanel attributesPanel= new JPanel(new GridLayout(10,4,5,5));
        JPanel backpackPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel= new JPanel(new GridLayout(8,1,5,5));
        JPanel backpackListPanel = new JPanel(new BorderLayout());
        ImageIcon icon = new ImageIcon("icon1.jpg");  
        icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),  
                icon.getIconHeight(), Image.SCALE_DEFAULT)); 
//        id = 2;

        add(backPanel, BorderLayout.NORTH);
        backPanel.add(characterPanel, BorderLayout.WEST);
        backPanel.add(attributesPanel, BorderLayout.CENTER);
        backPanel.add(backpackPanel, BorderLayout.EAST);
        backpackPanel.add(buttonsPanel, BorderLayout.SOUTH);
//        backpackPanel.add(backpackListPanel, BorderLayout.CENTER);
        characterList.setPreferredSize(new Dimension(200,560));
        characterPanel.add(backpackListPanel, BorderLayout.CENTER);
        backpackListPanel.add(characterListPane, BorderLayout.CENTER);
//        characterButtomPanel.add(removeBtn);
        helmetBtn.setIcon(icon);
        
        backpackListPanel.setPreferredSize(new Dimension(200,260));
//        buttonsPanel.setPreferredSize(new Dimension(100,320));
//        characterLeftPanel.setPreferredSize(new Dimension(60,320));
//        characterRightPanel.setPreferredSize(new Dimension(60,320));
        attributesPanel.setPreferredSize(new Dimension(600,320));
//        characterImagePanel.setPreferredSize(new Dimension(350,320));

//        characterImagePanel.setPreferredSize(new Dimension(350,320));
        JLabel lb1 = new JLabel(" ");
        JLabel nameModiferL = new JLabel(" L ");
        JLabel levelModiferL = new JLabel(" L ");
        JLabel strengthModiferL = new JLabel(" L ");
        JLabel dexModiferL = new JLabel(" L ");
        JLabel conModiferL = new JLabel(" L ");
        JLabel intelliModiferL = new JLabel(" L ");
        JLabel wisModiferL = new JLabel(" L ");
        JLabel chaModiferL = new JLabel(" L ");
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
        lb1.setIcon(icon);
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
        attributesPanel.add(randomBtn);
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
        
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(createBtn);        
        buttonsPanel.add(editBtn);
        buttonsPanel.add(deleteBtn);
        buttonsPanel.add(saveBtn);
        buttonsPanel.add(cancelBtn);
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.setSize(300,500);
        
        characterList.addListSelectionListener(this);

    	createBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
//               CharacterEditLayout ce1 = new CharacterEditLayout();
//               CharacterEditLayout ceFrame = new CharacterEditLayout();
    			CharacterEditLayout.createAndShowGUI(getThisFrame());
               setEnabled(false);
               
            }
        });
    	
    	editBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){

               CharacterEditLayout ceFrame = new CharacterEditLayout();
               ceFrame.createAndShowGUI(getThisFrame());
               setEnabled(false);
               
            }
        });

        focusManage();
    }//end of constructor
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
    public void valueChanged(ListSelectionEvent e)
    {
        if (!characterList.isSelectionEmpty())
        {
            String key = (String) characterList.getSelectedValue();
            Fighter f1 = hm1.get(key);
            nameTextF.setText(f1.getName());
            levelTextF.setText(Integer.toString(f1.getLevel()));
            strengthTextF.setText(Integer.toString(f1.getStrength()));
            dexterityTextF.setText(Integer.toString(f1.getDexterity()));
        }
        System.out.println("value changed");
    }
	
	public CharacterSelection getThisFrame(){
		return this;
	}
	
	public int getID(){
		return id;
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
                            	String listItem1 = it1.next();
                                jlistModel.addElement(listItem1);
                            }
                		}        			
            		}
            		catch (NullPointerException ex){
            			System.out.println("there is a NullPointerException");
            		}
        			
        		}
        		System.out.println("continue");
        		System.out.println("character list: " + characterList);
//                for (String key: hm1.keySet()){
//                    jlistModel.addElement(key);
//                }
                
                //刷新列表，reload文件。
            }  
  
            @Override  
            public void windowLostFocus(WindowEvent e) {  
                // TODO Auto-generated method stub  
                System.out.println("The CS window is not focused.");  
            }  
              
        });  
        
    }

}