package ddg.ui.view;

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
import ddg.model.Fighter;
import ddg.model.entity.BaseCampaign;
import ddg.model.entity.Chest;
import ddg.model.entity.Game;
import ddg.model.entity.Item;
import ddg.model.entity.Map;
import ddg.strategy.AgressiveStrategy;
import ddg.strategy.FriendlyStrategy;
import ddg.strategy.HumanStrategy;
import ddg.strategy.IStrategy.TurnCallback;
import ddg.ui.TurnDriven;
import ddg.ui.view.component.DButton;
import ddg.ui.view.dialog.BackpackTrade;
import ddg.ui.view.dialog.DDGaming;

/**
 * 
 * MapPanelInGame include the map of game, character detail and inventory detail
 * @author Bo
 * @date Mar 12, 2017
 * 
 */
public class MapPanelInGame extends JPanel implements Observer, KeyListener, ActionListener, TurnCallback{
	private JScrollPane jspanel;
	private JPanel mapPanel;
	private Game game;
	private Fighter selectedCharacter;

	private CharacterPanel characterPanel = new CharacterPanel(this);
	private InventoryPanel inventoryPanel = new InventoryPanel();
    private int xIndex = -1;
    private int yIndex = -1; 
	protected boolean isCharacter = false;
    
	ImageIcon floor = new ImageIcon("res/floor.png");
	ImageIcon chest = new ImageIcon("res/chest.png");
	ImageIcon emptychest = new ImageIcon("res/emptychest.png");
	ImageIcon wall = new ImageIcon("res/wall.png");
	ImageIcon indoor = new ImageIcon("res/indoor.png");
	ImageIcon outdoor = new ImageIcon("res/outdoor.png");
	ImageIcon playcharacter = new ImageIcon("res/playcharacter.png");
	ImageIcon mainplayer = new ImageIcon("res/Mainplayer.png");
	ImageIcon deadnpc = new ImageIcon("res/deadnpc.png");
	JTextArea log = new JTextArea();
	private TurnDriven turnDriven;
	private TurnCallback mCallBack;
	/**
	 * Constructor
	 * @param fighter the play character who is chosen by user
	 * @param campaign the Campaign which user would like to play
	 */
	public MapPanelInGame(Fighter fighter, BaseCampaign campaign){
		this.game = new Game(fighter,campaign);
		turnDriven = new TurnDriven();
		setLayout(new BorderLayout());
		setFocusable(true);
		
		initMapData();
		initcontent();
		initStrategy();
	}

	public MapPanelInGame(Game game){
		this.game = game;
		turnDriven = new TurnDriven();
		setLayout(new BorderLayout());
		setFocusable(true);
		
		initMapData();
		initcontent();
		initStrategy();
	}
	protected void initStrategy() {
		Map playingMap = game.getPlayingmap();
		
		this.game.getFighter().setStrategy(new HumanStrategy() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void moveCells(TurnCallback cb) {
				mCallBack = cb;
				System.out.println(game.getFighter().getName() + " may moveCells, when finished, click again.");
			}

			@Override
			protected void attack(TurnCallback cb) {
				mCallBack = cb;
				System.out.println(game.getFighter().getName() + " may attack, when finished, click again.");
			}

			@Override
			protected void interaction(TurnCallback cb) {
				mCallBack = cb;
				System.out.println(game.getFighter().getName() + " may interaction, when finished, click again.");
			}
			
		});
		
//		turnDriven.addFighter(this.game.getFighter());

		
		for(int i=0;i< playingMap.getRow();i++){
            for(int j=0;j< playingMap.getColumn();j++){
            	if(playingMap.getLocation()[i][j]=='p'){
            		Fighter fighter = (Fighter)playingMap.getCellsinthemap()[i][j].getContent();
            		if(playingMap.getCellsinthemap()[i][j].getIsfriendly()){
            			fighter.setStrategy(new FriendlyStrategy(game,i,j));
            			turnDriven.addFighter(fighter);
            		}
            		else{
            			fighter.setStrategy(new AgressiveStrategy(game,i,j));
            			turnDriven.addFighter(fighter);
            		}
            	}
            }
		}
		
		
		//should add the other fighter
		
//		boolean friendly = playingMap.getCellsinthemap()[yIndex][xIndex].getIsfriendly();
//    	if(friendly) {
//    	} else {
//    		selectedCharacter.setStrategy(new AgressiveStrategy() {
//				private static final long serialVersionUID = 1L;
//
//				@Override
//				protected void searchPlayer(TurnCallback cb) {
//					// TODO Auto-generated method stub
//					System.out.println(selectedCharacter.getName() + " may searchPlayer, when finished, callback.");
//					cb.finish();
//				}
//    			
//    		});
//    		turnDriven.addFighter(selectedCharacter);
//    	}
		
//		turnDriven.startTurn();
	}
	
//	/**
//	 * read data from file, and initialize all data on the Panel
//	 */
//	private void initdata() {
//		
//			
//			
//		
//	}
	
	/**
	 * initial data of playing map and do adaptedLevel() function
	 */
	private void initMapData(){
		if(game.getPlayingmap()!=null){
		    xIndex = -1;
		    yIndex = -1;
		    game.getPlayingmap().addObserver(this);
		    if (xIndex != game.getYofplayer() || yIndex != game.getXofplayer()){
		    	isCharacter = false;
		    }
		    if (isCharacter == false){
		    	characterPanel.setVisible(false);
		    	inventoryPanel.setVisible(false);
		    }
		    
		    log.setText("Enter game, Welcome!\n");
			log.append("Current Level:"+game.getFighter().getLevel()+"\n");
			log.append("Current Map:"+game.getPlayingmap().getName()+"\n");
		}
		else{
			log.append("You finish this Campaign, Congratulation!!!");
			JOptionPane.showMessageDialog(null, "You finish this Campaign, Congratulation!!!");
			JDialog ddgamedialog = (JDialog) SwingUtilities.getWindowAncestor(mapPanel);
			ddgamedialog.dispose();
			DDGaming newddgamedialog = new DDGaming();
			newddgamedialog.popShow(null, "Gaming");
		}
		System.out.println("initMapDataFinish!!");
	}
	
	/**
	 * initialize the view of this panel
	 */
	private void initcontent() {
		Map playingMap = game.getPlayingmap();
		mapPanel = new JPanel(){
			private static final long serialVersionUID = -8627231216589776568L;
			
			@Override  
	        public void paint(Graphics g) {
				System.out.println(xIndex+"=============="+yIndex);
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
								if(((Fighter)playingMap.getCellsinthemap()[i][j].getContent()).isAlive())
									g.drawImage(playcharacter.getImage(), j*50, i*50, 50, 50, null);
								else
									g.drawImage(deadnpc.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
							if (playingMap.getLocation()[i][j] == 'd'){
								g.drawImage(deadnpc.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
	                    }
	                }
	            }
	            g.drawImage(mainplayer.getImage(), game.getYofplayer()*50, game.getXofplayer()*50, null);
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
		            System.out.println(xIndex+"1=============="+yIndex);
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
		
		JPanel logPanel = new JPanel(new BorderLayout());
		logPanel.setPreferredSize(new Dimension(240, 505));
		
		add(logPanel,BorderLayout.WEST);
		add(jspanel, BorderLayout.CENTER);
		add(emptyPanel, BorderLayout.EAST);
		
		log.setDisabledTextColor(Color.BLACK);
		log.setPreferredSize(new Dimension(240, 505));
		log.setBorder(Config.border);
		log.setEnabled(false);
		DButton savebutton = new DButton("SAVE", this);
		logPanel.add(log, BorderLayout.CENTER);
		logPanel.add(savebutton, BorderLayout.SOUTH);
		
//		emptyPanel.add(log);
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
				else if(xIndex == game.getYofplayer() && yIndex == game.getXofplayer()){
		        	isCharacter = true;
		        	characterPanel.setVisible(true);
		        	inventoryPanel.setVisible(true);
		        	if(selectedCharacter!=null) {
		        		selectedCharacter.deleteObserver(characterPanel);
		        		selectedCharacter.deleteObserver(inventoryPanel);
		        	}
		        	selectedCharacter = game.getFighter();
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
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if(xIndex == game.getYofplayer() && yIndex == game.getXofplayer() && isCharacter == true){
				moveOnMap(game.getXofplayer()-1,game.getYofplayer());
				if(isCharacter){
					xIndex = game.getYofplayer();
					yIndex = game.getXofplayer();
				}
			} else {
				moveOnMap(game.getXofplayer()-1,game.getYofplayer());
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(xIndex == game.getYofplayer() && yIndex == game.getXofplayer() && isCharacter == true){
				moveOnMap(game.getXofplayer()+1,game.getYofplayer());
				if(isCharacter){
					xIndex = game.getYofplayer();
					yIndex = game.getXofplayer();
				}
			} else {
				moveOnMap(game.getXofplayer()+1,game.getYofplayer());
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(xIndex == game.getYofplayer() && yIndex == game.getXofplayer() && isCharacter == true){
				moveOnMap(game.getXofplayer(),game.getYofplayer()-1);
				if(isCharacter){
					xIndex = game.getYofplayer();
					yIndex = game.getXofplayer();
				}
			} else {
				moveOnMap(game.getXofplayer(),game.getYofplayer()-1);
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(xIndex == game.getYofplayer() && yIndex == game.getXofplayer() && isCharacter == true){
				moveOnMap(game.getXofplayer(),game.getYofplayer()+1);
				if(isCharacter){
					xIndex = game.getYofplayer();
					yIndex = game.getXofplayer();
				}
			} else {
				moveOnMap(game.getXofplayer(),game.getYofplayer()+1);
			}			
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			if(game.getPlayingmap().getLocation()[game.getXofplayer()][game.getYofplayer()]=='d'){
				System.out.println("interact with corpse!!!\n");
				Fighter corpse = (Fighter)game.getPlayingmap().getCellsinthemap()[game.getXofplayer()][game.getYofplayer()].getContent();
				this.game.getFighter().lootCorpseItems(corpse);
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
		Map playingMap = game.getPlayingmap();
		Fighter fighter = game.getFighter();
		if( x>=playingMap.getRow()|| x<0|| y >= playingMap.getColumn()|| y<0)
			return;
		char temp = playingMap.getLocation()[x][y];
		if(temp =='f'||temp=='d'){
			game.setXofplayer(x);
			game.setYofplayer(y);
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
		
		else if(temp=='o'){ //playingMap.getLocation()[game.getXofplayer()][game.getYofplayer()]=='o'
			// check whether there is key on player's backpack, if so can interact with exit door, otherwise popup a warm message
			Boolean containKey = false;
			for(Item item:fighter.getBackpack()){
				if(item.getName().equals("key")){
					containKey = true;
					if(JOptionPane.showConfirmDialog(null, "Do you want to entry next map?", "Confirm", JOptionPane.YES_NO_OPTION)==0){
						System.out.println(fighter.getLevel()+"\t\t\t\t\t");
						// create function levelup in Fighter, to increase level and save in the file
						fighter.getBackpack().remove(item);
						fighter.levelUp();
						fighter.deleteObservers();
						Fighter.saveFighter(fighter);
						game.nextMap();
						initMapData();
						initStrategy();
						removeAll();
						System.out.println(xIndex+"2=============="+yIndex);
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
				repaint();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Turn+++++++++++++++++++++++++++++++++"+turnDriven.getFighters().size());
		for (Fighter fighter : turnDriven.getFighters()) {
			if(fighter.isAlive())
				fighter.turn(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contains("Inventory")) {
			System.out.println("OPEN Inventory");
			//////TEMP
			if(mCallBack!=null) {
				mCallBack.finish();
				return;
			}
			//////TEMP
			if(inventoryPanel.isVisible()) {
				inventoryPanel.setVisible(false);
				requestFocus();
			} else {
				inventoryPanel.setVisible(true);
				requestFocus();
			}
		}
		if(e.getActionCommand().contains("SAVE")){
			System.out.println("SAVE BUTTON");
			
			prepareForSaveGame();
			System.out.println(game.getFighter().countObservers());
			if(Game.saveGame(this.game)){
				JOptionPane.showMessageDialog(null, "Save Success!!", "Save Success!!", JOptionPane.INFORMATION_MESSAGE);
			}
			game.getPlayingmap().addObserver(this);
			if(isCharacter&&selectedCharacter!=null){
				selectedCharacter.addObserver(characterPanel);
				selectedCharacter.addObserver(inventoryPanel);
			}
			requestFocus();
			
		}
	}
	
	private void prepareForSaveGame() {
		System.out.println(this.getKeyListeners());
		game.getPlayingmap().deleteObservers();
		if(isCharacter&&selectedCharacter!=null)
			selectedCharacter.deleteObservers();
		for (Fighter fighter : turnDriven.getFighters()) {
			fighter.setStrategy(null);
		}
		System.out.println(this.getKeyListeners());
	}

	/**
	 * using to gain play Character's location point
	 * @return location point of play character
	 */
	public Point getPlayerLocation(){
		return new Point(game.getXofplayer(),game.getYofplayer());
	}

	@Override
	public void finish() {
		JDialog mapSizeFrame = (JDialog) SwingUtilities.getWindowAncestor(this);
		mapSizeFrame.dispose();
	}
}


	
