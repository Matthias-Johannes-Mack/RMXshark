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
public class Record implements Serializable {
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -5625099400637018899L;
	/**
	 * Arraylist for all recorded strings
	 */
	private ArrayList<int[]> recordLines;
	/**
	 * timestamp for the makro
	 */
	private LocalDateTime timestamp;

	/**
	 * Constructor
	 */
	public Record() {

		// set the dateTime
		this.timestamp = LocalDateTime.now();
	}

	/**
	 * @return the recordLines
	 */
	public ArrayList<int[]> getRecordLines() {
		return recordLines;
	}

	/**
	 * @param recordVals the recordLines to set
	 */
	public void setRecordLines(ArrayList<int[]> recordVals) {
		this.recordLines = recordVals;
	}

	/**
	 * @return the timestamp
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
