package com.uriel.graphs.types.structs;

import java.util.AbstractQueue;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PriorityQueue<E extends Heapable> extends AbstractQueue<E> {

    private MinHeap<E> heap;
    private Map<E, Integer> heapMap = new HashMap<>();

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

    public boolean decreasePriority(E e, double k) {
        Integer index = heapMap.get(e);
        if (index == null) return false;

        heap.decreaseKey(index, k, heapMap::put);

        return true;
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean offer(E e) {
        heap.add(e, heapMap::put);

        return true;
    }

    @Override
    public E poll() {
        return heap.extractRoot(heapMap::put);
    }

    @Override
    public E peek() {
        return heap.root();
    }

    protected MinHeap<E> getHeap() {
        return heap;
    }
}
