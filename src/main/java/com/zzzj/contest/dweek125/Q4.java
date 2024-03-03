package com.zzzj.contest.dweek125;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public class Q4 {

    public static void main(String[] args) {

        // 输入：
        // [0,92,56,3,34,23,56]
        // 7
        // [[2,6],[4,1],[5,0],[1,0],[3,1],[6,3]]
        //输出：
        //285
        //预期：
        //288
        System.out.println(maximumValueSum(new int[]{0,92,56,3,34,23,56}, 7, LeetUtils.convertInts("[[2,6],[4,1],[5,0],[1,0],[3,1],[6,3]]")));

        System.out.println(maximumValueSum(new int[]{1, 2, 1}, 3, LeetUtils.convertInts("[[0,1],[0,2]]")));

        System.out.println(maximumValueSum(new int[]{2, 3}, 7, LeetUtils.convertInts("[[0,1]]")));

        System.out.println(maximumValueSum(new int[]{7, 7, 7, 7, 7, 7}, 3, LeetUtils.convertInts("[[0,1],[0,2],[0,3],[0,4],[0,5]]")));
    }

    public static long maximumValueSum(int[] nums, int k, int[][] edges) {

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], integer -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], integer -> new HashSet<>()).add(edge[0]);
        }

        int[] r = dfs(0, -1, graph, nums, k);

        return Math.max(r[0], r[1]);
    }

    // 0 = 不异或
    // 1 = 异或
    public static int[] dfs(
            int node,
            int pa,
            Map<Integer, Set<Integer>> graph,
            int[] nums,
            int k
    ) {

        Set<Integer> children = graph.get(node);

        // 叶子节点
        if (children.size() == 1 && children.contains(pa))
            return new int[]{nums[node], nums[node] ^ k};

        int[] res = new int[2];

        int v = nums[node];
        int xv = nums[node] ^ k;

        List<int[]> vs = new ArrayList<>(children.size());

        int s0 = 0;

        for (Integer child : children) {

            if (child == pa)
                continue;

            int[] cr = dfs(child, node, graph, nums, k);

            vs.add(cr);

            s0 += cr[0];
        }

        vs.sort((o1, o2) -> o2[1] - o1[1]);

        res[0] = v + s0;

        int s1 = 0;

        for (int i = 0; i < vs.size(); i++) {

            s1 += vs.get(i)[1];
            s0 -= vs.get(i)[0];

            if (i % 2 == 0) {
                res[1] = Math.max(res[1], s1 + s0 + xv);
            } else {
                res[0] = Math.max(res[0], s1 + s0 + v);
            }

        }

        vs.sort((o1, o2) -> o2[0] - o1[0]);

        for (int i = 0; i < vs.size(); i++) {

            s0 += vs.get(i)[0];
            s1 -= vs.get(i)[1];

            if (i % 2 == 0) {
                res[1] = Math.max(res[1], s1 + s0 + xv);
            } else {
                res[0] = Math.max(res[0], s1 + s0 + v);
            }

        }

        return res;
    }

}
