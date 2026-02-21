package com.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScriptStackTest 
{
    AbstractQueue<Byte[]> queue = new ScriptStack();

    @BeforeEach
    public void setUp(){
        queue.add(new Byte[]{1});
        queue.add(new Byte[]{2});
        queue.add(new Byte[]{3});
        queue.add(new Byte[]{4});
    }

    @Test
    public void iterator(){
        ArrayList<Byte[]> queueElements = new ArrayList<Byte[]>();
        queue.forEach(queueElements :: add);

        ArrayList<Byte[]> resultado = new ArrayList<Byte[]>();
        resultado.add(new Byte[]{4});
        resultado.add(new Byte[]{3});
        resultado.add(new Byte[]{2});
        resultado.add(new Byte[]{1});

        for (int i = 0; i < queueElements.size(); i++) {
            assertArrayEquals(queueElements.get(i), resultado.get(i));
        }
    }

    @Test
    public void forEach(){
        ArrayList<Byte[]> queueElements = new ArrayList<Byte[]>();
        queue.forEach(queueElements :: add);

        BlockingDeque<Byte[]> deque = new LinkedBlockingDeque<Byte[]>();
        deque.add(new Byte[]{1});
        deque.add(new Byte[]{2});
        deque.add(new Byte[]{3});
        deque.add(new Byte[]{4});

        ArrayList<Byte[]> dequeElements = new ArrayList<Byte[]>();
        deque.descendingIterator().forEachRemaining(dequeElements :: add);

        for (int i = 0; i < queueElements.size(); i++) {
            assertArrayEquals(queueElements.get(i), dequeElements.get(i));
        }
    }

    @Test
    public void eliminarElementos(){
        queue.clear();
        Assertions.assertEquals(null, queue.poll());
    }

    @Test
    public void getLast(){
        ScriptStack stack = new ScriptStack();
        Assertions.assertEquals(null, stack.getLast());
    }
}