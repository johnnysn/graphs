package com.uriel.graphs.builders;

import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import com.uriel.graphs.types.Edge;
import com.uriel.graphs.types.Graph;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GraphBuilderTest {

    private final GraphBuilder subject;

    public GraphBuilderTest() {
        this.subject = new GraphBuilder();
    }

    @Test
    void mustBuildGraphCorrectly() {
        // arrange
        var verticesDescriptors = VerticeDescriptor.of("0,1,2,3");
        var edgeDescriptors = EdgeDescriptor.of("0->1,0->2,1->2,2->3,3->1");
        // act
        var graph = subject.build(verticesDescriptors, edgeDescriptors);
        // assert
        var vertices = graph.getVertices();
        var adj = graph.getAdj();
        printGraph(graph);
        assertEquals(verticesDescriptors.size(), vertices.size());
        assertEquals(verticesDescriptors.size(), adj.size());
        assertEquals(edgeDescriptors.size(), graph.getEdges().size());
        assertTrue(adj.get(0).contains(Edge.of(0,1)));
        assertTrue(adj.get(0).contains(Edge.of(0,2)));
        assertTrue(adj.get(1).contains(Edge.of(1,2)));
        assertTrue(adj.get(2).contains(Edge.of(2,3)));
        assertTrue(adj.get(3).contains(Edge.of(3,1)));
    }

    @Test
    void mustUndirectedGraphCorrectly() {
        // arrange
        var verticesDescriptors = VerticeDescriptor.of("0,1,2,3");
        var edgeDescriptors = EdgeDescriptor.of("0->1,0->2,1->2,2->3,1->3");
        // act
        var graph = subject.build(verticesDescriptors, edgeDescriptors, true);
        // assert
        var vertices = graph.getVertices();
        var adj = graph.getAdj();
        printGraph(graph);
        assertEquals(verticesDescriptors.size(), vertices.size());
        assertEquals(verticesDescriptors.size(), adj.size());
        assertEquals(edgeDescriptors.size(), graph.getEdges().size());
        assertTrue(adj.get(1).contains(Edge.of(0,1)));
        assertTrue(adj.get(2).contains(Edge.of(0,2)));
        assertTrue(adj.get(2).contains(Edge.of(1,2)));
        assertTrue(adj.get(3).contains(Edge.of(1,3)));
        assertTrue(adj.get(3).contains(Edge.of(2,3)));
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
