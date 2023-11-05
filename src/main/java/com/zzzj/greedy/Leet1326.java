package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.Comparator;

public class Leet1326 {

    public static void main(String[] args) {

        System.out.println(minTaps(3, new int[]{2, 0, 0, 1}));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {

            int N = LeetUtils.random.nextInt(300) + 2;

            int[] arr = ArrayUtil.generateArray(N, 0, N);

            int r = minTaps(N - 1, arr);
            int rr = right(N - 1, arr);

            if (r != rr) {
                System.out.println("N = " + (N - 1));
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                System.out.println("Error");
                return;
            }
        }

        System.out.println("Ok");

    }

    public static int minTaps(int n, int[] ranges) {

        int N = ranges.length;

        int[][] R = new int[ranges.length][2];

        for (int i = 0; i < N; i++) {
            R[i][0] = Math.max(0, i - ranges[i]);
            R[i][1] = Math.min(N, i + ranges[i]);
        }

        Arrays.sort(R, Comparator.comparingInt(o -> o[0]));

        int left = 0;

        int right = 0;

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int start = R[i][0];
            int end = R[i][1];

            if (right < start) {
                return -1;
            }

            if (start > left) {
                left = right;
                right = Math.max(right, end);
                ans++;
            } else {
                right = Math.max(right, end);
            }

            if (right >= n)
                return ans + 1;

        }

        return -1;
    }


    public static int right(int n, int[] ranges) {
        int[][] intervals = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            int start = Math.max(0, i - ranges[i]);
            int end = Math.min(n, i + ranges[i]);
            intervals[i] = new int[]{start, end};
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            if (dp[start] == Integer.MAX_VALUE) {
                return -1;
            }
            for (int j = start; j <= end; j++) {
                dp[j] = Math.min(dp[j], dp[start] + 1);
            }
        }
        return dp[n];
    }

}
