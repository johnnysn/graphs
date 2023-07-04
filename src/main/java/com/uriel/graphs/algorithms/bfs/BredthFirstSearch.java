package com.uriel.graphs.algorithms.bfs;

import com.uriel.graphs.algorithms.types.VisitColor;
import com.uriel.graphs.types.models.Graph;
import com.uriel.graphs.types.models.Vertice;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

@Component
public class BredthFirstSearch {

    public BFSData bfs(Graph<?> g, int rootIndex, Consumer<Vertice<?>> consumer) {
        var V = g.getVertices();
        var data = new BFSData(V.size());
        var root = V.get(rootIndex);
        var queue = new ArrayDeque<Vertice<?>>();
        consumer.accept(root);
        data.getColors()[root.getId()] = VisitColor.GRAY;
        queue.add(root);

        while (!queue.isEmpty()) {
            var u = queue.poll();
            if (Set.of(VisitColor.WHITE, VisitColor.GRAY).contains(data.getColors()[u.getId()]))
                bfsVisit(g, u, 1, data, queue, consumer);
        }

        return data;
    }

    private int bfsVisit(Graph<?> g, Vertice<?> u, int time, BFSData data, Queue<Vertice<?>> queue, Consumer<Vertice<?>> consumer) {
        var adj = g.getAdj();
        data.getD()[u.getId()] = time;
        for (var e : adj.get(u.getId())) {
            var v = e.getV();
            if (data.getColors()[v.getId()] == VisitColor.WHITE) {
                data.getColors()[v.getId()] = VisitColor.GRAY;
                data.getPi()[v.getId()] = u;
                consumer.accept(v);
                queue.add(v);
            }
        }
        data.getColors()[u.getId()] = VisitColor.BLACK;

        return ++time;
    }

}
