package ddg.view.component;

import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
 * This class inherit the JButton to build the option button
 * 
 * @author Zhen Du
 * @date Feb 5, 2017
 */
public class DButton extends JButton {

	public DButton() {
		super();
	}

	public DButton(String text, ActionListener a) {
		super(text);
		addActionListener(a);
	}
}