package ddg.model;

import java.util.ArrayList;

import ddg.item.entity.BaseItem;
import ddg.item.entity.ListEntry;
import model.Map;

import javax.swing.*;

/**
 * @author Bo
 * a model for save and retrieve all the map in the MapFile
 */
public class MapEditorModel {
		private ArrayList<Map> maps;
	
		public MapEditorModel() {
			super();
			this.maps = new ArrayList<Map>();
		}
		
		/**
		 * @param maps arraylist of map
		 */
		public MapEditorModel(ArrayList<Map> maps) {
			super();
			this.maps = maps;
		}

		/**
		 * @param map add a new map into the map arraylist
		 */
		public void add(Map map) {
			maps.add(map);
		}

		/**
		 * @return defaultListModel include all map's name, used for JList
		 */
		public DefaultListModel getMapListModel(){
			DefaultListModel l = new DefaultListModel();
			for (Map i : maps) {
				l.addElement(new ListEntry(i.getName()));
			}
			return l;
		}

		/**
		 * @param index	the index which user select
		 * @return the map of specified index in the map array list
		 */
		public Map getMapByIndex(int index){
			if (index <0 || index > maps.size())
				return null;
			return maps.get(index);
		}

	    /**
	     * @return whole map array list (all map in the map file).
	     */
	    public ArrayList<Map> getMaps() {
		return maps;
	    }
}
