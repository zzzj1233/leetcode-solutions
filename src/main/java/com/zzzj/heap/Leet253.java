package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Zzzj
 * @create 2022-03-14 19:48
 */
public class Leet253 {

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(LeetUtils.convertInts("[[2,11],[6,16],[11,16]]")));
    }


    public static int minMeetingRooms(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(o -> ((int[]) o)[0]).thenComparingInt(o -> ((int[]) o)[1]));

        // [[13,15],[1,13]]
        // [[2,11],[6,16],[11,16]]
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> ((int[]) o)[1]).thenComparingInt(o -> ((int[]) o)[0]));

        int cur = 0;

        for (int i = 0; i < intervals.length; i++) {
            int[] item = intervals[i];
            queue.add(new int[]{i, item[0]});
            queue.add(new int[]{i, item[1]});
        }

        int max = 0;
        int ans = 0;

        while (!queue.isEmpty()) {
            int[] item = queue.remove();
            int index = item[0];
            int value = item[1];
            if (value == intervals[index][0]) {
                max++;
            } else {
                max--;
            }
            ans = Math.max(ans, max);
        }

        return ans;
    }


}
