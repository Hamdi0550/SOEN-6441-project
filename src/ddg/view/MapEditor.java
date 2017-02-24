package ddg.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import ddg.Config;
import ddg.item.entity.BaseItem;
import ddg.model.ItemEditorModel;
import ddg.model.MapEditorModel;
import ddg.utils.Utils;
import ddg.view.component.DButton;
import model.Cell;
import model.Map;
/**
 * This class is show map editor view
 * 
 * @author Du Zhen, Bo, Qin yi
 * @date Feb 5, 2017
 */
public class MapEditor extends JPanel implements ActionListener {

	private ActionListener listener;
	private MapEditorModel mapsmodel;
	// set the size of map. it could be changed if click the S/M/L button  
	public static int  MAP_SIZE = 10;
	char[][] maplocation;
	ImageIcon[][] mapicons;
	Cell[][] mapcells;
	java.util.Map<String, String> usedcell = new HashMap<>();
	
	ArrayList<Map> listforsavemap = new ArrayList<Map>();
	boolean hasvaildpath;
	
	JPanel optionPanel;
	JPanel contentPanel;
	JPanel mapPanel;
	JPanel mapiconPanel;
	JComboBox<ImageIcon> options_of_element_on_cell;
	ImageIcon floor = new ImageIcon("floor.png");
	
	public MapEditor(ActionListener a) {
		this.listener = a;
		this.hasvaildpath = false;
		
		optionPanel = new JPanel();
		contentPanel = new JPanel();
		
		initView();
		
	}

	private void initData(){
		String g = Utils.readFile(Config.MAP_FILE);
		this.mapsmodel = Utils.fromJson(g, MapEditorModel.class);
		if (this.mapsmodel == null) {
			this.mapsmodel = new MapEditorModel();
		}
	}
	
	private void initView() {
	    setLayout(new BorderLayout());
	    System.out.println(MAP_SIZE);
	    
	    addContentPanel();
	    addOption();
	}
	/**
	 * This method is used for add Content panel
	 * 
	 */
	private void addContentPanel(){
		maplocation = new char[MAP_SIZE][MAP_SIZE];
		for (int i = 0;i<MAP_SIZE; i++){
			for (int j = 0;j<MAP_SIZE; j++)
				maplocation[i][j] = 'f';
		}
		
		mapicons = new ImageIcon[MAP_SIZE][MAP_SIZE];
		mapcells = new Cell[MAP_SIZE][MAP_SIZE];
		
		System.out.println(mapicons.length);
		mapPanel = new JPanel(){
			@Override  
	        public void paint(Graphics g) {  
	            super.paint(g);  
	            for(int i=0;i< MAP_SIZE;i++){
	                for(int j=0;j< MAP_SIZE;j++){
	                	//draw background
	                	g.drawImage(floor.getImage(), j*50, i*50, 50, 50, null);
	                	
	                    //draw Icons on the map
	                    if(mapicons[i][j]!=null){  
	                        g.drawImage(mapicons[i][j].getImage(), j*50, i*50, 50, 50, null);  
	                    }
	                }
	            }
	            for(int i=0; i<MAP_SIZE; i++){
	            	g.drawLine(i*50, 0, i*50, 50*MAP_SIZE);
	            	g.drawLine(0, i*50, MAP_SIZE*50, i*50);
	            }
			}
		};
		mapPanel.setPreferredSize(new Dimension(50*MAP_SIZE, 50*MAP_SIZE));
//		mapPanel.setBorder(Config.border);
		
//		for (int i = 0; i < MAP_SIZE*MAP_SIZE; i++) {
//			ImageIcon icon0 = new ImageIcon("002.png");
//			JLabel image = new JLabel(icon0);
//			image.setBorder(BorderFactory.createRaisedSoftBevelBorder());
//			mapPanel.add(image);
//		}
		
		mapPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				int x = e.getX()/50;
				int y = e.getY()/50;
//				JOptionPane.showinput
				
				ImageIcon icon = (ImageIcon)options_of_element_on_cell.getSelectedItem();
				char num = icon.toString().charAt(0);
				System.out.println(x+"<>"+y);
				System.out.println(num);
				
				if(num == 'c'){
//					itemPopUp();
//					JPopupMenu popup = new JPopupMenu();
//					JMenuItem item;
//					popup.add(item = new JMenuItem("!!!!!\n\n"+"22222",new ImageIcon("floor.png")));
//					item.setHorizontalTextPosition(JMenuItem.RIGHT);
//					popup.add(item = new JMenuItem("!!!!!\n\n"+"22222",new ImageIcon("tree.png")));
//					item.setHorizontalTextPosition(JMenuItem.RIGHT);
//					popup.show(mapPanel, e.getX(), e.getY());
					String bigList[] = new String[30];

				    for (int i = 0; i < bigList.length; i++) {
				      bigList[i] = Integer.toString(i);
				    }
//				    JOptionPane.showinput
				    JOptionPane.showInputDialog(mapPanel, "Pick a printer", "Input", JOptionPane.QUESTION_MESSAGE,
				        null, bigList, "Titan");
				}
				
				mapicons[y][x] = icon;
				maplocation[y][x] = num;
				addInCell(num,y,x);
				
				mapPanel.repaint();
				for (int i = 0;i<MAP_SIZE; i++){
					for (int j = 0;j<MAP_SIZE; j++)
						System.out.print(maplocation[i][j]);
					System.out.print("\n");
				}
				
			}
		});
		
		JScrollPane jspanel = new JScrollPane(mapPanel);
		jspanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		jspanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jspanel.setPreferredSize(new Dimension(500, 500));
		jspanel.setBorder(Config.border);
		
		JPanel iconpanel = new JPanel();
		iconpanel.setPreferredSize(new Dimension(90, 500));
		options_of_element_on_cell = new JComboBox<ImageIcon>();  
		options_of_element_on_cell.addItem(new ImageIcon("floor.png"));  
		options_of_element_on_cell.addItem(new ImageIcon("tree.png"));
		options_of_element_on_cell.addItem(new ImageIcon("indoor.png"));
		options_of_element_on_cell.addItem(new ImageIcon("chest1.png"));
		options_of_element_on_cell.addItem(new ImageIcon("outdoor.png"));
		options_of_element_on_cell.setLocation(0, 0);
		iconpanel.add(options_of_element_on_cell, BorderLayout.NORTH);
		iconpanel.setBorder(Config.border);
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(jspanel);
		contentPanel.add(iconpanel);
		add(contentPanel, BorderLayout.WEST);
	}
	public void itemPopUp() {
		// TODO Auto-generated method stub
		JFrame rootframe = (JFrame) SwingUtilities.getWindowAncestor(this);
		PopUpForItem itempopup = new PopUpForItem(rootframe);
		itempopup.setVisible(true);
		rootframe.setEnabled(false);
	}

	/**
	 * This method is used for add option panel
	 * 
	 */
	private void addOption() {
		
	    optionPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT));
	    optionPanel.setBorder(Config.border);
	    JTextArea optionTitle = new JTextArea("OPTION");
	    optionTitle.setEditable(false);
	    DButton sizeBtn = new DButton("S/M/L", this);
	    sizeBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    DButton clearBtn = new DButton("CLEAR", this);
	    clearBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    DButton validateBtn = new DButton("VALIDATE", this);
	    validateBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    DButton saveBtn = new DButton("SAVE", this);
	    saveBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    DButton backBtn = new DButton("BACK", this);
	    backBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    DButton createBtn = new DButton("CREATE", this);
	    createBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    optionPanel.add(optionTitle);
	    optionPanel.add(createBtn);
	    optionPanel.add(sizeBtn);
	    optionPanel.add(clearBtn);
	    optionPanel.add(validateBtn);
	    optionPanel.add(saveBtn);
	    optionPanel.add(backBtn);
	    add(optionPanel, BorderLayout.EAST);
	}
	
	/**
	 * 
	 * @param charoficon When draw a icon on the map, the char of icon will be recorded in to the Cells array if the icon is character or chest
	 */
	public void addInCell(char charoficon, int x, int y){
		if(charoficon == 'c'){
			System.out.println("!!!!!!!!!!!!!!!!!!!!!");
			BaseItem item = new BaseItem("Helmet");
			mapcells[x][y] = new Cell(item);
			System.out.println(((BaseItem)mapcells[x][y].getContent()).getName());
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("CREATE")){
			JFileChooser addChooser = new JFileChooser(".");
			addChooser.setApproveButtonText("OK");
			addChooser.setDialogTitle("OPEN a current map");
			int result = addChooser.showOpenDialog(mapPanel);
			if (result == JFileChooser.APPROVE_OPTION){
				File file = addChooser.getSelectedFile();
			}else if(result == JFileChooser.CANCEL_OPTION){
				JLabel label = new JLabel();
				label.setText("No file be choosed");
				//
			}
		}
		if(e.getActionCommand().equals("BACK")) {
			e = new ActionEvent(e.getSource(), e.getID(), "MAP-BACK");
		}
		if(e.getActionCommand().equals("S/M/L")){
			if(MAP_SIZE >= 50){
				MAP_SIZE = 10;
			}
			else
				MAP_SIZE += 20;
			//this.removeAll();
			contentPanel.removeAll();
			contentPanel.revalidate();
			addContentPanel();		
		}
		if(e.getActionCommand().equals("VALIDATE")){
			if(checkValidation()){
				JOptionPane.showMessageDialog(null, "<html>Vaild Success!!!</html>");

			}
			else
				JOptionPane.showMessageDialog(null, "<html>The map is invalid <br> it must have a indoor, a outdoor and Feasiable Path</html>","Invalid",JOptionPane.ERROR_MESSAGE);
		}
		
		if(e.getActionCommand().equals("SAVE")){
			if(checkValidation()){
				Map map = new Map(maplocation, mapcells);
				listforsavemap.add(map);
				listforsavemap.add(map);
				Map.savemap(listforsavemap);
			}
			else
				JOptionPane.showMessageDialog(null, "<html>The map is invalid <br> it must have a indoor, a outdoor and Feasiable Path</html>","Invalid",JOptionPane.ERROR_MESSAGE);
//			JFileChooser addChooser = new JFileChooser();
//			addChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//			int returnval = addChooser.showDialog(mapPanel, "selection of file");
//			{
//				String str = addChooser.getSelectedFile().getPath();
//				targetfolder.setText(str);
//			}
			
		}
		if(e.getActionCommand().equals("CLEAR")){
			for (int i = 0;i<MAP_SIZE; i++){
				for (int j = 0;j<MAP_SIZE; j++)
					maplocation[i][j] = 'f';
			}
			mapicons = new ImageIcon[MAP_SIZE][MAP_SIZE];
			mapPanel.repaint();
		}
		
		listener.actionPerformed(e);
	}

	public boolean checkValidation(){
		if(hasEntryDoor() && hasExitDoor()){
			return true;
		}
		return false;
	}

	public void hasValidPath(int i, int j) {
		if(maplocation[i][j] == 'o')
			hasvaildpath=true;
		else{
			usedcell.put(i+","+j, "i*j");
			if( j>0 && maplocation[i][j-1]!='t'){
				if(usedcell.get(i+","+ (j-1) )== null)
					hasValidPath(i, j-1);
			}
			
			if(i>0 && maplocation[i-1][j]!='t'){
				if(usedcell.get(i-1 +","+ j)== null)
					hasValidPath(i-1, j);
			}
			
			if(j<Config.MAP_SIZE-1 && maplocation[i][j+1]!='t'){
				if(usedcell.get(i +","+ (j+1))== null)
					hasValidPath(i, j+1);
			}
			
			if(i<Config.MAP_SIZE-1 && maplocation[i+1][j]!='t'){
				if(usedcell.get((i+1) +","+ j)== null)
					hasValidPath(i+1, j);
			}
		}
	}
	/**
	 * 
	 * @param maplocation detail location of map.
	 * @return true if there is a indoor(Entry door) in the location of map 
	 */
	public boolean hasEntryDoor() {
		for(int i=0;i<Config.MAP_SIZE;i++){
			for(int j=0;j<Config.MAP_SIZE;j++){
				if(maplocation[i][j] == 'i'){
					usedcell.clear();
					hasValidPath(i,j);
				}
			}
		}
		
		if(hasvaildpath){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param maplocation detail location of map.
	 * @return true if there is a outdoor(Exit door) in the location of map 
	 */
	public  static boolean hasExitDoor() {
		
		return true;
	}

}
