package console;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Utilities.Constants;
import Utilities.Flags;

/**
 * Class for filtering values by an Filter Array
 *
 * @author Matthias Mack 3316380
 */
public class Filter {
	/**
	 * Arraylist with all values which can be filtered by
	 */
	private static ArrayList<String> uniqueEntries;

	/**
	 * Constructor for init
	 */
	private Filter() {
		Filter.uniqueEntries = new ArrayList<>();
	}

	/**
	 * Method, that shows the filter Form
	 */
	protected static synchronized void showFilter() {
		// show the filter
		JFrame frame = new JFrame(Constants.DE_MENU_FILTER);
		// hides the form
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// the  filter frame does not need to bee resized
		frame.setResizable(false);
		// put in the padding / border
		JPanel p = new JPanel();
		p.setBorder(new EmptyBorder(10, 10, 10, 10));
		// add the label
		JLabel lbl_filter = new JLabel(Constants.DE_MENU_FILTER, JLabel.CENTER);
		p.add(lbl_filter, BorderLayout.NORTH);
		// ----------------------------------------------------------------------
		// Checkboxes for each Status
		// ----------------------------------------------------------------------
		// -----------------0x01------------------------------------------------
		JCheckBox chkBox_status_0x01 = new JCheckBox("0x01");
		chkBox_status_0x01.setSelected(false);
		chkBox_status_0x01.addActionListener(new ActionListener() {
			// if checkbox is pressed check it
			public void actionPerformed(ActionEvent e) {
				// status filtered 0x01
				if (chkBox_status_0x01.isSelected()) {
					Flags.setBool_0x01(true);
				} else {
					Flags.setBool_0x01(false);
				}
			}
		});
		p.add(chkBox_status_0x01, BorderLayout.NORTH);
		// -----------------0x04------------------------------------------------
		JCheckBox chkBox_status_0x04 = new JCheckBox("0x04");
		chkBox_status_0x04.setSelected(false);
		chkBox_status_0x04.addActionListener(new ActionListener() {
			// if checkbox is pressed check it
			public void actionPerformed(ActionEvent e) {
				// status filtered 0x04
				if (chkBox_status_0x04.isSelected()) {
					Flags.setBool_0x04(true);
				} else {
					Flags.setBool_0x04(false);
				}
			}
		});
		p.add(chkBox_status_0x04, BorderLayout.NORTH);
		// ----------------------------------------------------------------------
		// -----------------0x06------------------------------------------------
		JCheckBox chkBox_status_0x06 = new JCheckBox("0x06");
		chkBox_status_0x06.setSelected(false);
		chkBox_status_0x06.addActionListener(new ActionListener() {
			// if checkbox is pressed check it
			public void actionPerformed(ActionEvent e) {
				// status filtered 0x06
				if (chkBox_status_0x06.isSelected()) {
					Flags.setBool_0x06(true);
				} else {
					Flags.setBool_0x06(false);
				}
			}
		});
		p.add(chkBox_status_0x06, BorderLayout.NORTH);
		// ----------------------------------------------------------------------
		// -----------------0x08------------------------------------------------
		JCheckBox chkBox_status_0x08 = new JCheckBox("0x08");
		chkBox_status_0x08.setSelected(false);
		chkBox_status_0x08.addActionListener(new ActionListener() {
			// if checkbox is pressed check it
			public void actionPerformed(ActionEvent e) {
				// status filtered 0x08
				if (chkBox_status_0x08.isSelected()) {
					Flags.setBool_0x08(true);
				} else {
					Flags.setBool_0x08(false);
				}
			}
		});
		p.add(chkBox_status_0x08, BorderLayout.NORTH);
		// ----------------------------------------------------------------------
		// -----------------0x20------------------------------------------------
		JCheckBox chkBox_status_0x20 = new JCheckBox("0x20");
		chkBox_status_0x20.setSelected(false);
		chkBox_status_0x20.addActionListener(new ActionListener() {
			// if checkbox is pressed check it
			public void actionPerformed(ActionEvent e) {
				// status filtered 0x20
				if (chkBox_status_0x20.isSelected()) {
					Flags.setBool_0x20(true);
				} else {
					Flags.setBool_0x20(false);
				}
			}
		});
		p.add(chkBox_status_0x20, BorderLayout.NORTH);
		// ----------------------------------------------------------------------
		// -----------------0x24------------------------------------------------
		JCheckBox chkBox_status_0x24 = new JCheckBox("0x24");
		chkBox_status_0x24.setSelected(false);
		chkBox_status_0x24.addActionListener(new ActionListener() {
			// if checkbox is pressed check it
			public void actionPerformed(ActionEvent e) {
				// status filtered 0x24
				if (chkBox_status_0x24.isSelected()) {
					Flags.setBool_0x24(true);
				} else {
					Flags.setBool_0x24(false);
				}
			}
		});
		p.add(chkBox_status_0x24, BorderLayout.NORTH);
		// ----------------------------------------------------------------------
		// -----------------0x28------------------------------------------------
		JCheckBox chkBox_status_0x28 = new JCheckBox("0x28");
		chkBox_status_0x28.setSelected(false);
		chkBox_status_0x28.addActionListener(new ActionListener() {
			// if checkbox is pressed check it
			public void actionPerformed(ActionEvent e) {
				// status filtered 0x08
				if (chkBox_status_0x28.isSelected()) {
					Flags.setBool_0x28(true);
				} else {
					Flags.setBool_0x28(false);
				}
			}
		});
		p.add(chkBox_status_0x28, BorderLayout.NORTH);
		// ----------------------------------------------------------------------
		// add the panel
		frame.add(p);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * @return the uniqueEntries
	 */
	public static synchronized ArrayList<String> getUniqueEntries() {
		return uniqueEntries;
	}

	/**
	 * @param uniqueEntries the uniqueEntries to set
	 */
	public static synchronized void setUniqueEntries(ArrayList<String> uniqueEntries) {
		Filter.uniqueEntries = uniqueEntries;
	}
}
