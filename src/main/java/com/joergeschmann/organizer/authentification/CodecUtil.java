package com.joergeschmann.organizer.authentification;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.glassfish.jersey.internal.util.Base64;

/**
 * Helper class to encode and decode
 * 
 */
public class CodecUtil {

	/**
	 * Creates a string
	 * 
	 * @param input
	 * @return
	 */
	public static String byteToString(byte[] input) {
		return new String(input);
	}

	/**
	 * Encodes a byte array into hex
	 * 
	 * @param input
	 * @return the hex string
	 */
	public static String byteToHex(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Decodes a hex into a byte array
	 * 
	 * @param input
	 * @return the byte array, empty in error case
	 */
	public static byte[] hexToByte(final String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			return new byte[0];
		}
	}

	/**
	 * Decodes a string with base64
	 * 
	 * @param input
	 * @return the decoded string
	 */
	public static String base64ToString(final String input) {
		return Base64.decodeAsString(input);
	}

	/**
	 * Encodes a string with base64
	 * 
	 * @param input
	 * @return the encoded string
	 */
	public static String stringToBase64(final String input) {
		return Base64.encodeAsString(input);
	}
}
