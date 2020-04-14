package Utilities;

import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;

public class ByteUtil {
	/**
	 * Method which converts a int into 2 bytes
	 *
	 * @param val = integer as value
	 * @return = returns a byte Array
	 */
	public static byte[] convertToBytes(int val) {
		byte[] twoBytes = new byte[2];

		twoBytes[1] = (byte) (val & 0xFF);
		twoBytes[0] = (byte) ((val >> 8) & 0xFF);

		return twoBytes;
	}

	/**
	 * Method which converts 2 bytes (High and Low Byte) to an integer
	 *
	 * @param lowByte  low byte: byte to the right with the smaller x in 2^x
	 * @param highByte high byte: byte to the left with the higher x in 2^x
	 * @return int - int value of the two bytes
	 */
	public static int convertToInt(byte lowByte, byte highByte) {
		int solution = ((int) highByte << 8) | ((int) lowByte & 0xFF);
		return solution;
	}

	/**
	 * checks if bit is set in given message
	 *
	 * @param value    byte value to check
	 * @param bitIndex counting from 0-7
	 * @return true = if set, else false
	 */
	public static boolean bitIsSet(int value, int bitIndex) {
		BitSet bitSet = BitSet.valueOf(new long[] { value });

		return (bitSet.get(bitIndex) == true);
	}

	/**
	 * sets bit at the given index with given value
	 *
	 * @param currentByte
	 * @param bitIndex
	 * @param value
	 * @return
	 */
	public static int setBitAtPos(int currentByte, int bitIndex, int value) {
		int mask = 1 << bitIndex;
		return (currentByte & ~mask) | ((value << bitIndex) & mask);
	}

	/**
	 * toggles bit at the given index
	 *
	 * @param currentByte byte to toggle the bit
	 * @param bitIndex    Index of the bit to toggle
	 * @return
	 */
	public static int toggleBitAtPos(int currentByte, int bitIndex) {
		return (currentByte ^= 1 << bitIndex);
	}

	public static Integer[] getByteArrayByByte(byte currentByte) {

		Integer[] result = new Integer[8];

		for (int i = 7; i >= 0; --i) {
			result[i] = (currentByte >>> i & 1);
		}

		return result;
	}

	public static int getByteByByteArray(Integer[] byteArray) {

		StringBuilder sb = new StringBuilder();

		for (int i = 7; i >= 0; --i) {
			sb.append(byteArray[i]);
		}

		return Integer.parseInt(sb.toString(), 2);

	}

	public static int signedByteToUnsignedInt(byte b) {
		return b & 0xFF;
	}

	public static byte[] convertIntArrayToByteArray(int[] values) {

		byte[] arrayByte = new byte[values.length];

		for (int i = 0; i < values.length; ++i) {
			arrayByte[i] = (byte) (values[i]);
		}

		return arrayByte;
	}

	/**
	 * Method that caluclates the bitIndex in binary code
	 * 
	 * @author Matthias Mack 3316380
	 * @param bitIndex
	 * @return
	 */
	public static int calcBinaryValueFromInt(int bitIndex) {
		return (int) Math.pow(2, bitIndex);
	}
}