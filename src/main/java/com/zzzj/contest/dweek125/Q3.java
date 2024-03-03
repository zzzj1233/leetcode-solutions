package com.zzzj.contest.dweek125;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public class Q3 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(countPairsOfConnectableServers(LeetUtils.convertInts("[[0,1,1],[1,2,5],[2,3,13],[3,4,9],[4,5,2]]"), 1)));

    }

    public static int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {

        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], integer -> new HashMap<>()).put(edge[1], edge[2]);
            graph.computeIfAbsent(edge[1], integer -> new HashMap<>()).put(edge[0], edge[2]);
        }

        int N = graph.size();

        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {

            Map<Integer, Integer> map = graph.get(i);

            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

            boolean[] va = new boolean[N];

            for (int a = 0; a < list.size(); a++) {

                Integer na = list.get(a).getKey();
                Integer disa = list.get(a).getValue();

                va[na] = true;
                va[i] = true;

                boolean[] vb = new boolean[N];

                for (int b = a + 1; b < list.size(); b++) {

                    Integer nb = list.get(b).getKey();
                    Integer disb = list.get(b).getValue();

                    if (vb[nb])
                        continue;

                    vb[nb] = true;

                    ans[i] += calc(na, disa, nb, disb, signalSpeed, va, vb, graph);
                }

            }


        }

        return ans;
    }

    public static int calc(
            int na,
            int disa,
            int nb,
            int disb,
            int signalSpeed,
            boolean[] va,
            boolean[] vb,
            Map<Integer, Map<Integer, Integer>> graph
    ) {

        int res = 0;

        if (na % signalSpeed == 0 && nb % signalSpeed == 0)
            res = 1;

        for (Map.Entry<Integer, Integer> entry : graph.get(nb).entrySet()) {

            int next = entry.getKey();

            int dis = entry.getValue();

            if (va[next] || vb[next])
                continue;

            vb[next] = true;

            res += calc(na, disa, next, disb + dis, signalSpeed, va, vb, graph);
        }

        return res;
    }


}
