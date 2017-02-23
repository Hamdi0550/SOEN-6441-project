package ddg.model;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import ddg.item.entity.BaseItem;
import ddg.item.entity.ListEntry;

/**
 * 
 * This is Item model to restore all items
 * 
 * @author Zhen Du
 * @date Feb 22, 2017
 */
public class ItemEditorModel {
	
	private ArrayList<BaseItem> items;

	public ItemEditorModel() {
		super();
		this.items = new ArrayList<BaseItem>();
	}
	
	public ItemEditorModel(ArrayList<BaseItem> items) {
		super();
		this.items = items;
	}

	public void addItem(BaseItem item) {
		int size = 0;
		for(int i = 0; i < this.items.size(); i++) {
			if(items.get(i).getName().equals(item.getName())) {
				size++;
			}
		}
		item.setId(item.getName()+"_"+(size+1));
		this.items.add(item);
	}
	
	public DefaultListModel getListModel() {
		DefaultListModel l = new DefaultListModel();
		for(BaseItem i : items) {
			l.addElement(new ListEntry(i.getId(), new ImageIcon("res/"+i.getName()+".jpg")));
		}
		return l;
	}
	
	public BaseItem getItemByIndex(int index) {
		if(index < 0 || index > items.size()-1)
			return null;
		return items.get(index);
	}
}
