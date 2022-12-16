package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.Unresolved;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-11-23 15:33
 */
public class Leet2406 {

    public static void main(String[] args) {
        System.out.println(minGroups(LeetUtils.convertInts("[[5,10],[6,8],[1,5],[2,3],[1,10]]")));
        System.out.println(minGroups(LeetUtils.convertInts("[[1,3],[5,6],[8,10],[11,13]]")));
    }

    public static int minGroups(int[][] intervals) {

        // 按照开始时间排序,越靠前的start越小
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int N = intervals.length;

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int[] interval = intervals[i];

            if (!queue.isEmpty() && interval[0] > queue.peek()) {
                queue.remove();
            }

            queue.add(interval[1]);
        }

        return queue.size();
    }


}
