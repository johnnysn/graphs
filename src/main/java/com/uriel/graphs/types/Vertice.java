package com.uriel.graphs.types;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of = {"id"})
public class Vertice<T> implements Comparable<Vertice<T>> {

    private final int id;
    private final String label;

    @Setter
    private T value;

    public static <T> Vertice<T> of(int id) {
        return new Vertice<>(id, String.valueOf(id),null);
    }

    public static <T> Vertice<T> of(int id, String label) {
        return new Vertice<>(id, label,null);
    }

    public static <T> Vertice<T> of(int id, String label, T value) {
        return new Vertice<>(id, label, value);
    }

    public Vertice(int id, String label, T value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString() {
        return "v{" +
                + id +
                (value == null ? "" : ", value=" + value) +
                '}';
    }

    @Override
    public int compareTo(Vertice<T> o) {
        return Long.compare(id, o.id);
    }
}
