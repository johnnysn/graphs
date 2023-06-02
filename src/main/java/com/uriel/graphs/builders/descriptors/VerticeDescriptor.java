package com.uriel.graphs.builders.descriptors;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
public class VerticeDescriptor<T> {

    public final int id;

    public final String label;

    public final T value;

    public static <T> VerticeDescriptor<T> of(int id, String label, T value) {
        return new VerticeDescriptor<>(id, label, value);
    }

    public static <T> VerticeDescriptor<T> of(int id) {
        return new VerticeDescriptor<>(id, String.valueOf(id),null);
    }
}
