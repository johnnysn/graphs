package com.uriel.graphs.algorithms.ssmp;

import com.uriel.graphs.exceptions.UnsupportedGraphException;
import com.uriel.graphs.types.models.Graph;
import org.springframework.stereotype.Component;

@Component
public class BellmanFord {

    public SSMPData run(Graph<?> g, int source) {
        var V = g.getVertices();
        var adj = g.getAdj();

        var data = new SSMPData(V.size());
        var d = data.getD();
        var pi = data.getPi();
        for (var v : V) {
            d[v.getId()] = (v.getId() == source) ? 0 : Double.MAX_VALUE;
            pi[v.getId()] = -1;
        }

        for (int i = 0; i < V.size() - 1; i++)
            for (var u : V) for (var e : adj.get(u.getId())) {
                var v = e.getTo();
                if (d[u.getId()] != Double.MAX_VALUE && d[u.getId()] + e.getWeight() < d[v.getId()]) {
                    d[v.getId()] = d[u.getId()] + e.getWeight();
                    pi[v.getId()] = u.getId();
                }
            }

        for (var u : V) for (var e : adj.get(u.getId())) {
            var v = e.getTo();
            if (d[u.getId()] != Double.MAX_VALUE && d[u.getId()] + e.getWeight() < d[v.getId()])
                throw new UnsupportedGraphException("The given graph has negative weight cycles.");
        }

        return data;
    }

}
