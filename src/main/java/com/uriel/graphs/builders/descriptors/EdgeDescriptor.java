package com.uriel.graphs.builders.descriptors;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"id1", "id2"})
@AllArgsConstructor
public class EdgeDescriptor {

    public final int id1;

    public final int id2;

    public final Double weight;

    public static  EdgeDescriptor of(int id1, int id2) {
        return new EdgeDescriptor(id1, id2, null);
    }

    public static  EdgeDescriptor of(int id1, int id2, Double weight) {
        return new EdgeDescriptor(id1, id2, weight);
    }
}
