package ddg.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ddg.Config;
import ddg.model.Fighter;
import ddg.model.MapEditorModel;
import ddg.model.entity.BaseItem;
import ddg.model.entity.Cell;
import ddg.model.entity.Chest;
import ddg.model.entity.Map;
import ddg.ui.view.component.DButton;
import ddg.ui.view.component.ListEntryCellRenderer;
import ddg.ui.view.dialog.PopUpForCreateNewMap;
import ddg.ui.view.dialog.PopUpForFighter;
import ddg.ui.view.dialog.PopUpForItem;
/**
 * This class is show map editor view
 * 
 * @author Du Zhen, Bo, Qin yi
 * @date Feb 5, 2017
 */
public class MapEditor extends JPanel implements ActionListener, ListSelectionListener {

	private ActionListener listener;
	private MapEditorModel mapsmodel;
	private Map selectedmap;// = new Map();
	// set the size of map. it could be changed if click the S/M/L button
	private JList list;

	boolean hasvaildpath;
	
	JPanel optionPanel;
	JPanel contentPanel;
	JPanel mapPanel;
	JPanel mapiconPanel;
	JComboBox<ImageIcon> optionsofelementoncell;
	ImageIcon floor = new ImageIcon("res/floor.png");
	ImageIcon chest = new ImageIcon("res/chest.png");
	ImageIcon wall = new ImageIcon("res/wall.png");
	ImageIcon indoor = new ImageIcon("res/indoor.png");
	ImageIcon outdoor = new ImageIcon("res/outdoor.png");
	ImageIcon playcharacter = new ImageIcon("res/playcharacter.png");
	
	public MapEditor(ActionListener a) {
		this.listener = a;
		this.hasvaildpath = false;
//		selectedmap=null;
		optionPanel = new JPanel();
		contentPanel = new JPanel();
		initData();
		initView();
		
	}

	private void initData(){
//		String g = Utils.readFile(Config.MAP_FILE);
//		this.mapsmodel = Utils.fromJson(g, MapEditorModel.class);
		try
	      {
	         FileInputStream fileIn = new FileInputStream(Config.MAP_FILE);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         mapsmodel = (MapEditorModel) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c)
	      {
	         c.printStackTrace();
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
		
		if (this.mapsmodel == null) {
			this.mapsmodel = new MapEditorModel();
			this.mapsmodel.add(new Map());
		}
	}
	
	private void initView() {
	    setLayout(new BorderLayout());
	    addListView();
	    list.setSelectedIndex(0);
	    addContentPanel();
	    addOption();
	    list.setSelectedIndex(0);
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
							if (selectedmap.getLocation()[i][j] == 'w'){
								g.drawImage(wall.getImage(), j*50, i*50, 50, 50, null);
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
							if (selectedmap.getLocation()[i][j] == 'p'){
								g.drawImage(playcharacter.getImage(), j*50, i*50, 50, 50, null);
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
				char num = icon.toString().charAt(4);
				System.out.println(x+"<>"+y);
				System.out.println(num);
				
				if(num == 'c'){
					JFrame rootframe = (JFrame) SwingUtilities.getWindowAncestor(mapPanel);
					PopUpForItem itempopup = new PopUpForItem(rootframe,"Select Item for Chect!");
					if(itempopup.getSelecteditem() != null){
						addChestInCell(itempopup.getSelecteditem(), y, x);
						selectedmap.changeLocation(y, x, 'c');
					}
				}
				else if(num =='p'){
					JFrame rootframe = (JFrame) SwingUtilities.getWindowAncestor(mapPanel);
					PopUpForFighter fighterpopup = new PopUpForFighter(rootframe,"Select Character!");
					
					fighterpopup.pack();
					fighterpopup.setVisible(true);
					
					if(fighterpopup.getFighter()!=null){
						selectedmap.changeCellsinthemap(y, x, new Cell<Fighter>(fighterpopup.getFighter(),fighterpopup.getIsfriendly()));
						selectedmap.changeLocation(y, x, num);
					}
				}
				else{
					selectedmap.changeLocation(y, x, num);
					selectedmap.changeCellsinthemap(y, x, null);
				}
				
				mapPanel.repaint();
				for (int i = 0;i<selectedmap.getRow(); i++){
					for (int j = 0;j<selectedmap.getColumn(); j++)
						System.out.print(selectedmap.getLocation()[i][j]);
					System.out.print("\n");
				}
				
				for (int i = 0;i<selectedmap.getRow(); i++){
					for (int j = 0;j<selectedmap.getColumn(); j++)
						System.out.print(selectedmap.getCellsinthemap()[i][j]);
					System.out.print("\n");
				}
				
			}
		});
		
		JScrollPane jspanel = new JScrollPane(mapPanel);
		jspanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		jspanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jspanel.setPreferredSize(new Dimension(505, 505));
		jspanel.setBorder(Config.border);
		
		JPanel iconpanel = new JPanel();
		iconpanel.setPreferredSize(new Dimension(90, 500));
		optionsofelementoncell = new JComboBox<ImageIcon>();  
		optionsofelementoncell.addItem(floor);  
		optionsofelementoncell.addItem(wall);
		optionsofelementoncell.addItem(indoor);
		optionsofelementoncell.addItem(chest);
		optionsofelementoncell.addItem(outdoor);
		optionsofelementoncell.addItem(playcharacter);
		optionsofelementoncell.setLocation(0, 0);
		iconpanel.add(optionsofelementoncell, BorderLayout.NORTH);
		iconpanel.setBorder(Config.border);
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(jspanel);
		contentPanel.add(iconpanel);
		add(contentPanel, BorderLayout.WEST);
	}


	/**
	 * create a dialog to ask the map's size when user create a new map
	 */
	public void mapCreatePopUp(){
		JFrame mapSizeFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		PopUpForCreateNewMap popupforcreatenewmap = new PopUpForCreateNewMap(mapSizeFrame, "Select Map Size");
		if(popupforcreatenewmap.getRow()>=10 && popupforcreatenewmap.getColumn()>=10)
		{
			mapsmodel.add(new Map("Map"+(mapsmodel.getMaps().size() + 1),popupforcreatenewmap.getRow(),popupforcreatenewmap.getColumn()));
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
	 * @param item item which save in the chest and then put on the map
	 * @param x the row-coordinate on the map, ensure the location of the cell
	 * @param y the column-coordinate on the map, ensure the location of the cell
	 * 
	 */
	public void addChestInCell(BaseItem item, int x, int y){
		
		selectedmap.changeCellsinthemap(x, y, new Cell<Chest>(new Chest(item)));
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
				JOptionPane.showMessageDialog(this, "Save Success!");
			}
			else
				JOptionPane.showMessageDialog(null, "<html>The map is invalid <br> it must have:<br> a indoor, a outdoor, a key <br>and Feasiable Path</html>","Invalid",JOptionPane.ERROR_MESSAGE);
		}
		if(e.getActionCommand().equals("CLEAR")){
			char maplocation[][] = selectedmap.getLocation();
			for (int i = 0;i<selectedmap.getRow(); i++){
				for (int j = 0;j<selectedmap.getColumn(); j++){
					maplocation[i][j] = 'f';
					selectedmap.getCellsinthemap()[i][j]=null;
				}
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
