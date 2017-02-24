package ddg.model;

import java.util.ArrayList;

import ddg.item.entity.BaseItem;
import model.Map;

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
}
