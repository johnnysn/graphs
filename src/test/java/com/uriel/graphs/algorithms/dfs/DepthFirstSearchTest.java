package com.uriel.graphs.algorithms.dfs;

import com.uriel.graphs.algorithms.types.VisitColor;
import com.uriel.graphs.builders.GraphBuilder;
import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class DepthFirstSearchTest {

    private final GraphBuilder builder;
    private final DepthFirstSearch subject;

    DepthFirstSearchTest() {
        builder = new GraphBuilder();

        subject = new DepthFirstSearch();
    }

    @Test
    void mustSearchCorrectly() {
        // arrange
        var verticesDescriptors = VerticeDescriptor.of("0,1,2,3");
        var edgesDescriptors = EdgeDescriptor
                .of("0->1, 0->2, 1->2, 2->3, 3->0");
        var graph = builder.build(verticesDescriptors, edgesDescriptors);
        // act
        var data = subject.dfs(graph, 0, System.out::println);
        // assert
        Arrays.stream(data.getColors()).forEach(c -> Assertions.assertEquals(VisitColor.BLACK, c));
        for (int i = 0; i < data.getD().length; i++) {
            Assertions.assertTrue(data.getD()[i] < data.getF()[i]);
        }
        Assertions.assertEquals(0, data.getPi()[1].getId()); // 0 -> 1
        Assertions.assertEquals(1, data.getPi()[2].getId()); // 1 -> 2
        Assertions.assertEquals(2, data.getPi()[3].getId()); // 2 -> 3
        Assertions.assertNull(data.getPi()[0]); // 3 -> X
        System.out.println(Arrays.toString(data.getColors()));
        System.out.println(Arrays.toString(data.getD()));
        System.out.println(Arrays.toString(data.getF()));
    }
}