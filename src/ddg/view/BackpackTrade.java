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
import ddg.utils.UtilityStorage;
import ddg.utils.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.*;
/**
 * 
 * 
 * This class provides an interface to allow user to check a character's attributes, worn equipments and the backpack.
 * 
 * @author Fei Yu
 * @date Mar 3, 2017
 */
public class BackpackTrade extends JDialog implements ActionListener, ListSelectionListener {

    private final JButton cancelBtn = new JButton("    Cancel  ");
    private final JButton tradeBtn = new JButton("   Exchange  ");
    
    private final DefaultListModel<String> playerItemModel = new DefaultListModel<String>(); 
    private final JList<String> playerItemList = new JList<String>();
    private final DefaultListModel<String> npcItemModel = new DefaultListModel<String>(); 
    private final JList<String> npcItemList = new JList<String>();        
    
    private JLabel nameL = new JLabel(" L  ");
    private JLabel typeL = new JLabel(" L  ");
    private JLabel attributeL = new JLabel(" L  ");
    private JLabel valueL = new JLabel("  L ");
    private JLabel name2L = new JLabel(" L  ");
    private JLabel type2L = new JLabel(" L  ");
    private JLabel attribute2L = new JLabel("  L ");
    private JLabel value2L = new JLabel(" L  ");
    
    public String selectedWorn = null;
    public BaseItem selectedBackPackItem = null;
    public Fighter f2 = null;
    private static CharacterEditor owner;
    
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
		createAndShowGUI(null);
	}
   
    /**
     * 
     * @param ownerFrame
     */
    public static void createAndShowGUI(CharacterEditor ownerFrame) {
        owner = (CharacterEditor) ownerFrame;
    	BackpackTrade frame1 = new BackpackTrade(); 
        frame1.setBounds(260, 260, 500, 500);
        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame1.pack();
        frame1.setResizable(false);
        frame1.setVisible(true);
    }

    /**
     * Constructor
     */
    BackpackTrade()
    {
        super();
        setTitle("Trade");
        setModal(true);
        setLayout(new BorderLayout());
        JPanel backPanel= new JPanel(new BorderLayout());
        JPanel playerPanel= new JPanel(new BorderLayout());
        JPanel playerTopPanel= new JPanel(new FlowLayout());
        JPanel playerItemListPanel= new JPanel(new BorderLayout());
        JPanel playerItemAttrPanel= new JPanel(new GridLayout(8,2,5,5));
        JPanel npcPanel = new JPanel(new BorderLayout());
        JPanel npcTopPanel= new JPanel(new FlowLayout());
        JPanel npcItemAttrPanel= new JPanel(new GridLayout(8,2,5,5));
        JPanel npcItemListPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel= new JPanel(new GridLayout(8,1,5,5));
        
        add(backPanel, BorderLayout.NORTH);
        backPanel.add(playerPanel, BorderLayout.WEST);
        backPanel.add(buttonPanel, BorderLayout.CENTER);
        backPanel.add(npcPanel, BorderLayout.EAST);
        playerPanel.add(playerItemListPanel, BorderLayout.WEST);
        playerPanel.add(playerItemAttrPanel, BorderLayout.CENTER);
        playerPanel.add(playerTopPanel, BorderLayout.NORTH);
        npcPanel.add(npcItemAttrPanel, BorderLayout.WEST);
        npcPanel.add(npcItemListPanel, BorderLayout.EAST);
        npcPanel.add(npcTopPanel, BorderLayout.NORTH);

        playerItemAttrPanel.add(new JLabel("      "));
        playerItemAttrPanel.add(new JLabel("      "));
        playerItemAttrPanel.add(new JLabel(" Name   "));
        playerItemAttrPanel.add(nameL);
        playerItemAttrPanel.add(new JLabel(" Type   "));
        playerItemAttrPanel.add(typeL);
        playerItemAttrPanel.add(new JLabel(" Attribute   "));
        playerItemAttrPanel.add(attributeL);
        playerItemAttrPanel.add(new JLabel(" Value   "));
        playerItemAttrPanel.add(valueL);
        playerItemAttrPanel.add(new JLabel("      "));
        playerItemAttrPanel.add(new JLabel("      "));
        playerItemAttrPanel.add(new JLabel("      "));
        playerItemAttrPanel.add(new JLabel("      "));
        playerItemAttrPanel.add(new JLabel("      "));
        playerItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel(" Name   "));
        npcItemAttrPanel.add(name2L);
        npcItemAttrPanel.add(new JLabel(" Type   "));
        npcItemAttrPanel.add(type2L);
        npcItemAttrPanel.add(new JLabel(" Attribute   "));
        npcItemAttrPanel.add(attribute2L);
        npcItemAttrPanel.add(new JLabel(" Value   "));
        npcItemAttrPanel.add(value2L);
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel("      "));
        
        nameL.setBorder(new LineBorder(Color.BLACK));
        typeL.setBorder(new LineBorder(Color.BLACK));
        attributeL.setBorder(new LineBorder(Color.BLACK));
        valueL.setBorder(new LineBorder(Color.BLACK));
        name2L.setBorder(new LineBorder(Color.BLACK));
        type2L.setBorder(new LineBorder(Color.BLACK));
        attribute2L.setBorder(new LineBorder(Color.BLACK));
        value2L.setBorder(new LineBorder(Color.BLACK));

        playerTopPanel.add(new JLabel(" Player "));
        npcTopPanel.add(new JLabel(" NPC "));

        buttonPanel.add(new JLabel("      "));
        buttonPanel.add(new JLabel("      "));
        buttonPanel.add(new JLabel("      "));
        buttonPanel.add(new JLabel("      "));
        buttonPanel.add(new JLabel("      "));
        buttonPanel.add(tradeBtn);
        buttonPanel.add(cancelBtn);
        buttonPanel.add(new JLabel("      "));

        playerItemList.addListSelectionListener(this);
        playerItemList.setModel(playerItemModel);
        JScrollPane playerItemListPane = new JScrollPane(playerItemList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        playerItemListPanel.add(playerItemListPane, BorderLayout.CENTER);
        playerItemListPane.setPreferredSize(new Dimension(150,220));
        
        npcItemList.addListSelectionListener(this);
        npcItemList.setModel(npcItemModel);
        JScrollPane npcItemListPane = new JScrollPane(npcItemList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        npcItemListPanel.add(npcItemListPane, BorderLayout.CENTER);
        npcItemListPane.setPreferredSize(new Dimension(150,220));

        focusManage();
        
        tradeBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
            }
        });    	
    	
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("cancel clicked");
				dispose();				
	        }
		});        
    }

    /**
     * This method manage the actions of the window focus
     */
	private void focusManage() {        
		this.addWindowFocusListener(new WindowFocusListener() {          	
	        @Override  
	        public void windowGainedFocus(WindowEvent e) {
	            	
	        }
	
	        @Override  
	        public void windowLostFocus(WindowEvent e) {
	            System.out.println("The CS window is not focused.");  
	        }                
		});  
		
	}

	/**
	 * 
	 * @return
	 */
	public BackpackTrade getThisFrame() {
		return this;
	}
	
	/**
	 * 
	 */
	public CharacterEditor getOwner(){
		return owner;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			int index = playerItemList.getSelectedIndex();
			if(index >= 0) {
				System.out.println("list select:"+index);
				BaseItem item = owner.getOwner().fighter.getBackpack().get(index);
				
				attributeL.setText(item.getIncrease());
				valueL.setText(Integer.toString(item.getBonus()));
				selectedWorn = item.getName();
				selectedBackPackItem = item;
				
				UtilityStorage.setItem(item);
			}
		}
        System.out.println("value changed");
		
	}    
}
