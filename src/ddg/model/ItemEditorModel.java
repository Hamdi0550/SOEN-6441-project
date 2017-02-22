package ddg.model;

import java.util.ArrayList;

import ddg.item.entity.BaseItem;

/**
 * 
 * This class
 * 
 * @author Zhen Du
 * @date Feb 22, 2017
 */
public class ItemEditorModel {
	
	public ArrayList<BaseItem> items;

	public ItemEditorModel() {
		super();
		this.items = new ArrayList<BaseItem>();
	}
	
	public ItemEditorModel(ArrayList<BaseItem> items) {
		super();
		this.items = items;
	}

	public ArrayList<BaseItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<BaseItem> items) {
		this.items = items;
	}
}
