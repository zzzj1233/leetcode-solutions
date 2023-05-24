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

        System.out.println(lenLongestFibSubseq(new int[]{2, 4, 7, 8, 9, 10, 14, 15, 18, 23, 32, 50}));
    }

    // [1,2,3,4,5,6,7,8]
    public static int lenLongestFibSubseq(int[] arr) {

        int N = arr.length;

        int ans = 0;

        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 2);
        }

        for (int i = N - 1; i >= 0; i--) {

            for (int j = i - 1; j >= 0; j--) {

                int sum = arr[i] + arr[j];

                int sumIndex = Arrays.binarySearch(arr, i + 1, arr.length, sum);

                if (sumIndex >= 0) {
                    dp[j][i] = Math.max(dp[j][i], dp[i][sumIndex] + 1);
                    ans = Math.max(ans, dp[j][i]);
                }

            }

        }

        return ans > 2 ? ans : 0;
    }


}
