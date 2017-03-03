package ddg.map.entity;

import ddg.item.entity.BaseItem;

public class Chest implements java.io.Serializable{
	private static final long serialVersionUID = -28660141344286515L;
	
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
