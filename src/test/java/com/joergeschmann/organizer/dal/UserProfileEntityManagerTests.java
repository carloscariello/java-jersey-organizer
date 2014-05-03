package com.joergeschmann.organizer.dal;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.joergeschmann.organizer.authentification.AuthUtil;
import com.joergeschmann.organizer.authentification.CodecUtil;
import com.joergeschmann.organizer.model.UserProfile;

public class UserProfileEntityManagerTests {

	private static UserProfileEntityManager entityManager;

	@BeforeClass
	public static void beforeClass() {
		entityManager = EntityManagerBuilder.getInstance().createEntityManager(
				UserProfileEntityManager.class);
	}

	@Before
	public void beforeTest() {
		// Before each test
	}

	@Test
	public void testShouldNotFindNotExistingUserProfile() {
		String username = "Test User Not In DB";

		final UserProfile result = entityManager.findByUsername(username);

		assertNull(result);
	}

	@Test
	public void testShouldInsertAndFindAUserprofile()
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		String username = "Test User";
		String email = "test.user@example.com";
		String password = "top secret";

		byte[] salt = AuthUtil.generateSalt();
		byte[] encryptedPassword = AuthUtil.encryptPasswordWithSalt(password,
				salt);

		UserProfile newProfile = new UserProfile();
		newProfile.setUsername(username);
		newProfile.setEmail(email);
		newProfile.setPasswordSalt(CodecUtil.byteToHex(salt));
		newProfile.setPassword(CodecUtil.byteToHex(encryptedPassword));

		entityManager.create(newProfile);

		UserProfile foundProfile = entityManager.findByEmail(email);

		byte[] encryptedAttemptedPassword = AuthUtil.encryptPasswordWithSalt(
				password, CodecUtil.hexToByte(foundProfile.getPasswordSalt()));

		assertTrue(Arrays.equals(encryptedAttemptedPassword,
				CodecUtil.hexToByte(foundProfile.getPassword())));
	}

}
