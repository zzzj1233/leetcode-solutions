package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-07 18:52
 */
public class Leet265 {

    public static void main(String[] args) {
        System.out.println(minCostII(LeetUtils.convertInts("[[1,5,3],[2,9,4]]")));
    }

    // 原始版
    public static int minCostII2(int[][] costs) {
        int N = costs.length;
        int K = costs[0].length;

        int[][] dp = new int[N][K];

        for (int i = 0; i < K; i++) {
            dp[0][i] = costs[0][i];
        }

        for (int i = 1; i < N; i++) {

            for (int j = 0; j < K; j++) {

                int min = Integer.MAX_VALUE;

                for (int k = 0; k < K; k++) {
                    if (k == j) {
                        continue;
                    }
                    min = Math.min(min, dp[i - 1][k]);
                }

                dp[i][j] = min + costs[i][j];
            }

        }

        int ans = dp[N - 1][0];

        for (int i = 1; i < K; i++) {
            ans = Math.max(ans, dp[N - 1][i]);
        }

        return ans;
    }

    // 空间压缩版 -> 滚动数组
    public static int minCostII(int[][] costs) {
        int N = costs.length;
        int K = costs[0].length;

        int[] dp = new int[K];
        int[] copy = new int[K];

        for (int i = 0; i < K; i++) {
            dp[i] = costs[0][i];
            copy[i] = dp[i];
        }


        for (int i = 1; i < N; i++) {

            for (int j = 0; j < K; j++) {

                int min = Integer.MAX_VALUE;

                for (int k = 0; k < K; k++) {

                    if (k == j) {
                        continue;
                    }

                    min = Math.min(min, dp[k]);
                }

                copy[j] = min + costs[i][j];
            }

            dp = copy;

        }


        int ans = dp[0];

        for (int i = 1; i < K; i++) {
            ans = Math.min(ans, dp[i]);
        }

        return ans;
    }

}
