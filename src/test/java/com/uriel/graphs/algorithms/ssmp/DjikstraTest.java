package com.uriel.graphs.algorithms.ssmp;

import com.uriel.graphs.builders.GraphBuilder;
import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import static org.junit.jupiter.api.Assertions.*;

import com.uriel.graphs.exceptions.UnsupportedGraphException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class DjikstraTest {

    private final GraphBuilder builder;
    private final Djikstra subject;

    DjikstraTest() {
        builder = new GraphBuilder();
        subject = new Djikstra();
    }

    @Test
    void mustThrowExceptionIfGraphHasNegativeWeightedEdge() {
        // arrange
        var g = builder.build(VerticeDescriptor.of("0:a, 1:b, 2:c, 3:d"), EdgeDescriptor.of("""
                    d->a:7, a->b:2, b->c:-4, c->a:5
                """));
        // act & assert
        assertThrows(UnsupportedGraphException.class, () -> subject.run(g, 3));
    }

    @Test
    void mustFindMinimumPaths() {
        // arrange
        var g = builder.build(
                VerticeDescriptor.of("0:a, 1:b, 2:c, 3:d, 4:e, 5:f"),
                EdgeDescriptor.of("""
                        a->b:7, b->d:9, d->f:1, b->c:2, 
                        a->c:12, c->e:10, e->d:4, e->f:5
                        """),
                false
        );
        // act
        var data = subject.run(g, 0);
        // assert
        System.out.println(Arrays.toString(data.getD()));
        System.out.println(Arrays.toString(data.getPi()));
        assertTrue(Arrays.equals(new double[]{0.0, 7.0, 9.0, 16.0, 19.0, 17.0}, data.getD()));
        assertTrue(Arrays.equals(new int[]{-1, 0, 1, 1, 2, 3}, data.getPi()));
    }
}