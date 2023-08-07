package com.uriel.graphs.builders;

import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import com.uriel.graphs.exceptions.InvalidGraphConfigException;
import com.uriel.graphs.types.models.Edge;
import com.uriel.graphs.types.models.Graph;
import com.uriel.graphs.types.models.Vertice;
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
        manageWeights(sortedEdges);

        var adj = new ArrayList<List<Edge<T>>>();
        sortedVertices.forEach(v -> adj.add(new ArrayList<>()));
        for (var edge : sortedEdges) {
            adj.get(edge.getFrom().getId()).add(edge);
            if (bidirectional) {
                adj.get(edge.getTo().getId()).add(edge);
            }
        }

        return new Graph<>(sortedVertices, sortedEdges, adj, !bidirectional);
    }

    private <T> void manageWeights(List<Edge<T>> sortedEdges) {
        boolean hasWeight = false;
        boolean hasNullWeight = false;
        for (var edge : sortedEdges) {
            if (edge.getWeight() != null)
                hasWeight = true;
            else
                hasNullWeight = true;
            if (hasNullWeight && hasWeight)
                throw new InvalidGraphConfigException("Come on!");
        }
        if (!hasWeight) {
            sortedEdges.forEach(e -> e.setWeight(1.0));
        }
    }

    private <T> List<Edge<T>> getSortedEdges(Set<EdgeDescriptor> edges, List<Vertice<T>> sortedVertices) {
        return edges.stream().map(
                    d -> {
                        if (d.id1 == -1 || d.id2 == -1) {
                            var v1 = sortedVertices.stream().filter(v -> v.getLabel().equals(d.lbl1)).findFirst()
                                    .orElseThrow(() -> new InvalidGraphConfigException("Couldn't find vertice for label " + d.lbl1 + "."));
                            var v2 = sortedVertices.stream().filter(v -> v.getLabel().equals(d.lbl2)).findFirst()
                                    .orElseThrow(() -> new InvalidGraphConfigException("Couldn't find vertice for label " + d.lbl2 + "."));
                            return Edge.of(v1, v2, d.weight);
                        }

                        return Edge.of(sortedVertices.get(d.id1), sortedVertices.get(d.id2), d.weight);
                    }
                )
                .sorted().toList();
    }

    private <T> List<Vertice<T>> getSortedVertices(Set<VerticeDescriptor<T>> vDescriptors) {
        return vDescriptors.stream().map(d -> Vertice.of(d.id, d.label, d.value)).sorted().toList();
    }

}
