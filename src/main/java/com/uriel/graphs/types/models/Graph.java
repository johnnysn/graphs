package com.uriel.graphs.types.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class Graph<T> {

    private List<Vertice<T>> vertices;
    private List<Edge<T>> edges;
    private List<List<Edge<T>>> adj;
    private boolean directed;

}
