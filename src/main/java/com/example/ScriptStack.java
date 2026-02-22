package com.example;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;

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

    @Override
    public Iterator<byte[]> iterator() {
        return super.descendingIterator();
    }

    @Override
    public void forEach(Consumer<? super byte[]> action) {
        super.descendingIterator().forEachRemaining(action);
    }

    public void printStackState() {
        System.out.println("Stack state:");
        Iterator<byte[]> it = super.descendingIterator();
        while (it.hasNext()) {
            System.out.println(Arrays.toString(it.next()));
        }
    }
}