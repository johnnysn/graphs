package com.uriel.graphs.types;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
public class Vertice<T> implements Comparable<Vertice<T>> {

    private final long id;

    @Setter
    private T value;

    public static <T> Vertice<T> of(long id) {
        return new Vertice<>(id, null);
    }

    public static <T> Vertice<T> of(long id, T value) {
        return new Vertice<>(id, value);
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
