package com.zzzj.greedy;


/**
 * @author zzzj
 * @create 2023-05-19 11:18
 */
public class Leet1130 {

    public static void main(String[] args) {
        System.out.println(mctFromLeafValues(new int[]{1, 2, 3, 4, 5, 6}));

        System.out.println(mctFromLeafValues(new int[]{6, 2, 4}));
    }

    public static int mctFromLeafValues(int[] arr) {
        return dp(arr);
    }

    public static int dp(int[] arr) {
        int N = arr.length;

        int[][] dp = new int[N][N];

        for (int i = N - 1; i >= 0; i--) {

            for (int j = i + 1; j < N; j++) {

                if (j - i == 1) {
                    dp[i][j] = arr[i] * arr[j];
                } else {
                    int min = Integer.MAX_VALUE;

                    for (int k = i; k < j; k++) {

                        int leftMax = max(i, k, arr);

                        int rightMax = max(k + 1, j, arr);

                        min = Math.min(
                                min,
                                leftMax * rightMax + dp[i][k] + dp[k + 1][j]
                        );
                    }

                    dp[i][j] = min;
                }

            }

        }

        return dp[0][N - 1];
    }

    public static int dfs(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        if (left + 1 == right) {
            return arr[left] * arr[right];
        }

        int min = Integer.MAX_VALUE;

        for (int i = left; i < right; i++) {
            // 以i为分割点进行分割
            int leftMax = max(left, i, arr);

            int rightMax = max(i + 1, right, arr);

            min = Math.min(
                    min,
                    leftMax * rightMax + dfs(arr, left, i) + dfs(arr, i + 1, right)
            );
        }

        return min;
    }

    public static int max(int start, int end, int[] arr) {
        int max = arr[start];

        for (int i = start; i <= end; i++) {
            max = Math.max(arr[i], max);
        }

        return max;
    }
}
