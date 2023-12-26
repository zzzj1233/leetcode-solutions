package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-11-07 17:19
 */
public class Leet741 {

    public static void main(String[] args) {

        System.out.println(cherryPickup(LeetUtils.convertInts("[[0,1,-1],[1,0,-1],[1,1,1]]")));

        System.out.println(cherryPickup(LeetUtils.convertInts("[[1,1,-1],[1,-1,1],[-1,1,1]]")));

        System.out.println(cherryPickup(LeetUtils.convertInts("[[1,-1,1,-1,1,1,1,1,1,-1],[-1,1,1,-1,-1,1,1,1,1,1],[1,1,1,-1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1],[-1,1,1,1,1,1,1,1,1,1],[1,-1,1,1,1,1,-1,1,1,1],[1,1,1,-1,1,1,-1,1,1,1],[1,-1,1,-1,-1,1,1,1,1,1],[1,1,-1,-1,1,1,1,-1,1,-1],[1,1,-1,1,1,1,1,1,1,1]]")));

    }

    public static int cherryPickup(int[][] grid) {

        int N = grid.length;

        int[][][][] dp = new int[N][N][N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    for (int x = 0; x < N; x++) {
                        dp[i][j][k][x] = -1;
                    }
                }
            }
        }

        dp[0][0][0][0] = grid[0][0];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {

                        int g1 = grid[i][j];

                        int g2 = grid[x][y];

                        if (g2 == -1 || g1 == -1) {
                            continue;
                        }

                        int case1 = i - 1 >= 0 && x - 1 >= 0 ? dp[i - 1][j][x - 1][y] : -1;
                        int case2 = i - 1 >= 0 && y - 1 >= 0 ? dp[i - 1][j][x][y - 1] : -1;
                        int case3 = j - 1 >= 0 && x - 1 >= 0 ? dp[i][j - 1][x - 1][y] : -1;
                        int case4 = j - 1 >= 0 && y - 1 >= 0 ? dp[i][j - 1][x][y - 1] : -1;

                        int val = i == x && j == y ? g1 : g1 + g2;

                        int m1 = Math.max(case1, case2);

                        int m2 = Math.max(case3, case4);

                        if (m1 == -1 && m2 == -1) {
                            continue;
                        }

                        dp[i][j][x][y] = Math.max(m1, m2) + val;
                    }
                }
            }
        }

        return Math.max(0, dp[N - 1][N - 1][N - 1][N - 1]);
    }

}
