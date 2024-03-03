package com.zzzj.contest.week387;

import com.zzzj.leet.LeetUtils;

public class Q2 {

    public static int countSubmatrices(int[][] grid, int k) {

        int N = grid.length;

        int M = grid[0].length;

        int[][] ps = calculatePrefixSum(grid);

        int ans = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (ps[i][j] <= k)
                    ans++;
            }
        }

        return ans;
    }

    public static int[][] calculatePrefixSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0][0];
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // 创建一个与原始数组大小相同的前缀和数组
        int[][] prefixSum = new int[rows][cols];

        // 计算第一行的前缀和
        prefixSum[0][0] = matrix[0][0];
        for (int j = 1; j < cols; j++) {
            prefixSum[0][j] = prefixSum[0][j - 1] + matrix[0][j];
        }

        // 计算第一列的前缀和
        for (int i = 1; i < rows; i++) {
            prefixSum[i][0] = prefixSum[i - 1][0] + matrix[i][0];
        }

        // 计算其他位置的前缀和
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                prefixSum[i][j] = matrix[i][j] + prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1];
            }
        }

        return prefixSum;
    }

    public static void main(String[] args) {

        System.out.println(countSubmatrices(LeetUtils.convertInts("[[7,6,3],[6,6,1]]"), 18));

        System.out.println(countSubmatrices(LeetUtils.convertInts("[[7,2,9],[1,5,0],[2,6,6]]"), 20));

    }

}
