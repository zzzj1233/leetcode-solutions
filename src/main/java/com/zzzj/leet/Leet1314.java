package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-12-08 12:28
 */
public class Leet1314 {

    /**
     * <p>
     * 给你一个m x n的矩阵mat和一个整数 k ，请你返回一个矩阵answer，其中每个answer[i][j]是所有满足下述条件的元素mat[i][j] 的和：
     * <p>
     * i - k <= i <= i + k,
     * j - k <= j <= j + k 且
     * (i, j)在矩阵内。
     * <p>
     * 示例 1：
     * <p>
     * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
     * 输出：[[12,21,16],[27,45,33],[24,39,28]]
     */

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(matrixBlockSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 1)));
    }
    //         0  0  0  0
    // 1 2 3   0  1  3  6     12 21 16
    // 4 5 6   0  5  12 21    27 45 33
    // 7 8 9   0  12 27 45    24 39 28

    public static int[][] matrixBlockSum(int[][] mat, int k) {
        return null;
    }


}
