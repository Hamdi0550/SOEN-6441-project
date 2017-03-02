package ddg.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ddg.Config;
import ddg.item.entity.BaseItem;
import ddg.model.Fighter;
import ddg.model.MapEditorModel;
import ddg.ui.DDGameMain;
import ddg.utils.Utils;
import ddg.view.component.DButton;
import ddg.view.component.ListEntryCellRenderer;
import model.Cell;
import model.Map;
/**
 * This class is show map editor view
 * 
 * @author Du Zhen, Bo, Qin yi
 * @date Feb 5, 2017
 */
public class MapEditor extends JPanel implements ActionListener, ListSelectionListener {

	private ActionListener listener;
	private MapEditorModel mapsmodel;
	private Map selectedmap;
	// set the size of map. it could be changed if click the S/M/L button
	private JList list;

	boolean hasvaildpath;
	
	JPanel optionPanel;
	JPanel contentPanel;
	JPanel mapPanel;
	JPanel mapiconPanel;
	JComboBox<ImageIcon> optionsofelementoncell;
	ImageIcon floor = new ImageIcon("floor.png");
	ImageIcon chest = new ImageIcon("chest.png");
	ImageIcon tree = new ImageIcon("tree.png");
	ImageIcon indoor = new ImageIcon("indoor.png");
	ImageIcon outdoor = new ImageIcon("outdoor.png");
	ImageIcon playcharacter = new ImageIcon("playcharacter.png");
	ImageIcon key = new ImageIcon("key.png");
	
	public MapEditor(ActionListener a) {
		this.listener = a;
		this.hasvaildpath = false;
		selectedmap=null;
		optionPanel = new JPanel();
		contentPanel = new JPanel();
		initData();
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
	    addListView();
	    list.setSelectedIndex(0);
	    addContentPanel();
	    addOption();
	}

	private JPanel addListView(){
		JPanel listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.OPTION_HEIGHT/3));
		DefaultListModel l = mapsmodel.getMapListModel();
		list = new JList(l);
		list.setCellRenderer(new ListEntryCellRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(5);
		JScrollPane mapScrollPane = new JScrollPane(list);
		mapScrollPane.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.OPTION_HEIGHT/3));
		listPanel.add(mapScrollPane);
		return listPanel;
	}
	/**
	 * This method is used for add Content panel
	 *
	 */
	private void addContentPanel(){
		contentPanel.removeAll();
		contentPanel.revalidate();
		System.out.println(selectedmap.getRow()+" "+ selectedmap.getColumn());
		mapPanel = new JPanel(){
			@Override  
	        public void paint(Graphics g) {  
	            super.paint(g);  
	            for(int i=0;i< selectedmap.getRow();i++){
	                for(int j=0;j< selectedmap.getColumn();j++){
	                	//draw background
	                	g.drawImage(floor.getImage(), j*50, i*50, 50, 50, null);
	                	
	                    if(selectedmap != null){
	                    	if(selectedmap.getLocation()[i][j] == 'f' ){
							    g.drawImage(floor.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
							if (selectedmap.getLocation()[i][j] == 't'){
								g.drawImage(tree.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
							if (selectedmap.getLocation()[i][j] == 'i'){
								g.drawImage(indoor.getImage(), j*50, i*50, 50, 50, null);
								continue;}
							if (selectedmap.getLocation()[i][j] == 'c'){
								g.drawImage(chest.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
							if (selectedmap.getLocation()[i][j] == 'o'){
								g.drawImage(outdoor.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
							if (selectedmap.getLocation()[i][j] == 'k'){
								g.drawImage(key.getImage(), j*50, i*50, 50, 50, null);
							    continue;}
	                    }
	                }
	            }
	            for(int i=0; i<selectedmap.getRow(); i++){
	            	g.drawLine(0, i*50, selectedmap.getColumn()*50, i*50);
	            }
	            for(int i=0; i<selectedmap.getColumn(); i++){
	            	g.drawLine(i*50, 0, i*50, 50*selectedmap.getRow());
	            }
			}
		};
		mapPanel.setPreferredSize(new Dimension(50*selectedmap.getColumn(), 50*selectedmap.getRow()));
		
		mapPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				int x = e.getX()/50;
				int y = e.getY()/50;
				
				ImageIcon icon = (ImageIcon)optionsofelementoncell.getSelectedItem();
				char num = icon.toString().charAt(0);
				System.out.println(x+"<>"+y);
				System.out.println(num);
				
				if(num == 'c'){
					JFrame rootframe = (JFrame) SwingUtilities.getWindowAncestor(mapPanel);
					PopUpForItem itempopup = new PopUpForItem(rootframe,"Select Item for Chect!");
					if(itempopup.getSelecteditem() != null){
						addItemInCell(itempopup.getSelecteditem(), y, x);
						selectedmap.changeLocation(y, x, 'c');
					}
				}
				else if(num =='p'){
					JFrame rootframe = (JFrame) SwingUtilities.getWindowAncestor(mapPanel);
//					PopUpForFighter fighterpopup = new PopUpForFighter(rootframe,"Select Character!");
					CharacterSelection fighterpopup = new CharacterSelection(rootframe, "CS from map editor");
					fighterpopup.pack();
					fighterpopup.setVisible(true);
					System.out.println();
					
					//Here you got the character to put on the map
					Fighter obtainedFighter = ((DDGameMain) rootframe).getSelectedFighter();
					System.out.println("Got a fighter with level: " + obtainedFighter.getLevel() + " strength: " + obtainedFighter.getStrength() + " dexterity: " + obtainedFighter.getDexterity());
				}
				else{
					selectedmap.changeLocation(y, x, num);
				}
				
				mapPanel.repaint();
				for (int i = 0;i<selectedmap.getRow(); i++){
					for (int j = 0;j<selectedmap.getColumn(); j++)
						System.out.print(selectedmap.getLocation()[i][j]);
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
		optionsofelementoncell = new JComboBox<ImageIcon>();  
		optionsofelementoncell.addItem(floor);  
		optionsofelementoncell.addItem(tree);
		optionsofelementoncell.addItem(indoor);
		optionsofelementoncell.addItem(chest);
		optionsofelementoncell.addItem(outdoor);
		optionsofelementoncell.addItem(playcharacter);
		optionsofelementoncell.addItem(key);
		optionsofelementoncell.setLocation(0, 0);
		iconpanel.add(optionsofelementoncell, BorderLayout.NORTH);
		iconpanel.setBorder(Config.border);
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(jspanel);
		contentPanel.add(iconpanel);
		add(contentPanel, BorderLayout.WEST);
	}


	/**
	 * this function create a popup dialog which allow user to chose the item. 
	 * It may be used with add chest on the map.
	 */
	public void itemPopUp() {
		
		JFrame rootframe = (JFrame) SwingUtilities.getWindowAncestor(this);
		PopUpForItem itempopup = new PopUpForItem(rootframe,"Select Item for Chect!");
		if(itempopup.getSelecteditem() != null){
			
		}
		
	}
	
	/**
	 * create a dialog to ask the map's size when user create a new map
	 */
	public void mapCreatePopUp(){
		JFrame mapSizeFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		PopUpForCreateNewMap popUpForCreateNewMap = new PopUpForCreateNewMap(mapSizeFrame, "Select Map Size");
		if(popUpForCreateNewMap.getRow()>=10 && popUpForCreateNewMap.getColumn()>=10)
		{
			mapsmodel.add(new Map("Map"+(mapsmodel.getMaps().size() + 1),popUpForCreateNewMap.getRow(),popUpForCreateNewMap.getColumn()));
			DefaultListModel l = mapsmodel.getMapListModel();
			list.setModel(l);
			list.setSelectedIndex(l.size()-1);
			list.ensureIndexIsVisible(l.size()-1);
		}
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
		JPanel listPanel = addListView();
		clearBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    optionPanel.add(optionTitle);
	    optionPanel.add(createBtn);
	    optionPanel.add(clearBtn);
	    optionPanel.add(validateBtn);
	    optionPanel.add(saveBtn);
	    optionPanel.add(backBtn);
	    optionPanel.add(listPanel);
	    add(optionPanel, BorderLayout.EAST);
	}
	
	/**
	 * 
	 * @param item When draw a icon on the map, the char of icon will be recorded in to the Cells array if the icon is character or chest
	 * @param x the row-coordinate on the map, ensure the location of the cell
	 * @param y the column-coordinate on the map, ensure the location of the cell
	 * 
	 */
	public void addItemInCell(BaseItem item, int x, int y){
		selectedmap.changeCellsinthemap(x, y, new Cell(item));
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("BACK")) {
			e = new ActionEvent(e.getSource(), e.getID(), "MAP-BACK");
		}
		if (e.getActionCommand().equals("CREATE")){
			mapCreatePopUp();
			mapPanel.repaint();
		}
// 		if(e.getActionCommand().equals("S/M/L")){
// 			if(MAP_SIZE >= 50){
// 				MAP_SIZE = 10;
// 			}
// 			else
// 				MAP_SIZE += 20;
// 			//this.removeAll();
// 			contentPanel.removeAll();
// 			contentPanel.revalidate();
// 			addContentPanel();
// 		}
		if(e.getActionCommand().equals("VALIDATE")){
			if(selectedmap.checkValidation(selectedmap)){
				JOptionPane.showMessageDialog(null, "<html>Vaild Success!!!</html>");

			}
			else
				JOptionPane.showMessageDialog(null, "<html>The map is invalid <br> it must have:<br> a indoor, a outdoor, a key <br>and Feasiable Path</html>","Invalid",JOptionPane.ERROR_MESSAGE);
		}
		
		if(e.getActionCommand().equals("SAVE")){
			if(selectedmap.checkValidation(selectedmap)){
				Map.savemap(mapsmodel);
			}
			else
				JOptionPane.showMessageDialog(null, "<html>The map is invalid <br> it must have:<br> a indoor, a outdoor, a key <br>and Feasiable Path</html>","Invalid",JOptionPane.ERROR_MESSAGE);
		}
		if(e.getActionCommand().equals("CLEAR")){
			char maplocation[][] = selectedmap.getLocation();
			for (int i = 0;i<selectedmap.getRow(); i++){
				for (int j = 0;j<selectedmap.getColumn(); j++)
					maplocation[i][j] = 'f';
			}
			mapPanel.repaint();
		}

		listener.actionPerformed(e);
	}

	

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			int index = list.getSelectedIndex();
			if(index >= 0) {
				System.out.println("list select:"+index);
				Map map = mapsmodel.getMapByIndex(index);
				
				selectedmap = map;
	 			contentPanel.removeAll();
	 			contentPanel.revalidate();
	 			addContentPanel();
			}
		}
	}
}
