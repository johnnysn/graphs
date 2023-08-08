package com.uriel.graphs.algorithms.ssmp;

import com.uriel.graphs.algorithms.sort.TopologicalSort;
import com.uriel.graphs.builders.GraphBuilder;
import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DAGSingleSourceMinPathTest {

    private final TopologicalSort topologicalSort;
    private final GraphBuilder builder;
    private final DAGSingleSourceMinPath subject;

    DAGSingleSourceMinPathTest() {
        builder = new GraphBuilder();
        topologicalSort = mock(TopologicalSort.class);

        subject = new DAGSingleSourceMinPath(topologicalSort);
    }

    @Test
    void mustFindMinimumPathsCorrectly() {
        // arrange
        var g = builder.build(
                VerticeDescriptor.of("0:r, 1:t, 2:x, 3:s, 4:z, 5:y"),
                EdgeDescriptor.of("""
                        r->t:3, r->s:5, t->x:7, t->y:4, x->y:-1, 
                        x->z:1, s->x:6, s->t:2, y->z:-2
                        """),
                false
        );
        when(topologicalSort.ts(g)).thenReturn(
                List.of(0, 3, 1, 2, 5, 4)
        );
        // act
        var data = subject.run(g, 3);
        // assert
        System.out.println(Arrays.toString(data.getD()));
        System.out.println(Arrays.toString(data.getPi()));
        assertEquals(Double.MAX_VALUE, data.getD()[0]);
        assertEquals(2.0, data.getD()[1]);
        assertEquals(6.0, data.getD()[2]);
        assertEquals(0.0, data.getD()[3]); // Source
        assertEquals(3.0, data.getD()[4]);
        assertEquals(5.0, data.getD()[5]);
        assertEquals(-1, data.getPi()[0]);
        assertEquals(3, data.getPi()[1]);
        assertEquals(3, data.getPi()[2]);
        assertEquals(-1, data.getPi()[3]); // Source
        assertEquals(5, data.getPi()[4]);
        assertEquals(2, data.getPi()[5]);
    }
}