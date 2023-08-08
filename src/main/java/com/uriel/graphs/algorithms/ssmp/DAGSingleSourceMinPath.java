package com.uriel.graphs.algorithms.ssmp;

import com.uriel.graphs.algorithms.sort.TopologicalSort;
import com.uriel.graphs.types.models.Graph;
import org.springframework.stereotype.Component;

@Component
public class DAGSingleSourceMinPath {

    private final TopologicalSort topologicalSort;

    public DAGSingleSourceMinPath(TopologicalSort topologicalSort) {
        this.topologicalSort = topologicalSort;
    }

    public SSMPData run(Graph<?> g, int source) {
        var V = g.getVertices();
        var adj = g.getAdj();
        var data = new SSMPData(g.getVertices().size());
        var d = data.getD();
        var pi = data.getPi();

        for (int i = 0; i < V.size(); i++) {
            d[i] = (i == source) ? 0 : Double.MAX_VALUE;
            pi[i] = -1;
        }

        var order = topologicalSort.ts(g);

        for (int u_index : order) {
            for (var e : adj.get(u_index)) {
                int v_index = e.getTo().getId();
                if (d[u_index] != Double.MAX_VALUE && d[u_index] + e.getWeight() < d[v_index]) {
                    d[v_index] = d[u_index] + e.getWeight();
                    pi[v_index] = u_index;
                }
            }
        }

        return data;
    }

}
