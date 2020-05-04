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
import console.PopUp_IP_Port;
import javafx.application.Application;
import newDesign.ConsoleController;
import tutorial.TutorialController;

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
		command(args);
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
	private static void command(String[] args) {
		// create the makro folder, if it does not exists
		new File(Constants.MAKRO_FOLDERNAME).mkdir();
		// run the console
		Console.runConsole();
		 Thread thread = new Thread(){
			    public void run(){
			    	Application.launch(TutorialController.class, args);
			    }
			  };
		 thread.start();
		
		// show popup before connecting
		PopUp_IP_Port.showPopup();
		// wait & notify
		while (PopUp_IP_Port.isDisplayed()) {

		}
		SocketConnector.Connect();
	}

}
