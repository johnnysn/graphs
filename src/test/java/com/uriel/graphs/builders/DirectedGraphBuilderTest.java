package com.uriel.graphs.builders;

import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import com.uriel.graphs.types.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class DirectedGraphBuilderTest {

    private final DirectedGraphBuilder subject;

    public DirectedGraphBuilderTest() {
        this.subject = new DirectedGraphBuilder();
    }

    @Test
    void mustBuildGraphCorrectly() {
        // arrange
        var verticesDescriptors = Set.of(VerticeDescriptor.of(1), VerticeDescriptor.of(2), VerticeDescriptor.of(3), VerticeDescriptor.of(4));
        var edgesDescriptors = Set.of(
                EdgeDescriptor.of(1, 2),
                EdgeDescriptor.of(1, 3),
                EdgeDescriptor.of(2, 3),
                EdgeDescriptor.of(3, 4),
                EdgeDescriptor.of(4, 2)
        );
        // act
        var graph = subject.build(verticesDescriptors, edgesDescriptors);
        // assert
        var vertices = graph.getVertices();
        var adj = graph.getAdj();
        printGraph(graph);
        Assertions.assertEquals(verticesDescriptors.size(), vertices.size());
        Assertions.assertEquals(verticesDescriptors.size(), adj.size());
        Assertions.assertEquals(edgesDescriptors.size(), graph.getEdges().size());
    }

    private <T> void printGraph(Graph<T> graph) {
        var vertices = graph.getVertices();
        var adj = graph.getAdj();
        for (var vertice : vertices) {
            System.out.println(vertice.toString() + ": ");
            for (var edge : adj.get( vertice.getId() ) ) {
                System.out.println("\t" + edge);
            }
        }
    }
}
