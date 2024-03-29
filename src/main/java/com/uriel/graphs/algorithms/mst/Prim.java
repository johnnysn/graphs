package com.uriel.graphs.algorithms.mst;

import com.uriel.graphs.exceptions.UnsupportedGraphException;
import com.uriel.graphs.types.models.Edge;
import com.uriel.graphs.types.models.Graph;
import com.uriel.graphs.types.structs.Heapable;
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
            int u_id = state.index;
            for (var e : g.getAdj().get(u_id)) {
                int v_id = e.getTo().getId();
                if (v_id == u_id) v_id = e.getFrom().getId(); // Dealing with bidirectionality
                if (states[v_id].inQueue && states[v_id].key > e.getWeight()) {
                    states[v_id].edge = e;
                    queue.decreasePriority(states[v_id], e.getWeight());
                }
            }
        }

        return data;
    }

    private static class State implements Heapable {
        int index;
        double key = Double.MAX_VALUE;
        Edge<?> edge = null;
        boolean inQueue = true;

        public State(int index) {
            this.index = index;
        }


        @Override
        public double getKey() {
            return key;
        }

        @Override
        public void setKey(double value) {
            key = value;
        }

        @Override
        public String toString() {
            return "State{" +
                    "index=" + index +
                    ", key=" + key +
                    ", edge=" + edge +
                    ", inQueue=" + inQueue +
                    '}';
        }
    }

}
