package com.uriel.graphs.types.structs;

import java.util.AbstractQueue;
import java.util.Iterator;

public class PriorityQueue<E extends Comparable<E>> extends AbstractQueue<E> {

    private MinHeap<E> heap;

    public PriorityQueue() {
        heap = new MinHeap<>();
    }

    public PriorityQueue(int initialCapacity) {
        heap = new MinHeap<>(initialCapacity);
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


        return true;
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
