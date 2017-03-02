package ddg.view;

import ddg.Config;
import ddg.model.MapEditorModel;
import ddg.view.component.DButton;
import ddg.view.component.ListEntryCellRenderer;
import model.Map;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * dialog for asking map's size when user create a new map
 * @author Bo, Yi Qin
 * @date 2017-02-28.
 */
public class PopUpForCreateNewMap extends JDialog implements ActionListener,ChangeListener{
	private int row;
	private int column;
	private JTextField rowtext = new JTextField("10",4);
	private JTextField columntext = new JTextField("10",4);
    private JSlider rowslider = new JSlider(10,190,10);
    private JSlider columnslider = new JSlider(10,190,10);

    /**
     * @param owner	the owner frame of this dialog
     * @param title	the title of this dialog
     */
    public PopUpForCreateNewMap(Frame owner, String title) {
        super(owner, title);
        this.row = 0;
        this.column = 0;
        rowslider.setPaintTicks(true);
        rowslider.setPaintLabels(true);
        rowslider.setMajorTickSpacing(30);
        rowslider.setMinorTickSpacing(15);
        rowslider.addChangeListener(this);
        
        columnslider.setPaintTicks(true);
        columnslider.setPaintLabels(true);
        columnslider.setMajorTickSpacing(30);
        columnslider.setMinorTickSpacing(15);
        columnslider.addChangeListener(this);
        rowtext.setEnabled(false);
        columntext.setEnabled(false);
        
        
        initView();
    }

    private void initView(){
        setSize(350, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        addListView();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addListView(){
    	getContentPane().setLayout(new FlowLayout());
    	// add information of the Dialog
        JLabel text = new JLabel("Select map size, default is 10.");
        
        // Panel for height slider
        JPanel rowpanel = new JPanel();
        rowpanel.add(new JLabel("row:     "));
        rowpanel.add(rowslider);
        rowpanel.add(rowtext);
        
        // Panel for width slider
        JPanel columnpanel = new JPanel();
        columnpanel.add(new JLabel("column:"));
        columnpanel.add(columnslider);
        columnpanel.add(columntext);
        
        
        // ensure button
        DButton button = new DButton("OK", this);
        button.setBorder(BorderFactory.createLineBorder(Color.black));
        
//        contentpanel.add(text);
        
        getContentPane().add(text);
        getContentPane().add(rowpanel);
        getContentPane().add(columnpanel);
        getContentPane().add(button);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK")){
        	System.out.println(rowslider.getValue() +" "+ columnslider.getValue());
            this.row = rowslider.getValue();
            this.column = columnslider.getValue();
            JButton button = (JButton)e.getSource();
            SwingUtilities.getWindowAncestor(button).dispose();
        }
    }

	/**
	 * @return	return the number of the row in the map(decide by user)
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return	return the number of the column in the map(decide by user)
	 */
	public int getColumn() {
		return column;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof JSlider) {
            rowtext.setText(String.valueOf(rowslider.getValue()));
            columntext.setText(String.valueOf(columnslider.getValue()));
        }
		
	}

}
