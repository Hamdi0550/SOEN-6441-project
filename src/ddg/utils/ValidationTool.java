package ddg.utils;

import java.util.ArrayList;
import java.util.HashMap;

import ddg.model.Map;
import ddg.model.entity.Chest;

/**
 * @author Bo
 * use to check the validation of map
 */
public class ValidationTool {
	class Graph {
		private Map map;
		private ArrayList<Integer>[] vector;
		
		/**
		 * Constructor
		 * @param map
		 */
		public Graph(Map map) {
			this.map = map;
			vector = new ArrayList[map.getRow()*map.getColumn()];
			for(int i=0;i<map.getRow()*map.getColumn();i++) {
				vector[i] = new ArrayList<Integer>();
			}
			buildGraph();
		}

		/**
		 * build graph according the map
		 */
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
		
		/**
		 * 
		 * get index using this method 
		 * @param i the x coordinate  it will times the column of map
		 * @param j the y coordinate 
		 * @return	which number is this location
		 */
		private int getIndex(int i, int j) {
			return i*map.getColumn()+j;
		}
		
		/**
		 * get map content in char type according location
		 * @param i x coordinate of location
		 * @param j y coordinate of location
		 * @return content in char type
		 */
		private char getMapChar(int i, int j) {
			return this.map.getLocation()[i][j];
		}
		
		/**
		 * add edge into vector
		 * @param v	the vector store index
		 * @param w	x * y where wall is
		 */
		private void addEdge(int v, int w) {
			vector[v].add(w);
		}
		
		/**
		 * search from a point in vector to end point
		 * @param from	a index where the search start
		 * @param to a index where the search end
		 * @param visit	
		 * @return
		 */
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
		
		/**
		 * check if it is connected from start poing to end point
		 * @param from	staring point
		 * @param to	ending point
		 * @return
		 */
		public boolean isConnect(int from, int to) {
			boolean[] visit = new boolean[map.getRow()*map.getColumn()];
			for(int i=0;i<map.getRow()*map.getColumn();i++){
				visit[i] = false;
			}
			return search(from, to, visit);
		}
		
		
	}
	private Map map;
	private boolean hasValidPath;
	private java.util.Map<String, String> usedcell = new HashMap<>();
	char maplocation[][];
	/**
	 * @param map the map whose validation you want to check
	 */
	public ValidationTool(Map map) {
		// TODO Auto-generated constructor stub
		this.map = map;
		hasValidPath = false;
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

	/**
	 * 
	 * @param from starting index
	 * @param to 	ending index
	 * @return	true if there is valid path, alse return false
	 */
	public boolean isValidPath(int from, int to) {
		if(from < 0 || to < 0)
			return false;
		Graph g = new Graph(map);
		return g.isConnect(from, to);
	}

	/**
	 * manage to find the valid path
	 * @param i	the x coordinate would like to check
	 * @param j	the y coordinate would like to check
	 */
	public void validPath(int i, int j) {
		hasValidPath = false;
		if(maplocation[i][j] == 'o'){
			hasValidPath=true;
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
            		hasValidPath = isValidPath(enter, to.get(m));
            		if(hasValidPath)
            			return;
            	}
            } else if(map.getLocation()[i][j] == 'c'){
				 String name = ((Chest) map.getCellsinthemap()[i][j].getContent()).getItem().getName();
				 if(name.equals("key")){
					for(int m=0;m<to.size();m++) {
						hasValidPath = isValidPath(enter, key.get(m));
						if(hasValidPath)
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
		hasValidPath = false;
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
		else if(hasValidPath){
			return true;
		}
		return false;
	}
	
	/**
	 * @return true when there is key on the map, otherwise return false.
	 */
	public boolean hasKey() {
		hasValidPath = false;
		
		for(int i=0;i<map.getRow();i++){
			for(int j=0;j<map.getColumn();j++){
				if(map.getLocation()[i][j] == 'c'){
					 String name = ((Chest) map.getCellsinthemap()[i][j].getContent()).getItem().getName();
					 if(name.equals("key")){
						usedcell.clear();
						hasValidPath(i,j);
						if(hasValidPath)
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
		return hasValidPath;
	}
}
