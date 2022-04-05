package com.zzzj.hot;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-04-05 20:51
 */
public class Leet56 {


    public static void main(String[] args) {
        merge(LeetUtils.convertInts("[[1,3],[8,10],[2,6],[15,18]]"));
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int start = intervals[0][0];
        int end = intervals[0][1];

        List<int[]> list = new ArrayList<>(intervals.length);

        for (int i = 1; i < intervals.length; i++) {
            int s = intervals[i][0];
            int e = intervals[i][1];
            if (s <= end) {
                if (e > end) {
                    end = e;
                }
            } else {
                list.add(new int[]{start, end});
                start = s;
                end = e;
            }
        }

        list.add(new int[]{start, end});

        int[][] ans = new int[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }


}
