package com.zzzj.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2022-08-13 14:23
 */
public class Leet2049 {

    public static void main(String[] args) {
        System.out.println(countHighestScoreNodes(new int[]{-1, 2, 0}));
        System.out.println(max);
    }

    public static long max;
    public static int ans;

    public static int countHighestScoreNodes(int[] parents) {
        max = -1;
        ans = 0;

        Map<Integer, int[]> map = new HashMap<>();

        for (int i = 0; i < parents.length; i++) {
            int parent = parents[i];
            int[] ints = map.get(parent);
            if (ints == null) {
                ints = new int[]{i, -1};
                map.put(parent, ints);
            } else {
                ints[1] = i;
            }
        }

        dfs(map, 0, parents.length);

        return ans;
    }

    public static int dfs(Map<Integer, int[]> parents, int index, int N) {
        if (index >= N || index < 0) {
            return 0;
        }

        int[] children = parents.get(index);

        if (children == null) {
            computeAns(N - 1);
            return 1;
        } else {
            int left = dfs(parents, children[0], N);

            int right = dfs(parents, children[1], N);

            computeAns((long) Math.max(left, 1) * Math.max(right, 1) * Math.max((N - 1 - left - right), 1));

            return left + right + 1;
        }
    }

    public static void computeAns(long nodes) {
        if (nodes > max) {
            ans = 1;
            max = nodes;
        } else if (nodes == max) {
            ans++;
        }
    }

}
