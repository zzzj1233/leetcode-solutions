package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-06-15 21:45
 */
public class Leet361 {

    public static void main(String[] args) {
//        // 1
//        System.out.println(maxKilledEnemies(LeetUtils.convertChars("[[\"W\",\"W\",\"W\"],[\"0\",\"0\",\"0\"],[\"E\",\"E\",\"E\"]]")));
//        // 3
//        System.out.println(maxKilledEnemies(LeetUtils.convertChars("[[\"0\",\"E\",\"0\",\"0\"],[\"E\",\"0\",\"W\",\"E\"],[\"0\",\"E\",\"0\",\"0\"]]")));
//        // 3
//        System.out.println(maxKilledEnemies(LeetUtils.convertChars("[[\"E\",\"E\",\"0\",\"W\"],[\"E\",\"0\",\"0\",\"E\"]]")));

        System.out.println(maxKilledEnemies(LeetUtils.convertChars("[[\"E\",\"W\",\"E\",\"0\"],[\"E\",\"W\",\"E\",\"0\"]]")));
        System.exit(0);
        for (int i = 0; i < 10000; i++) {
            char[][] chars = LeetUtils.random2DChars(2, 4, "0EW");
            if (maxKilledEnemies(chars) != right(chars)) {
                System.out.println("Error");
                System.out.println(LeetUtils.charsToLeetCode(chars));
                System.out.println(maxKilledEnemies(chars));
                System.out.println(right(chars));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int maxKilledEnemies(char[][] grid) {
        return dp(grid, grid.length, grid[0].length);
    }

    public static int dp(char[][] grid, int N, int M) {
        int[][][] dp = new int[N][M][2];

        dp[0][0][0] = grid[0][0] == 'E' ? 1 : 0;
        dp[0][0][1] = dp[0][0][0];

        int ans = dp[0][0][0];

        // 第一行
        for (int j = 1; j < M; j++) {
            if (grid[0][j] != 'W') {
                dp[0][j][0] = dp[0][j - 1][0] + (grid[0][j] == 'E' ? 1 : 0);
                dp[0][j][1] = (grid[0][j] == 'E' ? 1 : 0);
            }
        }

        // 第一列
        for (int i = 1; i < N; i++) {
            if (grid[i][0] != 'W') {
                dp[i][0][1] = dp[i - 1][0][0] + (grid[i][0] == 'E' ? 1 : 0);
                dp[i][0][0] = grid[i][0] == 'E' ? 1 : 0;
            }
        }


        // 最后一行
        for (int j = 1; j < M; j++) {
            if (grid[N - 1][j] != 'W') {
                dp[N - 1][j][0] = dp[N - 1][j - 1][0] + (grid[N - 1][j] == 'E' ? 1 : 0);
                dp[N - 1][j][1] = (grid[N - 1][j] == 'E' ? 1 : 0);
            }
        }

        // 最后一列
        for (int i = 1; i < N; i++) {
            if (grid[i][M - 1] != 'W') {
                dp[i][M - 1][1] = dp[i - 1][M - 1][0] + (grid[i][M - 1] == 'E' ? 1 : 0);
                dp[i][M - 1][0] = dp[i][M - 2][0] + (grid[i][M - 1] == 'E' ? 1 : 0);
            }
        }


        // 第一列反向
        for (int i = N - 2; i >= 0; i--) {
            if (grid[i][0] != 'W') {
                dp[i][0][1] = dp[i + 1][0][1];
            }
            // 最后一列反向
            if (grid[i][M - 1] != 'W') {
                dp[i][M - 1][1] = dp[i + 1][M - 1][1];
            }
        }

        // 第一行反向
        for (int j = M - 2; j >= 0; j--) {
            if (grid[0][j] != 'W') {
                dp[0][j][0] = dp[0][j + 1][0];
            }
            // 最后第一行反向
            if (grid[N - 1][j] != 'W') {
                dp[N - 1][j][0] = dp[N - 1][j + 1][0];
            }
        }

        System.out.println(Arrays.deepToString(dp));

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {
                // 不是炸弹
                if (grid[i][j] != 'W') {
                    dp[i][j][0] = pick(dp, i, j - 1, 0) + ((grid[i][j] == 'E') ? 1 : 0);
                    dp[i][j][1] = pick(dp, i - 1, j - 1, 1) + ((grid[i][j] == 'E') ? 1 : 0);

                    if (grid[i][j] == '0') {
                        ans = Math.max(ans, Math.max(pick(dp, i - 1, j, 1), pick(dp, i + 1, j, 1)) + Math.max(pick(dp, i, j - 1, 0), pick(dp, i, j + 1, 0)));
                    }

                }
            }

        }

        return ans;
    }

    public static int pick(int[][][] dp, int i, int j, int k) {
        if (i < 0 || j < 0 || i >= dp.length || j >= dp[i].length) {
            return 0;
        }
        return dp[i][j][k];
    }

    public static int right(char[][] A) {
        // 如果为这些不正常的情况，肯定结果是0
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }
        int m = A.length;
        int n = A[0].length;
        // 向上能炸死多少敌人的辅助数组
        int[][] up = new int[m][n];
        // 向下能炸死多少敌人的辅助数组
        int[][] down = new int[m][n];
        // 向左能炸死多少敌人的辅助数组
        int[][] left = new int[m][n];
        // 向右能炸死多少敌人的辅助数组
        int[][] right = new int[m][n];

        // 完成向上和向左的辅助数组的初始化
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果是面墙，则直接返回0。
                if (A[i][j] == 'W') {
                    up[i][j] = 0;
                    left[i][j] = 0;
                    continue;
                }
                // 如果是空地，则返回上一个
                else if (A[i][j] == '0') {
                    if (i > 0) {
                        up[i][j] = up[i - 1][j];
                    } else {
                        up[i][j] = 0;
                    }

                    if (j > 0) {
                        left[i][j] = left[i][j - 1];
                    } else {
                        left[i][j] = 0;
                    }
                }
                // 只能是敌人
                else {
                    if (i > 0) {
                        up[i][j] = up[i - 1][j] + 1;
                    } else {
                        up[i][j] = 1;
                    }

                    if (j > 0) {
                        left[i][j] = left[i][j - 1] + 1;
                    } else {
                        left[i][j] = 1;
                    }
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // 如果是面墙，则直接返回0。
                if (A[i][j] == 'W') {
                    down[i][j] = 0;
                    right[i][j] = 0;
                }
                // 如果是空地，则返回上一个
                else if (A[i][j] == '0') {
                    if (i < m - 1) {
                        down[i][j] = down[i + 1][j];
                    } else {
                        down[i][j] = 0;
                    }

                    if (j < n - 1) {
                        right[i][j] = right[i][j + 1];
                    } else {
                        right[i][j] = 0;
                    }
                }
                // 只能是敌人
                else {
                    if (i < m - 1) {
                        down[i][j] = down[i + 1][j] + 1;
                    } else {
                        down[i][j] = 1;
                    }

                    if (j < n - 1) {
                        right[i][j] = right[i][j + 1] + 1;
                    } else {
                        right[i][j] = 1;
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == '0') {
                    res = Integer.max(res, up[i][j] + down[i][j] + left[i][j] + right[i][j]);
                }
            }
        }
        return res;
    }


}
