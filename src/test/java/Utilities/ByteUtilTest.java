package Utilities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the ByteUtil utility class.
 */
public class ByteUtilTest {

	// --- convertToBytes ---

	@Test
	public void convertToBytes_zero_returnsTwoZeroBytes() {
		byte[] result = ByteUtil.convertToBytes(0);
		assertEquals(2, result.length);
		assertEquals(0, result[0]);
		assertEquals(0, result[1]);
	}

	@Test
	public void convertToBytes_255_returnsCorrectBytes() {
		byte[] result = ByteUtil.convertToBytes(255);
		assertEquals(0, result[0]);
		assertEquals((byte) 0xFF, result[1]);
	}

	@Test
	public void convertToBytes_256_returnsCorrectBytes() {
		byte[] result = ByteUtil.convertToBytes(256);
		assertEquals(1, result[0]);
		assertEquals(0, result[1]);
	}

	@Test
	public void convertToBytes_0x7C04_returnsHeadAndCount() {
		byte[] result = ByteUtil.convertToBytes(0x7C04);
		assertEquals(0x7C, result[0]);
		assertEquals(0x04, result[1]);
	}

	// --- convertToInt ---

	@Test
	public void convertToInt_zeroBytes_returnsZero() {
		assertEquals(0, ByteUtil.convertToInt((byte) 0, (byte) 0));
	}

	@Test
	public void convertToInt_lowByteOnly_returnsLowByte() {
		assertEquals(0xFF, ByteUtil.convertToInt((byte) 0xFF, (byte) 0));
	}

	@Test
	public void convertToInt_highByteOnly_returns256() {
		assertEquals(256, ByteUtil.convertToInt((byte) 0, (byte) 1));
	}

	@Test
	public void convertToInt_bothBytes_returnsCombined() {
		assertEquals(0x0102, ByteUtil.convertToInt((byte) 0x02, (byte) 0x01));
	}

	// --- bitIsSet ---

	@Test
	public void bitIsSet_bit0Set_returnsTrue() {
		assertTrue(ByteUtil.bitIsSet(1, 0));
	}

	@Test
	public void bitIsSet_bit0NotSet_returnsFalse() {
		assertFalse(ByteUtil.bitIsSet(0, 0));
	}

	@Test
	public void bitIsSet_bit7Set_returnsTrue() {
		assertTrue(ByteUtil.bitIsSet(128, 7));
	}

	@Test
	public void bitIsSet_bit3In0xFF_returnsTrue() {
		assertTrue(ByteUtil.bitIsSet(0xFF, 3));
	}

	@Test
	public void bitIsSet_bit4In0x0F_returnsFalse() {
		assertFalse(ByteUtil.bitIsSet(0x0F, 4));
	}

	// --- setBitAtPos ---

	@Test
	public void setBitAtPos_setBit0_returns1() {
		assertEquals(1, ByteUtil.setBitAtPos(0, 0, 1));
	}

	@Test
	public void setBitAtPos_clearBit0_returns0() {
		assertEquals(0, ByteUtil.setBitAtPos(1, 0, 0));
	}

	@Test
	public void setBitAtPos_setBit7_returns128() {
		assertEquals(128, ByteUtil.setBitAtPos(0, 7, 1));
	}

	@Test
	public void setBitAtPos_clearBit7_returnsWithout128() {
		assertEquals(0x7F, ByteUtil.setBitAtPos(0xFF, 7, 0));
	}

	@Test
	public void setBitAtPos_setBitAlreadySet_noChange() {
		assertEquals(0xFF, ByteUtil.setBitAtPos(0xFF, 3, 1));
	}

	// --- toggleBitAtPos ---

	@Test
	public void toggleBitAtPos_toggleBit0On_returns1() {
		assertEquals(1, ByteUtil.toggleBitAtPos(0, 0));
	}

	@Test
	public void toggleBitAtPos_toggleBit0Off_returns0() {
		assertEquals(0, ByteUtil.toggleBitAtPos(1, 0));
	}

	@Test
	public void toggleBitAtPos_toggleBit3_returnsToggled() {
		assertEquals(0x08, ByteUtil.toggleBitAtPos(0, 3));
		assertEquals(0, ByteUtil.toggleBitAtPos(0x08, 3));
	}

	// --- getByteArrayByByte ---

	@Test
	public void getByteArrayByByte_zero_returnsAllZeros() {
		Integer[] result = ByteUtil.getByteArrayByByte((byte) 0);
		assertEquals(8, result.length);
		for (Integer bit : result) {
			assertEquals(Integer.valueOf(0), bit);
		}
	}

	@Test
	public void getByteArrayByByte_0xFF_returnsAllOnes() {
		Integer[] result = ByteUtil.getByteArrayByByte((byte) 0xFF);
		assertEquals(8, result.length);
		for (Integer bit : result) {
			assertEquals(Integer.valueOf(1), bit);
		}
	}

	@Test
	public void getByteArrayByByte_0x01_returnsOnlyBit0Set() {
		Integer[] result = ByteUtil.getByteArrayByByte((byte) 0x01);
		assertEquals(Integer.valueOf(1), result[0]);
		for (int i = 1; i < 8; i++) {
			assertEquals(Integer.valueOf(0), result[i]);
		}
	}

	// --- getByteByByteArray ---

	@Test
	public void getByteByByteArray_allZeros_returnsZero() {
		Integer[] input = {0, 0, 0, 0, 0, 0, 0, 0};
		assertEquals(0, ByteUtil.getByteByByteArray(input));
	}

	@Test
	public void getByteByByteArray_allOnes_returns255() {
		Integer[] input = {1, 1, 1, 1, 1, 1, 1, 1};
		assertEquals(255, ByteUtil.getByteByByteArray(input));
	}

	@Test
	public void getByteByByteArray_onlyBit0_returns1() {
		Integer[] input = {1, 0, 0, 0, 0, 0, 0, 0};
		assertEquals(1, ByteUtil.getByteByByteArray(input));
	}

	@Test
	public void getByteArrayByByte_roundTrip() {
		byte original = (byte) 0xA5;
		Integer[] bits = ByteUtil.getByteArrayByByte(original);
		int reconstructed = ByteUtil.getByteByByteArray(bits);
		assertEquals(ByteUtil.signedByteToUnsignedInt(original), reconstructed);
	}

	// --- signedByteToUnsignedInt ---

	@Test
	public void signedByteToUnsignedInt_positive_returnsSame() {
		assertEquals(127, ByteUtil.signedByteToUnsignedInt((byte) 127));
	}

	@Test
	public void signedByteToUnsignedInt_negative_returnsUnsigned() {
		assertEquals(128, ByteUtil.signedByteToUnsignedInt((byte) -128));
		assertEquals(255, ByteUtil.signedByteToUnsignedInt((byte) -1));
	}

	@Test
	public void signedByteToUnsignedInt_zero_returnsZero() {
		assertEquals(0, ByteUtil.signedByteToUnsignedInt((byte) 0));
	}

	// --- convertIntArrayToByteArray ---

	@Test
	public void convertIntArrayToByteArray_emptyArray_returnsEmptyArray() {
		byte[] result = ByteUtil.convertIntArrayToByteArray(new int[]{});
		assertEquals(0, result.length);
	}

	@Test
	public void convertIntArrayToByteArray_singleElement_returnsCorrectByte() {
		byte[] result = ByteUtil.convertIntArrayToByteArray(new int[]{0x7C});
		assertEquals(1, result.length);
		assertEquals(0x7C, result[0]);
	}

	@Test
	public void convertIntArrayToByteArray_rmxMessage_returnsCorrectBytes() {
		int[] input = {0x7C, 0x04, 0x08, 0x01};
		byte[] result = ByteUtil.convertIntArrayToByteArray(input);
		assertEquals(4, result.length);
		assertEquals(0x7C, result[0]);
		assertEquals(0x04, result[1]);
		assertEquals(0x08, result[2]);
		assertEquals(0x01, result[3]);
	}

	// --- calcBinaryValueFromInt ---

	@Test
	public void calcBinaryValueFromInt_0_returns1() {
		assertEquals(1, ByteUtil.calcBinaryValueFromInt(0));
	}

	@Test
	public void calcBinaryValueFromInt_1_returns2() {
		assertEquals(2, ByteUtil.calcBinaryValueFromInt(1));
	}

	@Test
	public void calcBinaryValueFromInt_7_returns128() {
		assertEquals(128, ByteUtil.calcBinaryValueFromInt(7));
	}

	@Test
	public void calcBinaryValueFromInt_allBitPositions() {
		for (int i = 0; i < 8; i++) {
			assertEquals(1 << i, ByteUtil.calcBinaryValueFromInt(i));
		}
	}
}
