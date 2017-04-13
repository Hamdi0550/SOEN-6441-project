package ddg.model;

import ddg.model.entity.BaseCampaign;

/**
 * 
 * This class is game model while running/playing
 * 
 * @author Zhen Du
 * @date Mar 12, 2017
 */
public class GameModel {

	private Fighter fighter;
	private BaseCampaign campaign;
	private boolean isComputer= false;
	
	/**
	 * 
	 * Constructors for GameModel
	 * 
	 * @param fighter
	 * @param campaign
	 */
	public GameModel(Fighter fighter, BaseCampaign campaign) {
		this.fighter = fighter;
		this.campaign = campaign;
	}

	/**
	 * This method is to set the game as a computer run game
	 * @param isComputer
	 */
	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}

	/**
	 * This method is to check if the game is a computer run game.
	 * @return isComputer
	 */
	boolean isComputer() {
		return this.isComputer;
	}
	
	/**
	 * 
	 * This method getFighter
	 * 
	 * @return
	 */
	public Fighter getFighter() {
		return fighter;
	}

	/**
	 * 
	 * This method getCampaign
	 * 
	 * @return
	 */
	public BaseCampaign getCampaign() {
		return campaign;
	}
	
	
}
