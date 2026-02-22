package com.example;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LectorDeBytesTest {
    @Test
    public void leerSecuencia1(){
        String filePath = "src/test/resources/secuencia1.txt";
        byte[] lectura = null;
        
        try {
            lectura = LectorDeBytes.getByteArray(filePath);
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }

        List<Byte> temp = new ArrayList<>();
        temp.add((byte) 0x76);
        temp.add((byte) 0xa9);
        temp.add((byte) 0x4c);
        temp.add((byte) 0x03);
        temp.add((byte) 0x01);
        temp.add((byte) 0x02);
        temp.add((byte) 0x03);
        temp.add((byte) 0x87);

        byte[] secuencia = new byte[temp.size()];
        for (int i = 0; i < secuencia.length; i++) {
            secuencia[i] = temp.get(i);
        }
        
        Assertions.assertArrayEquals(secuencia, lectura);
    }
}
