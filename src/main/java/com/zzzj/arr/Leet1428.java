package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-11-25 16:57
 */
public class Leet1428 {

    public static void main(String[] args) {
        System.out.println(leftMostColumnWithOne(new BinaryMatrixImpl(
                LeetUtils.convertInts("[[0,0],[0,1]]")
        )));
    }

    private static class BinaryMatrixImpl implements BinaryMatrix {

        private final int[][] matrix;

        private final List<Integer> dimensions;

        private BinaryMatrixImpl(int[][] matrix) {
            this.matrix = matrix;
            this.dimensions = Arrays.asList(matrix.length, matrix[0].length);
        }

        @Override
        public int get(int row, int col) {
            return matrix[row][col];
        }

        @Override
        public List<Integer> dimensions() {
            return dimensions;
        }
    }

    interface BinaryMatrix {
        int get(int row, int col);

        List<Integer> dimensions();
    }

    public static int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {

        List<Integer> dimensions = binaryMatrix.dimensions();

        int row = dimensions.get(0);
        int col = dimensions.get(1);

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < row; i++) {

            int left = 0;
            int right = col - 1;

            int index = -1;

            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                int value = binaryMatrix.get(i, mid);
                if (value == 1) {
                    index = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            if (index != -1) {
                ans = Math.min(ans, index);
            }

        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
