package com.example;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.OPCODES.OPCODE;

public class ConvertBytesToOPTest {
    
    @Test
    public void secuencia1(){
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

        ConvertBytesToOP convertidor = new ConvertBytesToOP(secuencia);

        List<OPCODE> resultadoOP = new ArrayList<>();
        resultadoOP.add(OPCODE.OP_DUP);
        resultadoOP.add(OPCODE.OP_HASH160);
        resultadoOP.add(OPCODE.OP_PUSHDATA1);
        resultadoOP.add(OPCODE.OP_EQUAL);

        List<byte[]> resultadoByte = new ArrayList<>();
        resultadoByte.add(new byte[]{1, 2, 3});

        Assertions.assertEquals(resultadoOP, convertidor.getOpcodes());
        for (int i = 0; i < resultadoByte.size(); i++) {
            Assertions.assertArrayEquals(resultadoByte.get(i), convertidor.getDataToPush().get(i));
        }
    }
}
