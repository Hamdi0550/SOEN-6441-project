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
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
import ddg.view.component.DButton;
import ddg.view.component.ListEntryCellRenderer;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

public class PopUpForFighter2 extends JDialog implements ActionListener{
	private JList fighterslist;
	private FighterEditorModel fightersmodel;
	private JCheckBox checkisfriendly;
	

	private Fighter selectedfighter;
	private boolean isfriendly;
	
	public boolean getIsfriendly() {
		return isfriendly;
	}

	private JTextField nameTextF = new JTextField(4);
	private JTextField levelTextF = new JTextField(4);
	private JTextField strengthTextF = new JTextField(4);
	private JTextField dexterityTextF = new JTextField(4);
	private JTextField constitutionTextF = new JTextField(4);
	private JTextField intelligenceTextF = new JTextField(4);
	private JTextField wisdomTextF = new JTextField(4);
	private JTextField charismaTextF = new JTextField(4);
		    
		    JLabel nameModiferL = new JLabel("  ");
		    JLabel strengthModiferL = new JLabel("  ");
		    JLabel dexModiferL = new JLabel("  ");
		    JLabel conModiferL = new JLabel("  ");
		    JLabel intelliModiferL = new JLabel("  ");
		    JLabel wisModiferL = new JLabel("  ");
		    JLabel chaModiferL = new JLabel("  ");
	/**
	 * @param owner	the owner frame of this dialog
     * @param title	the title of this dialog
	 */
	public PopUpForFighter2(JFrame owner, String title) {
		super(owner,title);
		selectedfighter = null;
		isfriendly = false;
		
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
					nameTextF.setText(""+fighter.getName());
					levelTextF.setText(""+fighter.getLevel());
					strengthTextF.setText(""+fighter.getStrength());
					dexterityTextF.setText(""+fighter.getDexterity());
//					constitutionTextF.setText(""+fighter.getConstitution());
//					intelligenceTextF.setText(""+fighter.getIntelligence());
//					wisdomTextF.setText(""+fighter.getWisdom());
//					charismaTextF.setText(""+fighter.getCharisma());
				}
			}
		}
	};
	

	private void initView() {
		// TODO Auto-generated method stub
		setSize(518,464);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
        
		addListView();
		addContentView();
		addButtonView();
		
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
		JPanel fighterdetailPanel = new JPanel(new GridLayout(0, 3, 0, 0));
		fighterdetailPanel.setBounds(24, 56, 235, 187);
		
        contentPanel.setLayout(null);
        
        fighterdetailPanel.add(new JLabel(" Name "));
        fighterdetailPanel.add(nameTextF);
        fighterdetailPanel.add(nameModiferL);
        
        fighterdetailPanel.add(new JLabel(" Level "));
        fighterdetailPanel.add(levelTextF);
        fighterdetailPanel.add(new JLabel(" Modifier "));
        
        fighterdetailPanel.add(new JLabel(" Strength "));
        fighterdetailPanel.add(strengthTextF);
        fighterdetailPanel.add(strengthModiferL);
        
        fighterdetailPanel.add(new JLabel(" Dexterity "));
        fighterdetailPanel.add(dexterityTextF);
        fighterdetailPanel.add(dexModiferL);
        
        fighterdetailPanel.add(new JLabel(" Constitution "));
        fighterdetailPanel.add(constitutionTextF);
        fighterdetailPanel.add(conModiferL);
        
        fighterdetailPanel.add(new JLabel(" Intelligence "));
        fighterdetailPanel.add(intelligenceTextF);
        fighterdetailPanel.add(intelliModiferL);
        
        fighterdetailPanel.add(new JLabel(" Wisdom "));
        fighterdetailPanel.add(wisdomTextF);
        fighterdetailPanel.add(wisModiferL);
        
        fighterdetailPanel.add(new JLabel(" Charisma "));
        fighterdetailPanel.add(charismaTextF);
        fighterdetailPanel.add(chaModiferL);
		
		contentPanel.add(fighterdetailPanel);
		getContentPane().add(contentPanel,BorderLayout.CENTER);
	}
	
	private void addButtonView() {
		JPanel buttonPanel = new JPanel();
		checkisfriendly = new JCheckBox("is friendly?");
		DButton ensurebutton = new DButton("Ensure",this);
		ensurebutton.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
		
		buttonPanel.add(checkisfriendly);
		buttonPanel.add(ensurebutton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
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
			System.out.println(isfriendly);
			if(index>=0){
				selectedfighter = fightersmodel.getItemByIndex(fighterslist.getSelectedIndex());
				isfriendly = checkisfriendly.isSelected();
				System.out.println(isfriendly);
				System.out.println(selectedfighter.getName());
			}
			JButton button = (JButton)e.getSource();
            SwingUtilities.getWindowAncestor(button).dispose();
		}
	}
}
