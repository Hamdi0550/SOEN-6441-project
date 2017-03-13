package ddg.map.entity;

import ddg.item.entity.BaseItem;

public class Chest implements java.io.Serializable{
	private static final long serialVersionUID = -28660141344286515L;
	
	private BaseItem item;
	private boolean isempty;
	
	public Chest(){
		item = null;
		isempty = true;
	}
	
	public Chest(BaseItem item){
		this.item = item;
		this.isempty = false;
	}

	public BaseItem getItem() {
		return item;
	}

	public boolean isEmpty() {
		return isempty;
	}
	
	public void becameEmpty(){
		isempty = true;
	}
}
