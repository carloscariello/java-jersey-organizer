package com.joergeschmann.organizer.authentification;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * Helper class for hashing
 * 
 */
public class HashUtil {

	private static final String HMAC_SHA256 = "HmacSHA256";

	/**
	 * Creates a HMAC with SHA256 and salt
	 * 
	 * @param key
	 * @param data
	 * @return the HMAC or an empty String in error case
	 */
	public static String hmacSha256(String key, String data) {
		try {
			Mac hmacSha256 = Mac.getInstance(HMAC_SHA256);
			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(),
					HMAC_SHA256);
			hmacSha256.init(secret_key);
			return Hex.encodeHexString(hmacSha256.doFinal(data.getBytes()));
		} catch (NoSuchAlgorithmException exc) {
			return "";
		} catch (InvalidKeyException exc) {
			return "";
		}
	}
}
