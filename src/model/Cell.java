package model;
/**
 * Cells class which include content on the Map
 * @author Bo
 * @Feb 22,2017
 */
public class Cell {
	private Object content;
	private boolean isfriendly;

	public Cell(Object content){
		this.content = content;
		this.isfriendly = true;
	}
	
	public Cell(Object content, boolean isfriendly){
		this.content = content;
		this.isfriendly = isfriendly;
	}
	
	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
	public boolean getIsfriendly(){
		return isfriendly;
	}
	
}
