package com.uriel.graphs.types.structs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MinHeap<E extends Comparable<E>> implements Iterable<E> {

    private List<E> A;

    private int size;

    public MinHeap() {
        A = new ArrayList<>();
    }

    public MinHeap(int initialCapacity) {
        A = new ArrayList<>(initialCapacity);
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return A.iterator();
    }

    private void heapify(int i) {
        if (i <= 0 || i >= size) return;

        int left = left(i);
        int right = right(i);

        int lowest = i;
        if (left >= 0 && A.get(left).compareTo(A.get(i)) < 0) {
            lowest = left;
        }
        if (right >= 0 && A.get(right).compareTo(A.get(i)) < 0) {
            lowest = right;
        }
        if (lowest == i) return;

        E aux = A.get(i);
        A.set(i, A.get(lowest));
        A.set(lowest, aux);
        heapify(lowest);
    }

    private int parent(int i) {
        if (i <= 0) {
            return -1;
        }

        return (i + 1) / 2 - 1;
    }

    private int left(int i) {
        int index = (i + 1) * 2 - 1;

        if (index >= size) return -1;

        return index;
    }

    private int right(int i) {
        int index = (i + 1) * 2;

        if (index >= size) return -1;

        return index;
    }
}
