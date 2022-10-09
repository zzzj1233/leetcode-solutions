package com.zzzj.graph;

/**
 * @author Zzzj
 * @create 2022-09-25 20:39
 */
public class Leet1267 {

    public static int countServers(int[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        boolean[][] visited = new boolean[M][N];

        int[] row = new int[N];
        int[] col = new int[M];


        int ans = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1 && (row[i] > 1 || col[j] > 1)) {
                    ans++;
                }
            }
        }


        return ans;
    }


}
