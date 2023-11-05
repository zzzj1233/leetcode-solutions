package com.zzzj.dp;

public class Lint125 {


    public static void main(String[] args) {

        System.out.println(backPackII(10, new int[]{2, 3, 5, 7}, new int[]{1, 5, 2, 4}));

    }

    public static int backPackII(int m, int[] a, int[] v) {

        int N = a.length;

        int[] dp = new int[m + 1];

        for (int i = 0; i < N; i++) {

            for (int j = m; j >= a[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - a[i]] + v[i]);
            }

        }

        return dp[m];
    }
}
