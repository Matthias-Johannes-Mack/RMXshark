package makro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import Utilities.Constants;
import connection.Sender;

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

	/**
	 * Method that returns the count of makros in the folder
	 * 
	 * @return - int count of makros
	 */
	public static int getMakroCount() {
		// if the folder Makros exists
		if (new File(Constants.MAKRO_FOLDERNAME).exists()) {
			// return the count of makros avalible
			return new File(Constants.MAKRO_FOLDERNAME).listFiles().length;
		} else {
			return 0;
		}
	}

	/**
	 * Method that returns all files from the Folder makros
	 * 
	 * @return-file array with all files in it
	 */
	public static File[] getMakroFileList() {
		// if the folder Makros exists
		if (new File(Constants.MAKRO_FOLDERNAME).exists()) {
			// return the count of makros avalible
			return new File(Constants.MAKRO_FOLDERNAME).listFiles();
		}
		// else return null
		return null;
	}

	/**
	 * Method that save a makro to a file
	 * 
	 * @param makroCount
	 */
	public static void saveMakro(int makroCount) {
		Record record = new Record();
		record.setRecordLines(recordVals);
		// try to write the makro to a file in the makros folder
		// makros/makro_XY.mk
		try (FileOutputStream fos = new FileOutputStream(Constants.MAKRO_FOLDERNAME + "/" + Constants.MAKRO_FILENAME
				+ makroCount + Constants.MAKRO_FILEEXTENSION); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			// write the object
			oos.writeObject(record);
		} catch (IOException e) { // on error put out the error message
			System.out.println(Constants.DE_MAKRO_CREATION_FAILURE);
		}
	}

	/**
	 * Runs the specified makro from the menus dropdown
	 * 
	 * @param makroName
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void runMakro(String makroName) throws IOException, ClassNotFoundException {
		// catch the null
		if (makroName != null) {
			// create the input stream
			FileInputStream fis = new FileInputStream(
					Constants.MAKRO_FOLDERNAME + "/" + makroName + Constants.MAKRO_FILEEXTENSION);
			// get teh object
			ObjectInputStream ois = new ObjectInputStream(fis);
			// get the class
			Record record = (Record) ois.readObject();
			// catch the null
			if (record != null) {
				// go through the actions
				for (int[] arr : record.getRecordLines()) {
					System.out.println("-> " + Arrays.toString(arr));
					// add the array
					Sender.addMessageQueue(arr);
				}
			}
		}
	}
}