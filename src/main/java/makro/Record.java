package makro;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for a macro record
 *
 * @author Matthias Mack 3316380
 */
public class Record implements Serializable{
	/**
	 * Record needs a unique ID
	 */
	private String recordID;
	/**
	 * Arraylist for all recorded strings
	 */
	private ArrayList<String> recordLines;
	/**
	 * Path Variable
	 */
	private String savePath;
	/**
	 * Constructor
	 */
	public Record() {
		
	}
}
