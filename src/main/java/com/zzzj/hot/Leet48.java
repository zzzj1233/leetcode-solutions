package com.zzzj.hot;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-04-04 14:57
 */
public class Leet48 {

    public static void main(String[] args) {
        // int[][] matrix = LeetUtils.convertInts("[[1,2,3],[4,5,6],[7,8,9]]");
        int[][] matrix = LeetUtils.convertInts(" [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]");

        rotate(matrix);

        System.out.println(Arrays.deepToString(matrix));
    }

    public static void rotate(int[][] matrix) {

        // tr = top row
        // bc = bottom col
        int tr = 0;
        int tc = 0;
        int br = matrix.length - 1;
        int bc = matrix[0].length - 1;

        while (tr < br) {

            int groups = bc - tc;

            for (int i = 0; i < groups; i++) {
                int temp1 = matrix[tr][tc + i];
                int temp2 = matrix[tr + i][bc];
                int temp3 = matrix[br][bc - i];
                int temp4 = matrix[br - i][tc];

                matrix[tr + i][bc] = temp1;
                matrix[br][bc - i] = temp2;
                matrix[br - i][tc] = temp3;
                matrix[tr][tc + i] = temp4;
            }

            tr++;
            tc++;
            br--;
            bc--;
        }
    }

    public static void swap(int[][] matrix, int i, int j, int i2, int j2) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i2][j2];
        matrix[i2][j2] = temp;
    }

}
