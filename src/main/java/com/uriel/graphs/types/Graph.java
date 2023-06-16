package com.uriel.graphs.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Graph<T> {

    private List<Vertice<T>> vertices;
    private List<Edge<T>> edges;
    private List<List<Edge<T>>> adj;
    private boolean directed;

}
