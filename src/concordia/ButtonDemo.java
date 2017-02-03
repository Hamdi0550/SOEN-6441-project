package concordia;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
Simple demonstration of putting buttons in a JFrame window.
*/
public class ButtonDemo extends JFrame implements ActionListener
{
public static final int WIDTH = 400;
public static final int HEIGHT = 300;
public ButtonDemo()
{
setSize(WIDTH, HEIGHT);
WindowDestroyer listener = new WindowDestroyer();
addWindowListener(listener);
Container contentPane = getContentPane();
contentPane.setBackground(Color.WHITE);
contentPane.setLayout(new FlowLayout());
JButton sunnyButton = new JButton("Sunny");
sunnyButton.addActionListener(this);
contentPane.add(sunnyButton);
JButton cloudyButton = new JButton("Cloudy");
JButton cloudy2Button = new JButton("Cloudy2");
cloudyButton.addActionListener(this);
cloudy2Button.addActionListener(this);
contentPane.add(cloudyButton);
contentPane.add(cloudy2Button);
}
public void actionPerformed(ActionEvent e)
{
String actionCommand = e.getActionCommand();
Container contentPane = getContentPane();
if (actionCommand.equals("Sunny"))
contentPane.setBackground(Color.BLUE);
else if (actionCommand.equals("Cloudy"))
contentPane.setBackground(Color.GRAY);
else if (actionCommand.equals("Cloudy2")){
	contentPane.setBackground(Color.RED);
	System.out.println("£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡");	
}

else
System.out.println("Error in button interface.");
}
}