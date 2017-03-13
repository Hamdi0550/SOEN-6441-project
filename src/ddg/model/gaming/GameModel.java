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
	
	public GameModel(Fighter fighter, BaseCampaign campaign) {
		this.fighter = fighter;
		this.campaign = campaign;
	}

	public Fighter getFighter() {
		return fighter;
	}

	public BaseCampaign getCampaign() {
		return campaign;
	}
	
	
}
