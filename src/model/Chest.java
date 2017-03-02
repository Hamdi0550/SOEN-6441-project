package model;

import ddg.item.entity.BaseItem;

public class Chest {
	private BaseItem item;
	
	public Chest(){
		item = null;
	}
	
	public Chest(BaseItem item){
		this.item = item;
	}

	public BaseItem getItem() {
		return item;
	}
	
}
