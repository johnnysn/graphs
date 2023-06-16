package com.uriel.graphs.algorithms.bfs;

import com.uriel.graphs.algorithms.types.VisitColor;
import com.uriel.graphs.builders.GraphBuilder;
import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BredthFirstSearchTest {

    private final GraphBuilder builder;
    private final BredthFirstSearch subject;

    BredthFirstSearchTest() {
        builder = new GraphBuilder();
        subject = new BredthFirstSearch();
    }

    @Test
    void mustSearchCorrectly() {
        // arrange
        var verticesDescriptors = VerticeDescriptor.of("0,1,2,3,4");
        var edgesDescriptors = EdgeDescriptor.of("""
                0 -> 1, 0 -> 2, 0 -> 3, 1 -> 2, 1 -> 4, 2 -> 3, 3 -> 1, 4 -> 2
                """);
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