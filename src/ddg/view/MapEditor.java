package ddg.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import ddg.Config;
import ddg.view.component.OButton;
/**
 * This class is show map editor view
 * 
 * @author 
 * @date Feb 5, 2017
 */
public class MapEditor extends JPanel implements ActionListener {

	private ActionListener listener;
	
	JPanel optionPanel;
	JPanel contentPanel;
	JPanel mapPanel;
	JComboBox<ImageIcon> options_of_element_on_cell;
	public MapEditor(ActionListener a) {
		this.listener = a;
		optionPanel = new JPanel();
		contentPanel = new JPanel();
		
		initView();
	}

	private void initView() {
	    setLayout(new BorderLayout());
	    
		GridLayout layout = new GridLayout();
		mapPanel = new JPanel();
		mapPanel.setPreferredSize(new Dimension(50*Config.MAP_SIZE, 50*Config.MAP_SIZE));
		mapPanel.setLayout(layout);
		layout.setColumns(Config.MAP_SIZE); 
		layout.setRows(Config.MAP_SIZE);
		for (int i = 0; i < Config.MAP_SIZE*Config.MAP_SIZE; i++) {
			ImageIcon icon0 = new ImageIcon("002.png");
			JLabel image = new JLabel(icon0);
			image.setBorder(BorderFactory.createRaisedSoftBevelBorder());
//			image.setBackground(Color.BLUE);
//			JTextField t = new JTextField(i + "");
//			t.setName(i + "");
//			t.setBorder(null);
//			t.setBackground(Color.BLUE);
//			t.setSize(new Dimension(100, 100));
//			t.addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					System.out.println(e.getActionCommand());
//					// observer.stateChanged(model);
//				}
//			});
//			contentPanel.add(i + "", t);
			mapPanel.add(image);
		}
		JScrollPane jspanel = new JScrollPane(mapPanel);
		jspanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		jspanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jspanel.setPreferredSize(new Dimension(500, 500));
//		jspanel.setBorder(Config.border);
		
		JPanel iconpanel = new JPanel();
		iconpanel.setPreferredSize(new Dimension(90, 500));
		options_of_element_on_cell = new JComboBox<ImageIcon>();  
		options_of_element_on_cell.addItem(new ImageIcon("002.png"));  
		options_of_element_on_cell.addItem(new ImageIcon("101.png"));  
		options_of_element_on_cell.setLocation(0, 0);
		iconpanel.add(options_of_element_on_cell, BorderLayout.NORTH);
//		iconpanel.setBorder(Config.border);
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(jspanel);
		contentPanel.add(iconpanel);
		add(contentPanel, BorderLayout.WEST);

		addOption();
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
	    OButton sizeBtn = new OButton("S/M/L", this);
	    sizeBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    OButton clearBtn = new OButton("CLEAR", this);
	    clearBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    OButton validateBtn = new OButton("VALIDATE", this);
	    validateBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    OButton saveBtn = new OButton("SAVE", this);
	    saveBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    OButton backBtn = new OButton("BACK", this);
	    backBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    optionPanel.add(optionTitle);
	    optionPanel.add(sizeBtn);
	    optionPanel.add(clearBtn);
	    optionPanel.add(validateBtn);
	    optionPanel.add(saveBtn);
	    optionPanel.add(backBtn);
	    add(optionPanel, BorderLayout.EAST);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("BACK")) {
			e = new ActionEvent(e.getSource(), e.getID(), "MAP-BACK");
		}
		listener.actionPerformed(e);
	}
}
