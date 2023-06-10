package com.uriel.graphs.algorithms.sort;

import com.uriel.graphs.algorithms.dfs.DepthFirstSearch;
import com.uriel.graphs.algorithms.types.Pair;
import com.uriel.graphs.types.Graph;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopologicalSort {

    private final DepthFirstSearch depthFirstSearch;

    public TopologicalSort(DepthFirstSearch depthFirstSearch) {
        this.depthFirstSearch = depthFirstSearch;
    }

    public List<Integer> ts(Graph<?> g) {
        var data = depthFirstSearch.dfs(g, v -> {});

        int[] fs = data.getF();
        var pairs = new ArrayList<Pair<Integer>>();
        for (int i = 0; i < fs.length; i++)
            pairs.add(new Pair<>(i, fs[i]));

        return pairs.stream()
                .sorted((p1, p2) -> -Integer.compare(p1.getValue(), p2.getValue()))
                .map(Pair::getIndex).toList();
    }

}
