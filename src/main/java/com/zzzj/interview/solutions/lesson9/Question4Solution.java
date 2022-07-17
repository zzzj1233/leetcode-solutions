package com.zzzj.interview.solutions.lesson9;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-10 22:30
 */
public class Question4Solution {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int[] arr = ArrayUtil.generateArray(100, 1, 100);
            if (question4(arr) != right(arr)) {
                System.out.println("Error");
                return;
            }
        }

        System.out.println("Ok");
    }


    public static int question4(int[] arr) {

        int N = arr.length;

        int sum = Arrays.stream(arr).sum();

        int min = Arrays.stream(arr).min().getAsInt();

        boolean[][] dp = new boolean[N][sum + 1];

        // dp[i][j] 代表 arr[0 ~ i]是否可达j
        dp[N - 1][sum] = true;

        for (int i = 0; i < N; i++) {
            dp[i][arr[i]] = true;
        }

        for (int i = 1; i < N; i++) {

            for (int j = min; j <= sum; j++) {
                if (!dp[i][j]) {
                    dp[i][j] = dp[i - 1][j] || (j - arr[i] >= 0 && dp[i - 1][j - arr[i]]);
                }
            }

        }

        for (int i = min; i <= sum; i++) {
            if (!dp[N - 1][i]) {
                return i;
            }
        }

        return sum + 1;
    }

    public static int right(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }
        // boolean[][] dp ...
        int N = arr.length;
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {// arr[0..i] 0
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j] || ((j - arr[i] >= 0) ? dp[i - 1][j - arr[i]] : false);
            }
        }
        for (int j = min; j <= sum; j++) {
            if (!dp[N - 1][j]) {
                return j;
            }
        }
        return sum + 1;
    }

}
