package ddg.utils;

import java.util.HashMap;

import ddg.map.entity.Chest;
import ddg.map.entity.Map;

/**
 * @author Bo
 * use to check the validation of map
 */
public class ValidationTool {
	private Map map;
	private boolean hasvaildpath;
	private java.util.Map<String, String> usedcell = new HashMap<>();

	/**
	 * @param map the map whose validation you want to check
	 */
	public ValidationTool(Map map) {
		// TODO Auto-generated constructor stub
		this.map = map;
		hasvaildpath = false;
	}
	
	/**
	 * check whether the map is Validation or not.
	 * check if the map has enter door, exit door and valid path
	 * @return if the map is validation return true, otherwise return false
	 */
	public boolean checkValidation(){
		if(hasEntryDoor() && hasExitDoor()&&hasKey()){
			return true;
		}
		return false;
	}

	

	/**
	 * check whether the map has valid path from enter door/or the chest containing key to exit door.
	 * @param i the row-coordinate of the enter door or the chest containing key
	 * @param j the column-coordinate of the enter door or the chest containing key
	 */
	public void hasValidPath(int i, int j) {
		char maplocation[][] = map.getLocation();
		if(maplocation[i][j] == 'o')
			hasvaildpath=true;
		else{
			usedcell.put(i+","+j, "i*j");
			if( j>0 && maplocation[i][j-1]!='w'){
				if(usedcell.get(i+","+ (j-1) )== null)
					hasValidPath(i, j-1);
			}
			
			if(i>0 && maplocation[i-1][j]!='w'){
				if(usedcell.get(i-1 +","+ j)== null)
					hasValidPath(i-1, j);
			}
			
			if(j<map.getColumn()-1 && maplocation[i][j+1]!='w'){
				if(usedcell.get(i +","+ (j+1))== null)
					hasValidPath(i, j+1);
			}
			
			if(i<map.getRow()-1 && maplocation[i+1][j]!='w'){
				if(usedcell.get((i+1) +","+ j)== null)
					hasValidPath(i+1, j);
			}
		}
	}

	/**
	 *  
	 * @return true when there is a indoor(Entry door) in the location of map 
	 */
	public boolean hasEntryDoor() {
		hasvaildpath = false;
		for(int i=0;i<map.getRow();i++){
			for(int j=0;j<map.getColumn();j++){
				if(map.getLocation()[i][j] == 'i'){
					usedcell.clear();
					hasValidPath(i,j);
				}
			}
		}
		
		if(hasvaildpath){
			return true;
		}
		
		return false;
	}
	
	/**
	 * @return true when there is key on the map, otherwise return false.
	 */
	public boolean hasKey() {
		hasvaildpath = false;
//		for(int i=0;i<map.getRow();i++){
//			for(int j=0;j<map.getColumn();j++){
//				if(map.getLocation()[i][j] == 'k'){
//					return true;
//				}
//			}
//		}
		for(int i=0;i<map.getRow();i++){
			for(int j=0;j<map.getColumn();j++){
				if(map.getLocation()[i][j] == 'c'){
					 String name = ((Chest) map.getCellsinthemap()[i][j].getContent()).getItem().getName();
					 if(name.equals("key")){
						usedcell.clear();
						hasValidPath(i,j);
						if(hasvaildpath)
							return true;
					 }
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 *  maplocation detail location of map.
	 * @return true when there is a outdoor(Exit door) in the location of map 
	 */
	public boolean hasExitDoor() {
		for(int i=0;i<map.getRow();i++){
			for(int j=0;j<map.getColumn();j++){
				if(map.getLocation()[i][j] == 'o'){
					return true;
				}
			}
		}
		return false;
	}

	public boolean isHasvaildpath() {
		return hasvaildpath;
	}
}
