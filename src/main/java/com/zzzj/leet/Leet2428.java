package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-10-11 12:21
 */
public class Leet2428 {


    public static void main(String[] args) {
        System.out.println(maxSum(LeetUtils.convertInts("[[1,2,3],[4,5,6],[7,8,9]]")));
    }

    public static int maxSum(int[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        int ans = 0;

        int[][] preSum = new int[M + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            preSum[1][i] = preSum[1][i - 1] + grid[0][i - 1];
        }

        for (int i = 1; i <= M; i++) {
            preSum[i][1] = preSum[i - 1][1] + grid[i - 1][0];
        }

        for (int i = 2; i <= M; i++) {
            for (int j = 2; j <= N; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }

        for (int i = 3; i <= M; i++) {

            for (int j = 3; j <= N; j++) {
                int sum = preSum[i][j] - preSum[i - 3][j] - preSum[i][j - 3] + preSum[i - 3][j - 3];

                ans = Math.max(ans, sum - grid[i - 2][j - 3] - grid[i - 2][j - 1]);
            }

        }

        return ans;
    }

}
