package makro;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class for a macro record
 *
 * @author Matthias Mack 3316380
 */
public class Record implements Serializable{
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -5625099400637018899L;
	/**
	 * Record needs a unique ID
	 */
	private int recordID;
	/**
	 * Arraylist for all recorded strings
	 */
	private ArrayList<String> recordLines;
	/**
	 * Path Variable
	 */
	private String savePath;
	/**
	 * timestamp for the makro
	 */
	private LocalDateTime timestamp;
	
	/**
	 * Constructor
	 */
	public Record(int recordID) {
		this.recordID = recordID;
		// set the dateTime
		this.timestamp = LocalDateTime.now();
	}
}
