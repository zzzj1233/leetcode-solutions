package com.zzzj.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-01-05 14:42
 */
public class Leet2371 {

    public static int[][] minScore(int[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        int[][] items = new int[M * N][2];

        int index = 0;

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++, index++) {

                items[index] = new int[]{i, j};

            }

        }

        Arrays.sort(items, Comparator.comparingInt(o -> grid[o[0]][o[1]]));

        int[] row = new int[M];

        int[] col = new int[N];

        int[][] ans = new int[M][N];

        for (int[] item : items) {
            int x = item[0];
            int y = item[1];

            ans[x][y] = Math.max(row[x], col[y]) + 1;
            row[x] = ans[x][y];
            col[y] =  ans[x][y];
        }

        return ans;
    }

}
