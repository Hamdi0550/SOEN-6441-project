package ddg.model;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class FighterEditorModel {
	private ArrayList<Fighter> fighters;

	public FighterEditorModel() {
		super();
		this.setFigthers(new ArrayList<Fighter>());
	}

	public FighterEditorModel(ArrayList<Fighter> figthers) {
		super();
		this.setFigthers(figthers);
	}

	public ArrayList<Fighter> getFigthers() {
		return fighters;
	}

	public void setFigthers(ArrayList<Fighter> figthers) {
		this.fighters = figthers;
	}

	public DefaultListModel getListModel() {
		DefaultListModel l = new DefaultListModel();
		for (Fighter i : fighters) {
			l.addElement(i.getName());
		}
		return l;
	}

	public Fighter getItemByIndex(int index) {
		if (index < 0 || index > fighters.size() - 1)
			return null;
		return fighters.get(index);
	}
}
