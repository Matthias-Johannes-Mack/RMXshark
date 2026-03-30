package connection;

import Utilities.Flags;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Tests for the Receiver.processMessage method.
 */
public class ReceiverTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		// Reset all filter flags
		Flags.setBool_0x01(false);
		Flags.setBool_0x04(false);
		Flags.setBool_0x06(false);
		Flags.setBool_0x08(false);
		Flags.setBool_0x20(false);
		Flags.setBool_0x24(false);
		Flags.setBool_0x28(false);
	}

	@After
	public void tearDown() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	public void processMessage_emptyMessage_noOutput() {
		Receiver.processMessage(new int[]{});
		assertEquals("", outContent.toString().trim());
	}

	@Test
	public void processMessage_positiveAck_allowsNextRequest() {
		SocketConnector.nextRequestAllowed.set(false);
		Receiver.processMessage(new int[]{0x00, 0x00});
		assertTrue(SocketConnector.nextRequestAllowed.get());
	}

	@Test
	public void processMessage_negativeAck_allowsNextRequest() {
		SocketConnector.nextRequestAllowed.set(false);
		Receiver.processMessage(new int[]{0x01, 0x01});
		assertTrue(SocketConnector.nextRequestAllowed.get());
	}

	@Test
	public void processMessage_negativeAck_unknownOpcode_printsError() {
		Receiver.processMessage(new int[]{0x01, 0x01});
		assertTrue(errContent.toString().contains("unknown OPCODE"));
	}

	@Test
	public void processMessage_negativeAck_lokNotInDb_printsError() {
		Receiver.processMessage(new int[]{0x01, 0x03});
		assertTrue(errContent.toString().contains("lok not in database"));
	}

	@Test
	public void processMessage_negativeAck_inputError_printsError() {
		Receiver.processMessage(new int[]{0x01, 0x04});
		assertTrue(errContent.toString().contains("input error"));
	}

	@Test
	public void processMessage_unknownOpcode_printsWarning() {
		Receiver.processMessage(new int[]{0xFF});
		assertTrue(outContent.toString().contains("unknown OPCODE"));
	}

	@Test
	public void processMessage_0x04_filtered_noOutput() {
		Flags.setBool_0x04(true);
		Receiver.processMessage(new int[]{0x04, 0x01});
		// When filtered, message should not be written to console
		assertFalse(outContent.toString().contains("0x04"));
	}

	@Test
	public void processMessage_0x04_unfiltered_writesOutput() {
		Flags.setBool_0x04(false);
		Receiver.processMessage(new int[]{0x04, 0x01});
		assertTrue(outContent.toString().contains("0x04"));
	}

	@Test
	public void processMessage_0x06_filtered_noOutput() {
		Flags.setBool_0x06(true);
		Receiver.processMessage(new int[]{0x06, 0x01, 0x62, 0x01});
		assertFalse(outContent.toString().contains("0x06"));
	}

	@Test
	public void processMessage_0x06_unfiltered_writesOutput() {
		Flags.setBool_0x06(false);
		Receiver.processMessage(new int[]{0x06, 0x01, 0x62, 0x01});
		assertTrue(outContent.toString().contains("0x06"));
	}

	@Test
	public void processMessage_initResponse_allowsNextRequest() {
		SocketConnector.nextRequestAllowed.set(false);
		Receiver.processMessage(new int[]{0x03, 0x01});
		assertTrue(SocketConnector.nextRequestAllowed.get());
	}
}
