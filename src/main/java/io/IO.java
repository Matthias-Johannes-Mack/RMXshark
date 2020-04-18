package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Utilities.Constants;
import Utilities.Flags;

public class IO {
	/**
	 * Method that reads the Config and writes it
	 */
	protected static Flags readFile() {
		Flags flags = null;
		try (FileInputStream fis = new FileInputStream(Constants.Config_Filename);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			flags = (Flags) ois.readObject();

		} catch (FileNotFoundException fno) { // if the file is not found, create it

		} catch (Exception e) {
			System.out.println("File could not be readed!");
		}

		return flags;
	}

	/**
	 * Method that creates a file from filepath
	 */
	protected static void writeFile() {
		Flags flags_new = new Flags();
		try (FileOutputStream fos = new FileOutputStream(Constants.Config_Filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(flags_new);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
