package com.zzzj.arr;

import com.zzzj.util.CopyableIterator;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-12-16 11:19
 */
public class Leet1288 {

    public static void main(String[] args) {
        // [[1,2],[1,4],[3,4]]
    }

    public static int removeCoveredIntervals(int[][] intervals) {

        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        int max = -1;

        int N = intervals.length;

        int ans = N;

        for (int i = 0; i < N; i++) {
            if (intervals[i][1] <= max) {
                ans--;
            } else {
                max = intervals[i][1];
            }
        }


        return ans;
    }

}
