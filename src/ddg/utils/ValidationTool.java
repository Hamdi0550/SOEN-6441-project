package ddg.utils;

import java.util.ArrayList;
import java.util.HashMap;

import ddg.map.entity.Chest;
import ddg.map.entity.Map;

/**
 * @author Bo
 * use to check the validation of map
 */
public class ValidationTool {
	class Graph {
		private Map map;
		private ArrayList<Integer>[] vector;
		
		public Graph(Map map) {
			this.map = map;
			vector = new ArrayList[map.getRow()*map.getColumn()];
			for(int i=0;i<map.getRow()*map.getColumn();i++) {
				vector[i] = new ArrayList<Integer>();
			}
			buildGraph();
		}

		private void buildGraph() {
			for(int i=0;i<map.getRow();i++){
				for(int j=0;j<map.getColumn();j++){
					if(getMapChar(i,j) == 'w')
						continue;
					if(i==0&&j==0) {
						if(getMapChar(i+1,j)!='w') {
							addEdge(getIndex(i,j), getIndex(i+1,j));
						}
						if(getMapChar(i,j+1)!='w') {
							addEdge(getIndex(i,j), getIndex(i,j+1));
						}
					} else if(i==0&&j==map.getColumn()-1) {
						if(getMapChar(i+1,j)!='w') {
							addEdge(getIndex(i,j), getIndex(i+1,j));
						}
						if(getMapChar(i,j-1)!='w') {
							addEdge(getIndex(i,j), getIndex(i,j-1));
						}
					} else if(i==map.getRow()-1&&j==0) {
						if(getMapChar(i-1,j)!='w') {
							addEdge(getIndex(i,j), getIndex(i-1,j));
						}
						if(getMapChar(i,j+1)!='w') {
							addEdge(getIndex(i,j), getIndex(i,j+1));
						}
					} else if(i==map.getRow()-1&&j==map.getColumn()-1) {
						if(getMapChar(i-1,j)!='w') {
							addEdge(getIndex(i,j), getIndex(i-1,j));
						}
						if(getMapChar(i,j-1)!='w') {
							addEdge(getIndex(i,j), getIndex(i,j-1));
						}
					} else if((i==0||i==map.getRow()-1)&&(j>0&&j<map.getColumn()-1)) {
						int r = 1;
						if(i==0) {
							r = 1;
						} else {
							r = map.getRow()-2;
						}
						if(getMapChar(r,j)!='w') {
							addEdge(getIndex(i,j), getIndex(r,j));
						}
						if(getMapChar(i,j-1)!='w') {
							addEdge(getIndex(i,j), getIndex(i,j-1));
						}
						if(getMapChar(i,j+1)!='w') {
							addEdge(getIndex(i,j), getIndex(i,j+1));
						}
					} else if((j==0||j==map.getColumn()-1)&&(i>0&&i<map.getRow()-1)) {
						int c = 1;
						if(j==0) {
							c = 1;
						} else {
							c = map.getColumn()-2;
						}
						if(getMapChar(i,c)!='w') {
							addEdge(getIndex(i,j), getIndex(i,c));
						}
						if(getMapChar(i-1,j)!='w') {
							addEdge(getIndex(i,j), getIndex(i-1,j));
						}
						if(getMapChar(i+1,j)!='w') {
							addEdge(getIndex(i,j), getIndex(i+1,j));
						}
					} else {
						if(getMapChar(i-1,j)!='w') {
							addEdge(getIndex(i,j), getIndex(i-1,j));
						}
						if(getMapChar(i,j-1)!='w') {
							addEdge(getIndex(i,j), getIndex(i,j-1));
						}
						if(getMapChar(i+1,j)!='w') {
							addEdge(getIndex(i,j), getIndex(i+1,j));
						}
						if(getMapChar(i,j+1)!='w') {
							addEdge(getIndex(i,j), getIndex(i,j+1));
						}
					}
				}
			}
		}
		
		private int getIndex(int i, int j) {
			return i*map.getColumn()+j;
		}
		
		private char getMapChar(int i, int j) {
			return this.map.getLocation()[i][j];
		}
		
		private void addEdge(int v, int w) {
			vector[v].add(w);
		}
		
		private boolean search(int from, int to, boolean[] visit) {
			visit[from] = true;
			for(int i=0;i<vector[from].size();i++) {
				int next = vector[from].get(i);
				if(!visit[next]) {
					if(next == to) {
						return true;
					} else {
						if(search(next, to, visit)) {
							return true;
						} else {
							continue;
						}
					}
				}
			}
			return false;
		}
		
		public boolean isConnect(int from, int to) {
			boolean[] visit = new boolean[map.getRow()*map.getColumn()];
			for(int i=0;i<map.getRow()*map.getColumn();i++){
				visit[i] = false;
			}
			return search(from, to, visit);
		}
		
		
	}
	private Map map;
	private boolean hasvalidpath;
	private java.util.Map<String, String> usedcell = new HashMap<>();
	char maplocation[][];
	/**
	 * @param map the map whose validation you want to check
	 */
	public ValidationTool(Map map) {
		// TODO Auto-generated constructor stub
		this.map = map;
		hasvalidpath = false;
		maplocation = map.getLocation();
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

	public boolean isValidPath(int from, int to) {
		if(from < 0 || to < 0)
			return false;
		Graph g = new Graph(map);
		return g.isConnect(from, to);
	}

	public void validPath(int i, int j) {
		hasvalidpath = false;
		if(maplocation[i][j] == 'o'){
			hasvalidpath=true;
			return;
		} else {
			int enter = -1;
			ArrayList<Integer> to = new ArrayList<Integer>();
			ArrayList<Integer> key = new ArrayList<Integer>();
			for (int r = 0; r < map.getRow() ; r ++) {
	            for (int c =0; c < map.getColumn(); c++ ){
	                if(map.getLocation()[r][c] == 'i'){
	                	enter = r*map.getColumn()+c;
	                } else if(map.getLocation()[r][c] == 'o') {
	                	to.add(r*map.getColumn()+c);
	                } else if(map.getLocation()[r][c] == 'c'){
						 String name = ((Chest) map.getCellsinthemap()[r][c].getContent()).getItem().getName();
						 if(name.equals("key")){
							 key.add(r*map.getColumn()+c);
						 }
	                }
	            }
			}
			if(map.getLocation()[i][j] == 'i') {
            	for(int m=0;m<to.size();m++) {
            		hasvalidpath = isValidPath(enter, to.get(m));
            		if(hasvalidpath)
            			return;
            	}
            } else if(map.getLocation()[i][j] == 'c'){
				 String name = ((Chest) map.getCellsinthemap()[i][j].getContent()).getItem().getName();
				 if(name.equals("key")){
					for(int m=0;m<to.size();m++) {
						hasvalidpath = isValidPath(enter, key.get(m));
						if(hasvalidpath)
	            			return;
					}
				 }
            }
		}
	}
	/**
	 * check whether the map has valid path from enter door/or the chest containing key to exit door.
	 * @param i the row-coordinate of the enter door or the chest containing key
	 * @param j the column-coordinate of the enter door or the chest containing key
	 */
	public void hasValidPath(int i, int j) {
		validPath(i, j);
//		if(maplocation[i][j] == 'o'){
//			hasvalidpath=true;
//			return;
//		}
//		else{
//			usedcell.put(i+","+j, "");
//			if( j>0 && maplocation[i][j-1]!='w'){
//				if(usedcell.get(i+","+ (j-1) )== null)
//					hasValidPath(i, j-1);
//				if(hasvalidpath)
//					return;
//			}
//			
//			if(i>0 && maplocation[i-1][j]!='w'){
//				if(usedcell.get(i-1 +","+ j)== null)
//					hasValidPath(i-1, j);
//				if(hasvalidpath)
//					return;
//			}
//			
//			if(j<map.getColumn()-1 && maplocation[i][j+1]!='w'){
//				if(usedcell.get(i +","+ (j+1))== null)
//					hasValidPath(i, j+1);
//				if(hasvalidpath)
//					return;
//			}
//			
//			if(i<map.getRow()-1 && maplocation[i+1][j]!='w'){
//				if(usedcell.get((i+1) +","+ j)== null)
//					hasValidPath(i+1, j);
//				if(hasvalidpath)
//					return;
//			}
//		}
	}

	/**
	 *  
	 * @return true when there is a indoor(Entry door) in the location of map 
	 */
	public boolean hasEntryDoor() {
		hasvalidpath = false;
		int indooraccount = 0;
		for(int i=0;i<map.getRow();i++){
			for(int j=0;j<map.getColumn();j++){
				if(map.getLocation()[i][j] == 'i'){
					usedcell.clear();
					hasValidPath(i,j);
					indooraccount++;
				}
			}
		}
		if(indooraccount!=1){
			return false;
		}
		else if(hasvalidpath){
			return true;
		}
		return false;
	}
	
	/**
	 * @return true when there is key on the map, otherwise return false.
	 */
	public boolean hasKey() {
		hasvalidpath = false;
		
		for(int i=0;i<map.getRow();i++){
			for(int j=0;j<map.getColumn();j++){
				if(map.getLocation()[i][j] == 'c'){
					 String name = ((Chest) map.getCellsinthemap()[i][j].getContent()).getItem().getName();
					 if(name.equals("key")){
						usedcell.clear();
						hasValidPath(i,j);
						if(hasvalidpath)
							return true;
					 }
				}
			}
		}
		return false;
	}
	
	/**
	 * 
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

	/**
	 * @return true if the map has valid
	 */
	public boolean isHasvalidpath() {
		return hasvalidpath;
	}
}
