package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

public class Leet2140 {

    public static void main(String[] args) {
        System.out.println(mostPoints(LeetUtils.convertInts("[[3,2],[4,3],[4,4],[2,5]]")));
    }

    public static long mostPoints(int[][] questions) {

        int N = questions.length;

        long[] dp = new long[N + 1];

        for (int i = N - 1; i >= 0; i--) {
            int point = questions[i][0];
            int bp = questions[i][1];
            dp[i] = Math.max(dp[i + 1], point + (i + bp + 1 >= N ? 0 : dp[i + bp + 1]));
        }

        return dp[0];
    }

}
