package com.zzzj.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-04-25 17:55
 */
public class Leet57 {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int start = newInterval[0];
        int end = newInterval[0];

        List<int[]> ans = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            // start
            int s = interval[0];

            // end
            int e = interval[1];


        }

        return null;
    }

}
