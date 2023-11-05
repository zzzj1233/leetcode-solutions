package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayCopyIterator;

import java.util.*;

public class Leet886 {

    public static void main(String[] args) {
        System.out.println(possibleBipartition(3, LeetUtils.convertInts("[[1,2],[1,3],[2,3]]")));

        System.out.println(possibleBipartition(4, LeetUtils.convertInts("[[1,2],[1,3],[2,4]]")));
    }

    public static boolean possibleBipartition(int n, int[][] dislikes) {

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] dislike : dislikes) {
            graph.computeIfAbsent(dislike[0], integer -> new HashSet<>()).add(dislike[1]);
            graph.computeIfAbsent(dislike[1], integer -> new HashSet<>()).add(dislike[0]);
        }

        int[] color = new int[n + 1];

        Arrays.fill(color, -1);

        color[0] = 0;

        for (int i = 1; i <= n; i++) {
            if (color[i] != -1)
                continue;
            if (checkCycle(i, graph, color, 0))
                return false;
        }

        return true;
    }

    public static boolean checkCycle(int node, Map<Integer, Set<Integer>> graph, int[] color, int prev) {

        if (color[node] == -1)
            color[node] = color[prev] ^ 1;

        Set<Integer> adjs = graph.get(node);

        if (adjs != null) {
            for (Integer adj : adjs) {
                if (color[adj] == color[node] && adj != prev)
                    return true;
                if (color[adj] == -1) {
                    if (checkCycle(adj, graph, color, node)) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

}
