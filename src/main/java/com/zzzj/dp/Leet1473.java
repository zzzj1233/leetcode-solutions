package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-07-11 15:47
 */
public class Leet1473 {

    public static void main(String[] args) {

        System.out.println(minCost(new int[]{0, 0, 0, 0, 0}, LeetUtils.convertInts("[[1,10],[10,1],[10,1],[1,10],[5,1]]"), 5, 2, 3));
////
        System.out.println(minCost(new int[]{0, 2, 1, 2, 0}, LeetUtils.convertInts("[[1,10],[10,1],[10,1],[1,10],[5,1]]"), 5, 2, 3));
////
        System.out.println(minCost(new int[]{0, 0, 0, 0, 0}, LeetUtils.convertInts("[[1,10],[10,1],[1,10],[10,1],[1,10]]"), 5, 2, 5));
////
        System.out.println(minCost(new int[]{3, 1, 2, 3}, LeetUtils.convertInts("[[1,1,1],[1,1,1],[1,1,1],[1,1,1]]"), 4, 3, 3));

        System.out.println(minCost(new int[]{0, 0, 0, 1}, LeetUtils.convertInts("[[1,5],[4,1],[1,3],[4,4]]"), 4, 2, 4));
    }

    // 输入：houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
    // ans: 11
    // [2,2,1,2,2]
    // target = 3 个街区，分别是 [{2,2}, {1}, {2,2}]。
    // 给第一个和最后一个房子涂色的花费为 (10 + 1) = 11。
    public static int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int N = houses.length;

        int[][][] dp = new int[N][n + 1][target + 1];

        fill(dp[0], Integer.MAX_VALUE);
        // 还未涂色
        if (houses[0] == 0) {
            for (int i = 1; i <= n; i++) {
                dp[0][i][1] = cost[0][i - 1];
            }
            // 已涂色
        } else {
            dp[0][houses[0]][1] = 0;
        }

        for (int i = 1; i < N; i++) {

            fill(dp[i], Integer.MAX_VALUE);

            // 已经涂色
            if (houses[i] != 0) {
                for (int k = 1; k <= n; k++) {

                    int min = Math.min(i + 1, target);

                    for (int j = 1; j <= min; j++) {
                        // 如果和上一个房子颜色相同,那么可以取target相同的值
                        if (houses[i] == k) {
                            dp[i][houses[i]][j] = Math.min(dp[i - 1][houses[i]][j], dp[i][houses[i]][j]);
                            // 如果和上一个房子颜色不同,那么只能取target - 1的值
                        } else { // house[i] != k
                            if (j > 1) {
                                dp[i][houses[i]][j] = Math.min(dp[i - 1][k][j - 1], dp[i][houses[i]][j]);
                            }
                        }
                    }
                }
            } else {
                // 还未涂色
                for (int k = 1; k <= n; k++) {

                    // 当前房子涂成k的成本
                    int curCost = cost[i][k - 1];

                    int min = Math.min(i + 1, target);

                    for (int j = 1; j <= min; j++) {
                        // 当前颜色
                        dp[i][k][j] = dp[i - 1][k][j] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[i - 1][k][j] + curCost;
                        // 不同颜色
                        if (j > 1) {
                            for (int l = 1; l <= n; l++) {
                                if (k != l && dp[i - 1][l][j - 1] != Integer.MAX_VALUE) {
                                    dp[i][k][j] = Math.min(dp[i - 1][l][j - 1] + curCost, dp[i][k][j]);
                                }
                            }
                        }
                    }


                }
            }
        }

        int min = Integer.MAX_VALUE;
        if (houses[N - 1] == 0) {
            for (int i = 1; i <= n; i++) {
                min = Math.min(min, dp[N - 1][i][target]);
            }
        } else {
            min = dp[N - 1][houses[N - 1]][target];
        }


        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void fill(int[][] dp, int fill) {
        for (int[] ints : dp) {
            Arrays.fill(ints, fill);
        }
    }

}
