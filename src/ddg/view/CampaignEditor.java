package ddg.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.DefaultListModel;
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
import ddg.campaign.entity.BaseCampaign;
import ddg.model.CampaignEditorModel;
import ddg.model.MapEditorModel;
import ddg.utils.Utils;
import ddg.view.component.DButton;
import ddg.view.component.DComboBox;
import ddg.view.component.DComboBox.DItemListener;
import ddg.view.component.ListEntryCellRenderer;

/**
 * This class is show campaign editor view
 * 
 * @author Hamzah Hamdi
 * @date Feb 5, 2017
 */
public class CampaignEditor extends JPanel implements ActionListener, ListSelectionListener {

	private ActionListener listener;
	private CampaignEditorModel model;
	private JList list;
	private JPanel contentPanel;
	private MapEditorModel mapData;

	/**
	 * This is the constructor
	 * @param a action listener
	 */
	public CampaignEditor(ActionListener a) {
		this.listener = a;
		initData();
		initView();
	}

	/**
	 * 
	 * This method for init data 
	 *
	 */
	private void initData() {
//		String m = Utils.readFile(Config.MAP_FILE);
//		this.mapData = Utils.fromJson(m, MapEditorModel.class);
		try
	      {
	         FileInputStream fileIn = new FileInputStream(Config.MAP_FILE);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         this.mapData = (MapEditorModel) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace(); 
	         this.mapData = new MapEditorModel();
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         this.mapData = new MapEditorModel();
	      }
		
		if (this.mapData == null) {
			this.mapData = new MapEditorModel();
		}

		String g = Utils.readFile(Config.CAMPAIGN_FILE);
		this.model = Utils.fromJson(g, CampaignEditorModel.class);
		if (this.model == null) {
			this.model = new CampaignEditorModel();
		}
	}

	/**
	 * 
	 * This method is for init view
	 *
	 */
	private void initView() {
		BorderLayout l = new BorderLayout();
		setLayout(l);
		FlowLayout f = new FlowLayout(FlowLayout.LEFT);
		this.contentPanel = new JPanel();
		this.contentPanel.setPreferredSize(new Dimension(Config.CENTER_WIDTH - 26, Config.CENTER_HEIGHT * 2));
		this.contentPanel.setLayout(f);

		addListView();
		addEditorView();
		addOptionView();
		list.setSelectedIndex(0);
	}

	/**
	 * 
	 * This method is for adding list view on the lift side
	 *
	 */
	private void addListView() {
		JPanel listPanel = new JPanel();
		BorderLayout layout = new BorderLayout();
		listPanel.setLayout(layout);
		listPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT));
		DefaultListModel l = model.getListModel();
		list = new JList(l);
		list.setCellRenderer(new ListEntryCellRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(15);
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane
				.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT - 3 * Config.BTN_HEIGHT));
		listPanel.add(listScrollPane);

		DButton addBtn = new DButton("ADD", this);
		listPanel.add(addBtn, BorderLayout.SOUTH);

		add(listPanel, BorderLayout.WEST);

	}

	/**
	 * 
	 * This method is to edit view
	 *
	 */
	private void addEditorView() {
		JTextArea ddg = new JTextArea("CAMPAIGN");
		ddg.setEditable(false);
		this.contentPanel.add(ddg);
		JPanel centerPanel = new JPanel();

		DButton addBtn = new DButton("+", this);
		addBtn.setPreferredSize(new Dimension(Config.CENTER_WIDTH - 10, Config.BTN_HEIGHT));
		add(addBtn, BorderLayout.NORTH);
		ActionListener l = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				if (index >= 0) {
					BaseCampaign c = model.getItemByIndex(index);
					DComboBox combo = new DComboBox<String>(c == null ? 0 + "" : c.getMaps().size() + "");
					combo.setPreferredSize(new Dimension(Config.BTN_WIDTH / 2, Config.BTN_HEIGHT));
					for (int i = 0; i < mapData.getMaps().size(); i++) {
						combo.addItem(mapData.getMapByIndex(i).getName());
					}
					c.addMapToCampaign(mapData.getMapByIndex(0).getName());
					combo.addDItemListener(new DItemListener() {

						@Override
						public void itemChanged(ItemEvent e, String name) {
							if (e.getStateChange() == ItemEvent.SELECTED) {
								int index = Integer.valueOf(name);
								c.setMapByIndex(index, combo.getSelectedItem().toString());

							}
						}
					});
					contentPanel.add(combo);
					contentPanel.repaint();
					contentPanel.doLayout();
				}
			}
		};
		addBtn.removeActionListener(this);
		addBtn.addActionListener(l);
		BorderLayout layout = new BorderLayout();
		centerPanel.setLayout(layout);
		centerPanel.add(addBtn, BorderLayout.SOUTH);
		JScrollPane listScrollPane = new JScrollPane(this.contentPanel);
		listScrollPane.setBorder(null);
		centerPanel.add(listScrollPane);
		add(centerPanel, BorderLayout.CENTER);
		loadCampaignView();
	}

	/**
	 * 
	 * This method is to load the campaign from the file 
	 *
	 */
	private void loadCampaignView() {
		contentPanel.removeAll();
		int index = list.getSelectedIndex();
		if (index >= 0) {
			BaseCampaign c = this.model.getItemByIndex(index);
			int i = 0;
			for (String s : c.getMaps()) {
				DComboBox combo = new DComboBox<String>((i++) + "");
				combo.setPreferredSize(new Dimension(Config.BTN_WIDTH / 2, Config.BTN_HEIGHT));
				for (int j = 0; j < mapData.getMaps().size(); j++) {
					combo.addItem(mapData.getMapByIndex(j).getName());
				}
				combo.setSelectedItem(s);
				combo.addDItemListener(new DItemListener() {

					@Override
					public void itemChanged(ItemEvent e, String name) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							int index = Integer.valueOf(name);
							c.setMapByIndex(index, combo.getSelectedItem().toString());

						}
					}

				});
				this.contentPanel.add(combo);
			}

		}
		contentPanel.repaint();
		contentPanel.doLayout();
	}

	/**
	 * 
	 * This method is add options on right side 
	 *
	 */
	private void addOptionView() {
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
		if (e.getActionCommand().equals("BACK")) {
			e = new ActionEvent(e.getSource(), e.getID(), "CAMPAIGN-BACK");
		} else if (e.getActionCommand().equals("SAVE")) {
			String g = Utils.toJson(this.model);
			Utils.save2File(Config.CAMPAIGN_FILE, g);
			JOptionPane.showMessageDialog(this, "Save Success!");
		} else if (e.getActionCommand().equals("ADD")) {
			BaseCampaign i = new BaseCampaign(BaseCampaign.NAME);
			model.addCampaign(i);
			DefaultListModel l = model.getListModel();
			list.setModel(l);
			list.setSelectedIndex(l.size() - 1);
			list.ensureIndexIsVisible(l.size() - 1);
		}
		listener.actionPerformed(e);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			int index = list.getSelectedIndex();
			if (index >= 0) {
				System.out.println("list select:" + index);
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						loadCampaignView();
					}

				});
			}
		}
	}
}
