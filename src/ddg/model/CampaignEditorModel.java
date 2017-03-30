package ddg.model;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import ddg.model.entity.BaseCampaign;
import ddg.model.entity.ListEntry;

/**
 * 
 * This class is restore data from campaigns file
 * 
 * @author Hamzah Hamdi
 * @date Feb 05, 2017
 *
 */
public class CampaignEditorModel {

	private ArrayList<BaseCampaign> campaigns;

	/**
	 * 
	 * This is the constructor for CampaignEditorModel
	 * 
	 */
	public CampaignEditorModel() {
		super();
		this.campaigns = new ArrayList<BaseCampaign>();

	}

	/**
	 * 
	 * constructor for CampaignEditorModel
	 * 
	 * @param items
	 */
	public CampaignEditorModel(ArrayList<BaseCampaign> items) {
		super();
		this.campaigns = items;
	}

	/**
	 * 
	 * 
	 * This method is to add campaign 
	 * 
	 * @param c is the campaign that needs to be adding 
	 */
	public void addCampaign(BaseCampaign c) {
		int size = this.campaigns.size();
		c.setId(c.getName() + "_" + (size + 1));
		this.campaigns.add(c);
	}

	/**
	 * 
	 * 
	 * This method is to get List Model
	 * 
	 * @return
	 */
	public DefaultListModel getListModel() {
		DefaultListModel l = new DefaultListModel();
		for (BaseCampaign i : this.campaigns) {
			l.addElement(new ListEntry(i.getId(), new ImageIcon("res/Campaign.png")));
		}
		return l;
	}
	
	/**
	 * 
	 * This method return list
	 * 
	 * @return
	 */
	public ArrayList<String> getCampaignList() {
		ArrayList<String> ids = new ArrayList<String>();
		for (BaseCampaign i : this.campaigns) {
			ids.add(i.getId());
		}
		return ids;
	}

	/**
	 * 
	 * 
	 * This method is to get the item 
	 * 
	 * @param index of the list 
	 * @return
	 */
	public BaseCampaign getItemByIndex(int index) {
		if (index < 0 || index > this.campaigns.size() - 1)
			return null;
		return this.campaigns.get(index);
	}
}
