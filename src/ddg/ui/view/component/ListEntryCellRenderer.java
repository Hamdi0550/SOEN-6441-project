package ddg.ui.view.component;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import ddg.Config;
import ddg.model.entity.ListEntry;

/**
 * 
 * This class is Cell Renderer to show icon and text
 * 
 * @author Zhen Du
 * @date Feb 23, 2017
 */
public class ListEntryCellRenderer extends JLabel implements ListCellRenderer {
	private JLabel label;

	/**
	 * getListCellRendererComponent
	 * 
	 * @param list
	 * @param value
	 * @param index
	 * @param isSelected
	 * @param cellHasFocus
	 * 
	 * @return Component
	 */
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		ListEntry entry = (ListEntry) value;
		
		setBorder(Config.border);
		setIconTextGap(10);
		setText(value.toString());
		setIcon(entry.getIcon());

		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		setEnabled(list.isEnabled());
		setFont(list.getFont());
		setOpaque(true);

		return this;
	}
}
