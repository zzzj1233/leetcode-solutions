package com.zzzj.review;

/**
 * @author zzzj
 * @create 2023-03-15 17:20
 */
public class Leet62 {

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
    }

    public static int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0){
                    dp[i][j] = 1;
                    continue;
                }

                dp[i][j] = pick(dp, i - 1, j) + pick(dp, i, j - 1);
            }

        }

        return dp[m - 1][n - 1];
    }

    public static int pick(int[][] arr, int i, int j) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[i].length) {
            return 0;
        }
        return arr[i][j];
    }

}
