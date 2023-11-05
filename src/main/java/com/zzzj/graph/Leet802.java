package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public class Leet802 {

    public static void main(String[] args) {

        System.out.println(eventualSafeNodes(LeetUtils.convertInts("[[1,2],[2,3],[5],[0],[5],[],[]]")));

    }

    public static List<Integer> eventualSafeNodes(int[][] graph) {

        int N = graph.length;

        int[] inDegree = new int[N];

        Map<Integer, Set<Integer>> G = new HashMap<>();

        for (int i = 0; i < graph.length; i++) {

            for (int adj : graph[i]) {

                inDegree[i]++;

                G.computeIfAbsent(adj, integer -> new HashSet<>())
                        .add(i);
            }

        }

        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0)
                queue.add(i);
        }

        List<Integer> ans = new ArrayList<>();

        while (!queue.isEmpty()) {

            Integer item = queue.remove();

            ans.add(item);

            Set<Integer> adjs = G.getOrDefault(item, Collections.emptySet());

            for (Integer adj : adjs) {
                inDegree[adj]--;
                if (inDegree[adj] == 0)
                    queue.add(adj);
            }

        }

        Collections.sort(ans);

        return ans;
    }

}
