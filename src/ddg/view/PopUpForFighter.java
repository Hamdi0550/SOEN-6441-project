package ddg.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ddg.Config;
import ddg.item.entity.BaseItem;
import ddg.model.Fighter;
import ddg.model.FighterEditorModel;
import ddg.model.FighterModel;
import ddg.model.ItemEditorModel;
import ddg.utils.Utils;
import ddg.view.component.ListEntryCellRenderer;

public class PopUpForFighter extends JDialog implements ActionListener{
	private JList fighterslist;
	private FighterEditorModel fightersmodel;
	
	private Fighter selectedfighter;
	
	/**
	 * @param owner	the owner frame of this dialog
     * @param title	the title of this dialog
	 */
	public PopUpForFighter(JFrame owner, String title) {
		super(owner,title);
		selectedfighter = null;
		
		initData();
		initView();
	}

	private void initData() {
		// TODO Auto-generated method stub
		String g = Utils.readFile(Config.CHARACTOR_FILE);
		this.fightersmodel = Utils.fromJson(g, FighterEditorModel.class);
		if (this.fightersmodel == null) {
			this.fightersmodel = new FighterEditorModel();
		}
	}
	
	ListSelectionListener slsnr = new ListSelectionListener() {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (e.getValueIsAdjusting() == false) {
				int index = fighterslist.getSelectedIndex();
				if(index >= 0) {
					System.out.println("list select:"+index);
					Fighter fighter = fightersmodel.getItemByIndex(index);
//					detailofitem.setText(item.getId() + "\n\n +" + item.getBonus() +" "+item.getIncreate());
				}
			}
		}
	};

	private void initView() {
		// TODO Auto-generated method stub
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
        
		addListView();
		addContentView();
		
		setLocationRelativeTo(null);
        setVisible(true);
		
		
	}

	private void addListView() {
		JPanel listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.OPTION_HEIGHT));
		fighterslist = new JList(fightersmodel.getListModel());
		fighterslist.setCellRenderer(new ListEntryCellRenderer());
		fighterslist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fighterslist.addListSelectionListener(slsnr);
        JScrollPane listScrollPane = new JScrollPane(fighterslist);
        listScrollPane.setPreferredSize(new Dimension(Config.OPTION_WIDTH, 400));
        listPanel.add(listScrollPane);
        
        getContentPane().add(listPanel, BorderLayout.WEST);
	}
	
	private void addContentView() {
		// TODO Auto-generated method stub
		JPanel contentPanel = new JPanel();
		JPanel fighterPanel = new JPanel();
		JPanel abilityscoresPanel = new JPanel();
		JPanel equipPanel = new JPanel();
		
		
		abilityscoresPanel.setBackground(Color.RED);
		fighterPanel.add(abilityscoresPanel);
		fighterPanel.setBackground(Color.BLUE);;
		
		
		equipPanel.setBackground(Color.pink);
		JLabel jb = new JLabel();
		jb.setBorder(BorderFactory.createLineBorder(Color.black));
		jb.setIcon(new ImageIcon("outdoor.png"));
		jb.setVerticalTextPosition(SwingConstants.BOTTOM);
		jb.setHorizontalTextPosition(SwingConstants.CENTER);
		jb.setText("lalallalala");
		equipPanel.add(jb);
		
//		contentPanel.setPreferredSize(new Dimension(200, 200));
		contentPanel.add(fighterPanel,BorderLayout.CENTER);
		contentPanel.add(equipPanel,BorderLayout.SOUTH);
		
		getContentPane().add(contentPanel);
	}
	/**
	 * @return fighter which user select
	 */
	public Fighter getSelectedFighter() {
		return selectedfighter;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Ensure")){
			int index = fighterslist.getSelectedIndex();
			if(index>=0)
				selectedfighter = fightersmodel.getItemByIndex(fighterslist.getSelectedIndex());
			System.out.println(selectedfighter.getName());
			JButton button = (JButton)e.getSource();
            SwingUtilities.getWindowAncestor(button).dispose();
		}
	}
}
