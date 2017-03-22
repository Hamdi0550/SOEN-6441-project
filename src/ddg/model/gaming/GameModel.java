package ddg.model.gaming;

import ddg.campaign.entity.BaseCampaign;
import ddg.model.Fighter;

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
