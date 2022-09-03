package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-08-30 18:14
 */
public class Leet799 {

    public static void main(String[] args) {
//        System.out.println(champagneTower(1, 1, 1));
//        System.out.println(champagneTower(2, 1, 1));
//        System.out.println(champagneTower(4, 2, 1));
        System.out.println(champagneTower(100000009, 33, 17));
        System.out.println(champagneTower(25, 6, 1));
    }

    public static double champagneTower(int poured, int row, int col) {
        double[][] dp = new double[row + 1][row + 1];

        dp[0][0] = poured;

        for (int i = 1; i <= row; i++) {

            dp[i][0] = Math.max(0, (dp[i - 1][0] - 1) / 2);

            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.max((dp[i - 1][j - 1] - 1) / 2, 0);
                dp[i][j] += Math.max((dp[i - 1][j] - 1) / 2, 0);
            }

            dp[i][i] = Math.max(0, (dp[i - 1][i - 1] - 1) / 2);
        }

        return Math.min(1, dp[row][col]);
    }

}
