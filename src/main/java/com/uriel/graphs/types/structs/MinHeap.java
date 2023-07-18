package com.uriel.graphs.types.structs;

import com.uriel.graphs.exceptions.InvalidOperationException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

public class MinHeap<E extends Heapable> implements Iterable<E> {

    private List<E> array;

    private int size;

    public MinHeap() {
        array = new ArrayList<>();
    }

    public MinHeap(int initialCapacity) {
        array = new ArrayList<>(initialCapacity);
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }

    public E root() {
        if (size == 0) return null;

        return array.get(0);
    }

    public List<E> getData() {
        return array.subList(0, size);
    }

    public E extractRoot(BiConsumer<E, Integer> updateIndex) {
        if (size == 0) return null;

        var root = array.get(0);

        array.set(0, array.get(size - 1));
        size--;
        updateIndex.accept(array.get(0), 0);
        heapify(0, updateIndex);

        return root;
    }

    public void add(E e, BiConsumer<E, Integer> updateIndex) {
        if (array.size() < size + 1) {
            array.add(e);
        } else {
            array.set(size, e);
        }
        updateIndex.accept(e, size);
        size++;
        // It's like key has been decreased from infinity
        decreasedKey(size - 1, updateIndex);
    }

    public void decreaseKey(int i, double k, BiConsumer<E, Integer> updateIndex) {
        if (k > array.get(i).getKey()) {
            throw new InvalidOperationException("New key must be lower than current key.");
        }

        array.get(i).setKey(k);
        decreasedKey(i, updateIndex);
    }

    private void decreasedKey(int i, BiConsumer<E, Integer> updateIndex) {
        int parent = parent(i);
        if (parent >= 0 && array.get(parent).getKey() > array.get(i).getKey()) { // Swap with parent
            var aux = array.get(parent);
            array.set(parent, array.get(i));
            array.set(i, aux);

            updateIndex.accept(array.get(i), i);
            updateIndex.accept(array.get(parent), parent);
            decreasedKey(parent, updateIndex);
        }
    }

    private void heapify(int i, BiConsumer<E, Integer> updateIndex) {
        if (i < 0 || i >= size) return;

        int left = left(i);
        int right = right(i);

        int lowest = i;
        if (left >= 0 && array.get(left).getKey() < array.get(i).getKey()) {
            lowest = left;
        }
        if (right >= 0 && array.get(right).getKey() < array.get(lowest).getKey()) {
            lowest = right;
        }
        if (lowest == i) return;

        E aux = array.get(i);
        array.set(i, array.get(lowest));
        array.set(lowest, aux);

        updateIndex.accept(array.get(i), i);
        updateIndex.accept(array.get(lowest), lowest);
        heapify(lowest, updateIndex);
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
