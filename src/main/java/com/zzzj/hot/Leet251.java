package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-04-22 16:20
 */
public class Leet251 {

    static class Vector2D {

        private final int[][] vec;

        private int row;
        private int col;

        private boolean hasNext;

        // [[[[1,2],[3],[4,5,6]]],[],[],[],[],[],[],[],[],[],[],[],[],[]]
        public Vector2D(int[][] vec) {
            this.vec = vec;
            // initHasNext
        }

        public void initHasNext() {
            if (col >= vec[row].length) {
                col = 0;
                row++;
            }
            while (row < vec.length && col >= vec[row].length) {
                row++;
            }
            hasNext = row < vec.length;
        }

        public int next() {
            int result = vec[row][col];
            col++;
            if (col >= vec[row].length) {
                col = 0;
                row++;
                while (row < vec.length && col >= vec[row].length) {
                    row++;
                }
                hasNext = row < vec.length;
            }
            return result;
        }

        public boolean hasNext() {
            return hasNext;
        }
    }

}
