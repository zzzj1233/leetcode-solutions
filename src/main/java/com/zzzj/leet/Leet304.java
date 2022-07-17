package com.zzzj.leet;

import java.util.Arrays;

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


        int[][] prefix;

        public NumMatrix(int[][] matrix) {
            int N = matrix.length;
            int M = matrix[0].length;

            prefix = new int[N + 1][M + 1];

            for (int i = 1; i <= N; i++) {

                for (int j = 1; j <= M; j++) {
                    // 图例

                    // https://leetcode.cn/problems/range-sum-query-2d-immutable/solution/er-wei-qian-zhui-he-jian-dan-tui-dao-tu-sqekv/

                    prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] + matrix[i - 1][j - 1] - prefix[i - 1][j - 1];
                }

            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {

            // + 左上角的值,这块区域被-了两次
            return prefix[row2 + 1][col2 + 1] - prefix[row2 + 1][col1] - prefix[row1][col2 + 1] + prefix[row1][col1];
        }

    }

}
