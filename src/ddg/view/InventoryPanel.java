package ddg.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;

import ddg.item.entity.BaseItem;
import ddg.model.Fighter;

/**
 * 
 * This class to show InventoryPanel during playing
 * 
 * @author Zhen Du
 * @date Mar 22, 2017
 */
public class InventoryPanel extends InventoryView implements Observer, ActionListener {

	private JPanel operation;
	private JPanel attribute;
	/**
	 * 
	 * Constructors for InventoryPanel
	 * 
	 * @param fighter
	 */
	public InventoryPanel(Fighter fighter) {
		super(fighter);
	}

	/**
	 * 
	 * Constructors for InventoryPanel
	 *
	 */
	public InventoryPanel() {
		super();
	}

	@Override
	protected void initialization() {
		setPreferredSize(new Dimension(740,170));
		setLayout(new BorderLayout());
		JPanel equipmentPanel= new JPanel(new BorderLayout());
		
		JPanel btnPanel= new JPanel(new GridLayout(3,1,1,1));
		btnPanel.setPreferredSize(new Dimension(180, 170));
		operation= new JPanel(new GridLayout(1,2,5,5));
		
		JPanel embedPanel= new JPanel(new BorderLayout());
		JPanel characterImagePanel= new EmbeddedPanel();
		characterImagePanel.setPreferredSize(new Dimension(180,180));
		
		attribute = new JPanel();
		attribute.add(equipmentTypeL);
		attribute.add(new JLabel("  ↑"));
		attribute.add(attributeL);
		attribute.add(new JLabel("  +"));
		attribute.add(valueL);
		embedPanel.add(attribute, BorderLayout.NORTH);
		embedPanel.add(characterImagePanel, BorderLayout.CENTER);
        equipmentPanel.add(btnPanel, BorderLayout.WEST);
        equipmentPanel.add(embedPanel, BorderLayout.CENTER);
        equipmentPanel.add(operation, BorderLayout.EAST);
        
        JPanel buttonPanel= new JPanel(new GridLayout(1,3,1,1));
        JPanel buttonPanel2= new JPanel(new GridLayout(1,3,1,1));
        JPanel ringPanel= new JPanel(new GridLayout(1,1,5,5));
        
        ringPanel.add(ringBtn);
        btnPanel.add(buttonPanel);
        btnPanel.add(buttonPanel2);
        btnPanel.add(ringPanel);
        helmetBtn.setText("Helmet");
        armorBtn.setText("Armor");
        beltBtn.setText("Belt");
        ringBtn.setText("Ring");
        bootsBtn.setText("Boots");
        weaponBtn.setText("Weapon");
        shieldBtn.setText("Shield");
        
        helmetBtn.setActionCommand("Helmet");
        armorBtn.setActionCommand("Armor");
        beltBtn.setActionCommand("Belt");
        ringBtn.setActionCommand("Ring");
        bootsBtn.setActionCommand("Boots");
        weaponBtn.setActionCommand("Weapon");
        shieldBtn.setActionCommand("Shield");
        
        helmetBtn.setMargin(new Insets(1,1,1,1));
        armorBtn.setMargin(new Insets(1,1,1,1));
        beltBtn.setMargin(new Insets(1,1,1,1));
        ringBtn.setMargin(new Insets(1,1,1,1));
        bootsBtn.setMargin(new Insets(1,1,1,1));
        weaponBtn.setMargin(new Insets(1,1,1,1));
        shieldBtn.setMargin(new Insets(1,1,1,1));
        
        helmetBtn.addActionListener(this);
        armorBtn.addActionListener(this);
        beltBtn.addActionListener(this);
        ringBtn.addActionListener(this);
        bootsBtn.addActionListener(this);
        weaponBtn.addActionListener(this);
        shieldBtn.addActionListener(this);
        equipBtn.addActionListener(this);
        removeBtn.addActionListener(this);
        
        buttonPanel.add(helmetBtn);
        buttonPanel.add(armorBtn);
        buttonPanel.add(beltBtn);
        buttonPanel2.add(bootsBtn);
        buttonPanel2.add(weaponBtn);
        buttonPanel2.add(shieldBtn);
        
        equipBtn.setText("<");
        removeBtn.setText(">");
        operation.add(equipBtn);
        operation.add(removeBtn);
        add(equipmentPanel, BorderLayout.CENTER);
        
        JScrollPane itemListPane = new JScrollPane(backpackItemList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        backpackListPanel.add(itemListPane, BorderLayout.CENTER);
        backpackListPanel.setPreferredSize(new Dimension(240,180));
        add(backpackListPanel, BorderLayout.EAST);

        initMethod();
	}

	/**
	 * 
	 * This method to updateView
	 * 
	 * @param f
	 * @param isPlayer
	 */
	protected void updateView(Fighter f, boolean isPlayer) {
		super.updateView(f);
		if(isPlayer) {
			operation.setVisible(true);
		} else {
			operation.setVisible(false);
		}
		attribute.setVisible(false);
	}

	@Override
	public void update(Observable o, Object arg) {
		updateView((Fighter)arg);
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if(!aFlag) {
			attribute.setVisible(aFlag);
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		super.valueChanged(e);
		attribute.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<")) {
			attribute.setVisible(false);
		} else if(e.getActionCommand().equals(">")) {
			attribute.setVisible(false);
		} else {
			setItemAttribute(fighter.getWearItemByName(e.getActionCommand()));
		}
	}
	
	/**
	 * 
	 * This method setItemAttribute
	 * 
	 * @param item
	 */
	private void setItemAttribute(BaseItem item) {
		if(item == null) {
			attribute.setVisible(false);
			return;
		}
		attribute.setVisible(true);
		equipmentTypeL.setText(item.getName());
		attributeL.setText(item.getIncrease());
		valueL.setText(Integer.toString(item.getBonus()));
	}
}
