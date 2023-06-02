package com.uriel.graphs.algorithms.dfs;

import com.uriel.graphs.algorithms.types.VisitColor;
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

    public DFSData(int n) {
        d = new int[n];
        f = new int[n];
        colors = new VisitColor[n];
        for (int i = 0; i < n; i++) {
            d[i] = -1;
            f[i] = -1;
            colors[i] = VisitColor.WHITE;
        }
    }
}
