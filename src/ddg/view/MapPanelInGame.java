package ddg.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import ddg.Config;
import ddg.campaign.entity.BaseCampaign;
import ddg.map.entity.Cell;
import ddg.map.entity.Chest;
import ddg.map.entity.Map;
import ddg.model.Fighter;
import ddg.model.MapEditorModel;
import ddg.ui.DDGaming;

/**
 * 
 * @author Bo
 * @date Mar 12, 2017
 * 
 */
/**
 * 
 * @author Bo
 * @date Mar 13, 2017
 * 
 */
public class MapPanelInGame extends JPanel implements Observer, KeyListener{
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
	
	public MapPanelInGame(Fighter fighter, BaseCampaign campaign){
		this.campaign = campaign;
		this.fighter = fighter;
		setLayout(new BorderLayout());
		setFocusable(true);
		
		initdata();
		this.playingMap.addObserver(this);
		initcontent();
	}
	
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
						mapsofcampaignlist.add(map);
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
		    }
			this.playingMap = this.mapsofcampaign.next();
			this.playingMap.adaptedLevel(fighter.getLevel());
			
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
			JOptionPane.showMessageDialog(null, "You finish this Campaign, Congratulation!!!");
			JDialog ddgamedialog = (JDialog) SwingUtilities.getWindowAncestor(mapPanel);
			ddgamedialog.dispose();
			DDGaming newddgamedialog = new DDGaming();
			newddgamedialog.popShow(null, "Gaming");
		}
	}
	
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
		emptyPanel.setPreferredSize(new Dimension(270, 500));
		add(jspanel, BorderLayout.CENTER);
		add(emptyPanel, BorderLayout.EAST);

		emptyPanel.add(characterPanel);
		characterPanel.setPreferredSize(new Dimension(270, 500));
		characterPanel.setVisible(false);
		
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
		        	System.out.println("Character selected");
		        	System.out.println(playingMap.getCellsinthemap()[yIndex][xIndex].getContent());
		        	selectedCharacter = (Fighter) playingMap.getCellsinthemap()[yIndex][xIndex].getContent();

		        	System.out.println("selectedCharacter = " + selectedCharacter);
		        	characterPanel.updateAttributes(selectedCharacter);
		        	characterPanel.iconL.setIcon(Config.NPC_ICON);
		        	System.out.println(selectedCharacter.getName());
		        }
				else if(xIndex == yofplayer && yIndex == xofplayer){
		        	isCharacter = true;
		        	characterPanel.setVisible(true);
		        	selectedCharacter = fighter;
		        	System.out.println("selectedCharacter = " + selectedCharacter);
		        	characterPanel.updateAttributes(selectedCharacter);
		        	characterPanel.iconL.setIcon(Config.PLAYER_ICON);
				}
				else {

		        	System.out.println(selectedCharacter);
		        	isCharacter = false;
		        	characterPanel.setVisible(false);
				}
		        
				mapPanel.repaint();
				System.out.println("mapPanel repainted");
			}
		});
		
	}

	/**
	 * 
	 * @return
	 */
	public Fighter getSelectedCharacter() {
		return selectedCharacter;
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
//				fighter.interactWithNpc(npc);
				BackpackTrade.createAndShowGUI(null);
			}
		}
		
		if(playingMap.getLocation()[xofplayer][yofplayer]=='o'){
			// check whether there is key on player's backpack, if so can interact with exit door, otherwise popup a warm message
			
			if(JOptionPane.showConfirmDialog(null, "Do you want to entry next map?", "Confirm", JOptionPane.YES_NO_OPTION)==0){
				
				// create function levelup in Fighter, to increase level and save in the file
				fighter.levelUp();
				
				initMapData();
				mapPanel.repaint();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}


	
