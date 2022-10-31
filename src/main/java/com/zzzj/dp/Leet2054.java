package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-27 18:14
 */
public class Leet2054 {

    public static void main(String[] args) {
        System.out.println(maxTwoEvents(LeetUtils.convertInts("[[10,83,9],[63,87,8],[97,100,6],[51,61,2]]")));
    }

    public static int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[2] - o1[2];
            }
            return o1[1] - o2[1];
        });

        int N = events.length;

        int[] values = new int[N];
        values[0] = events[0][2];

        for (int i = 1; i < N; i++) {
            values[i] = Math.max(values[i - 1], events[i][2]);
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int index = binarySearch(events, i);
            ans = Math.max(ans, events[i][2]);
            if (index >= 0) {
                ans = Math.max(ans, values[index] + events[i][2]);
            }
        }

        return ans;
    }

    public static int binarySearch(int[][] events, int i) {
        int start = events[i][0];

        int left = 0;
        int right = i - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (events[mid][1] < start) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left - 1;
    }

}
