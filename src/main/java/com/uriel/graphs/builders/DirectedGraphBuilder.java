package com.uriel.graphs.builders;

import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import com.uriel.graphs.types.Edge;
import com.uriel.graphs.types.Graph;
import com.uriel.graphs.types.Vertice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DirectedGraphBuilder {

    public <T> Graph<T> build(Set<VerticeDescriptor<T>> vDescriptors, Set<EdgeDescriptor> edges) {
        var sortedVertices = vDescriptors.stream().map(d -> Vertice.of(d.id, d.label, d.value)).sorted().toList();
        var verticeMap = sortedVertices.stream().collect(Collectors.toMap(Vertice::getId, Function.identity()));
        var sortedEdges = edges.stream().map(d -> Edge.of(verticeMap.get(d.id1), verticeMap.get(d.id2), d.weight))
                .sorted().toList();

        var adj = new ArrayList<List<Edge<T>>>();
        for (var vertice: sortedVertices) {
            adj.add(new ArrayList<>());
        }
        for (var edge: sortedEdges) {
            adj.get(edge.getU().getId()).add(edge);
        }
        
        return new Graph<>(sortedVertices, sortedEdges, adj);
    }

}
