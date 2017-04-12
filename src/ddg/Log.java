package ddg;

import javax.swing.JTextArea;

/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 12, 2017
 */
public class Log {

	private static Log instance;
	private JTextArea textView;
	
	private Log() {
	}

	public static Log getInstance() {
		if (instance == null) {
			instance = new Log();
		}
		return instance;
	}
	
	public void setView(JTextArea v) {
		textView = v;
	}
	
	public void printLog(String log) {
		if(textView!=null) {
			textView.append(log+"\n");
		}
	}
}
