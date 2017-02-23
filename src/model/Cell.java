package model;
/**
 * Cells class which include content on the Map
 * @author Bo
 * @Feb 22,2017
 */
public class Cell {
	private Object content;

	public Cell(Object content){
		this.content = content;
	}
	
	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}
