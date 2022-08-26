package com.zzzj.design;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-24 19:24
 */
public class Leet1476 {

    private static class SubrectangleQueries {

        private final int[][] rectangle;

        private List<int[]> version = new ArrayList<>();

        public SubrectangleQueries(int[][] rectangle) {
            this.rectangle = rectangle;
        }

        public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
            version.add(new int[]{row1, row2, col1, col2, newValue});
        }

        public int getValue(int row, int col) {
            int N = version.size();
            for (int i = N - 1; i >= 0; i--) {
                int[] item = version.get(i);
                if (row >= item[0] && row <= item[1] && col >= item[2] && col <= item[3]) {
                    return item[4];
                }
            }
            return rectangle[row][col];
        }
    }

}
