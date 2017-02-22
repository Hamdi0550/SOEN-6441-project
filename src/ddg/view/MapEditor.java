package ddg.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ImageCapabilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import ddg.Config;
import ddg.view.component.DButton;
import model.Map;
/**
 * This class is show map editor view
 * 
 * @author Du Zhen, Bo, Qin yi
 * @date Feb 5, 2017
 */
public class MapEditor extends JPanel implements ActionListener {

	private ActionListener listener;
	// set the size of map. it could be changed if click the S/M/L button  
	public static int  MAP_SIZE = 10;
	static char[][] maplocation;
	static ImageIcon[][] mapicons;
	
	JPanel optionPanel;
	JPanel contentPanel;
	JPanel mapPanel;
	JPanel mapiconPanel;
	JComboBox<ImageIcon> options_of_element_on_cell;
	ImageIcon floor = new ImageIcon("floor.png");
	
	public MapEditor(ActionListener a) {
		this.listener = a;
		
		
		optionPanel = new JPanel();
		contentPanel = new JPanel();
		
		initView();
		
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
				
				
				ImageIcon icon = (ImageIcon)options_of_element_on_cell.getSelectedItem();
				char num = icon.toString().charAt(0);
				System.out.println(x+"<>"+y);
				System.out.println(num);
				
				
				mapicons[y][x] = icon;
				maplocation[y][x] = num;
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
		options_of_element_on_cell.addItem(new ImageIcon("chest1.png"));
		options_of_element_on_cell.addItem(new ImageIcon("indoor.png"));
		options_of_element_on_cell.setLocation(0, 0);
		iconpanel.add(options_of_element_on_cell, BorderLayout.NORTH);
		iconpanel.setBorder(Config.border);
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(jspanel);
		contentPanel.add(iconpanel);
		add(contentPanel, BorderLayout.WEST);
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
		if(e.getActionCommand().equals("SAVE")){
			Map map = new Map(maplocation);
			JFileChooser addChooser = new JFileChooser();
			addChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int returnval = addChooser.showDialog(mapPanel, "selection of file");
//			{
//				String str = addChooser.getSelectedFile().getPath();
//				targetfolder.setText(str);
//			}
			Map.savemap(map);
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
}
