package ddg.ui.view.dialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ddg.model.Fighter;
import ddg.model.entity.BaseItem;
import ddg.model.entity.Item;
import ddg.utils.Utils;
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
    private final JButton tradeBtn = new JButton("   Trade  ");
    
    private ArrayList<Item> playerbackpack = new ArrayList<>();
    private DefaultListModel<String> playerItemModel = new DefaultListModel<String>();
    private JList<String> playerItemList = new JList<String>();
    
    private ArrayList<Item> npcbackpack = new ArrayList<>();
    private DefaultListModel<String> npcItemModel = new DefaultListModel<String>();
    private JList<String> npcItemList = new JList<String>();        
    
    private JLabel nameL = new JLabel();
    private JLabel typeL = new JLabel();
    private JLabel attributeL = new JLabel();
    private JLabel valueL = new JLabel();
    
    private JLabel name2L = new JLabel();
    private JLabel type2L = new JLabel();
    private JLabel attribute2L = new JLabel();
    private JLabel value2L = new JLabel();
    
//    public BaseItem playeritem = null;
//    public BaseItem npcitem = null;
    
   
    private void initData(Fighter player, Fighter npc) {
    	playerItemModel.clear();
    	npcItemModel.clear();
    	playerbackpack = player.getBackpack();
    	npcbackpack = npc.getBackpack();
    	for(Item playeritem : playerbackpack){
    		playerItemModel.addElement(playeritem.getId());
    	}
    	
    	for(Item npcitem : npcbackpack){
    		npcItemModel.addElement(npcitem.getId());
    	}
    	
	}
    
    /**
     * Constructor
     */
    public BackpackTrade(Fighter player,Fighter npc)
    {
        super();
        setTitle("Trade");
        setModal(true);
        setBounds(260, 260, 700, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        initData(player,npc);
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
        playerItemAttrPanel.add(new JLabel(" Attribute"));
        playerItemAttrPanel.add(attributeL);
        playerItemAttrPanel.add(new JLabel(" Value   "));
        playerItemAttrPanel.add(valueL);
        playerItemAttrPanel.add(new JLabel("      "));
        playerItemAttrPanel.add(new JLabel("      "));
        playerItemAttrPanel.add(new JLabel("      "));
        playerItemAttrPanel.add(new JLabel("      "));
        playerItemAttrPanel.add(new JLabel("      "));
        playerItemAttrPanel.add(new JLabel("      "));
        playerItemAttrPanel.setPreferredSize(new Dimension(160, 230));
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel(" Name   "));
        npcItemAttrPanel.add(name2L);
        npcItemAttrPanel.add(new JLabel(" Type   "));
        npcItemAttrPanel.add(type2L);
        npcItemAttrPanel.add(new JLabel(" Attribute"));
        npcItemAttrPanel.add(attribute2L);
        npcItemAttrPanel.add(new JLabel(" Value   "));
        npcItemAttrPanel.add(value2L);
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.add(new JLabel("      "));
        npcItemAttrPanel.setPreferredSize(new Dimension(160, 230));

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
        buttonPanel.setPreferredSize(new Dimension(200, 250));
        playerItemList.addListSelectionListener(this);
        playerItemList.setModel(playerItemModel);
        JScrollPane playerItemListPane = new JScrollPane(playerItemList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        playerItemListPanel.add(playerItemListPane, BorderLayout.CENTER);
        playerItemListPane.setPreferredSize(new Dimension(120,230));
        
        npcItemList.addListSelectionListener(this);
        npcItemList.setModel(npcItemModel);
        JScrollPane npcItemListPane = new JScrollPane(npcItemList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        npcItemListPanel.add(npcItemListPane, BorderLayout.CENTER);
        npcItemListPane.setPreferredSize(new Dimension(120,230));

        tradeBtn.addActionListener(new ActionListener(){ 
    		public void actionPerformed(ActionEvent e){
    			int indexplayer = playerItemList.getSelectedIndex();
    			int indexnpc = npcItemList.getSelectedIndex();
    			if(indexplayer>=0){
    				Item item = null;
    				if(indexnpc<0){
    					if(npcItemModel.size()!=0) {
    						indexnpc = Utils.getRadom(npcItemModel.size());
    						item = npcbackpack.get(indexnpc);
    					}
    				} else {
						item = npcbackpack.get(indexnpc);
    				}
    				player.trade(playerbackpack.get(indexplayer), npc, item);
    				refreshListView();
    			}
    			else if(indexplayer<0){
    				JOptionPane.showMessageDialog(null, "Must chose one item to exchange!!","NotSelected",JOptionPane.ERROR_MESSAGE);
    			}
    		}

			private void refreshListView() {
				initData(player, npc);
				playerItemList.setModel(playerItemModel);
				npcItemList.setModel(npcItemModel);
				
			}
        });    	
    	
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("cancel clicked");
				dispose();				
	        }
		});        
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			int index = playerItemList.getSelectedIndex();
			int indexnpc = npcItemList.getSelectedIndex();
			if(index >= 0) {
				System.out.println("list select:"+index);
				Item item = playerbackpack.get(index);
				nameL.setText(item.getId());
				typeL.setText(item.getName());
				attributeL.setText(item.getIncrease());
				valueL.setText(Integer.toString(item.getBonus()));
			}
			if(indexnpc >= 0) {
				System.out.println("list select:"+indexnpc);
				Item item = npcbackpack.get(indexnpc);
				name2L.setText(item.getId());
				type2L.setText(item.getName());
				attribute2L.setText(item.getIncrease());
				value2L.setText(Integer.toString(item.getBonus()));
			}
		}
        System.out.println("value changed");
		
	}    
}
