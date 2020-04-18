package console;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Utilities.Constants;
import Utilities.Constants.Bus;
import javafx.scene.control.ComboBox;
import makro.Makro;

public class Menu {
	static JComboBox comboBox;
	
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

		// Create the menu bar
		menuBar = new JMenuBar();
		jFrame.setJMenuBar(menuBar);

		// add a wrapper for design purpose
		JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// add a label for the bus chooser
		final JLabel jLabel = new JLabel(Constants.DE_BUS_LABEL);
		wrapper.add(jLabel);
		// add the bus chooser with values from Constants.bus enum
		comboBox = new JComboBox(Constants.Bus.values());
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
		// ---------------Makro-Field-------------------------------------------------------
		JButton btn_makro = new JButton("O");
		btn_makro.setFont(new Font("Dialog", Font.BOLD, 13));
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
				} else {
					btn_makro.setForeground(Color.BLACK);
				}
			}
		});
		// add the wrapper to the menu
		menuBar.add(wrapper_filter);
		// add a dropdown for the Makros
		// add the Makro filelist
		JComboBox<String> cmb_Makro = new JComboBox();
		// TODO add the file list
		cmb_Makro.addItem("MakroXY");
		cmb_Makro.setVisible(true);
		cmb_Makro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// put out the choosen bus
				System.out.println("------------------------------");
				System.out.println("Bus: " + cmb_Makro.getSelectedItem().toString());
				System.out.println("------------------------------");
			}
		});
		wrapper_filter.add(cmb_Makro);

		// ---------------------------------------------------------------------------
		// Built the Filter-------------------------------------------------------
		JButton btn_Filter = new JButton(Constants.DE_MENU_FILTER);
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

}