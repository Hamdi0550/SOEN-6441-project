package ddg.model.entity;
/**
 * Cells class which include content on the Map
 * @author Bo
 * @param <T>
 * @Feb 22,2017
 */
public class Cell<T> implements java.io.Serializable{
	private static final long serialVersionUID = -4203753997046674059L;
	
	private T content;	// content in the cell
	private boolean isFriendly;	//show if the content is friendly to player, mainly used to describe character content

	
	/**
	 * @param content content in the cell e.g. character, chest
	 */
	public Cell(T content){
		this.content = content;
		this.isFriendly = true;
	}
	
	/**
	 * @param content content content in the cell e.g. character, chest
	 * @param isfriendly defend the content is friendly to player or not
	 */
	public Cell(T content, boolean isfriendly){
		this.content = content;
		this.isFriendly = isfriendly;
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
	public void setContent(T content) {
		this.content = content;
	}
	
	/**
	 * @return return whether the content of the Cell is friendly, if it is return true, else return false.
	 */
	public boolean getIsfriendly(){
		return isFriendly;
	}
	
}
