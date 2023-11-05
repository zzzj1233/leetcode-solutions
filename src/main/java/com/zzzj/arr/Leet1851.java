package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-10-17 16:34
 */
public class Leet1851 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(minInterval(
                LeetUtils.convertInts("[[1,4],[2,4],[3,6],[4,4]]"),
                new int[]{2, 3, 4, 5}
        )));
//
//        System.out.println(Arrays.toString(minInterval(
//                LeetUtils.convertInts("[[2,3],[2,5],[1,8],[20,25]]"),
//                new int[]{2, 19, 5, 22}
//        )));

        System.out.println(Arrays.toString(minInterval(
                LeetUtils.convertInts("[[4, 5], [1, 6], [5, 8], [1, 9], [8, 10]]"),
                new int[]{3, 3, 7, 9, 9}
        )));

    }

    public static final int INDEX_START = 0;

    public static final int INDEX_END = 1;

    public static int size(int[][] intervals, int index) {
        return intervals[index][1] - intervals[index][0] + 1;
    }

    public static int[] minInterval(int[][] intervals, int[] queries) {

        int N = intervals.length;

        int M = queries.length;

        int[][] combined = new int[M][2];

        for (int i = 0; i < M; i++) {
            combined[i][0] = queries[i];
            combined[i][1] = i;
        }

        Arrays.sort(combined, Comparator.comparingInt(o -> o[0]));

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[INDEX_START]));

        int[] ans = new int[M];

        int right = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(value -> size(intervals, value)));

        for (int i = 0; i < M; i++) {

            int query = combined[i][0];

            int ansIndex = combined[i][1];

            while (!queue.isEmpty() && intervals[queue.peek()][INDEX_END] < query)
                queue.remove();

            while (right < N && intervals[right][INDEX_START] <= query) {

                if (intervals[right][INDEX_END] < query) {
                    right++;
                    continue;
                }

                queue.add(right);

                right++;
            }

            ans[ansIndex] = queue.isEmpty() ? -1 : size(intervals, queue.peek());
        }

        return ans;
    }

}
