package com.uriel.graphs.builders.descriptors;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
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
                .map(desc -> {
                    if (desc.contains(":")) {
                        var aux = desc.split("\\s*:\\s*");
                        var id = Integer.parseInt(aux[0]);
                        var label = aux[1];
                        return VerticeDescriptor.<T>of(id, label, null);
                    }
                    return VerticeDescriptor.<T>of(Integer.parseInt(desc));
                })
                .collect(Collectors.toSet());
    }
 }
