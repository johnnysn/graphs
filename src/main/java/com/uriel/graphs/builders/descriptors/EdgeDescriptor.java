package com.uriel.graphs.builders.descriptors;

import com.uriel.graphs.builders.utils.DescriptorUtils;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(of = {"id1", "id2", "lbl1", "lbl2"})
@AllArgsConstructor
public class EdgeDescriptor {

    public final int id1;

    public final int id2;

    public final String lbl1;
    public final String lbl2;

    public final Double weight;

    public EdgeDescriptor(int id1, int id2, Double weight) {
        this.id1 = id1;
        this.id2 = id2;
        this.lbl1 = "";
        this.lbl2 = "";
        this.weight = weight;
    }

    public EdgeDescriptor(String lbl1, String lbl2, Double weight) {
        this.id1 = -1;
        this.id2 = -1;
        this.lbl1 = lbl1;
        this.lbl2 = lbl2;
        this.weight = weight;
    }

    public static Set<EdgeDescriptor> of(String description) {
        return Arrays.stream(description.split("\\s*,\\s*"))
            .map(d -> {
                var ids = d.trim().split("\\s*->\\s*");
                var u = ids[0];
                var v = ids[1];
                Double weight = null;
                if (ids[1].contains(":")) {
                    var aux = ids[1].split("\\s*:\\s*");
                    v = aux[0];
                    weight = Double.parseDouble(aux[1]);
                }

                if (DescriptorUtils.isValidIndex(u) && DescriptorUtils.isValidIndex(v))
                    return new EdgeDescriptor(Integer.parseInt(u), Integer.parseInt(v), weight);
                else
                    return new EdgeDescriptor(u, v, weight);
            })
            .collect(Collectors.toSet());
    }
}
