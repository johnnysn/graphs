package com.uriel.graphs.algorithms.mst;

import com.uriel.graphs.exceptions.UnsupportedGraphException;
import com.uriel.graphs.types.models.Edge;
import com.uriel.graphs.types.models.Graph;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Kruskal {

    public MSTData run(Graph<?> g) {
        if (g.isDirected())
            throw new UnsupportedGraphException("Cannot find MST for a directed graph.");

        MSTData data = new MSTData();
        Map<Integer, Set<Integer>> sets = new HashMap<>();
        for (var vertex : g.getVertices()) {
            var set = new HashSet<Integer>();
            set.add(vertex.getId());
            sets.put(vertex.getId(), set);
        }
        var edges = g.getEdges().stream().sorted(Comparator.comparing(Edge::getWeight)).toList();

        for (var edge: edges) {
            var setU = sets.get(edge.getU().getId());
            var setV = sets.get(edge.getV().getId());

            if (setU != setV) {
                setU.addAll(setV); // Merge sets
                setU.forEach(v -> sets.put(v, setU)); // Maps all vertices in the set to the set

                data.getEdges().add(edge);
            }
        }

        return data;
    }

}
