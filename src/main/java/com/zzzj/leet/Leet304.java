package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-06-24 21:50
 */
public class Leet304 {

    public static void main(String[] args) {

        NumMatrix numMatrix = new NumMatrix(LeetUtils.convertInts("[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]"));

        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));

    }

    private static class NumMatrix {

        private final int[][] preSum;

        // 二维数组前缀和
        public NumMatrix(int[][] matrix) {
            int M = matrix.length;
            int N = matrix[0].length;

            this.preSum = new int[M + 1][N + 1];

            for (int i = 1; i <= M; i++)
                for (int j = 1; j <= N; j++)
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];

        }

        public int sumRegion(int r, int c, int r1, int c1) {
            return preSum[r1 + 1][c1 + 1] - preSum[r1 + 1][c] - preSum[r][c1 + 1] + preSum[r][c];
        }

    }

}
