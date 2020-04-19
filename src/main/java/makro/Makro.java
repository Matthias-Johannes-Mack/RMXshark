package makro;

import java.util.ArrayList;

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

	private static volatile ArrayList<int[]> recordVals;

	public Makro() {
		Makro.recordVals = new ArrayList<>();
	}

	/**
	 * Add lines to the Array
	 * 
	 * @param intArr
	 */
	public static synchronized void addLines(int[] intArr) {
		recordVals.add(intArr);
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

	/**
	 * Get the size of the makro array
	 * 
	 * @return - 0 if the list is empty 
	 */
	public static int getSize() {
		if (recordVals == null) {
			return 0;
		} else {
			return recordVals.size();
		}
	}
}
