package model;
/**
 * Cells class which include content on the Map
 * @author Bo
 * @Feb 22,2017
 */
public class Cell {
	private Object content;	// content in the cell
	private boolean isfriendly;	//show if the content is friendly to player, mainly used to describe character content

	
	/**
	 * @param content content in the cell e.g. character, chest
	 */
	public Cell(Object content){
		this.content = content;
		this.isfriendly = true;
	}
	
	/**
	 * @param content content content in the cell e.g. character, chest
	 * @param isfriendly defend the content is friendly to player or not
	 */
	public Cell(Object content, boolean isfriendly){
		this.content = content;
		this.isfriendly = isfriendly;
	}
	
	/**
	 * @return content in the cell
	 */
	public Object getContent() {
		return content;
	}

	/**
	 * @param content set the content object in the Cell
	 */
	public void setContent(Object content) {
		this.content = content;
	}
	
	/**
	 * @return return whether the content of the Cell is friendly, if it is return true, else return false.
	 */
	public boolean getIsfriendly(){
		return isfriendly;
	}
	
}
