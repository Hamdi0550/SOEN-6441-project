package ddg.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ddg.Config;
import ddg.item.entity.BaseItem;
import ddg.model.ItemEditorModel;
import ddg.utils.Utils;
import ddg.view.component.ListEntryCellRenderer;

public class PopUpForItem extends JFrame implements ActionListener{
	private JFrame owner;
	private ItemEditorModel itemsmodel;
	private JList itemslist;
	private JTextArea detailofitem;
	private BaseItem selecteditem;
	
	
	public PopUpForItem(JFrame owner) {
		this.owner = owner;
//		Container contofframe = this.getContentPane();
		selecteditem = null;
		initData();
		initView();
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				owner.setEnabled(true);
				owner.setVisible(true);	
			}
		});
	}

	private void initData() {
		String g = Utils.readFile(Config.ITEM_FILE);
		this.itemsmodel = Utils.fromJson(g, ItemEditorModel.class);
		if (this.itemsmodel == null) {
			this.itemsmodel = new ItemEditorModel();
		}
		
	}
	
	ListSelectionListener slsnr = new ListSelectionListener() {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (e.getValueIsAdjusting() == false) {
				int index = itemslist.getSelectedIndex();
				if(index >= 0) {
					System.out.println("list select:"+index);
					BaseItem item = itemsmodel.getItemByIndex(index);
					detailofitem.setText(item.getId() + "\n\n +" + item.getBonus() +" "+item.getIncreate());
				}
			}
		}
	};
	
	private void initView() {
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Chosing Item for Chest");
		
		addListView();
		addContentView();
		
		
	}
	
	private void addListView(){
		JPanel listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT));
		itemslist = new JList<>(itemsmodel.getListModel());
		itemslist.setCellRenderer(new ListEntryCellRenderer());
		itemslist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemslist.addListSelectionListener(slsnr);
		itemslist.setVisibleRowCount(15);
        JScrollPane listScrollPane = new JScrollPane(itemslist);
        listScrollPane.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT-3*Config.BTN_HEIGHT));
        listPanel.add(listScrollPane);
        
        add(listPanel, BorderLayout.WEST);
	}
	
	private void addContentView(){
		JPanel contentPanel = new JPanel();
		detailofitem = new JTextArea(5,10);
		detailofitem.setEditable(false);
		
		JButton bensure = new JButton("Ensure");
		bensure.addActionListener(this);
		
		contentPanel.add(detailofitem);
		contentPanel.add(bensure);
		
		add(contentPanel);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Ensure")){
			int index = itemslist.getSelectedIndex();
			if(index>=0)
				selecteditem = itemsmodel.getItemByIndex(itemslist.getSelectedIndex());
			System.out.println(selecteditem.getId());
		}
	}
}
