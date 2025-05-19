package com.cineworld.util;

	import javax.crypto.Cipher;
	import javax.crypto.spec.SecretKeySpec;
	import java.util.Base64;
	/**
	 * passwordUtil.java
	 * 
	 * Utility class for encrypting and decrypting passwords using AES encryption.
	 * 
	 * Provides static methods to encrypt plaintext passwords and decrypt encrypted passwords.
	 * Uses a fixed secret key internally for AES cipher operations.
	 * 
	 * Note: This implementation uses a hardcoded key and basic AES, which is not recommended for production security.
	 * Consider stronger, salted hashing algorithms like bcrypt or Argon2 for password storage.
	 * 
	 * Author: Dechen Lama
	 */

	public class passwordUtil {


	    private static final String SECRET_KEY = "MySuperSecretKey"; 

	    public static String encrypt(String password, String username) {
	        try {
	            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
	            Cipher cipher = Cipher.getInstance("AES");
	            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	            byte[] encrypted = cipher.doFinal(password.getBytes());
	            return Base64.getEncoder().encodeToString(encrypted);
	        } catch (Exception e) {
	            throw new RuntimeException("Error encrypting password", e);
	        }
	    }

	    public static String decrypt(String encryptedPassword, String username) {
	        try {
	            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
	            Cipher cipher = Cipher.getInstance("AES");
	            cipher.init(Cipher.DECRYPT_MODE, secretKey);
	            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
	            return new String(decrypted);
	        } catch (Exception e) {
	            throw new RuntimeException("Error decrypting password", e);
	        }
	    }
	}

