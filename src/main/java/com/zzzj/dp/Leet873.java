package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-07-05 19:17
 */
public class Leet873 {

    public static void main(String[] args) {
        System.out.println(lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));

        System.out.println(lenLongestFibSubseq(new int[]{1, 3, 7, 11, 12, 14, 18}));
    }

    // x + y = j
    public static int lenLongestFibSubseq(int[] arr) {

        int ans = 1;

        int N = arr.length;

        int[][] dp = new int[N][N];

        for (int[] ints : dp) {
            Arrays.fill(ints, 2);
        }

        for (int i = 0; i < arr.length; i++) {

            for (int j = i + 1; j < arr.length; j++) {

                int sum = arr[i] + arr[j];

                int index = Arrays.binarySearch(arr, j, arr.length, sum);

                if (index >= 0) {
                    dp[j][index] = 1 + dp[i][j];
                    ans = Math.max(ans, dp[j][index]);
                }

            }

        }

        return ans;
    }

    public static int dp(int[] arr) {
        int N = arr.length;

        int[][] dp = new int[N][N];

        return -1;
    }

    public static int dfs(int[] arr, int i, int j) {
        for (int k = j + 1; k < arr.length; k++) {
            if (arr[k] == arr[i] + arr[j]) {
                return 1 + dfs(arr, j, k);
            }
        }
        return 0;
    }

}
