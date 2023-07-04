package com.uriel.graphs.algorithms.dfs;

import com.uriel.graphs.algorithms.types.VisitColor;
import com.uriel.graphs.types.models.Graph;
import com.uriel.graphs.types.models.Vertice;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class DepthFirstSearch {

    public DFSData dfs(Graph<?> g, int rootIndex, Consumer<Vertice<?>> consumer) {
        var V = g.getVertices();
        var data = new DFSData(V.size());
        var root = V.get(rootIndex);

        dfsVisit(g, root, 1, data, consumer);

        return data;
    }

    public DFSData dfs(Graph<?> g, Consumer<Vertice<?>> consumer) {
        var V = g.getVertices();
        var data = new DFSData(V.size());

        int time = 1;
        for (var root : V) {
            if (data.getColors()[root.getId()] == VisitColor.WHITE) {
                time = dfsVisit(g, root, time, data, consumer) + 1;
            }
        }

        return data;
    }

    private int dfsVisit(Graph<?> g, Vertice<?> u, int time, DFSData data, Consumer<Vertice<?>> consumer) {
        var adj = g.getAdj();
        data.getColors()[u.getId()] = VisitColor.GRAY;
        data.getD()[u.getId()] = time++;
        consumer.accept(u);
        for (var edge : adj.get(u.getId())) {
            var v = edge.getV();
            if (data.getColors()[v.getId()] == VisitColor.WHITE) {
                data.getPi()[v.getId()] = u;
                time = dfsVisit(g, v, time, data, consumer);
            }
        }
        data.getF()[u.getId()] = time++;
        data.getColors()[u.getId()] = VisitColor.BLACK;
        return time;
    }

}
