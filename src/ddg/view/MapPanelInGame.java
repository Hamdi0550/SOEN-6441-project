package ddg.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	private ListIterator<Map> mapsofcampaign;
	private int xofplayer;
	private int yofplayer;
	
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
		setLayout(new FlowLayout());
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
	            
	            for(int i=0; i<playingMap.getRow(); i++){
	            	g.drawLine(0, i*50, playingMap.getColumn()*50, i*50);
	            }
	            for(int i=0; i<playingMap.getColumn(); i++){
	            	g.drawLine(i*50, 0, i*50, 50*playingMap.getRow());
	            }
			}
		};
		mapPanel.setPreferredSize(new Dimension(50*playingMap.getColumn(), 50*playingMap.getRow()));
		
		jspanel = new JScrollPane(mapPanel);
		jspanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		jspanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jspanel.setPreferredSize(new Dimension(505, 505));
		jspanel.setBorder(Config.border);
		
		add(jspanel);
	}

	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		mapPanel.repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		String line1="1111"+e.getKeyChar();
		System.out.println(line1);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moveOnMap(xofplayer-1,yofplayer);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moveOnMap(xofplayer+1,yofplayer);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveOnMap(xofplayer,yofplayer-1);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveOnMap(xofplayer,yofplayer+1);
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
		// TODO Auto-generated method stub
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
			System.out.println(playingMap.getCellsinthemap()[x][y]);
			Fighter npc = (Fighter) playingMap.getCellsinthemap()[x][y].getContent();
			if(!playingMap.getCellsinthemap()[x][y].getIsfriendly()){
				fighter.attackCaracter(npc);
				if(!npc.isAlive()){
					playingMap.changeLocation(x, y, 'd');//dead npc
				}
			}
			else{
//				fighter.interactWithNpc(npc);
			}
		}
		
		if(playingMap.getLocation()[xofplayer][yofplayer]=='o'){
			// check whether there is key on player's backpack, if so can interact with exit door, otherwise popup a warm message
			
			if(JOptionPane.showConfirmDialog(null, "Do you want to entry next map?", "Confirm", JOptionPane.YES_NO_OPTION)==0){
				System.out.println(fighter.getLevel()+"\t\t\t\t\t");
				
				// create function levelup in Fighter, to increase level and save in the file
				fighter.levelUp();
				fighter.setLevel(fighter.getLevel()+1);
				System.out.println(fighter.getLevel()+"\tnew\t\t\t\t");
				
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


	
