package com.zzzj.graph;

import java.util.List;

public class Leet802 {

    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int N = graph.length;

        int[] outDegree = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                outDegree[j]++;
            }
        }


        boolean[] mark = new boolean[N];

        for (int i = N - 1; i >= 0; i--) {
            if (outDegree[i] == 0) {
                mark[i] = true;
            }
        }

        return null;
    }

}
