package com.zzzj.leet;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-05-18 14:49
 */
public class Leet435 {

    public static void main(String[] args) {
        eraseOverlapIntervals(LeetUtils.convertInts("[[1,2],[2,3],[3,4],[1,3]]"));
    }

    // [[0,2],[1,3],[2,4],[3,5],[4,6]]
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals,
                Comparator.comparingInt(o -> ((int[]) o)[0])
                        .thenComparing((o1, o2) -> ((int[]) o2)[1] - ((int[]) o1)[1])
        );

        int end = intervals[0][1];

        int ans = 0;

        for (int i = 1; i < intervals.length; i++) {
            int[] item = intervals[i];
            int start = item[0];
            int ed = item[1];
            if (start < end) {
                ans++;
                if (end >= ed) {
                    end = ed;
                }
            } else {
                end = ed;
            }
        }

        return ans;
    }

}
