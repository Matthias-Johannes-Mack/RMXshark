package connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Tests for the OutputUtil class.
 */
public class OutputUtilTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void tearDown() {
		System.setOut(originalOut);
	}

	@Test
	public void writeMsgToConsole_singleByte_formatsCorrectly() {
		OutputUtil.writeMsgToConsole(new int[]{0x7C});
		String output = outContent.toString().trim();
		assertEquals("0x7c", output);
	}

	@Test
	public void writeMsgToConsole_multipleBytes_formatsCorrectly() {
		OutputUtil.writeMsgToConsole(new int[]{0x7C, 0x04, 0x08});
		String output = outContent.toString().trim();
		assertEquals("0x7c 0x04 0x08", output);
	}

	@Test
	public void writeMsgToConsole_zeroByte_formatsTwoDigits() {
		OutputUtil.writeMsgToConsole(new int[]{0x00});
		String output = outContent.toString().trim();
		assertEquals("0x00", output);
	}

	@Test
	public void writeMsgToConsole_emptyArray_printsEmptyLine() {
		OutputUtil.writeMsgToConsole(new int[]{});
		String output = outContent.toString().trim();
		assertEquals("", output);
	}

	@Test
	public void writeMsgToConsole_fullHandshake_formatsCorrectly() {
		OutputUtil.writeMsgToConsole(new int[]{0x7C, 0x04, 0x00, 0x00});
		String output = outContent.toString().trim();
		assertEquals("0x7c 0x04 0x00 0x00", output);
	}

	@Test
	public void writeMsgToConsole_highValues_formatsCorrectly() {
		OutputUtil.writeMsgToConsole(new int[]{0xFF, 0xC0});
		String output = outContent.toString().trim();
		assertEquals("0xff 0xc0", output);
	}
}
