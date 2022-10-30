package com.zzzj.tree;

import com.zzzj.leet.LeetUtils;

import java.util.Collections;

public class Leet308 {

    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(LeetUtils.convertInts("[[1]]"));
        System.out.println(numMatrix.sumRegion(0, 0, 0, 0));
        numMatrix.update(0, 0, -1);
        System.out.println(numMatrix.sumRegion(0, 0, 0, 0));
    }

    private static class NumMatrix {

        final int M;

        final int N;
        private final BinaryIndexedTree tree;

        static class BinaryIndexedTree {

            final int[] indexes;

            int[] nums;

            public BinaryIndexedTree(int length) {
                indexes = new int[length + 1];
                this.nums = new int[length];
            }

            public BinaryIndexedTree(int[][] matrix) {
                int M = matrix.length;
                int N = matrix[0].length;

                indexes = new int[M * N + 1];
                this.nums = new int[M * N];

                for (int i = 0; i < M; i++) {
                    for (int j = 0; j < M; j++) {
                        this.nums[i * N + j] = matrix[i][j];
                    }
                }
            }

            public BinaryIndexedTree(int[] nums) {
                indexes = new int[nums.length + 1];
                this.nums = nums;
                this.init(nums);
            }

            private void init(int[] nums) {
                for (int i = 0; i < nums.length; i++) {
                    this.update(i, nums[i]);
                }
            }

            public void update(int index, int newValue) {
                int updateValue = newValue - sum(index, index);
                index++;
                while (index < indexes.length) {
                    indexes[index] += updateValue;
                    index += lowbit(index);
                }
            }


            public int sum(int rangeX, int rangeY) {
                return getValue(rangeY) - getValue(rangeX - 1);
            }

            public int getValue(int index) {
                index++;
                int sum = 0;
                while (index > 0) {
                    sum += indexes[index];
                    index -= lowbit(index);
                }
                return sum;
            }

            private int lowbit(int x) {
                return x & (~x + 1);
            }

        }

        public NumMatrix(int[][] matrix) {
            this.M = matrix.length;
            this.N = matrix[0].length;

            tree = new BinaryIndexedTree(matrix);

            for (int i = 0; i < M; i++) {

                for (int j = 0; j < N; j++) {
                    tree.update(index(i, j), matrix[i][j]);
                }

            }
        }

        private int index(int row, int col) {
            return row * N + col;
        }

        public void update(int row, int col, int val) {
            int index = index(row, col);
            tree.update(index, val);
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {

            int sum = 0;

            while (row1 <= row2) {
                sum += tree.sum(index(row1, col1), index(row1, col2));
                row1++;
            }

            return sum;
        }
    }

}
