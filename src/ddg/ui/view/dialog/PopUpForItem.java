package ddg.ui.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ddg.Config;
import ddg.model.FighterModel;
import ddg.model.ItemEditorModel;
import ddg.model.item.BaseItem;
import ddg.model.item.Item;
import ddg.ui.view.component.ListEntryCellRenderer;
import ddg.utils.Utils;

/**
 * @author Bo
 * dialog for selecting a item put in the chest
 *
 */
public class PopUpForItem extends JDialog implements ActionListener{
//	private JFrame owner;
	private ItemEditorModel itemsModel;
	private JList itemsList;
	private JTextArea detailOfItem;
	private Item selectedItem;
	
	/**
     * @param owner	the owner frame of this dialog
     * @param title	the title of this dialog
     */
	public PopUpForItem(JFrame owner, String title) {
		super(owner,title);
//		this.owner = owner;
//		Container contofframe = this.getContentPane();
		selectedItem = null;
		initData();
		initView();
	}

	/**
	 * read data from File, and initial data
	 */
	private void initData() {
//		String g = Utils.readFile(Config.ITEM_FILE);
//		this.itemsmodel = Utils.fromJson(g, ItemEditorModel.class);
		this.itemsModel = Utils.readObject(Config.ITEM_FILE, ItemEditorModel.class);
		if (this.itemsModel == null) {
			this.itemsModel = new ItemEditorModel();
		}
		BaseItem key = new BaseItem("key", 1, "wisdom");
		itemsModel.addItem(key);
	}
	
	ListSelectionListener slsnr = new ListSelectionListener() {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (e.getValueIsAdjusting() == false) {
				int index = itemsList.getSelectedIndex();
				if(index >= 0) {
					System.out.println("list select:"+index);
					Item item = itemsModel.getItemByIndex(index);
					detailOfItem.setText(item.getId() + "\n\n +" + item.getBonus() +" "+item.getIncrease());
				}
			}
		}
	};
	
	/**
	 * set view attribute of this View
	 */
	private void initView() {
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
        
		addListView();
		addContentView();
		
		setLocationRelativeTo(null);
		setVisible(true);

	}
	
	/**
	 * initial the listView of this pop up windows
	 */
	private void addListView(){
		JPanel listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT));
		itemsList = new JList(itemsModel.getListModel());
		itemsList.setCellRenderer(new ListEntryCellRenderer());
		itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemsList.addListSelectionListener(slsnr);
        JScrollPane listScrollPane = new JScrollPane(itemsList);
        listScrollPane.setPreferredSize(new Dimension(Config.OPTION_WIDTH, 400));
        listPanel.add(listScrollPane);
        
        getContentPane().add(listPanel, BorderLayout.WEST);
	}
	
	/**
	 * add Content View in this pop up windows
	 */
	private void addContentView(){
		JPanel contentPanel = new JPanel();
		detailOfItem = new JTextArea(5,10);
		detailOfItem.setEditable(false);
		
		JButton bensure = new JButton("Ensure");
		bensure.addActionListener(this);
		
		contentPanel.add(detailOfItem);
		contentPanel.add(bensure);
		
		getContentPane().add(contentPanel);
	}
	
	/**
	 * @return the item which selected by user, then put into the chest
	 */
	public Item getSelecteditem() {
		return selectedItem;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Ensure")){
			int index = itemsList.getSelectedIndex();
			if(index>=0){
				selectedItem = itemsModel.getItemByIndex(itemsList.getSelectedIndex());
				System.out.println(selectedItem.getId());
			}
			JButton button = (JButton)e.getSource();
            SwingUtilities.getWindowAncestor(button).dispose();
		}
	}
}
