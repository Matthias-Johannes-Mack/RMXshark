package console;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import Utilities.Constants;
import connection.Sender;
import makro.Makro;

public class Menu {
	/**
	 * Combobox for the menu
	 */
	static JComboBox comboBox;

	static JComboBox<String> cmb_Makro;

	static String pathStr;

	/**
	 * Method that creates a menu on the jframe
	 * 
	 * @param jFrame
	 */
	static void createMenu(JFrame jFrame) {
		// add the menu
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		Font font = new Font("Dialog", Font.BOLD, 12);

		// Create the menu bar
		menuBar = new JMenuBar();
		jFrame.setJMenuBar(menuBar);

		// add a wrapper for design purpose
		JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// add a label for the bus chooser
		final JLabel jLabel = new JLabel(Constants.DE_BUS_LABEL);
		jLabel.setFont(font);
		wrapper.add(jLabel);
		// add the bus chooser with values from Constants.bus enum
		comboBox = new JComboBox(Constants.Bus.values());
		comboBox.setFont(font);
		comboBox.setVisible(true);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// put out the choosen bus
				System.out.println("------------------------------");
				System.out.println("Bus: " + comboBox.getSelectedItem().toString());
				System.out.println("------------------------------");
			}
		});
		wrapper.add(comboBox);
		// add the wrapper to the menu
		menuBar.add(wrapper);

		// add a wrapper for design purpose
		JPanel wrapper_filter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// Built the Filter-------------------------------------------------------
		JButton btn_EMERGENCY = new JButton(Constants.DE_MENU_EMERGENCY);
		btn_EMERGENCY.setFont(font);
		wrapper_filter.add(btn_EMERGENCY);
		btn_EMERGENCY.addActionListener(new ActionListener() {
			// show the filter
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO implement emergency button
				Sender.addMessageQueue(new int[] {} );
			}
		});
		// add the wrapper to the menu
		menuBar.add(wrapper_filter);
		// ---------------Makro-Field-------------------------------------------------------
		JButton btn_makro = new JButton("O");
		btn_makro.setFont(font);
		wrapper_filter.add(btn_makro);
		// add the tooltip
		btn_makro.setToolTipText(Constants.DE_MAKRO_TOOLTIP);
		// add the action
		btn_makro.addActionListener(new ActionListener() {
			// show the filter
			@Override
			public void actionPerformed(ActionEvent e) {
				// set flag to run or if it is running kill the record
				if (Makro.isState()) {
					Makro.setState(false);
				} else {
					Makro.setState(true);
				}
				// set the color of the button
				if (Makro.isState()) {
					btn_makro.setForeground(Color.RED);
					// set the status
					System.out.println("-----------------------------");
					System.out.println(Constants.DE_MAKRO_RUNNING);
					System.out.println("-----------------------------");
				} else {
					btn_makro.setForeground(Color.BLACK);
					System.out.println("-----------------------------");
					System.out.println(Constants.DE_MAKRO_DONE);
					// put out the number of done Actions
					System.out.println("-> " + Makro.getSize() + Constants.DE_MAKRO_DONE_MESSAGES);
					System.out.println("-----------------------------");
					// implement write Makro to file by Makro.writeMakroToFile();
					if (Makro.getSize() > 0) {
						// saves a Makro by increment with one
						Makro.saveMakro(Makro.getMakroCount() + 1);
						System.out.println("Count Makros " + Makro.getMakroCount());
						// refresh the makrofield
						cmb_Makro.removeAll();
						refreshMakroCombo();
					}
				}
			}
		});
		// add the wrapper to the menu
		menuBar.add(wrapper_filter);
		// add a dropdown for the Makros
		// add the Makro filelist
		cmb_Makro = new JComboBox();
		cmb_Makro.setFont(font);
		// add the default, empty value
		cmb_Makro.addItem(" ");
		// refresh the thing
		refreshMakroCombo();
		cmb_Makro.setVisible(true);
		// run the makro
		cmb_Makro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// if it is not the empty value, try to run the makro
					if (!cmb_Makro.getSelectedItem().toString().equals(" ")) {
						// run the choosen makro
						System.out.println("------------------------------");
						System.out.println("Run Makro: " + cmb_Makro.getSelectedItem().toString());
						System.out.println("------------------------------");
						Makro.runMakro(cmb_Makro.getSelectedItem().toString());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		wrapper_filter.add(cmb_Makro);
		// ---------------------------------------------------------------------------
		// Built the Filter-------------------------------------------------------
		JButton btn_Filter = new JButton(Constants.DE_MENU_FILTER);
		btn_Filter.setFont(font);
		wrapper_filter.add(btn_Filter);
		btn_Filter.addActionListener(new ActionListener() {
			// show the filter
			@Override
			public void actionPerformed(ActionEvent e) {
				Filter.showFilter();
			}
		});
		// add the wrapper to the menu
		menuBar.add(wrapper_filter);
		// ---------------------------------------------------------------------------
		// Build the second menu
		menu = new JMenu(Constants.DE_MENU_HELP);
		menuBar.add(menu);
		// the submenu
		menuItem = new JMenuItem(Constants.DE_SUBMENU_HELP_ITEM_1);
		// calls the about field
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// show the about form
				About.showAbout();
			}
		});
		menu.add(menuItem);
	}

	private static void refreshMakroCombo() {
		// get all files in the makros folder
		if (Makro.getMakroFileList() != null) {
			for (File f : Makro.getMakroFileList()) {
				// get the pathName
				pathStr = f.getName().toString();
				// add item to the combobox
				cmb_Makro.addItem(pathStr);
			}
		}
	}
}
