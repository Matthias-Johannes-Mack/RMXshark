package console;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Utilities.Constants;

/**
 * Class that shows the About dialog
 *
 * @author Matthias Mack 3316380
 */
public class About {
	/**
	 * Method, that shows the about field
	 */
	static void showAbout() {
		JFrame frame = new JFrame(Constants.DE_SUBMENU_HELP_ITEM_2);
		// hides the form
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// add the label
		JLabel lbl_filter = new JLabel(Constants.DE_SUBMENU_HELP_ITEM_2 + " RMXshark", JLabel.CENTER);
		lbl_filter.setFont(lbl_filter.getFont().deriveFont(14f));
		frame.add(lbl_filter);
		frame.pack();
		frame.setVisible(true);
	}
}
