package com.zzzj.review;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-03-15 17:28
 */
public class Leet56 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(merge(LeetUtils.convertInts("[[1,4],[2,3]]"))));
    }

    /**
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * <p>
     * 输出：[[1,6],[8,10],[15,18]]
     * <p>
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     */
    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals,(o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        int N = intervals.length;

        List<int[]> list = new ArrayList<>();

        int start = intervals[0][0];

        int end = intervals[0][1];

        for (int i = 1; i < N; i++) {
            int s = intervals[i][0];
            int e = intervals[i][1];

            if (s > end) {
                list.add(new int[]{start, end});
                start = s;
            }

            end = Math.max(end, e);
        }

        list.add(new int[]{start, end});

        return list.toArray(new int[0][0]);
    }

}
