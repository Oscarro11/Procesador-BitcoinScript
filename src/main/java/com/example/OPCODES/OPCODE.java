package com.example.OPCODES;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum OPCODE {
   
    OP_0((byte) 0x00){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{0});
       } 
    },

    OP_1((byte) 0x51){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
            stack.add(new byte[]{1});
       } 
    },

    OP_2((byte) 0x52){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{2});
       } 
    },

    OP_3((byte) 0x53){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{3});
       } 
    },

    OP_4((byte) 0x54){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{4});
       } 
    },

    OP_5((byte) 0x55){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{5});
       } 
    },

    OP_6((byte) 0x56){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{6});
       } 
    },
    
    OP_7((byte) 0x57){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{7});
       } 
    },

    OP_8((byte) 0x58){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{8});
       } 
    },

    OP_9((byte) 0x59){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{9});
       } 
    },

    OP_10((byte) 0x5a){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{10});
       } 
    },

    OP_11((byte) 0x5b){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{11});
       } 
    },

    OP_12((byte) 0x5c){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{12});
       } 
    },

    OP_13((byte) 0x5d){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{13});
       } 
    },

    OP_14((byte) 0x5e){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{14});
       } 
    },

    OP_15((byte) 0x5f){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{15});
       } 
    },
    
    OP_16((byte) 0x60){
       @Override
       public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
           stack.add(new byte[]{16});
       } 
    },

    PUSHDATA((byte) 0x4c){
        @Override
        public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
            stack.add(dato);
        }
    },

    OP_DUP((byte) 0x76){
        @Override
        public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
            stack.add(stack.element());
        }
    },

    OP_DROP((byte) 0x75){
        @Override
        public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
            stack.remove();
        }
    },

    OP_EQUAL((byte) 0x87){
        @Override
        public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
            stack.add(Arrays.equals(stack.remove(), stack.remove()) ? new byte[]{1} : new byte[]{0});
        }
    },

    OP_EQUALVERIFY((byte) 0x88){
        @Override
        public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
            if (!Arrays.equals(stack.remove(), stack.remove())) {
                throw new IllegalStateException("Al comparar dos elementos en la cabeza del Stack, estos son desiguales");
            }
        }
    },

    OP_HASH160((byte) 0xa9){
        @Override
        public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) throws Exception {
            try {
                stack.add(Hash160.hash160(stack.remove()));    
            } catch (Exception e) {
                throw new Exception("La operacion de Hash160 no pudo completarse debido a que el hasheo tuvo problemas");
            }
        }
    },

    OP_CHECKSIG((byte) 0xac){
        @Override
        public void aplicar(byte[] dato, AbstractQueue<byte[]> stack) {
            byte[] publicKey = stack.remove();
            byte[] signature = stack.remove(); 

            stack.add(Arrays.equals(publicKey, signature) ? new byte[]{1} : new byte[]{0});
        }
    };

    private static final Map<Byte, OPCODE> BY_CODE = new HashMap<>();

    static {
        for (OPCODE op : values()) {
            BY_CODE.put(op.code, op);
        }
    }

    private final byte code;

    OPCODE(byte code){
        this.code = code;
    }

    public abstract void aplicar(byte[] dato, AbstractQueue<byte[]> stack) throws Exception;

    public static OPCODE fromId(byte code){
        OPCODE op = BY_CODE.get(code);
        if (op == null) {
            throw new IllegalArgumentException("Codigo no válido: " + code);
        }
        return op;
    }
}
