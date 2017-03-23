package ddg.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import ddg.Config;
import ddg.campaign.entity.BaseCampaign;
import ddg.item.entity.BaseItem;
import ddg.map.entity.Chest;
import ddg.map.entity.Map;
import ddg.model.Fighter;
import ddg.model.MapEditorModel;
import ddg.ui.DDGaming;

/**
 * 
 * MapPanelInGame include the map of game, character detail and inventory detail
 * @author Bo
 * @date Mar 12, 2017
 * 
 */
public class MapPanelInGame extends JPanel implements Observer, KeyListener, ActionListener {
	private JScrollPane jspanel;
	private JPanel mapPanel;
	private Map playingMap;
	private BaseCampaign campaign;
	private Fighter fighter;
	private Fighter selectedCharacter;
	private ListIterator<Map> mapsofcampaign;
	private int xofplayer;
	private int yofplayer;

	private CharacterPanel characterPanel = new CharacterPanel(this);
	private InventoryPanel inventoryPanel = new InventoryPanel();
    private int xIndex = -1;
    private int yIndex = -1; 
	protected boolean isCharacter = false;
    
	ImageIcon floor = new ImageIcon("floor.png");
	ImageIcon chest = new ImageIcon("chest.png");
	ImageIcon emptychest = new ImageIcon("emptychest.png");
	ImageIcon wall = new ImageIcon("wall.png");
	ImageIcon indoor = new ImageIcon("indoor.png");
	ImageIcon outdoor = new ImageIcon("outdoor.png");
	ImageIcon playcharacter = new ImageIcon("playcharacter.png");
	ImageIcon mainplayer = new ImageIcon("Mainplayer.png");
	ImageIcon deadnpc = new ImageIcon("deadnpc.png");
	JTextArea log = new JTextArea();
	
	/**
	 * Constructor
	 * @param fighter the play character who is chosen by user
	 * @param campaign the Campaign which user would like to play
	 */
	public MapPanelInGame(Fighter fighter, BaseCampaign campaign){
		this.campaign = campaign;
		this.fighter = fighter;
		setLayout(new BorderLayout());
		setFocusable(true);
		
		initdata();
		this.playingMap.addObserver(this);
		initcontent();
	}
	
	/**
	 * read data from file, and initialize all data on the Panel
	 */
	private void initdata() {
		
		try{
			//read maps files import maps
			FileInputStream fileIn = new FileInputStream(Config.MAP_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			MapEditorModel mapsmodel = (MapEditorModel) in.readObject();
			in.close();
			fileIn.close();
			
			if (mapsmodel == null) {
				mapsmodel = new MapEditorModel();
				mapsmodel.add(new Map());
			}
			
			// retrieval all map(name) in the campaign, then 
			// -----------------------------this part may be produce something wrong-------------------------------------
			ArrayList<Map> mapsofcampaignlist = new ArrayList<Map>();
			for(String nameofmap : campaign.getMaps()){
				for(Map map : mapsmodel.getMaps()){
					if(map.getName().equals(nameofmap)){
						mapsofcampaignlist.add(new Map(map));
						break;
					}
				}
			}
			
			this.mapsofcampaign = mapsofcampaignlist.listIterator();
			initMapData();
			
		}catch(IOException i){
			i.printStackTrace();
		}catch(ClassNotFoundException c){
			c.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * initial data of playing map and do adaptedLevel() function
	 */
	private void initMapData(){
		if(mapsofcampaign.hasNext()){
		    xIndex = -1;
		    yIndex = -1;        
		    if (xIndex != yofplayer || yIndex != xofplayer){
		    	isCharacter = false;
		    }
		    if (isCharacter == false){
		    	characterPanel.setVisible(false);
		    	inventoryPanel.setVisible(false);
		    }
			this.playingMap = this.mapsofcampaign.next();
			this.playingMap.adaptedLevel(fighter.getLevel());
			this.playingMap.addObserver(this);
			
			for(int i=0;i<playingMap.getRow();i++){
				for(int j=0;j<playingMap.getColumn();j++){
					if(playingMap.getLocation()[i][j] == 'i'){
						if(i>0 && playingMap.getLocation()[i-1][j]=='f'){
							 xofplayer = i-1;
							 yofplayer = j;
						}
						else if(i<playingMap.getRow()-1 && playingMap.getLocation()[i+1][j]=='f'){
							xofplayer = i+1;
							yofplayer = j;
						}
						else if(j>0 && playingMap.getLocation()[i][j-1]=='f'){
							xofplayer = i;
							yofplayer = j-1;
						}
						else if(j<playingMap.getColumn()-1 && playingMap.getLocation()[i][j+1]=='f'){
							xofplayer = i;
							yofplayer = j+1;
						}
					}
				}
			}
		}
		else{
			log.append("You finish this Campaign, Congratulation!!!");
			JOptionPane.showMessageDialog(null, "You finish this Campaign, Congratulation!!!");
			JDialog ddgamedialog = (JDialog) SwingUtilities.getWindowAncestor(mapPanel);
			ddgamedialog.dispose();
			DDGaming newddgamedialog = new DDGaming();
			newddgamedialog.popShow(null, "Gaming");
		}
		log.setText("Enter game, Welcome!\n");
		log.append("Current Level:"+fighter.getLevel()+"\n");
		log.append("Current Map:"+mapsofcampaign.nextIndex()+"\n");
		
	}
	
	/**
	 * initialize the view of this panel
	 */
	private void initcontent() {
		mapPanel = new JPanel(){
			private static final long serialVersionUID = -8627231216589776568L;

			@Override  
	        public void paint(Graphics g) {
	            super.paint(g);  
	            for(int i=0;i< playingMap.getRow();i++){
	                for(int j=0;j< playingMap.getColumn();j++){
	                	//draw background
	                	g.drawImage(floor.getImage(), j*50, i*50, 50, 50, null);
	                	
	                    if(playingMap != null){
	                    	if(playingMap.getLocation()[i][j] == 'f' ){
							    g.drawImage(floor.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
							if (playingMap.getLocation()[i][j] == 'w'){
								g.drawImage(wall.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
							if (playingMap.getLocation()[i][j] == 'i'){
								g.drawImage(indoor.getImage(), j*50, i*50, 50, 50, null);
								continue;}
							if (playingMap.getLocation()[i][j] == 'c'){
								g.drawImage(chest.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
							if (playingMap.getLocation()[i][j] == 'e'){
								g.drawImage(emptychest.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
							if (playingMap.getLocation()[i][j] == 'o'){
								g.drawImage(outdoor.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
							if (playingMap.getLocation()[i][j] == 'p'){
								g.drawImage(playcharacter.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
							if (playingMap.getLocation()[i][j] == 'd'){
								g.drawImage(deadnpc.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
	                    }
	                }
	            }
	            g.drawImage(mainplayer.getImage(), yofplayer*50, xofplayer*50, null);
	            g.setColor(Color.BLACK);
	            for(int i=0; i<playingMap.getRow(); i++){
	            	g.drawLine(0, i*50, playingMap.getColumn()*50, i*50);
	            }
	            for(int i=0; i<playingMap.getColumn(); i++){
	            	g.drawLine(i*50, 0, i*50, 50*playingMap.getRow());
	            }
	            
	            if (isCharacter == true){
		            g.setColor(Color.CYAN);
		            g.drawRect(xIndex * 50 + 1, yIndex * 50 + 1, 48, 48);
		            g.drawRect(xIndex * 50 + 2, yIndex * 50 + 2, 46, 46);	            	
	            } else {
		            g.setColor(Color.ORANGE);
		            g.drawRect(xIndex * 50 + 1, yIndex * 50 + 1, 48, 48);
		            g.drawRect(xIndex * 50 + 2, yIndex * 50 + 2, 46, 46);	            	
	            }
			}
		};
		mapPanel.setPreferredSize(new Dimension(50*playingMap.getColumn(), 50*playingMap.getRow()));
		
		jspanel = new JScrollPane(mapPanel);
		jspanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		jspanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jspanel.setPreferredSize(new Dimension(505, 505));
		jspanel.setBorder(Config.border);
		
		JPanel emptyPanel = new JPanel();
		emptyPanel.setLayout(new CardLayout());
		emptyPanel.setPreferredSize(new Dimension(240, 505));
		add(jspanel, BorderLayout.CENTER);
		add(emptyPanel, BorderLayout.EAST);

		log.setDisabledTextColor(Color.BLACK);
		log.setPreferredSize(new Dimension(240, 505));
		log.setBorder(Config.border);
		log.setEnabled(false);
		
		emptyPanel.add(log);
		emptyPanel.add(characterPanel);
		characterPanel.setPreferredSize(new Dimension(240, 505));
		characterPanel.setVisible(false);
		characterPanel.setBorder(Config.border);
		JPanel inventory = new JPanel();
		inventory.setPreferredSize(new Dimension(745, 180));
		inventory.add(inventoryPanel);
		inventoryPanel.setVisible(false);
		add(inventory, BorderLayout.SOUTH);
		inventory.setBorder(Config.border);
		
		mapPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				//TODO
		        int x = e.getX();
		        int y = e.getY();
		        xIndex = x / 50;
		        yIndex = y / 50;
				
		        System.out.println(playingMap.getLocation()[yIndex][xIndex]);
		        if (playingMap.getLocation()[yIndex][xIndex] == 'p'){
		        	isCharacter = true;
		        	characterPanel.setVisible(true);
		        	inventoryPanel.setVisible(true);
		        	log.setVisible(false);
		        	System.out.println("Character selected");
		        	System.out.println(playingMap.getCellsinthemap()[yIndex][xIndex].getContent());
		        	if(selectedCharacter!=null) {
		        		selectedCharacter.deleteObserver(characterPanel);
		        		selectedCharacter.deleteObserver(inventoryPanel);
		        	}
		        	selectedCharacter = (Fighter) playingMap.getCellsinthemap()[yIndex][xIndex].getContent();
		        	selectedCharacter.addObserver(characterPanel);
		        	selectedCharacter.addObserver(inventoryPanel);
		        	
		        	System.out.println("selectedCharacter = " + selectedCharacter);
		        	characterPanel.updateAttributes(selectedCharacter);
		        	characterPanel.iconL.setIcon(Config.NPC_ICON);
		        	inventoryPanel.updateView(selectedCharacter, false);
		        	System.out.println(selectedCharacter.getName());
		        }
				else if(xIndex == yofplayer && yIndex == xofplayer){
		        	isCharacter = true;
		        	characterPanel.setVisible(true);
		        	inventoryPanel.setVisible(true);
		        	log.setVisible(false);
		        	if(selectedCharacter!=null) {
		        		selectedCharacter.deleteObserver(characterPanel);
		        		selectedCharacter.deleteObserver(inventoryPanel);
		        	}
		        	selectedCharacter = fighter;
		        	selectedCharacter.addObserver(characterPanel);
		        	selectedCharacter.addObserver(inventoryPanel);
		        	System.out.println("selectedCharacter = " + selectedCharacter);
		        	characterPanel.updateAttributes(selectedCharacter);
		        	characterPanel.iconL.setIcon(Config.PLAYER_ICON);
		        	inventoryPanel.updateView(selectedCharacter, true);
				}
				else {

		        	System.out.println(selectedCharacter);
		        	isCharacter = false;
		        	characterPanel.setVisible(false);
		        	inventoryPanel.setVisible(false);
		        	log.setVisible(true);
				}
		        
				mapPanel.repaint();
				System.out.println("mapPanel repainted");
			}
		});
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		mapPanel.repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if(xIndex == yofplayer && yIndex == xofplayer && isCharacter == true){
				moveOnMap(xofplayer-1,yofplayer);
				xIndex = yofplayer;
				yIndex = xofplayer;
			} else {
				moveOnMap(xofplayer-1,yofplayer);
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(xIndex == yofplayer && yIndex == xofplayer && isCharacter == true){
				moveOnMap(xofplayer+1,yofplayer);
				xIndex = yofplayer;
				yIndex = xofplayer;
			} else {
				moveOnMap(xofplayer+1,yofplayer);
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(xIndex == yofplayer && yIndex == xofplayer && isCharacter == true){
				moveOnMap(xofplayer,yofplayer-1);
				xIndex = yofplayer;
				yIndex = xofplayer;
			} else {
				moveOnMap(xofplayer,yofplayer-1);
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(xIndex == yofplayer && yIndex == xofplayer && isCharacter == true){
				moveOnMap(xofplayer,yofplayer+1);
				xIndex = yofplayer;
				yIndex = xofplayer;
			} else {
				moveOnMap(xofplayer,yofplayer+1);
			}			
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			if(playingMap.getLocation()[xofplayer][yofplayer]=='d'){
				System.out.println("interact with corpse!!!\n");
				Fighter corpse = (Fighter)playingMap.getCellsinthemap()[xofplayer][yofplayer].getContent();
				this.fighter.lootCorpseItems(corpse);
				System.out.println("loot finish!");
			}
		}
	}

	/**
	 * this function deal with activities that player move and interact with elements of map
	 * @param x	the x-coordinate of the cell which player would like to move
	 * @param y	the y-coordinate of the cell which player would like to move
	 */
	private void moveOnMap(int x, int y) {
		if( x>=playingMap.getRow()|| x<0|| y >= playingMap.getColumn()|| y<0)
			return;
		char temp = playingMap.getLocation()[x][y];
		if(temp =='f'||temp=='d'||temp=='o'||temp=='i'){
			xofplayer=x;
			yofplayer=y;
			mapPanel.repaint();
		}
		else if (temp=='c') {
			Chest chest = (Chest) playingMap.getCellsinthemap()[x][y].getContent();
			System.out.println("1111111111111");
			fighter.openChest(chest);
			if(chest.isEmpty()){
				playingMap.changeLocation(x, y, 'e');
			}
		}
		else if(temp=='p'){
			Fighter npc = (Fighter) playingMap.getCellsinthemap()[x][y].getContent();
			if(!playingMap.getCellsinthemap()[x][y].getIsfriendly()){
				System.out.println("-----------"+npc.getHitPoints());
				fighter.attackCaracter(npc);
				if(!npc.isAlive()){
					playingMap.changeLocation(x, y, 'd');
				}
			}
			else{
				BackpackTrade bpteade = new BackpackTrade(fighter,npc);
				bpteade.setVisible(true);
			}
		}
		
		if(playingMap.getLocation()[xofplayer][yofplayer]=='o'){
			// check whether there is key on player's backpack, if so can interact with exit door, otherwise popup a warm message
			Boolean containKey = false;
			for(BaseItem item:fighter.getBackpack()){
				if(item.getName().equals("key")){
					containKey = true;
					if(JOptionPane.showConfirmDialog(null, "Do you want to entry next map?", "Confirm", JOptionPane.YES_NO_OPTION)==0){
						System.out.println(fighter.getLevel()+"\t\t\t\t\t");
						// create function levelup in Fighter, to increase level and save in the file
						fighter.getBackpack().remove(item);
						fighter.levelUp();
						fighter.deleteObservers();
						Fighter.saveFighter(fighter);
						
						initMapData();
						removeAll();
						initcontent();
//						jspanel.removeAll();
//						jspanel.add(mapPanel);
//						mapPanel.repaint();
//						jspanel.doLayout();
//						jspanel.revalidate();
						break;
					}
				}
			}
			if(!containKey){
				JOptionPane.showMessageDialog(null, "Please find a exit door key!", "Need to Find Exit Door Key", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contains("Inventory")) {
			System.out.println("OPEN Inventory");
			if(inventoryPanel.isVisible()) {
				inventoryPanel.setVisible(false);
				requestFocus();
			} else {
				inventoryPanel.setVisible(true);
				requestFocus();
			}
		}
	}
	
	/**
	 * using to gain play Character's location point
	 * @return location point of play character
	 */
	public Point getPlayerLocation(){
		return new Point(xofplayer,yofplayer);
	}
}


	
