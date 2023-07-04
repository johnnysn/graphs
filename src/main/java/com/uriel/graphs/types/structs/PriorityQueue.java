package com.uriel.graphs.types.structs;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PriorityQueue<E> extends AbstractQueue<E> {
    private List<E> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    public PriorityQueue(int initialCapacity) {
        heap = new ArrayList<>(initialCapacity);
    }

    @Override
    public Iterator<E> iterator() {
        return heap.iterator();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }
}
