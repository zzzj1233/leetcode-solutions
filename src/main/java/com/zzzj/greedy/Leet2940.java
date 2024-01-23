package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-12-22 17:52
 */
public class Leet2940 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(leftmostBuildingQueries(
                new int[]{5, 3, 8, 2, 6, 1, 4, 6},
                LeetUtils.convertInts("[[0,7],[3,5],[5,2],[3,0],[1,6]]")
        )));

    }

    public static int[] minHeap(int[] heights, int[][] queries) {

        int N = heights.length;

        int M = queries.length;

        int[] ans = new int[M];

        Arrays.fill(ans, -1);

        Map<Integer, List<Integer>> f = new HashMap<>();

        for (int i = 0; i < M; i++) {

            int left = Math.min(queries[i][0], queries[i][1]);

            int right = Math.max(queries[i][0], queries[i][1]);

            if (heights[right] > heights[left] || left == right)
                ans[i] = right;
            else
                f.computeIfAbsent(right, integer -> new ArrayList<>()).add(i);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(N, Comparator.comparingInt(o -> Math.max(
                heights[queries[o][0]],
                heights[queries[o][1]]
        )));

        for (int i = 0; i < N; i++) {

            while (!queue.isEmpty() && Math.max(
                    heights[queries[queue.peek()][0]],
                    heights[queries[queue.peek()][1]]
            ) < heights[i])
                ans[queue.remove()] = i;

            if (f.containsKey(i))
                queue.addAll(f.get(i));

        }

        return ans;
    }

    // 线段树 + 二分
    public static int[] leftmostBuildingQueries(int[] heights, int[][] queries) {

        int N = heights.length;

        int M = queries.length;

        int[] ans = new int[M];

        return ans;
    }

    private static class SegmentTree {

        private final int N;

        private final int[] tree;

        public SegmentTree(int[] data) {
            this.N = data.length + 1;
            this.tree = new int[this.N << 2];
            // buildTree(1,N,);
        }

        public void buildTree(int l, int r, int rt, int[] data) {

        }

    }


}
