package ddg.view;

import ddg.Config;
import ddg.model.MapEditorModel;
import ddg.view.component.DButton;
import ddg.view.component.ListEntryCellRenderer;
import model.Map;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by qinyi on 2017-02-28.
 */
public class PopUpForCreateNewMap extends JDialog implements ActionListener{
    private JList mapslist;
    private Map selectedMap;

    public PopUpForCreateNewMap(Frame owner, String title) {
        super(owner, title);
        selectedMap = null;
        initView();
    }

    private void initView(){
        setSize(250, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        addListView();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addListView(){
        JPanel listPanel = new JPanel();
        listPanel.setPreferredSize(new Dimension(4*Config.BTN_WIDTH, 4*Config.BTN_HEIGHT));
        String[] size = {"Small", "Medium", "Large"};
        mapslist = new JList(size);
        mapslist.setSize(3*Config.BTN_WIDTH, 3*Config.BTN_HEIGHT);
        mapslist.setBorder(BorderFactory.createTitledBorder("Choose Map Size"));
        mapslist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mapslist.setVisibleRowCount(3);
        listPanel.add(mapslist);
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(listPanel);
        DButton button = new DButton("OK", this);
        button.setSize(Config.BTN_WIDTH, Config.BTN_HEIGHT);
        getContentPane().add(button);
    }

    public Map getSelectedMap(){
        return selectedMap;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK")){
            int index = mapslist.getSelectedIndex();
            if (index == 0){
                Map map = new Map();
                selectedMap = map;
            }
            if (index == 1){
                Map map = new Map();
                selectedMap = map;
            }if (index == 2){
                Map map = new Map();
                selectedMap = map;
            }
            System.out.println("Selected map" + index);
            JButton button = (JButton)e.getSource();
            SwingUtilities.getWindowAncestor(button).dispose();
        }
    }
}
