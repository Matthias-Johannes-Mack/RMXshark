package Utilities;

import java.io.Serializable;

/**
 * Class that handles all public flags
 *
 * @author Matthias Mack 3316380
 */
public final class Flags implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Boolean for the first programm start / show language selection
	 */
	public static boolean first_start = true;
	/**
	 * @return the first_start
	 */
	public static boolean isFirst_start() {
		return first_start;
	}

	/**
	 * @param first_start the first_start to set
	 */
	public static void setFirst_start(boolean first_start) {
		Flags.first_start = first_start;
	}

	// ----------------------------------------------------
	// Flags for filtering the states
	// -----------------------------------------------------
	/**
	 * Boolean for the status
	 */
	public static boolean bool_0x01 = false;

	/**
	 * Boolean for the status
	 */
	public static boolean bool_0x04 = false;

	/**
	 * Boolean for the OPcode 0x06
	 */
	public static boolean bool_0x06 = false;

	/**
	 * Boolean for the OPcode 0x08
	 */
	public static boolean bool_0x08 = false;

	/**
	 * Boolean for the OPcode 0x20
	 */
	public static boolean bool_0x20 = false;

	/**
	 * Boolean for the OPcode 0x24
	 */
	public static boolean bool_0x24 = false;

	/**
	 * Boolean for the OPcode 0x28
	 */
	public static boolean bool_0x28 = false;

	/**
	 * @return the bool_0x01
	 */
	public static boolean isBool_0x01() {
		return bool_0x01;
	}

	/**
	 * @param bool_0x01 the bool_0x01 to set
	 */
	public static void setBool_0x01(boolean bool_0x01) {
		Flags.bool_0x01 = bool_0x01;
	}

	/**
	 * @return the bool_0x04
	 */
	public static boolean isBool_0x04() {
		return bool_0x04;
	}

	/**
	 * @param bool_0x04 the bool_0x04 to set
	 */
	public static void setBool_0x04(boolean bool_0x04) {
		Flags.bool_0x04 = bool_0x04;
	}

	/**
	 * @return the bool_0x06
	 */
	public static boolean isBool_0x06() {
		return bool_0x06;
	}

	/**
	 * @param bool_0x06 the bool_0x06 to set
	 */
	public static void setBool_0x06(boolean bool_0x06) {
		Flags.bool_0x06 = bool_0x06;
	}

	/**
	 * @return the bool_0x08
	 */
	public static boolean isBool_0x08() {
		return bool_0x08;
	}

	/**
	 * @param bool_0x08 the bool_0x08 to set
	 */
	public static void setBool_0x08(boolean bool_0x08) {
		Flags.bool_0x08 = bool_0x08;
	}

	/**
	 * @return the bool_0x20
	 */
	public static boolean isBool_0x20() {
		return bool_0x20;
	}

	/**
	 * @param bool_0x20 the bool_0x20 to set
	 */
	public static void setBool_0x20(boolean bool_0x20) {
		Flags.bool_0x20 = bool_0x20;
	}

	/**
	 * @return the bool_0x24
	 */
	public static boolean isBool_0x24() {
		return bool_0x24;
	}

	/**
	 * @param bool_0x24 the bool_0x24 to set
	 */
	public static void setBool_0x24(boolean bool_0x24) {
		Flags.bool_0x24 = bool_0x24;
	}

	/**
	 * @return the bool_0x28
	 */
	public static boolean isBool_0x28() {
		return bool_0x28;
	}

	/**
	 * @param bool_0x28 the bool_0x28 to set
	 */
	public static void setBool_0x28(boolean bool_0x28) {
		Flags.bool_0x28 = bool_0x28;
	}
}
