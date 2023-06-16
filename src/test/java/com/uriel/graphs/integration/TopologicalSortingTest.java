package com.uriel.graphs.integration;

import com.uriel.graphs.algorithms.sort.TopologicalSort;
import com.uriel.graphs.builders.GraphBuilder;
import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;

@SpringBootTest
class TopologicalSortingTest {

    @Autowired
    private TopologicalSort topologicalSort;

    @Autowired
    private GraphBuilder builder;

    @Test
    void mustExecuteCorrectly() {
        // arrange
        var v = VerticeDescriptor.of("0:High School,1:College,2:Get a job,3:Masters degree,4:PhD,5:Be a professor");
        var e = EdgeDescriptor.of("""
                    0 -> 1,
                    1 -> 3,
                    1 -> 4,
                    4 -> 5,
                    2 -> 5
                """);
        var g = builder.build(v, e);
        // act
        var result = topologicalSort.ts(g);
        // assert
        System.out.println(result.stream().map(id -> g.getVertices().get(id).getLabel()).collect(Collectors.joining(" -> ")));
        Assertions.assertTrue(result.indexOf(0) < result.indexOf(1));
        Assertions.assertTrue(result.indexOf(1) < result.indexOf(3));
        Assertions.assertTrue(result.indexOf(1) < result.indexOf(4));
        Assertions.assertTrue(result.indexOf(2) < result.indexOf(5));
        Assertions.assertTrue(result.indexOf(4) < result.indexOf(5));
    }
}
