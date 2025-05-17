package org.reuac.yrt_001.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SecurityUtil {

    private static final int SALT_LENGTH = 32;


    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }


    public static String hashPassword(String password, String salt) {
        if (password == null || salt == null) {
            throw new IllegalArgumentException("Password and salt cannot be null.");
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");


            String saltedPassword = salt + password;
            byte[] hashedPassword = md.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }


    public static boolean verifyPassword(String providedPassword, String storedHash, String salt) {
        if (providedPassword == null || storedHash == null || salt == null) {
            return false;
        }
        String newHash = hashPassword(providedPassword, salt);
        return newHash.equals(storedHash);
    }


    public static void main(String[] args) {
        String salt = generateSalt();
        String password = "mySecretPassword123";
        String hashedPassword = hashPassword(password, salt);

        System.out.println("Salt: " + salt);
        System.out.println("Original Password: " + password);
        System.out.println("Hashed Password: " + hashedPassword);

        System.out.println("Verification (correct): " + verifyPassword(password, hashedPassword, salt));
        System.out.println("Verification (incorrect): " + verifyPassword("wrongPassword", hashedPassword, salt));
    }
}
