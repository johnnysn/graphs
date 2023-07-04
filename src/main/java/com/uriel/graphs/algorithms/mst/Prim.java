package com.uriel.graphs.algorithms.mst;

import com.uriel.graphs.exceptions.UnsupportedGraphException;
import com.uriel.graphs.types.models.Edge;
import com.uriel.graphs.types.models.Graph;
import com.uriel.graphs.types.structs.PriorityQueue;
import org.springframework.stereotype.Component;

@Component
public class Prim {

    public MSTData run(Graph<?> g, int root) {
        if (g.isDirected())
            throw new UnsupportedGraphException("Cannot find MST for a directed graph.");

        MSTData data = new MSTData();
        State[] states = new State[g.getVertices().size()];
        PriorityQueue<State> queue = new PriorityQueue<>();
        g.getVertices().forEach(v -> {
            states[v.getId()] = new State(v.getId());
            if (v.getId() == root) states[v.getId()].key = 0;
            queue.add(states[v.getId()]);
        });

        while (!queue.isEmpty()) {
            var state = queue.poll();
            state.inQueue = false;
            if (state.edge != null) data.getEdges().add(state.edge);
            for (var e : g.getAdj().get(state.index)) {
                var vId = e.getV().getId();
                if (states[vId].inQueue && states[vId].key > e.getWeight()) {
                    states[vId].key = e.getWeight();
                    states[vId].edge = e;
                    // update priority queue: Must implement type
                }
            }
        }

        return data;
    }

    private static class State implements Comparable<State> {
        int index;
        double key = Double.MAX_VALUE;
        Edge<?> edge = null;
        boolean inQueue = true;

        public State(int index) {
            this.index = index;
        }

        @Override
        public int compareTo(State o) {
            return Double.compare(key, o.key);
        }
    }

}
