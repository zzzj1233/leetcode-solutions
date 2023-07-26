package com.zzzj.daily;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

public class Leet931 {

    public static void main(String[] args) {

        System.out.println(minFallingPathSum(LeetUtils.convertInts("[[2,1,3],[6,5,4],[7,8,9]]")));

        System.out.println(minFallingPathSum(LeetUtils.convertInts("[[-19,57],[-40,-5]]")));

    }

    public static int minFallingPathSum(int[][] matrix) {

        int M = matrix.length;

        int N = matrix[0].length;

        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            dp[i] = matrix[0][i];
        }

        for (int i = 1; i < M; i++) {

            int[] temp = new int[N];

            for (int j = 0; j < N; j++) {

                temp[j] = dp[j] + matrix[i][j];

                if (j - 1 >= 0) temp[j] = Math.min(temp[j], dp[j - 1] + matrix[i][j]);
                if (j + 1 < N) temp[j] = Math.min(temp[j], dp[j + 1] + matrix[i][j]);
            }

            for (int j = 0; j < N; j++) dp[j] = temp[j];

        }

        return Arrays.stream(dp).min().orElse(0);
    }

}
