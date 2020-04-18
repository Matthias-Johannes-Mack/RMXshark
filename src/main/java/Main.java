import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Utilities.Constants;
import Utilities.Flags;
import connection.SocketConnector;
import console.Console;

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
		readInit();
	}

	/**
	 * Method that reads the Config and writes it
	 */
	protected static Flags readInit() {
		Flags flags = null;
		try (FileInputStream fis = new FileInputStream(Constants.Config_Filename);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			 flags = (Flags) ois.readObject();
			
		} catch (FileNotFoundException fno) { // if the file is not found, create it
			createConfig();
		} catch (Exception e) {
			System.out.println("Config could not be readed!");
		}
	
		return flags;
	}

	/**
	 * Method that creates a config file for the flags
	 */
	protected static void createConfig() {
		Flags flags_new = new Flags();
		try (FileOutputStream fos = new FileOutputStream(Constants.Config_Filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(flags_new);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method with commands
	 */
	private static void command() {
		// create the makro folder, if it does not exists
		new File("Makros").mkdir();
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
