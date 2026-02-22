package com.example.OPCODES;

import java.security.MessageDigest;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Hash160 {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] sha256(byte[] input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(input);
    }

    public static byte[] ripemd160(byte[] input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("RIPEMD160", "BC");
        return digest.digest(input);
    }

    public static byte[] hash160(byte[] input) throws Exception {
        byte[] sha256 = sha256(input);
        return ripemd160(sha256);
    }
}