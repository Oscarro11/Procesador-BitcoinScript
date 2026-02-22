package com.example;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;

public class ScriptStack extends LinkedBlockingDeque<byte[]> {

    // PUSH arriba del stack
    public void pushItem(byte[] item) {
        super.addLast(item);
    }

    // POP desde arriba
    public byte[] popItem() {
        return super.removeLast();
    }

    // PEEK arriba
    public byte[] peekItem() {
        return super.peekLast();
    }

    public void printStackState() {
        System.out.println("Stack state:");
        Iterator<byte[]> it = super.descendingIterator();
        while (it.hasNext()) {
            System.out.println(Arrays.toString(it.next()));
        }
    }
}