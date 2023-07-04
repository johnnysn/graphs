package com.uriel.graphs.algorithms.mst;

import com.uriel.graphs.types.models.Edge;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MSTData {

    private List<Edge<?>> edges;

    public MSTData() {
        edges = new ArrayList<>();
    }
}
