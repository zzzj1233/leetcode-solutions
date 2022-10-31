package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-04-22 16:20
 */
public class Leet251 {

    static class Vector2D {

        private final int[][] vec;
        private final int N;

        private int row;
        private int col;


        public Vector2D(int[][] vec) {
            this.vec = vec;
            N = vec.length;
            while (row < N && col >= vec[row].length) {
                row++;
                col = 0;
            }
        }

        public int next() {
            while (row < N && col >= vec[row].length) {
                row++;
                col = 0;
            }

            int result = vec[row][col];
            col++;
            while (row < N && col >= vec[row].length) {
                row++;
                col = 0;
            }
            return result;
        }

        public boolean hasNext() {
            return row < N;
        }

    }

}
