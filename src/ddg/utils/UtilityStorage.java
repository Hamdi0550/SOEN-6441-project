package ddg.utils;

import ddg.item.entity.BaseItem;
import ddg.model.Fighter;

public class UtilityStorage {
	private static Fighter fighter;
	private static BaseItem item;
	
	public static Fighter getFighter(){
		return fighter;
	}
	
	public static BaseItem getItem(){
		return item;
	}
	
	public static void setFighter(Fighter newfighter){
		fighter = newfighter;
	}
	
	public static void setItem(BaseItem newitem){
		item = newitem;
	}

}
