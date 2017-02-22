package ddg.view;

import java.util.*;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import ddg.model.Fighter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.*;

public class CharacterEditLayout extends JFrame implements ActionListener {
    //define components in the frame

    private final JButton saveBtn = new JButton("      Save      ");
    private final JButton cancelBtn = new JButton("      Cancel      ");
    private final JButton randomBtn = new JButton("      Random      ");

    private final JButton helmetBtn = new JButton();
    private final JButton shoulderBtn = new JButton("  Shoulder  ");
    private final JButton beltBtn = new JButton("  Belt ");
    private final JButton ringBtn = new JButton("  Ring  ");
    private final JButton armorBtn = new JButton("  Armor  ");
    private final JButton shieldBtn = new JButton("  Shield  ");
    private final JButton bootsBtn = new JButton("    Boots  ");
    private final JButton weaponBtn = new JButton("   Weapon  ");

    public int id;
    //these 3 lines are for upper left jlist of games in a jscrollpanel
    private final DefaultListModel<String> model = new DefaultListModel<String>(); 
    
    
    //Text Fields to show the game results
    private final JTextField nameTextF = new JTextField();
    private final JTextField levelTextF = new JTextField();
    private final JTextField strengthTextF = new JTextField();
    private final JTextField dexterityTextF = new JTextField();
    private final JTextField constitutionTextF = new JTextField();
    private final JTextField intelligenceTextF = new JTextField();
    private final JTextField wisdomTextF = new JTextField();
    private final JTextField charismaTextF = new JTextField();
    private final JTextField modifier1TextF = new JTextField();
    private final JTextField modifier2TextF = new JTextField();
    private final JTextField modifier3TextF = new JTextField();
    private final JTextField modifier4TextF = new JTextField();
    private final JTextField modifier5TextF = new JTextField();
    private final JTextField modifier6TextF = new JTextField();
    private final JTextField modifier7TextF = new JTextField();
    private final JTextField modifier8TextF = new JTextField();
    private final JTextField modifier9TextF = new JTextField();
    
    private static JFrame owner;

    //define 2 colors that are used frequently
    private final Color dustYellow = new Color(211,211,55);
    private final Color lightPink = new Color(255,230,230);

    public static void main(String[] args) 
    {
        //call the method to build the frame
    	JFrame f1 = new JFrame("owner");
        createAndShowGUI(f1);
    }//end of main()
    ///////////////////////////////////////////////////////////////////////////
    public static void createAndShowGUI(JFrame ownerFrame) 
    {
        //new up  this class, & call constructor, --due to extends, it is a frame
        CharacterEditLayout frame1 = new CharacterEditLayout(); 
        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame1.pack();
//        frame1.setResizable(false);
        frame1.setVisible(true);
        if (ownerFrame != null){
            owner = ownerFrame;        	
        }
        	
//        this.owner = getOwner(ownerFrame);
        
    }//end of createAndShowGUI()
    ///////////////////////////////////////////////////////////////////////////
    CharacterEditLayout()
    {
        //build the frame with a title and define layout
        super("Character Editor");
        setLayout(new BorderLayout());
        JPanel backPanel= new JPanel(new BorderLayout());
        JPanel characterPanel= new JPanel(new BorderLayout());
        JPanel attributesPanel= new JPanel(new GridLayout(10,4,5,5));
        JPanel buttonsPanel= new JPanel(new GridLayout(10,1,5,5));
        ImageIcon icon = new ImageIcon("icon1.jpg");  
        icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),  
                icon.getIconHeight(), Image.SCALE_DEFAULT)); 
        id = 1;
        
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
        helmetBtn.setIcon(icon);
        characterLeftPanel.add(helmetBtn);
        characterLeftPanel.add(shoulderBtn);
        characterLeftPanel.add(armorBtn);
        characterLeftPanel.add(beltBtn);
        characterRightPanel.add(ringBtn);
        characterRightPanel.add(bootsBtn);
        characterRightPanel.add(weaponBtn);
        characterRightPanel.add(shieldBtn);
//        characterImagePanel.setSize(300, 500);
        characterImagePanel.setBackground(Color.YELLOW);
        characterImagePanel.setBounds(0, 0, 500, 500);
        characterImagePanel.add(new JLabel("                                                                 "));
        buttonsPanel.setPreferredSize(new Dimension(100,320));
        characterLeftPanel.setPreferredSize(new Dimension(160,320));
        characterRightPanel.setPreferredSize(new Dimension(60,320));
        attributesPanel.setPreferredSize(new Dimension(600,320));
        characterImagePanel.setPreferredSize(new Dimension(350,320));
        
        characterImagePanel.setPreferredSize(new Dimension(350,320));
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
        
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.add(randomBtn);
        buttonsPanel.add(saveBtn);
        buttonsPanel.add(cancelBtn);
        buttonsPanel.setSize(300,500);
        focusManage();
//        g.drawRect(0, 0, 300, 500);
        buttonsManage();
            
    }//end of constructor
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	void buttonsManage(){
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("cancel clicked");
				dispose();
				owner.setEnabled(true);
				owner.setVisible(true);
	        }
	    });
		saveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("save clicked");
				//validation, 新建ArrayList， 打开character文件，读取所有character
				//放到ArrayList里，新建character对象，getJTextField，赋值
				//把character对象add到ArrayList里，把ArrayList保存到文件。
				Fighter fighter1 = new Fighter(1, 10, 10);
				Fighter.saveFighter(fighter1);
				dispose();
				owner.setEnabled(true);
				owner.setVisible(true);
	        }
	    });
	}
	
//	void setDefaultCloseOperation(){
//		System.out.println("X clicked");
//		dispose();
//		owner.setEnabled(true);
//		owner.setVisible(true);
//	}

	public void focusManage() {
	 // TODO Auto-generated constructor stub  
	 this.addWindowFocusListener(new WindowFocusListener() {  
	 	
	     @Override  
	     public void windowGainedFocus(WindowEvent e) {  
	         // TODO Auto-generated method stub  
	         System.out.println("The CE window is focused.");  
	         //刷新列表，reload文件。
	     }  
	
	     @Override  
	     public void windowLostFocus(WindowEvent e) {  
	         // TODO Auto-generated method stub  
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	 });
	 
	}
	
	class EmbeddedPanel extends JPanel{

	    private ImageIcon icon;  
	    private Image img;  
	    public EmbeddedPanel() {  
	    	super();
	    	setOpaque(true);
	    	
	        //  /img/HomeImg.jpg 是存放在你正在编写的项目的bin文件夹下的img文件夹下的一个图片  
//	        icon=new ImageIcon(getClass().getResource("example.jpg"));  
//	        img=icon.getImage();  
	    	img = Toolkit.getDefaultToolkit().getImage( "example.jpg"); 
	    }  
	    public void paintComponent(Graphics g) {  
	        super.paintComponent(g);  
//	        setBackground(Color.WHITE);
	        g.drawImage(img, 0, 0,300, 300, this);  
//	        g.drawRect(5, 0, 30, 50);
//		    }  
		}
	}
    
}//end of class
