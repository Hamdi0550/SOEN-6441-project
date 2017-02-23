package ddg.campaign.entity;

import java.util.ArrayList;

/**
 *
 * This class is save campaign item data, means maps name
 * 
 * @author Zhen Du
 * @date Feb 23, 2017
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
	 * Constructors
	 * 
	 * @param maps
	 */
	public BaseCampaign(String name, ArrayList<String> maps) {
		super();
		this.name = name;
		this.maps = maps;
	}
	
	public void addMapToCampaign(String map) {
		maps.add(map);
	}
	
	public boolean setMapByIndex(int index, String map) {
		if(index < 0 || index > this.maps.size()-1)
			return false;
		this.maps.set(index, map);
		return true;
	}
	
	public ArrayList<String> getMaps() {
		return this.maps;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
