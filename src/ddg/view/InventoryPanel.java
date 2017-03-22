package ddg.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ddg.model.Fighter;

public class InventoryPanel extends InventoryView implements Observer {

	private JPanel operation;
	
	public InventoryPanel(Fighter fighter) {
		super(fighter);
	}

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
		JPanel characterImagePanel= new EmbeddedPanel();
		characterImagePanel.setPreferredSize(new Dimension(180,180));
        
        equipmentPanel.add(btnPanel, BorderLayout.WEST);
        equipmentPanel.add(characterImagePanel, BorderLayout.CENTER);
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
        
        helmetBtn.setMargin(new Insets(1,1,1,1));
        armorBtn.setMargin(new Insets(1,1,1,1));
        beltBtn.setMargin(new Insets(1,1,1,1));
        ringBtn.setMargin(new Insets(1,1,1,1));
        bootsBtn.setMargin(new Insets(1,1,1,1));
        weaponBtn.setMargin(new Insets(1,1,1,1));
        shieldBtn.setMargin(new Insets(1,1,1,1));
        
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

	
	protected void updateView(Fighter f, boolean isPlayer) {
		super.updateView(f);
		if(isPlayer) {
			operation.setVisible(true);
		} else {
			operation.setVisible(false);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		updateView((Fighter)arg);
	}
}
