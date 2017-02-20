import java.util.*;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.*;

public class InventoryView extends JFrame implements ActionListener {
    //define components in the frame

    private final JButton saveBtn = new JButton("      Save      ");
    private final JButton cancelBtn = new JButton("    Cancel  ");
    private final JButton equipBtn = new JButton("    Equip   ");
    private final JButton removeBtn = new JButton("   Take off  ");

    private final JButton helmetBtn = new JButton();
    private final JButton shoulderBtn = new JButton("  Shoulder  ");
    private final JButton beltBtn = new JButton("  Belt ");
    private final JButton ringBtn = new JButton("  Ring  ");
    private final JButton armorBtn = new JButton("  Armor  ");
    private final JButton shieldBtn = new JButton("  Shield  ");
    private final JButton bootsBtn = new JButton("    Boots  ");
    private final JButton weaponBtn = new JButton("   Weapon  ");

    
    //these 3 lines are for upper left jlist of games in a jscrollpanel
    private final DefaultListModel<String> model = new DefaultListModel<String>(); 
    private final JList<String> backpackItemList = new JList<String>(model);
    private final JScrollPane itemListPane = new JScrollPane(backpackItemList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
    
    //these 2 lines are for lower right output area showing a round played
    private final JTextArea roundResultsTA = new JTextArea(15, 43);
    
    //Text Fields to show the game results
    private final JLabel nameTextF = new JLabel(" L ");
    private final JLabel levelTextF = new JLabel(" L ");
    private final JLabel strengthTextF = new JLabel(" L ");
    private final JLabel dexterityTextF = new JLabel(" L ");
    private final JLabel constitutionTextF = new JLabel(" L ");
    private final JLabel intelligenceTextF = new JLabel(" L ");
    private final JLabel wisdomTextF = new JLabel(" L ");
    private final JLabel charismaTextF = new JLabel(" L ");

    
    //define 2 colors that are used frequently
    private final Color dustYellow = new Color(211,211,55);
    private final Color lightPink = new Color(255,230,230);

    public static void main(String[] args) 
    {
        //call the method to build the frame
        createAndShowGUI();
    }//end of main()
    ///////////////////////////////////////////////////////////////////////////
    public static void createAndShowGUI() 
    {
        //new up  this class, & call constructor, --due to extends, it is a frame
    	InventoryView frame1 = new InventoryView(); 
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.pack();
//        frame1.setResizable(false);
        frame1.setVisible(true);
    }//end of createAndShowGUI()
    ///////////////////////////////////////////////////////////////////////////
    InventoryView()
    {
        //build the frame with a title and define layout
        super("Inventory");
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
        backpackListPanel.add(itemListPane, BorderLayout.CENTER);
        backpackItemList.setPreferredSize(new Dimension(200,220));
        characterPanel.add(characterLeftPanel, BorderLayout.WEST);
        characterPanel.add(characterImagePanel, BorderLayout.CENTER);
        characterPanel.add(characterRightPanel, BorderLayout.EAST);
        characterPanel.add(characterButtomPanel, BorderLayout.SOUTH);
        characterButtomPanel.add(removeBtn);
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
        backpackListPanel.setPreferredSize(new Dimension(200,260));
//        buttonsPanel.setPreferredSize(new Dimension(100,320));
        characterLeftPanel.setPreferredSize(new Dimension(60,320));
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
        buttonsPanel.add(equipBtn);
        buttonsPanel.add(saveBtn);
        buttonsPanel.add(cancelBtn);
        buttonsPanel.add(new JLabel("    "));
        buttonsPanel.setSize(300,500);
        

        
//        g.drawRect(0, 0, 300, 500);
            
    }//end of constructor RPSGApp()
    ///////////////////////////////////////////////////////////////////////////
    //method to execute what to do when each button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
//	        if (img != null) {
//	           int height = img.getHeight(this);
//	           int width = img.getWidth(this);
//	   
//	           if (height != -1 && height > getHeight())
//	              height = getHeight();
//	   
//	           if (width != -1 && width > getWidth())
//	              width = getWidth();
//	   
//	           int x = (int) (((double) (getWidth() - width)) / 2.0);
//	           int y = (int) (((double) (getHeight() - height)) / 2.0);
	        //下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小  
	        g.drawImage(img, 0, 0,300, 300, this);  
//	        g.drawRect(5, 0, 30, 50);
//		    }  
		}
	}
    
}
