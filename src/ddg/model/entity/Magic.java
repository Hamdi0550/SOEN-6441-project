package ddg.model.entity;

import java.io.Serializable;

/**
 * 
 * This class define the name for increase
 * 
 * @author Zhen Du
 * @date Feb 22, 2017
 */
public class Magic implements Serializable {
	private static final long serialVersionUID = -6350068786148679730L;
	
	protected String name;
	/**
	 * Constructors
	 * 
	 */
	public Magic(String name) {
		this.name = name;
	}

	String getName(){return name;}
}
