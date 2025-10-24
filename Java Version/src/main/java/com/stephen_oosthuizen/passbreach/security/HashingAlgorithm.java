/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stephen_oosthuizen.passbreach.security;

import java.security.MessageDigest;
import org.springframework.stereotype.Service;

/**
 *
 * @author Stephen Oosthuizen
 */
@Service
public class HashingAlgorithm {
    public static String hash(String input, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));

            // Convert byte array into hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
