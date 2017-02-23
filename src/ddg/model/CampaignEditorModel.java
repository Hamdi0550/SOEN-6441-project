package ddg.model;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import ddg.campaign.entity.BaseCampaign;
import ddg.item.entity.ListEntry;

/**
 * 
 * This class is restore campaigns
 * 
 * @author Zhen Du
 * @date Feb 23, 2017
 */
public class CampaignEditorModel {
	
	private ArrayList<BaseCampaign> campaigns;
	
	public CampaignEditorModel() {
		super();
		this.campaigns = new ArrayList<BaseCampaign>();
	}
	
	public CampaignEditorModel(ArrayList<BaseCampaign> items) {
		super();
		this.campaigns = items;
	}
	
	public void addCampaign(BaseCampaign c) {
		int size = this.campaigns.size();
		c.setId(c.getName()+"_"+(size+1));
		this.campaigns.add(c);
	}
	
	public DefaultListModel getListModel() {
		DefaultListModel l = new DefaultListModel();
		for(BaseCampaign i : this.campaigns) {
			l.addElement(new ListEntry(i.getId(), new ImageIcon("res/Campaign.png")));
		}
		return l;
	}
	
	public BaseCampaign getItemByIndex(int index) {
		if(index < 0 || index > this.campaigns.size()-1)
			return null;
		return this.campaigns.get(index);
	}
}
