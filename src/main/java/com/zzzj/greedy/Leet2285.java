package com.zzzj.greedy;


import com.zzzj.leet.LeetUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-10-12 15:34
 */
public class Leet2285 {

    public static void main(String[] args) {
        System.out.println(maximumImportance(5, LeetUtils.convertInts("[[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]")));
    }

    public static long maximumImportance(int n, int[][] roads) {

        // 建图
        Map<ScoredRoad, Set<ScoredRoad>> graph = new HashMap<>(n);

        for (int i = 0; i < n; i++) {
            graph.put(new ScoredRoad(i), new HashSet<>());
        }

        for (int[] road : roads) {
            ScoredRoad x = new ScoredRoad(road[0]);
            ScoredRoad y = new ScoredRoad(road[1]);
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        List<ScoredRoad> list = graph.entrySet()
                .stream().sorted((o1, o2) -> o2.getValue().size() - o1.getValue().size())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        for (int i = 0; i < n; i++) {
            list.get(i).score = n - i;
        }

        long ans = 0;

        for (Map.Entry<ScoredRoad, Set<ScoredRoad>> entry : graph.entrySet()) {
            for (ScoredRoad scoredRoad : entry.getValue()) {
                ans += entry.getKey().score + scoredRoad.score;
            }

        }

        return ans;
    }

    private static class ScoredRoad {
        int index;
        int score;

        public ScoredRoad(int index) {
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ScoredRoad that = (ScoredRoad) o;

            return index == that.index;
        }

        @Override
        public int hashCode() {
            return index;
        }
    }

}
