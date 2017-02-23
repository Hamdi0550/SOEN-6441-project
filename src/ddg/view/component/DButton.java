package ddg.view.component;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ddg.Config;

/**
 * 
 * This class inherit the JButton to build the option button
 * 
 * @author Zhen Du
 * @date Feb 5, 2017
 */
public class DButton extends JButton {

	public DButton() {
		super();
		setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
	}

	public DButton(String text, ActionListener a) {
		super(text);
		setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
		addActionListener(a);
	}
}