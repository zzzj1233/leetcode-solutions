package com.zzzj.uf;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author Zzzj
 * @create 2022-09-25 17:28
 */
public class Leet1042 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(gardenNoAdj(3, LeetUtils.convertInts("[[1,2],[2,3],[3,1]]"))));
    }


    public static int[] gardenNoAdj(int n, int[][] paths) {

        int[] ans = new int[n];

        Map<Integer, List<Integer>> graph = new HashMap<>(n);

        for (int[] path : paths) {
            graph.computeIfAbsent(path[0], integer -> new ArrayList<Integer>(3))
                    .add(path[1]);

            graph.computeIfAbsent(path[1], integer -> new ArrayList<Integer>(3))
                    .add(path[0]);

        }

        for (int i = 1; i <= n; i++) {
            if (ans[i - 1] == 0) {
                dfs(graph, i, ans);
            }
        }

        return ans;
    }

    public static void dfs(Map<Integer, List<Integer>> graph, int index, int[] ans) {
        List<Integer> neighbor = graph.get(index);

        ans[index - 1] = 1;

        if (neighbor == null || neighbor.isEmpty()) {
            return;
        }

        int used = 0;

        for (Integer neigh : neighbor) {
            used |= ans[neigh - 1] << 1;
        }

        for (int i = 1; i < 5; i++) {
            if ((used >> i) == 0) {
                ans[index - 1] = i;
                break;
            }
        }

        for (Integer neigh : neighbor) {
            if (ans[neigh - 1] == 0) {
                dfs(graph, neigh, ans);
            }
        }

    }

}
