import java.util.ArrayList;

import connection.SocketConnector;
import console.Console;
import console.Filter;

/**
 * Class for controlling the whole Tool
 *
 * @author Matthias Mack 3316380
 */
public class Main {
	/**
	 * Main method
	 * 
	 * @param args - Arguments
	 */
	public static void main(String[] args) {
		command();
	}

	/**
	 * Method with commands
	 */
	private static void command() {
		// run the console
		Console.runConsole();
		head();
		SocketConnector.Connect();
	}

	/**
	 * Method for the head
	 * 
	 */
	private static void head() {
		System.out.println(
				"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		System.out.println("------------------------------------------RMXshark-----------------------------------");
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		System.out.println(
				"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	}
}
