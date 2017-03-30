package ddg.ui.view.component;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import ddg.Config;

/**
 * 
 * This class is Custom ComboBox add name for every ItemListener
 * 
 * @author Zhen Du
 * @date Feb 23, 2017
 */
public class DComboBox<T> extends JComboBox implements ItemListener {
	
	public interface DItemListener {
		void itemChanged(ItemEvent e, String name);
	}
	
	private DItemListener listener;
	
	/**
	 * 
	 * Constructors for DComboBox
	 * 
	 * @param name String name need to specify which comboBox 
	 */
	public DComboBox(String name) {
		super();
		setPreferredSize(new Dimension(Config.OPTION_WIDTH, Config.BTN_HEIGHT));
		setName(name);
	}

	/**
	 * 
	 * This method is add one Item Listener
	 * 
	 * @param l
	 */
	public void addDItemListener(DItemListener l) {
		this.listener = l;
		addItemListener(this);
	}
	
	/**
	 * 
	 * This method is remove the item listener
	 * 
	 * @param l
	 */
	public void removeDItemListener(DItemListener l) {
		super.removeItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		this.listener.itemChanged(e, getName());
	}
}
