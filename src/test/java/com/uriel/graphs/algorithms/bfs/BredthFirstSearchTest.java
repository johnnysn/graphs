package com.uriel.graphs.algorithms.bfs;

import com.uriel.graphs.algorithms.types.VisitColor;
import com.uriel.graphs.builders.DirectedGraphBuilder;
import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BredthFirstSearchTest {

    private final DirectedGraphBuilder builder;
    private final BredthFirstSearch subject;

    BredthFirstSearchTest() {
        builder = new DirectedGraphBuilder();
        subject = new BredthFirstSearch();
    }

    @Test
    void mustSearchCorrectly() {
        // arrange
        var verticesDescriptors = Set.of(
                VerticeDescriptor.of(0), VerticeDescriptor.of(1),
                VerticeDescriptor.of(2), VerticeDescriptor.of(3),
                VerticeDescriptor.of(4)
        );
        var edgesDescriptors = Set.of(
                EdgeDescriptor.of(0, 1),
                EdgeDescriptor.of(0, 2),
                EdgeDescriptor.of(0, 3),
                EdgeDescriptor.of(1, 2),
                EdgeDescriptor.of(1, 4),
                EdgeDescriptor.of(2, 3),
                EdgeDescriptor.of(3, 1),
                EdgeDescriptor.of(4, 2)
        );
        var g = builder.build(verticesDescriptors, edgesDescriptors);
        // act
        var data = subject.bfs(g, 0, System.out::println);
        // assert
        Arrays.stream(data.getColors()).forEach(c -> Assertions.assertEquals(VisitColor.BLACK, c));
        assertEquals(0, data.getPi()[1].getId()); // 0 -> 1
        assertEquals(0, data.getPi()[2].getId()); // 0 -> 2
        assertEquals(0, data.getPi()[3].getId()); // 0 -> 3
        assertEquals(1, data.getPi()[4].getId()); // 1 -> 4
        Assertions.assertNull(data.getPi()[0]);
    }
}