package com.zzzj.leet;

public class Leet1329 {

    public static int[][] diagonalSort(int[][] mat) {

        int M = mat.length;
        int N = mat[0].length;

        int[][] ans = new int[M][N];

        // 对角线
        // 2, 0
        // 1, 0 / 2, 1
        // 0, 0 / 1, 1 / 2, 2
        // 0, 1 / 1, 2 / 2, 3
        // 0, 2 / 1, 3
        // 0, 3

        return ans;
    }

}
