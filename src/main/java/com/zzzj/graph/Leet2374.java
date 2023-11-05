package com.zzzj.graph;

public class Leet2374 {

    public static int edgeScore(int[] edges) {

        int N = edges.length;

        int[] sum = new int[N];

        int maxSum = -1;
        int maxNode = Integer.MAX_VALUE;

        for (int i = 0; i < edges.length; i++) {
            sum[edges[i]] += i;
            if (sum[edges[i]] > maxSum) {
                maxSum = sum[edges[i]];
                maxNode = edges[i];
            } else if (sum[edges[i]] == maxSum)
                maxNode = Math.min(maxNode, edges[i]);
        }

        return maxNode;
    }

}
