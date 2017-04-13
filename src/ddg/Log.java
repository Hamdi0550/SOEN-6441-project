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
	
	/**
	 * Constructor
	 */
	private Log() {
	}

	/**
	 * This method is to get a single instance of Log class.
	 * @return instance
	 */
	public static Log getInstance() {
		if (instance == null) {
			instance = new Log();
		}
		return instance;
	}

	/**
	 * This method is to set text area of the log
	 * @param v
	 */
	public void setView(JTextArea v) {
		textView = v;
	}

	/**
	 * This method is to print the log
	 * @param log
	 */
	public void printLog(String log) {
		if(textView!=null) {
			textView.append(log+"\n");
		}
	}
}
