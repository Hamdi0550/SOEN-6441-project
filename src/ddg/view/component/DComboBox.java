package ddg.view.component;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class DComboBox<T> extends JComboBox {
	
	public DComboBox(String name) {
		super();
		setName(name);
	}

	public void addDItemListener(DItemListener l) {
		addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				l.itemChanged(e, getName());
			}
		});
	}
	
	public interface DItemListener {
		void itemChanged(ItemEvent e, String name);
	}
}
