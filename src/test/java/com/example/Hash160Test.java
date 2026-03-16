package com.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.OPCODES.Hash160;

public class Hash160Test {

    public static byte[] hex(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
        }

        return data;
    }

    public static String toHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);

        for (byte b : bytes) {
            hex.append(String.format("%02x", b & 0xff));
        }

        return hex.toString();
    }
    
    @Test
    void test1(){
        byte[] pubKey = hex("02c6047f9441ed7d6d3045406e95c07cd85a2c99fee1e7cda4b8f6e6b4b9a8e8a5");

        try {
            byte[] hash = Hash160.hash160(pubKey);
            Assertions.assertEquals("0bbc1aca4327be385a7cff2a7fe32066b1433f30", toHex(hash));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }   
    
}
