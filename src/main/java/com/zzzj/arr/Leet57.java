package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet57 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(insert(LeetUtils.convertInts("[[1,3],[6,9]]"), LeetUtils.convertInts1("[2,5]"))));
        System.out.println(Arrays.deepToString(insert(LeetUtils.convertInts("[[1,2],[3,5],[6,7],[8,10],[12,16]]"), LeetUtils.convertInts1("[4,8]"))));
        System.out.println(Arrays.deepToString(insert(LeetUtils.convertInts("[[1,5]]"), LeetUtils.convertInts1("[2,3]"))));
        System.out.println(Arrays.deepToString(insert(LeetUtils.convertInts("[[1,5]]"), LeetUtils.convertInts1("[2,7]"))));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {

        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }

        // 1. intervals无重叠

        // 2. 插入后需保证有序且无重叠

        // 3. 可以合并区间

        int newStart = newInterval[0];
        int newEnd = newInterval[1];

        int N = intervals.length;

        List<int[]> list = new ArrayList<>(N);

        int index = 0;

        int minStart = newStart;

        for (; index < N; index++) {
            int start = intervals[index][0];
            int end = intervals[index][1];

            if (newStart > end) {
                list.add(intervals[index]);
            } else {
                minStart = Math.min(start, minStart);
                if (newEnd <= end) {

                    if (newEnd < start) {
                        list.add(new int[]{minStart, newEnd});
                    } else {
                        list.add(new int[]{minStart, end});
                        index++;
                    }
                    break;
                }
            }
        }

        if (newEnd > intervals[N - 1][1]) {
            list.add(new int[]{minStart, newEnd});
        } else {
            for (; index < N; index++) {
                list.add(intervals[index]);
            }
        }

        int[][] ans = new int[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

}
