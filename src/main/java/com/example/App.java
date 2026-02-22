package com.example;

import java.util.Arrays;

import com.example.OPCODES.Hash160;
import com.example.OPCODES.OPCODE;

public class App {

    public static boolean traceMode = false;

    public static void main(String[] args) throws Exception {

        if (args.length > 0 && args[0].equals("--trace")) {
            traceMode = true;
        }

        ScriptStack stack = new ScriptStack();

        byte[] signature = new byte[]{6,7,8,9,10};
        byte[] publicKey = new byte[]{6,7,8,9,10};

        stack.pushItem(signature);
        stack.pushItem(publicKey);

        byte[] pubKeyHash = Hash160.hash160(publicKey);

        execute(OPCODE.OP_DUP, stack);
        execute(OPCODE.OP_HASH160, stack);

        stack.pushItem(pubKeyHash);
        if (traceMode) {
            System.out.println("PUSHDATA (pubKeyHash)");
            stack.printStackState();
        }

        execute(OPCODE.OP_EQUALVERIFY, stack);
        execute(OPCODE.OP_CHECKSIG, stack);

        byte[] result = stack.popItem();

        if (!traceMode) {
            System.out.println("Final result: " + Arrays.toString(result));
        }
    }

    private static void execute(OPCODE opcode, ScriptStack stack) throws Exception {
        opcode.aplicar(null, stack);

        if (traceMode) {
            System.out.println(opcode.name());
            stack.printStackState();
        }
    }
}