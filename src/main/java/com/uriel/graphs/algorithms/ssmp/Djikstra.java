package com.uriel.graphs.algorithms.ssmp;

import com.uriel.graphs.exceptions.UnsupportedGraphException;
import com.uriel.graphs.types.models.Graph;
import com.uriel.graphs.types.models.Vertice;
import com.uriel.graphs.types.structs.Heapable;
import com.uriel.graphs.types.structs.PriorityQueue;
import lombok.Getter;
import lombok.Setter;

public class Djikstra {

    public SSMPData run(Graph<?> g, int source) {
        if (!g.isDirected())
            throw new UnsupportedGraphException("This algorithm requires a directed graph.");

        var data = new SSMPData(g.getVertices().size());
        var V = g.getVertices();
        var adj = g.getAdj();
        var d = data.getD();
        var pi = data.getPi();
        var states = new State[V.size()];

        PriorityQueue<State> queue = new PriorityQueue<>(V.size());
        for (Vertice<?> v: V) {
            d[v.getId()] = v.getId() == source ? 0 : Double.MAX_VALUE;
            pi[v.getId()] = -1;
            states[v.getId()] = new State(v.getId());
            states[v.getId()].setKey(d[v.getId()]);
            states[v.getId()].inQueue = true;
            queue.offer(states[v.getId()]);
        }

        while (!queue.isEmpty()) {
            var u_state = queue.poll();
            var u = V.get(u_state.index);
            u_state.inQueue = false;
            for (var e : adj.get(u.getId())) {
                if (e.getWeight() < 0)
                    throw new UnsupportedGraphException("This algorithm does not support negative-weighted edges.");
                var v = e.getTo();
                var v_state = states[v.getId()];
                if (v_state.inQueue && d[u.getId()] + e.getWeight() < d[v.getId()]) {
                    d[v.getId()] = d[u.getId()] + e.getWeight();
                    queue.decreasePriority(v_state, d[v.getId()]);
                    pi[v.getId()] = u.getId();
                }
            }
        }

        return data;
    }

    private static class State implements Heapable {
        boolean inQueue;
        final int index;

        @Getter
        @Setter
        double key = Double.MAX_VALUE;

        private State(int index) {
            this.index = index;
        }
    }

}
