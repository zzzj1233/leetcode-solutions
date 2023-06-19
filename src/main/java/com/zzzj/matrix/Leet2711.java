package com.zzzj.matrix;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-06-19 11:29
 */
public class Leet2711 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(differenceOfDistinctValues(LeetUtils.convertInts("[[1,2,3],[3,1,5],[3,2,1]]"))));
    }

    public static int[][] differenceOfDistinctValues(int[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        int[][] ans = new int[M][N];

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                // topLeft

                boolean[] topLeftTab = new boolean[51];

                int topLeft = 0;

                int x = i - 1;
                int y = j - 1;

                while (x >= 0 && y >= 0) {
                    if (!topLeftTab[grid[x][y]]) {
                        topLeftTab[grid[x][y]] = true;
                        topLeft++;
                    }
                    x--;
                    y--;
                }

                // bottomRight
                x = i + 1;
                y = j + 1;

                boolean[] bottomRightTab = new boolean[51];

                int bottomRight = 0;

                while (x < M && y < N) {
                    if (!bottomRightTab[grid[x][y]]) {
                        bottomRightTab[grid[x][y]] = true;
                        bottomRight++;
                    }
                    x++;
                    y++;
                }

                ans[i][j] = Math.abs(topLeft - bottomRight);
            }

        }

        return ans;
    }

}
