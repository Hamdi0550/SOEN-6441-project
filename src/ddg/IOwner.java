package ddg;

import java.io.Serializable;

/**
 * This class is to be implemented by detail classes in a game
 * 
 * @author Zhen Du
 * @date Apr 3, 2017
 *
 */

public interface IOwner extends Serializable {
	int getLevel();
}
