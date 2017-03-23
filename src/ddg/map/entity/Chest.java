package ddg.map.entity;

import ddg.item.entity.BaseItem;

/**
 * Chest Class
 * @author Bo
 * @date Mar 22, 2017
 * 
 */
public class Chest implements Cloneable, java.io.Serializable{
	private static final long serialVersionUID = -28660141344286515L;
	
	private BaseItem item;
	private boolean isempty;
	
	public Chest(){
		item = null;
		isempty = true;
	}
	
	/**
	 * constructor create Chest by giving a item
	 * @param item the item you would like to put into ths Chest
	 */
	public Chest(BaseItem item){
		this.item = item;
		this.isempty = false;
	}

	/**
	 * return the item inside the Chest
	 * @return item the item inside Chest
	 */
	public BaseItem getItem() {
		return item;
	}

	/**
	 * check if this map is empty
	 * @return when empty return true, else return false
	 */
	public boolean isEmpty() {
		return isempty;
	}
	
	/**
	 * make the Chest to be empty
	 */
	public void becameEmpty(){
		isempty = true;
//		item=null;
	}
	
	public Chest clone(){
		Chest newchest = null;
		try{
			newchest=(Chest)super.clone();
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		return newchest;
	}
}
