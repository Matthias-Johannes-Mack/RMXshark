package Utilities;

/**
 * Class containing constants for the Connection Package
 */
public final class Constants {

	/**
	 * *private Constructor to prevent instantiation
	 */
	private Constants() {
	}

	// Main
	public static final String OPERATING_SYSTEM = System.getProperty("os.name");
	// console
	public static final String DE_RETRY_MESSAGE_RECONNECT = "Erneut verbinden?";
	public static final String DE_RETRY_HEAD_RECONNECT = "Server nicht erreichbar";
	public static final String EOF = "\n";
	public static final String EOF_SYS = System.getProperty("line.separator", EOF);
	// console_menu
	public static final String DE_MENU_NAME = "Debugger";
	public static final String DE_SUBMENU_ITEM_1 = "Schließen";
	public static final String DE_MENU_HELP = "Hilfe";
	public static final String DE_SUBMENU_HELP_ITEM_1 = "About";
	public static final String DE_MENU_FILTER = "Filter";

	public static final String AUTHORS = "Jan Dammrath, Matthias Mack, Angelo Gennaro, Patrick Mürdter";

	// enum for the bus
	public static enum Bus {
		RMX_0, RMX_1
	}

	public static final String DE_BUS_LABEL = "Bus:  ";
	public static final String CONSOLE_DESC = "-> ";

	// Connection
	public static byte RMX_VERSION = 0x01;
	public static final int RMX_HEAD = 0x7c;
	public static final int[] LOKDATENBANK_MESSAGE = new int[] { RMX_HEAD, 0x04, 0x08, 0x01 };
	public static final int[] INITALIZATION_MESSAGE = new int[] { RMX_HEAD, 0x05, 0x03, 0x02, RMX_VERSION };
	public static final int[] POSITIVE_HANDSHAKE = new int[] { RMX_HEAD, 0x04, 0x00, 0x00 };

	// Bus
	public static final int NUMBER_SYSTEMADRESSES_PER_BUS = 112;
	public final static int NUMBER_BITS_PER_BUS = NUMBER_SYSTEMADRESSES_PER_BUS * 8; // each systemadress has 8 bits (1
																						// byte)
	public final static int NUMBER_OF_BUSSES = 1; // TODO wenn mehrere Busse -> variable erhÃ¶hen (fÃ¼r checkall)

}
