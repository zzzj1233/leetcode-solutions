package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-01-28 16:13
 */
public class Leet1272 {

    public static void main(String[] args) {
        System.out.println(removeInterval(
                LeetUtils.convertInts("[[0,2],[3,4],[5,7]]"),
                LeetUtils.convertInts1("[1,6]")
        ));
    }

    public static List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {

        List<List<Integer>> ans = new ArrayList<>();

        int N = intervals.length;

        int removeStart = toBeRemoved[0];

        int removeEnd = toBeRemoved[1];

        for (int i = 0; i < N; i++) {
            int[] interval = intervals[i];

            int start = interval[0];

            int end = interval[1];

            // 区间之外
            if (start >= removeStart && end <= removeEnd) {
                continue;
            } else if (start >= removeEnd || end <= removeStart) {
                ans.add(Arrays.asList(start, end));
                continue;
            }
            if (start < removeStart) {
                ans.add(Arrays.asList(start, removeStart));
            }
            if (end > removeEnd) {
                ans.add(Arrays.asList(removeEnd, end));
            }
        }

        return ans;
    }

}
