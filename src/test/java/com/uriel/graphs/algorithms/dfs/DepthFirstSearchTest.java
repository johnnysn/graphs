package com.uriel.graphs.algorithms.dfs;

import com.uriel.graphs.algorithms.types.VisitColor;
import com.uriel.graphs.builders.DirectedGraphBuilder;
import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

class DepthFirstSearchTest {

    private final DirectedGraphBuilder builder;
    private final DepthFirstSearch subject;

    DepthFirstSearchTest() {
        builder = new DirectedGraphBuilder();

        subject = new DepthFirstSearch();
    }

    @Test
    void mustSearchCorrectly() {
        // arrange
        var verticesDescriptors = Set.of(VerticeDescriptor.of(0), VerticeDescriptor.of(1), VerticeDescriptor.of(2), VerticeDescriptor.of(3));
        var edgesDescriptors = Set.of(
                EdgeDescriptor.of(0, 1),
                EdgeDescriptor.of(0, 2),
                EdgeDescriptor.of(1, 2),
                EdgeDescriptor.of(2, 3),
                EdgeDescriptor.of(3, 1)
        );
        var graph = builder.build(verticesDescriptors, edgesDescriptors);
        // act
        var data = subject.dfs(graph, 0, System.out::println);
        // assert
        Arrays.stream(data.getColors()).forEach(c -> Assertions.assertEquals(VisitColor.BLACK, c));
        for (int i = 0; i < data.getD().length; i++) {
            Assertions.assertTrue(data.getD()[i] < data.getF()[i]);
        }
        System.out.println(Arrays.toString(data.getColors()));
        System.out.println(Arrays.toString(data.getD()));
        System.out.println(Arrays.toString(data.getF()));
    }
}