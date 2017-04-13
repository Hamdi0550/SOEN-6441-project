package ddg.model.entity;

import ddg.model.item.Item;

/**
 * Chest Class
 * @author Bo
 * @date Mar 22, 2017
 * 
 */
public class Chest implements Cloneable, java.io.Serializable{
	private static final long serialVersionUID = -28660141344286515L;
	
	private Item item;
	private boolean isEmpty;
	
	/**
	 * Constructor
	 */
	public Chest(){
		item = null;
		isEmpty = true;
	}
	
	/**
	 * constructor create Chest by giving a item
	 * @param item the item you would like to put into ths Chest
	 */
	public Chest(Item item){
		this.item = item;
		this.isEmpty = false;
	}

	/**
	 * return the item inside the Chest
	 * @return item the item inside Chest
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * check if this map is empty
	 * @return when empty return true, else return false
	 */
	public boolean isEmpty() {
		return isEmpty;
	}
	
	/**
	 * make the Chest to be empty
	 */
	public void becameEmpty(){
		isEmpty = true;
//		item=null;
	}
	
	/**
	 * This method clones a chest and return the new one
	 * @return newchest The new chest
	 */
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
