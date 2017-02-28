package ddg.model;

import java.util.ArrayList;

import ddg.item.entity.BaseItem;
import ddg.item.entity.ListEntry;
import model.Map;

import javax.swing.*;

public class MapEditorModel {
		private ArrayList<Map> maps;
	
		public MapEditorModel() {
			super();
			this.maps = new ArrayList<Map>();
		}
		
		public MapEditorModel(ArrayList<Map> maps) {
			super();
			this.maps = maps;
		}

		public void add(Map map) {
			maps.add(map);
		}

		public DefaultListModel getMapListModel(){
			DefaultListModel l = new DefaultListModel();
			for (Map i : maps) {
				l.addElement(new ListEntry(i.getName()));
			}
			return l;
		}

		public Map getMapByIndex(int index){
			if (index <0 || index > maps.size())
				return null;
			return maps.get(index);
		}

	    public ArrayList<Map> getMaps() {
		return maps;
	    }

	    public void setMaps(ArrayList<Map> maps) {
		this.maps = maps;
	    }
}
