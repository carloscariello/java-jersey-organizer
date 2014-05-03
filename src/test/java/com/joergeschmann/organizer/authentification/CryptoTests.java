package com.joergeschmann.organizer.authentification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class CryptoTests {

	@Test
	public void testShouldGetTheSameEncryptionForTheSamyPassword()
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		byte[] salt = AuthUtil.generateSalt();
		String plaintextPassword = "t1h2i3s4 a5t6e7s8t9$";
		byte[] encryptedPassword = AuthUtil.encryptPasswordWithSalt(
				plaintextPassword, salt);

		byte[] encryptedAttemptedPassword = AuthUtil.encryptPasswordWithSalt(
				plaintextPassword, salt);

		assertTrue(Arrays.equals(encryptedPassword, encryptedAttemptedPassword));

	}

	@Test
	public void testShouldGetTheSameValueAfterEncodingAndDecodingInHex()
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		byte[] salt = AuthUtil.generateSalt();
		String saltHex = CodecUtil.byteToHex(salt);
		byte[] convertedSalt = CodecUtil.hexToByte(saltHex);
		Assert.assertArrayEquals(salt, convertedSalt);

		String plaintextPassword = "t1h2i3s4 a5t6e7s8t9$";
		byte[] encryptedPassword = AuthUtil.encryptPasswordWithSalt(
				plaintextPassword, salt);
		String passwordHex = CodecUtil.byteToHex(encryptedPassword);
		byte[] convertedPassword = CodecUtil.hexToByte(passwordHex);
		Assert.assertArrayEquals(encryptedPassword, convertedPassword);

	}

	@Test
	public void testShouldGetTheSameHMACForTheSameInput()
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		String password = "secret";

		byte[] salt = AuthUtil.generateSalt();
		byte[] encryptedPassword = AuthUtil.encryptPasswordWithSalt(password,
				salt);

		String saltHex = CodecUtil.byteToHex(salt);
		String passwordHex = CodecUtil.byteToHex(encryptedPassword);

		String clientAuthMac = HashUtil.hmacSha256(saltHex, passwordHex);
		String serverAuthMac = HashUtil.hmacSha256(saltHex, passwordHex);
		assertEquals(clientAuthMac, serverAuthMac);

	}

	@Test
	public void testShouldGetTheSameValueAfterBase64EncodingAndDecoding()
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		String password = "secret";

		byte[] salt = AuthUtil.generateSalt();
		byte[] encryptedPassword = AuthUtil.encryptPasswordWithSalt(password,
				salt);

		String saltHex = CodecUtil.byteToHex(salt);
		String passwordHex = CodecUtil.byteToHex(encryptedPassword);

		String clientAuthMac = HashUtil.hmacSha256(saltHex, passwordHex);

		String encodedMac = CodecUtil.stringToBase64(clientAuthMac);
		String decodedMac = CodecUtil.base64ToString(encodedMac);

		assertEquals(clientAuthMac, decodedMac);
	}
}
