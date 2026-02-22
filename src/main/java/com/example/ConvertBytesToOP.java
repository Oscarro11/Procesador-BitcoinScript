package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.OPCODES.OPCODE;

public class ConvertBytesToOP{
    private int cursor = 0;
    private List<byte[]> dataToPush = new ArrayList<>();
    private List<OPCODE> opcodes = new ArrayList<>();
    private byte[] linea;

    public ConvertBytesToOP(byte[] linea){
        this.linea = linea;
        traducirToList();
    }

    private void traducirToList(){
        while (cursor < linea.length) {
            byte byteActual = linea[cursor];
            opcodes.add(OPCODE.fromId(byteActual));

            if (byteActual == (byte) 0x4c || byteActual == (byte) 0x4d || byteActual == (byte) 0x4e){
                dataToPush.add(PushDataBytes(byteActual));    
            }

            cursor++;
        }
    }

    private byte[] PushDataBytes(byte OP){
        int bytesALeer = 0;
        int bytesAIngresar = 0;

        switch (OP) {
            case 0x4c: bytesALeer = 1; break;
            case 0x4d: bytesALeer = 2; break;
            case 0x4e: bytesALeer = 4; break;

            default: break;
        }        

        for (int i = 0; i < bytesALeer; i++) {
            cursor++;
            bytesAIngresar += linea[cursor];
        }

        byte[] newHead = new byte[bytesAIngresar];

        for (int i = 0; i < bytesAIngresar; i++){
            cursor++;
            newHead[i] = linea[cursor];
        }

        return newHead;   
    }

    public List<byte[]> getDataToPush() {
        return dataToPush;
    }

    public List<OPCODE> getOpcodes() {
        return opcodes;
    }
}