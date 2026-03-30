package Utilities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the Flags configuration class.
 */
public class FlagsTest {

	@Before
	public void setUp() {
		// Reset all flags to defaults before each test
		Flags.setFirst_start(true);
		Flags.setBool_0x01(false);
		Flags.setBool_0x04(false);
		Flags.setBool_0x06(false);
		Flags.setBool_0x08(false);
		Flags.setBool_0x20(false);
		Flags.setBool_0x24(false);
		Flags.setBool_0x28(false);
	}

	@Test
	public void defaultFlags_allFiltersOff() {
		assertFalse(Flags.isBool_0x01());
		assertFalse(Flags.isBool_0x04());
		assertFalse(Flags.isBool_0x06());
		assertFalse(Flags.isBool_0x08());
		assertFalse(Flags.isBool_0x20());
		assertFalse(Flags.isBool_0x24());
		assertFalse(Flags.isBool_0x28());
	}

	@Test
	public void defaultFlags_firstStartTrue() {
		assertTrue(Flags.isFirst_start());
	}

	@Test
	public void setFirstStart_false_updatesCorrectly() {
		Flags.setFirst_start(false);
		assertFalse(Flags.isFirst_start());
	}

	@Test
	public void setBool0x01_true_updatesCorrectly() {
		Flags.setBool_0x01(true);
		assertTrue(Flags.isBool_0x01());
	}

	@Test
	public void setBool0x04_true_updatesCorrectly() {
		Flags.setBool_0x04(true);
		assertTrue(Flags.isBool_0x04());
	}

	@Test
	public void setBool0x06_true_updatesCorrectly() {
		Flags.setBool_0x06(true);
		assertTrue(Flags.isBool_0x06());
	}

	@Test
	public void setBool0x08_true_updatesCorrectly() {
		Flags.setBool_0x08(true);
		assertTrue(Flags.isBool_0x08());
	}

	@Test
	public void setBool0x20_true_updatesCorrectly() {
		Flags.setBool_0x20(true);
		assertTrue(Flags.isBool_0x20());
	}

	@Test
	public void setBool0x24_true_updatesCorrectly() {
		Flags.setBool_0x24(true);
		assertTrue(Flags.isBool_0x24());
	}

	@Test
	public void setBool0x28_true_updatesCorrectly() {
		Flags.setBool_0x28(true);
		assertTrue(Flags.isBool_0x28());
	}

	@Test
	public void setAndClearFlag_toggling() {
		Flags.setBool_0x01(true);
		assertTrue(Flags.isBool_0x01());
		Flags.setBool_0x01(false);
		assertFalse(Flags.isBool_0x01());
	}

	@Test
	public void settingOneFlag_doesNotAffectOthers() {
		Flags.setBool_0x01(true);
		assertFalse(Flags.isBool_0x04());
		assertFalse(Flags.isBool_0x06());
		assertFalse(Flags.isBool_0x08());
		assertFalse(Flags.isBool_0x20());
		assertFalse(Flags.isBool_0x24());
		assertFalse(Flags.isBool_0x28());
	}

	@Test
	public void flagsIsSerializable() {
		Flags flags = new Flags();
		assertTrue(flags instanceof java.io.Serializable);
	}
}
