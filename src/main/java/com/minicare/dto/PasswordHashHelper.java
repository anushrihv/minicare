package com.minicare.dto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHashHelper {
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        String passwordToHash = "password";
        byte[] salt = getSalt();

//        String securePassword = get_SHA_256_SecurePassword(passwordToHash, salt);
//        System.out.println(securePassword);

        String securePassword = get_SHA_256_SecurePassword(passwordToHash, salt);
        System.out.println(securePassword);

//        securePassword = get_SHA_384_SecurePassword(passwordToHash, salt);
//        System.out.println(securePassword);
//
//        securePassword = get_SHA_512_SecurePassword(passwordToHash, salt);
//        System.out.println(securePassword);
    }

    private static String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
