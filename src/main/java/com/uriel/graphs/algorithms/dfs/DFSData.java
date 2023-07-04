package com.uriel.graphs.algorithms.dfs;

import com.uriel.graphs.algorithms.types.VisitColor;
import com.uriel.graphs.types.models.Vertice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DFSData {
    private int[] d;
    private int[] f;
    private VisitColor[] colors;
    private Vertice<?>[] pi;

    public DFSData(int n) {
        d = new int[n];
        f = new int[n];
        pi = new Vertice[n];
        colors = new VisitColor[n];
        for (int i = 0; i < n; i++) {
            d[i] = -1;
            f[i] = -1;
            pi[i] = null;
            colors[i] = VisitColor.WHITE;
        }
    }
}
