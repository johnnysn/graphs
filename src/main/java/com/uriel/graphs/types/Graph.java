package com.uriel.graphs.types;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class Graph<T> {

    private List<Vertice<T>> vertices;
    private List<Edge<T>> edges;
    private Map<Long, List<Edge<T>>> adj;

    public Graph(List<Vertice<T>> vertices, List<Edge<T>> edges, Map<Long, List<Edge<T>>> adj) {
        this.vertices = vertices;
        this.edges = edges;
        this.adj = adj;
    }
}
