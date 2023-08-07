package com.uriel.graphs.algorithms.ssmp;

import lombok.Getter;

@Getter
public class SSMPData {

    private int[] pi;
    private double[] d;

    public SSMPData(int n) {
        pi = new int[n];
        d = new double[n];
    }
}
