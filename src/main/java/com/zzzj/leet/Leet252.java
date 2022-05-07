package com.zzzj.leet;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-05-07 18:25
 */
public class Leet252 {



    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

}
