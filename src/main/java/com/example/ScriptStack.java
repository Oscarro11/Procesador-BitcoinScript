package com.example;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;

/**
 * A blocking deque based on linked nodes for {@code Integer}, who has a forced LIFO ordering policy.
 */
public class ScriptStack extends LinkedBlockingDeque<byte[]>{
    
    @Override
    public Iterator<byte[]> iterator() {
        return super.descendingIterator();
    }

    @Override
    public void forEach(Consumer<? super byte[]> action) {
        super.descendingIterator().forEachRemaining(action);
    }

    @Override
    public byte[] getLast() {
        return null;
    }
}