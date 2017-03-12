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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ddg.Config;
import ddg.campaign.entity.BaseCampaign;
import ddg.map.entity.Cell;
import ddg.map.entity.Map;
import ddg.model.Fighter;
import ddg.model.MapEditorModel;

public class MapPartInGame extends JPanel implements Observer, KeyListener{
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
	ImageIcon wall = new ImageIcon("wall.png");
	ImageIcon indoor = new ImageIcon("indoor.png");
	ImageIcon outdoor = new ImageIcon("outdoor.png");
	ImageIcon playcharacter = new ImageIcon("playcharacter.png");
	ImageIcon Mainplayer = new ImageIcon("Mainplayer.png");
	public MapPartInGame(Fighter fighter, BaseCampaign campaign){
		this.campaign = campaign;
		this.fighter = fighter;
		setLayout(new FlowLayout());
		
		
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
			ArrayList<Map> mapsofcampaignlist = new ArrayList<Map>();
			for(String nameofmap : campaign.getMaps()){
				for(Map map : mapsmodel.getMaps()){
					if(map.getName().equals(nameofmap)){
						mapsofcampaignlist.add(map);
					}
					
					if(!mapsofcampaignlist.contains(map)){
						System.out.println("!!!!!!!!!!! do not have such map  !!!!!!!!!!!!!!!!!!!");
					}
				}
			}
			
			this.mapsofcampaign = mapsofcampaignlist.listIterator();
			this.playingMap = this.mapsofcampaign.next();
			
			for(int i=0;i<playingMap.getRow();i++){
				for(int j=0;j<playingMap.getColumn();j++){
					if(playingMap.getLocation()[i][j] == 'i'){
						if(i>0 && playingMap.getLocation()[i-1][j]=='f'){
							playingMap.getLocation()[i-1][j]='M';
							playingMap.getCellsinthemap()[i-1][j] = new Cell<Fighter>(fighter);
						}
						else if(i<playingMap.getRow()-1 && playingMap.getLocation()[i+1][j]=='f'){
							playingMap.getLocation()[i+1][j]='M';
							playingMap.getCellsinthemap()[i+1][j] = new Cell<Fighter>(fighter);
						}
						else if(j>0 && playingMap.getLocation()[i][j-1]=='f'){
							playingMap.getLocation()[i][j-1]='M';
							playingMap.getCellsinthemap()[i][j-1] = new Cell<Fighter>(fighter);
						}
						else if(j<playingMap.getColumn()-1 && playingMap.getLocation()[i][j+1]=='f'){
							playingMap.getLocation()[i][j+1]='M';
							playingMap.getCellsinthemap()[i][j+1] = new Cell<Fighter>(fighter);
						}
					}
				}
			}
			
		}catch(IOException i){
			i.printStackTrace();
		}catch(ClassNotFoundException c){
			c.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
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
							if (playingMap.getLocation()[i][j] == 'o'){
								g.drawImage(outdoor.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
							if (playingMap.getLocation()[i][j] == 'p'){
								g.drawImage(playcharacter.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
							if (playingMap.getLocation()[i][j] == 'M'){
								g.drawImage(Mainplayer.getImage(), j*50, i*50, 50, 50, null);
								xofplayer = i;
								yofplayer = j;
							    continue;}
	                    }
	                }
	            }
	            for(int i=0; i<playingMap.getRow(); i++){
	            	g.drawLine(0, i*50, playingMap.getColumn()*50, i*50);
	            }
	            for(int i=0; i<playingMap.getColumn(); i++){
	            	g.drawLine(i*50, 0, i*50, 50*playingMap.getRow());
	            }
			}
		};
		mapPanel.setPreferredSize(new Dimension(50*playingMap.getColumn(), 50*playingMap.getRow()));
//		mapPanel.setPreferredSize(new Dimension(500, 500));
		
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
		System.out.println("????????????????????");
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("????????????????????");
			playingMap.moveOnTheMap(xofplayer, yofplayer, xofplayer, yofplayer+1, 'M');
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}


	
