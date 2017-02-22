package ddg.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import ddg.Config;
import ddg.view.component.DButton;
/**
 * This class is show campaign editor view
 * 
 * @author 
 * @date Feb 5, 2017
 */
public class CampaignEditor extends JPanel implements ActionListener {

	private ActionListener listener;
	private ArrayList<MapModel> mapData; 
	
	public CampaignEditor(ActionListener a) {
		this.listener = a;
		initData();
		initView();
	}
	
	private void initData() {
		MapModel data = null;//new MapModel();
		mapData = new ArrayList<MapModel>(); 
		for (int i=0; i<20; i++){
			data = new MapModel("map"+i);
			mapData.add(data);
		}
	}
	
	private void initView() {
		BorderLayout l = new BorderLayout();
	    setLayout(l);
		JPanel contentPanel = new JPanel();
		JTextArea ddg = new JTextArea("CAMPAIGN");
		ddg.setEditable(false);
		contentPanel.add(ddg);
	    add(contentPanel, BorderLayout.CENTER);
	    addNewLevel(contentPanel);
	    addOption();
	}
	int i = 0;
	private void addNewLevel(JPanel contentPanel) {
		DButton addBtn = new DButton("ADD", this);
		contentPanel.add(addBtn);
		ActionListener l = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(i==20)
					return;
//			    addNewLevel(contentPanel);
				JTextArea map = new JTextArea(mapData.get(i).mapName);
				contentPanel.add(map);
			    contentPanel.doLayout();
			    i++;
			}
			
		}; 
		addBtn.removeActionListener(this);
		addBtn.addActionListener(l);
	}
	
	private void addOption() {
		JPanel optionPanel = new JPanel();
	    optionPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT));
	    optionPanel.setBorder(Config.border);
	    JTextArea optionTitle = new JTextArea("OPTION");
	    optionTitle.setEditable(false);
	    
	    DButton saveBtn = new DButton("SAVE", this);
	    saveBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    DButton backBtn = new DButton("BACK", this);
	    backBtn.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	    optionPanel.add(optionTitle);
	    optionPanel.add(saveBtn);
	    optionPanel.add(backBtn);
	    add(optionPanel, BorderLayout.EAST);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("BACK")) {
			e = new ActionEvent(e.getSource(), e.getID(), "CAMPAIGN-BACK");
		}
		listener.actionPerformed(e);
	}
	
	public class MapModel {
		public String mapName;

		public MapModel(String mapName) {
			super();
			this.mapName = mapName;
		}
		
	}
}
