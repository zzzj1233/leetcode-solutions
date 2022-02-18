package com.zzzj.prefxisum;

/**
 * Leet2234
 *
 * @author zzzj
 * @create 2021-12-10 15:23
 */
public class NumMatrix {

    private final int[] colSum;

    private final boolean[] visited;

    private final int[][] matrix;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.colSum = new int[matrix[0].length];
        this.visited = new boolean[matrix[0].length];
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {


        return -1;
    }

}
