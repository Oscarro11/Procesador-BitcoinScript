package com.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScriptStackTest 
{
    ScriptStack stack = new ScriptStack();

    @BeforeEach
    public void setUp(){
        stack.pushItem(new byte[]{1});
        stack.pushItem(new byte[]{2});
        stack.pushItem(new byte[]{3});
        stack.pushItem(new byte[]{4});
    }

    @Test
    public void iterator(){
        ArrayList<byte[]> stackElements = new ArrayList<byte[]>();
        stack.iterator().forEachRemaining(stackElements :: add);

        ArrayList<byte[]> resultado = new ArrayList<byte[]>();
        resultado.add(new byte[]{4});
        resultado.add(new byte[]{3});
        resultado.add(new byte[]{2});
        resultado.add(new byte[]{1});

        for (int i = 0; i < stackElements.size(); i++) {
            assertArrayEquals(stackElements.get(i), resultado.get(i));
        }
    }

    @Test
    public void forEach(){
        ArrayList<byte[]> stackElements = new ArrayList<byte[]>();
        stack.forEach(stackElements :: add);

        BlockingDeque<byte[]> deque = new LinkedBlockingDeque<byte[]>();
        deque.add(new byte[]{1});
        deque.add(new byte[]{2});
        deque.add(new byte[]{3});
        deque.add(new byte[]{4});

        ArrayList<byte[]> dequeElements = new ArrayList<byte[]>();
        deque.descendingIterator().forEachRemaining(dequeElements :: add);

        for (int i = 0; i < stackElements.size(); i++) {
            assertArrayEquals(stackElements.get(i), dequeElements.get(i));
        }
    }

    @Test
    public void eliminarElementos(){
        stack.clear();
        Assertions.assertEquals(null, stack.poll());
    }

    @Test
    public void getLast(){
        ScriptStack stack = new ScriptStack();
        Assertions.assertThrows(NoSuchElementException.class, () -> stack.popItem());
    }
}