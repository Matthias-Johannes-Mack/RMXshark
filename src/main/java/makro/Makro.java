package makro;

/**
 * Class that handles the macro recorder
 *
 * @author Matthias Mack 3316380
 */
public class Makro {
	/**
	 * Boolean for the state of the Recorder
	 */
	private static boolean state = false;

	public static void recordMakro() {
		Recording record = null;
		record.run();
		setState(false);
	}

	public class Recording implements Runnable {

		// TODO implement Makro recorder
		public void run() {
			while (!state) {
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

}
