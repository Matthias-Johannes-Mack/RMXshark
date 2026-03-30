package makro;

import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Tests for the Record class.
 */
public class RecordTest {

	@Test
	public void constructor_setsTimestamp() {
		LocalDateTime before = LocalDateTime.now();
		Record record = new Record();
		LocalDateTime after = LocalDateTime.now();

		assertNotNull(record.getTimestamp());
		assertFalse(record.getTimestamp().isBefore(before));
		assertFalse(record.getTimestamp().isAfter(after));
	}

	@Test
	public void setAndGetRecordLines() {
		Record record = new Record();
		ArrayList<int[]> lines = new ArrayList<>();
		lines.add(new int[]{0x7C, 0x04, 0x00, 0x00});
		lines.add(new int[]{0x7C, 0x05, 0x03, 0x02, 0x01});

		record.setRecordLines(lines);

		assertNotNull(record.getRecordLines());
		assertEquals(2, record.getRecordLines().size());
		assertArrayEquals(new int[]{0x7C, 0x04, 0x00, 0x00}, record.getRecordLines().get(0));
		assertArrayEquals(new int[]{0x7C, 0x05, 0x03, 0x02, 0x01}, record.getRecordLines().get(1));
	}

	@Test
	public void setAndGetTimestamp() {
		Record record = new Record();
		LocalDateTime customTime = LocalDateTime.of(2024, 1, 15, 10, 30, 0);
		record.setTimestamp(customTime);

		assertEquals(customTime, record.getTimestamp());
	}

	@Test
	public void serialization_roundTrip() throws IOException, ClassNotFoundException {
		Record original = new Record();
		ArrayList<int[]> lines = new ArrayList<>();
		lines.add(new int[]{0x7C, 0x04, 0x08, 0x01});
		original.setRecordLines(lines);

		// Serialize
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(original);
		oos.close();

		// Deserialize
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bais);
		Record deserialized = (Record) ois.readObject();
		ois.close();

		assertNotNull(deserialized);
		assertNotNull(deserialized.getRecordLines());
		assertEquals(1, deserialized.getRecordLines().size());
		assertArrayEquals(new int[]{0x7C, 0x04, 0x08, 0x01}, deserialized.getRecordLines().get(0));
		assertEquals(original.getTimestamp(), deserialized.getTimestamp());
	}

	@Test
	public void emptyRecordLines_returnsNull() {
		Record record = new Record();
		assertNull(record.getRecordLines());
	}
}
