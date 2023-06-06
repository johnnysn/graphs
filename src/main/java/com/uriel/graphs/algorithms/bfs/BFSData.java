package com.uriel.graphs.algorithms.bfs;

import com.uriel.graphs.algorithms.types.VisitColor;
import com.uriel.graphs.types.Vertice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BFSData {
    private int[] d;
    private VisitColor[] colors;
    private Vertice<?>[] pi;

    public BFSData(int n) {
        d = new int[n];
        pi = new Vertice[n];
        colors = new VisitColor[n];
        for (int i = 0; i < n; i++) {
            d[i] = -1;
            pi[i] = null;
            colors[i] = VisitColor.WHITE;
        }
    }
}
