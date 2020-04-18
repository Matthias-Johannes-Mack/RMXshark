package makro;

import java.io.File;
import java.nio.file.Files;

import javax.swing.ComboBoxModel;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

/**
 * Class that handles the macro recorder
 *
 * @author Matthias Mack 3316380
 */
public class Makro {
	/**
	 * Boolean for the state of the Recorder
	 */
	private static boolean state;

	public static void recordMakro() {
		Recording record = null;
		record.run();
		setState(false);
	}

	public class Recording implements Runnable {

		// TODO implement Makro recorder
		public void run() {
			while (isState()) {
				System.out.println("record...");
			}
		}
	}

	/**
	 * @return the state
	 */
	public static boolean isState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public static void setState(boolean state) {
		Makro.state = state;
	}

	public static ComboBoxModel getFiles() {
		// modell for the data
		ComboBoxModel<String> files;
		// check the filepath
//		if (new File("/Makros/").exists()) {
//			System.out.println("exists");
//		} else { // if not create it
//			new File("/Makros").mkdirs();
//		}
		return null;
	}

}
