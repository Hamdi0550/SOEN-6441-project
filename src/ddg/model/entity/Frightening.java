/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
package ddg.model.entity;

import ddg.model.Fighter;
import ddg.strategy.FrighteningStrategy;

/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 6, 2017
 */
public class Frightening extends Magic {

	private static final long serialVersionUID = 8920163609998420784L;

	/**
	 * Constructors
	 * 
	 */
	public Frightening() {
		super(MagicWeaponItem.FRIGHTENING);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public int getRunawayTurns() {
		return weapon==null?0:weapon.getBonus();
	}

	@Override
	public void attack(Fighter npc) {
		int xofcharactor = 0;
		int yofcharactor = 0;
		int turns[] ={ getRunawayTurns() };
		Map playingMap = ((Game)npc.getOwner()).getPlayingmap();
		
		for (int i = 0; i < playingMap.getRow(); i++) {
			for (int j = 0; j < playingMap.getColumn(); j++) {
				if (playingMap.getLocation()[i][j] == 'p') {
					Fighter fighter = (Fighter) playingMap.getCellsinthemap()[i][j].getContent();
					if (fighter.equals(npc)) {
						xofcharactor = i;
						yofcharactor = j;
						npc.setMagicStrategy(new FrighteningStrategy((Game)npc.getOwner(), xofcharactor, yofcharactor){

							@Override
							public int getTurns() {
								return turns[0];
							}
							
						});
						return;
					}
				}
			}
		}
	}
}
