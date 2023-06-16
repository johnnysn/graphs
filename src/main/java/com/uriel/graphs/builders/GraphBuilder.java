package com.uriel.graphs.builders;

import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import com.uriel.graphs.types.Edge;
import com.uriel.graphs.types.Graph;
import com.uriel.graphs.types.Vertice;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class GraphBuilder {

    public <T> Graph<T> build(Set<VerticeDescriptor<T>> vDescriptors, Set<EdgeDescriptor> edges) {
        return build(vDescriptors, edges, false);
    }

    public <T> Graph<T> build(Set<VerticeDescriptor<T>> vDescriptors, Set<EdgeDescriptor> edges, boolean bidirectional) {
        var sortedVertices = getSortedVertices(vDescriptors);
        var sortedEdges = getSortedEdges(edges, sortedVertices);

        var adj = new ArrayList<List<Edge<T>>>();
        sortedVertices.forEach(v -> adj.add(new ArrayList<>()));
        for (var edge : sortedEdges) {
            adj.get(edge.getU().getId()).add(edge);
            if (bidirectional) {
                adj.get(edge.getV().getId()).add(edge);
            }
        }

        return new Graph<>(sortedVertices, sortedEdges, adj, !bidirectional);
    }

    private <T> List<Edge<T>> getSortedEdges(Set<EdgeDescriptor> edges, List<Vertice<T>> sortedVertices) {
        return edges.stream().map(d -> Edge.of(sortedVertices.get(d.id1), sortedVertices.get(d.id2), d.weight))
                .sorted().toList();
    }

    private <T> List<Vertice<T>> getSortedVertices(Set<VerticeDescriptor<T>> vDescriptors) {
        return vDescriptors.stream().map(d -> Vertice.of(d.id, d.label, d.value)).sorted().toList();
    }

}
