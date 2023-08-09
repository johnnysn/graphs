package com.uriel.graphs.algorithms.ssmp;

import com.uriel.graphs.builders.GraphBuilder;
import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import com.uriel.graphs.exceptions.UnsupportedGraphException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BellmanFordTest {

    private final GraphBuilder builder;
    private final BellmanFord subject;

    BellmanFordTest() {
        builder = new GraphBuilder();
        subject = new BellmanFord();
    }

    @Test
    void mustThrowExceptionIfGraphHasNegativeWeightCycle() {
        // arrange
        var g = builder.build(VerticeDescriptor.of("0:a, 1:b, 2:c, 3:d"), EdgeDescriptor.of("""
                    d->a:7, a->b:-2, b->c:-4, c->a:5
                """));
        // act & assert
        assertThrows(UnsupportedGraphException.class, () -> subject.run(g, 3));
    }

    @Test
    void mustCalcMinimumPathCorrectly() {
        // arrange
        var g = builder.build(
                VerticeDescriptor.of("0:a, 1:b, 2:c, 3:d, 4:e"),
                EdgeDescriptor.of("""
                    a->b:-1, a->c:4, b->c:3, b->e:2, 
                    b->d:2, e->d:-3, d->b:1, d->c:5
                """));
        // act
        var data = subject.run(g, 0);
        // assert
        System.out.println(Arrays.toString(data.getD()));
        System.out.println(Arrays.toString(data.getPi()));
        assertTrue(Arrays.equals(new double[]{0.0, -1.0, 2.0, -2.0, 1.0}, data.getD()));
        assertTrue(Arrays.equals(new int[]{-1, 0, 1, 4, 1}, data.getPi()));
    }
}