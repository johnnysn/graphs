package com.uriel.graphs.algorithms.mst;

import com.uriel.graphs.builders.GraphBuilder;
import com.uriel.graphs.builders.descriptors.EdgeDescriptor;
import com.uriel.graphs.builders.descriptors.VerticeDescriptor;
import com.uriel.graphs.exceptions.UnsupportedGraphException;
import com.uriel.graphs.types.models.Edge;
import com.uriel.graphs.types.models.Graph;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class KruskalTest {

    private final GraphBuilder builder;
    private final Kruskal subject;

    KruskalTest() {
        builder = new GraphBuilder();
        subject = new Kruskal();
    }

    @Test
    void mustThrowIfGraphIsDirected() {
        // arrange
        var graph = Graph.builder().directed(true).build();
        // act & assert
        assertThrows(UnsupportedGraphException.class, () -> subject.run(graph));
    }

    @Test
    void mustGenerateMST() {
        // arrange
        var g = builder.build(
                VerticeDescriptor.of("0:a, 1:b, 2:c, 3:d, 4:e, 5:f, 6:g, 7:h, 8:i"),
                EdgeDescriptor.of("""
                        a->b:4, b->c:17, c->d:7, d->e:9, e->f:16,
                        f->g:2, g->h:1, g->i:15, b->h:11, i->h:13,
                        c->i:2, c->f:4, d->f:14, a->h:8
                        """),
                true
        );
        System.out.println(g.getVertices());
        System.out.println(g.getAdj());
        // act
        var data = subject.run(g);
        // assert
        var a = data.getEdges();
        System.out.println(a);
        assertTrue(a.contains(Edge.of(0,1)));
        assertTrue(a.contains(Edge.of(0,7)));
        assertTrue(a.contains(Edge.of(6,7)));
        assertTrue(a.contains(Edge.of(5,6)));
        assertTrue(a.contains(Edge.of(3,4)));
        assertTrue(a.contains(Edge.of(2,3)));
        assertTrue(a.contains(Edge.of(2,5)));
        assertTrue(a.contains(Edge.of(2,8)));
    }
}