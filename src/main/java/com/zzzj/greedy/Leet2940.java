package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-12-22 17:52
 */
public class Leet2940 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(leftmostBuildingQueries(
                new int[]{6, 4, 8, 5, 2, 7},
                LeetUtils.convertInts("[[0,1],[0,3],[2,4],[3,4],[2,2]]")
        )));

    }


    public static int[] leftmostBuildingQueries(int[] heights, int[][] queries) {

        int N = heights.length;

        int M = queries.length;

        int[] ans = new int[M];

        Integer[] indexes = new Integer[M];

        for (int i = 0; i < M; i++)
            indexes[i] = i;

        Arrays.sort(indexes, Comparator.comparingInt(index -> Math.max(queries[index][0], queries[index][1])));

        PriorityQueue<Integer> queue = new PriorityQueue<>(N, Comparator.comparingInt(o -> o));

        for (int i = 0; i < N; i++)
            queue.add(i);

        for (int i = 0; i < M; i++) {

            int[] query = queries[indexes[i]];

            int maxIndex = Math.max(query[0], query[1]);

            int value = Math.max(heights[query[0]], heights[query[1]]);

            while (!queue.isEmpty() && (queue.peek() < maxIndex || heights[queue.peek()] <= value))
                queue.remove();

            ans[indexes[i]] = queue.isEmpty() ? -1 : queue.peek();
        }

        return ans;
    }

}
