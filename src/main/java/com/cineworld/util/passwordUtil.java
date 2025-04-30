package com.cineworld.util;

	import javax.crypto.Cipher;
	import javax.crypto.spec.SecretKeySpec;
	import java.util.Base64;

	public class passwordUtil {

	    // You can use a username-based dynamic key if needed, here's a fixed 16-byte key
	    private static final String SECRET_KEY = "MySuperSecretKey"; // 16 characters

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

