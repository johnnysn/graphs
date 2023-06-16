package com.uriel.graphs.algorithms.sort;

import com.uriel.graphs.algorithms.dfs.DFSData;
import com.uriel.graphs.algorithms.dfs.DepthFirstSearch;
import com.uriel.graphs.builders.GraphBuilder;
import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import com.uriel.graphs.types.Vertice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

import static org.mockito.Mockito.*;

class TopologicalSortTest {

    private final DepthFirstSearch depthFirstSearch;
    private final TopologicalSort subject;

    private final GraphBuilder builder;

    TopologicalSortTest() {
        depthFirstSearch = mock(DepthFirstSearch.class);
        builder = new GraphBuilder();

        subject = new TopologicalSort(depthFirstSearch);
    }

    @Test
    void mustSortCorrectly() {
        // arrange
        var verticesDescriptors = VerticeDescriptor.of("0,1,2,3");
        var edgesDescriptors = EdgeDescriptor
                .of("0->1, 0->2, 1->2, 2->3");
        var g = builder.build(
                verticesDescriptors, edgesDescriptors
        );
        Consumer<Vertice<?>> consumer = (Vertice<?> v) -> {};
        var data = new DFSData(verticesDescriptors.size());
        data.setF(new int[]{4, 3, 2, 1});
        when(depthFirstSearch.dfs(same(g), any())).thenReturn(data);
        // act
        var sorted = subject.ts(g);
        // assert
        Assertions.assertEquals(List.of(0, 1, 2, 3), sorted);
        System.out.println(sorted);
    }
}