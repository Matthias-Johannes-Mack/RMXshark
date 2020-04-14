package console;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

import Utilities.Constants;

/**
 * Class that represents a console for the Output!
 *
 * @author Matthias Mack 3316380
 */
public class Console extends OutputStream {
	/**
	 * Appender for the recent actions
	 */
	private Append append;
	/**
	 * Bytearray for the data
	 */
	private byte[] byteArr;

	/**
	 * Constructor with init Textarea
	 * 
	 * @param jtxtarea - JTextArea with 1500 lines
	 */
	public Console(JTextArea jtxtarea) {
		this(jtxtarea, 1500);
	}

	/**
	 * Constructor
	 * 
	 * @param jtxtarea - the textarea
	 * @param maxLines - maximum lines
	 */
	public Console(JTextArea jtxtarea, int maxLines) {
		if (maxLines < 1) {
			throw new IllegalArgumentException("Maximale Zeilen müssen positiv sein! " + maxLines);
		}
		// set the array to length 1
		byteArr = new byte[1];
		// initiate the new Textarea
		append = new Append(jtxtarea, maxLines);
	}

	/**
	 * Method that runs the console
	 */
	public static void runConsole() {
		// create the frame
		JFrame jFrame = new JFrame("RMXshark");
		jFrame.add(new JLabel("RMXshark Console"), BorderLayout.NORTH);
		// create the menu
		createMenu(jFrame);
		// --------------------------------------------------------------
		JTextArea jTextArea = new JTextArea();
		Console console = new Console(jTextArea, 60);

		// the stream for the console -> redirect everything
		PrintStream printStream = new PrintStream(console);
		System.setOut(printStream);
		System.setErr(printStream);

		// add the frame
		jFrame.add(new JScrollPane(jTextArea));
		// read only
		jTextArea.setEditable(false);
		jFrame.pack();
		// visibility
		jFrame.setVisible(true);
		// size of the frame
		jFrame.setSize(800, 550);
		// exit on close
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// bigger font size 12 pt
		jTextArea.setFont(jTextArea.getFont().deriveFont(14f));
	}

	/**
	 * Method that creates a menu on the jframe
	 * 
	 * @param jFrame
	 */
	private static void createMenu(JFrame jFrame) {
		// add the menu
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		// Create the menu bar
		menuBar = new JMenuBar();
		jFrame.setJMenuBar(menuBar);
		// Build the first menu
		menu = new JMenu(Constants.MENU_NAME);
		menuBar.add(menu);
		// the submenu
		menuItem = new JMenuItem(Constants.SUBMENU_ITEM_1);
		menu.add(menuItem);
		// exit if close is halted
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// exit programm
				System.exit(0);
			}
		});

		// add a wrapper for design purpose
		JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// add a label for the bus chooser
		final JLabel jLabel = new JLabel(Constants.BUS_LABEL);
		wrapper.add(jLabel);
		// add the bus chooser with values from Constants.bus enum
		final JComboBox comboBox = new JComboBox(Constants.Bus.values());
		comboBox.setVisible(true);
		wrapper.add(comboBox);
		// add the wrapper to the menu
		menuBar.add(wrapper);
		// Build the second menu
		menu = new JMenu(Constants.MENU_HELP);
		menuBar.add(menu);
		// the submenu
		menuItem = new JMenuItem(Constants.SUBMENU_HELP_ITEM_1);
		menu.add(menuItem);
	}

	/**
	 * Clear the console
	 */
	public synchronized void clear() {
		// if there are no more lines clear
		if (append != null) {
			append.clear();
		}
	}

	/**
	 * write a single byte to JtextBox
	 */
	public synchronized void write(int val) {
		byteArr[0] = (byte) val;
		write(byteArr, 0, 1);
	}

	/**
	 * write an byte Array to textBox
	 */
	public synchronized void write(byte[] byteArr) {
		write(byteArr, 0, byteArr.length);
	}

	public synchronized void write(byte[] byteArr, int startPoint, int arrLenght) {
		if (append != null) {
			append.append(bytesToString(byteArr, startPoint, arrLenght));
		}
	}

	static private String bytesToString(byte[] byteArr, int startPoint, int arrLength) {
		try {
			return new String(byteArr, startPoint, arrLength, "UTF-8");
		} catch (UnsupportedEncodingException thr) {
			return new String(byteArr, startPoint, arrLength);
		}
	}

	/**
	 * Closes the console
	 */
	public synchronized void close() {
		append = null;
	}

	/**
	 * Class for the runnable Append
	 *
	 * @author Matthias Mack 3316380
	 */
	static class Append implements Runnable {
		/*
		 * the text Area
		 */
		private final JTextArea jTextArea;
		/*
		 * maximum lines of code
		 */
		private final int maxLines;
		/**
		 * Lists with the lengs and the vals of the textArea
		 */
		private final LinkedList<Integer> lengths;
		private final List<String> vals;

		private int currentLen;
		private boolean clear;
		private boolean queue;

		/**
		 * The Constructor of the Append
		 * 
		 * @param jTxtArea
		 * @param maxLines
		 */
		Append(JTextArea jTxtArea, int maxLines) {
			this.jTextArea = jTxtArea;
			this.maxLines = maxLines;
			this.lengths = new LinkedList<Integer>();
			this.vals = new ArrayList<String>();

			this.currentLen = 0;
			this.clear = false;
			this.queue = true;
		}

		/**
		 * Appends a val
		 * 
		 * @param val
		 */
		synchronized void append(String val) {
			vals.add(val);
			if (queue) {
				queue = false;
				EventQueue.invokeLater(this);
			}
		}

		/**
		 * Clears the console
		 */
		synchronized void clear() {
			clear = true;
			currentLen = 0;
			lengths.clear();
			vals.clear();
			if (queue) {
				queue = false;
				EventQueue.invokeLater(this);
			}
		}

		// regular run method for Runnable
		public synchronized void run() {
			if (clear) {
				jTextArea.setText("");
			}
			// for each String loop
			for (String val : vals) {
				currentLen += val.length();
				if (val.endsWith(Constants.EOF) || val.endsWith(Constants.EOF_SYS)) {
					if (lengths.size() >= maxLines) {
						jTextArea.replaceRange("", 0, lengths.removeFirst());
					}
					lengths.addLast(currentLen);
					currentLen = 0;
				}
				jTextArea.append(val);
			}
			vals.clear();
			clear = false;
			queue = true;
		}
	}
}