package ddg.model.entity;

import java.util.ArrayList;

/**
 * 
 * 
 * This class is to show the items on the list
 * 
 * @author Hamzah Hamdi
 * @date Mar 2, 2017
 */
public class BaseCampaign {

	public static final String NAME = "Campaign";

	private String id;
	private String name;
	private ArrayList<String> maps;

	/**
	 * Constructors
	 * 
	 */
	public BaseCampaign(String name) {
		super();
		this.name = name;
		this.maps = new ArrayList<String>();
	}

	/**
	 * 
	 * This is the constructor for base campaign
	 * 
	 * @param name campaign name 
	 * @param maps maps in campaigns 
	 */
	public BaseCampaign(String name, ArrayList<String> maps) {
		super();
		this.name = name;
		this.maps = maps;

	}

	/**
	 * 
	 * 
	 * This method is to add maps to campaigns
	 * 
	 * @param map the name of the map
	 */
	public void addMapToCampaign(String map) {
		maps.add(map);

	}

	/**
	 * 
	 * 
	 * This method is to edit the map from the list
	 * 
	 * @param index index of the list to find maps location 
	 * @param map the name is to set the map
	 * @return
	 */
	public boolean setMapByIndex(int index, String map) {
		if (index < 0 || index > this.maps.size() - 1)
			return false;
		this.maps.set(index, map);
		return true;
	}

	/**
	 * 
	 * This method is to get the list of maps 
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getMaps() {
		return this.maps;
	}

	/**
	 * 
	 * This method getting the campaign Id
	 * 
	 * @return Id
	 */
	public String getId() {
		return id;

	}

	/**
	 * 
	 * This method is to set or edit the campaign Id
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * This method is to get the name of the campaign 
	 * 
	 * @return the name 
	 */
	public String getName() {
		return name;

	}

	/**
	 * 
	 * This method is to set the campaign name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;

	}
}
