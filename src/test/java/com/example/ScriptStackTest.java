package com.example;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScriptStackTest 
{
    AbstractQueue<Integer> queue = new ScriptStack();

    @BeforeEach
    public void setUp(){
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
    }

    @Test
    public void iterator(){
        ArrayList<Integer> queueElements = new ArrayList<Integer>();
        queue.forEach(queueElements :: add);

        ArrayList<Integer> resultado = new ArrayList<Integer>();
        resultado.add(4);
        resultado.add(3);
        resultado.add(2);
        resultado.add(1);

        Assertions.assertEquals(resultado, queueElements);
    }

    @Test
    public void forEach(){
        ArrayList<Integer> queueElements = new ArrayList<Integer>();
        queue.forEach(queueElements :: add);

        BlockingDeque<Integer> deque = new LinkedBlockingDeque<Integer>();
        deque.add(1);
        deque.add(2);
        deque.add(3);
        deque.add(4);

        ArrayList<Integer> dequeElements = new ArrayList<Integer>();
        deque.descendingIterator().forEachRemaining(dequeElements :: add);

        Assertions.assertEquals(dequeElements, queueElements);
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