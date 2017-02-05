package ddg.view.component;

import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
 * This class inherit the JButton to build the option button
 * 
 * @author Zhen Du
 * @date Feb 5, 2017
 */
public class OButton extends JButton {

	public OButton() {
		super();
	}

	public OButton(String text, ActionListener a) {
		super(text);
		addActionListener(a);
	}
}