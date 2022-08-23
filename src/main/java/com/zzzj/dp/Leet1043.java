package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-08-22 19:07
 */
public class Leet1043 {

    public static void main(String[] args) {
        System.out.println(maxSumAfterPartitioning(new int[]{1, 15, 7, 9, 2, 5, 10}, 3));
        System.out.println(dfs(new int[]{1, 15, 7, 9, 2, 5, 10}, 3, 0));
    }

    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int N = arr.length;

        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {

            int start = Math.max(0, i - k + 1);

            int maxValue = -1;

            int max = -1;

            for (int j = i; j >= start; j--) {
                maxValue = Math.max(maxValue, arr[j]);
                max = Math.max(max, maxValue * (i - j + 1) + (j - 1 >= 0 ? dp[j - 1] : 0));
            }

            dp[i] = max;
        }

        return dp[N - 1];
    }

    private static int dfs(int[] arr, int k, int index) {
        if (index >= arr.length) {
            return 0;
        }
        // k组
        int sum = 0;

        int max = 0;

        int end = Math.min(k, arr.length - index);

        int maxValue = -1;

        for (int i = 0; i < end; i++) {
            maxValue = Math.max(maxValue, arr[i + index]);
            max = Math.max(max, maxValue * (i + 1) + dfs(arr, k, i + 1 + index));
        }

        return max;
    }

}
