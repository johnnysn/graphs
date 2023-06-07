package com.uriel.graphs.algorithms.sort;

import com.uriel.graphs.algorithms.dfs.DFSData;
import com.uriel.graphs.algorithms.dfs.DepthFirstSearch;
import com.uriel.graphs.builders.DirectedGraphBuilder;
import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import com.uriel.graphs.types.Vertice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static org.mockito.Mockito.*;

class TopologicalSortTest {

    private final DepthFirstSearch depthFirstSearch;
    private final TopologicalSort subject;

    private final DirectedGraphBuilder builder;

    TopologicalSortTest() {
        depthFirstSearch = mock(DepthFirstSearch.class);
        builder = new DirectedGraphBuilder();

        subject = new TopologicalSort(depthFirstSearch);
    }

    @Test
    void mustSortCorrectly() {
        // arrange
        var verticesDescriptors = Set.of(VerticeDescriptor.of(0), VerticeDescriptor.of(1), VerticeDescriptor.of(2), VerticeDescriptor.of(3));
        var edgesDescriptors = Set.of(
                EdgeDescriptor.of(0, 1),
                EdgeDescriptor.of(0, 2),
                EdgeDescriptor.of(1, 2),
                EdgeDescriptor.of(2, 3)
        );
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