package com.zzzj.hot;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-04-06 14:25
 */
public class Leet73 {

    public static void main(String[] args) {
//        int[][] ints = LeetUtils.convertInts("[[0,1,2,0],[3,4,5,2],[1,3,1,5]]");

        int[][] ints = {{1}, {0}};

        setZeroes(ints);

        System.out.println(Arrays.deepToString(ints));
    }

    public static void setZeroes(int[][] matrix) {

        boolean rowZero = false;
        boolean colZero = false;

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                rowZero = true;
                break;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colZero = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    // 把row和col置为0
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (rowZero) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }

        if (colZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

    }

}
