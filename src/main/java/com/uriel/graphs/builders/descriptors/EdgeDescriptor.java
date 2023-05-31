package com.uriel.graphs.builders.descriptors;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"id1", "id2"})
@AllArgsConstructor
public class EdgeDescriptor<T> {

    public final long id1;

    public final long id2;

    public final Double weight;

    public static <T> EdgeDescriptor<T> of(long id1, long id2) {
        return new EdgeDescriptor<>(id1, id2, null);
    }

    public static <T> EdgeDescriptor<T> of(long id1, long id2, Double weight) {
        return new EdgeDescriptor<>(id1, id2, weight);
    }
}
