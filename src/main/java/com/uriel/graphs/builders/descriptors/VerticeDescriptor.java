package com.uriel.graphs.builders.descriptors;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
public class VerticeDescriptor<T> {

    public long id;

    public T value;

    public static <T> VerticeDescriptor<T> of(long id, T value) {
        return new VerticeDescriptor<>(id, value);
    }

    public static <T> VerticeDescriptor<T> of(long id) {
        return new VerticeDescriptor<>(id, null);
    }
}
