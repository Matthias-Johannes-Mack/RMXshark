package Utilities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the Constants class.
 */
public class ConstantsTest {

	@Test
	public void rmxHead_isCorrectValue() {
		assertEquals(0x7C, Constants.RMX_HEAD);
	}

	@Test
	public void rmxVersion_isCorrectValue() {
		assertEquals(0x01, Constants.RMX_VERSION);
	}

	@Test
	public void positiveHandshake_hasCorrectFormat() {
		int[] expected = {0x7C, 0x04, 0x00, 0x00};
		assertArrayEquals(expected, Constants.POSITIVE_HANDSHAKE);
	}

	@Test
	public void lokdatenbankMessage_hasCorrectFormat() {
		int[] expected = {0x7C, 0x04, 0x08, 0x01};
		assertArrayEquals(expected, Constants.LOKDATENBANK_MESSAGE);
	}

	@Test
	public void initalizationMessage_hasCorrectFormat() {
		int[] expected = {0x7C, 0x05, 0x03, 0x02, 0x01};
		assertArrayEquals(expected, Constants.INITALIZATION_MESSAGE);
	}

	@Test
	public void positiveHandshake_startsWithHead() {
		assertEquals(Constants.RMX_HEAD, Constants.POSITIVE_HANDSHAKE[0]);
	}

	@Test
	public void numberSystemAdressesPerBus_is112() {
		assertEquals(112, Constants.NUMBER_SYSTEMADRESSES_PER_BUS);
	}

	@Test
	public void numberBitsPerBus_is896() {
		assertEquals(112 * 8, Constants.NUMBER_BITS_PER_BUS);
	}

	@Test
	public void busEnum_hasTwoValues() {
		assertEquals(2, Constants.Bus.values().length);
	}

	@Test
	public void busEnum_containsRMX0AndRMX1() {
		assertEquals(Constants.Bus.RMX_0, Constants.Bus.valueOf("RMX_0"));
		assertEquals(Constants.Bus.RMX_1, Constants.Bus.valueOf("RMX_1"));
	}

	@Test
	public void configFilename_isNotNull() {
		assertNotNull(Constants.Config_Filename);
		assertFalse(Constants.Config_Filename.isEmpty());
	}

	@Test
	public void makroFoldername_isNotNull() {
		assertNotNull(Constants.MAKRO_FOLDERNAME);
		assertEquals("Makros", Constants.MAKRO_FOLDERNAME);
	}

	@Test
	public void makroFileextension_isMk() {
		assertEquals(".mk", Constants.MAKRO_FILEEXTENSION);
	}

	@Test
	public void eofConstant_isNewline() {
		assertEquals("\n", Constants.EOF);
	}
}
