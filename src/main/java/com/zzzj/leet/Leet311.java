package com.zzzj.leet;

public class Leet311 {

    public static int[][] multiply(int[][] mat1, int[][] mat2) {

        int[][] result = new int[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                result[i][j] = 0;
                for (int k1 = 0; k1 < mat1[0].length; k1++) { // 1的列数

                    result[i][j] += mat1[i][k1] * mat2[k1][j];

                }
            }
        }
        return result;
    }

}
