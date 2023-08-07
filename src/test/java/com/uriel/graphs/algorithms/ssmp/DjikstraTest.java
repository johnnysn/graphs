package com.uriel.graphs.algorithms.ssmp;

import com.uriel.graphs.builders.GraphBuilder;
import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals(7.0, data.getD()[1]);
        assertEquals(9.0, data.getD()[2]);
        assertEquals(16.0, data.getD()[3]);
        assertEquals(19.0, data.getD()[4]);
        assertEquals(17.0, data.getD()[5]);
        assertEquals(2, data.getPi()[4]);
        assertEquals(1, data.getPi()[2]);
        assertEquals(0, data.getPi()[1]);
    }
}