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

        public NumMatrix(int[][] matrix) {

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return -1;
        }

    }

}
