package com.uriel.graphs.builders.descriptors;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static Set<EdgeDescriptor> of(String description) {
        return Arrays.stream(description.split("\\s*,\\s*"))
                .map(d -> {
                    var ids = d.trim().split("\\s*->\\s*");
                    return EdgeDescriptor.of(Integer.parseInt(ids[0]), Integer.parseInt(ids[1]));
                })
                .collect(Collectors.toSet());
    }
}
