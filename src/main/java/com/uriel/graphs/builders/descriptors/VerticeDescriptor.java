package com.uriel.graphs.builders.descriptors;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static <T> Set<VerticeDescriptor<T>> of(String ids) {
        return Arrays.stream(ids.split("\\s*,\\s*"))
                .map(id -> VerticeDescriptor.<T>of(Integer.parseInt(id.trim())))
                .collect(Collectors.toSet());
    }
 }
