package com.zzzj.leet;

import java.util.Arrays;
import java.util.LinkedList;

public class Leet2258 {

    public static void main(String[] args) {

        System.out.println(maximumMinutes(LeetUtils.convertInts("[[0,2,0,0,1],[0,2,0,2,2],[0,2,0,0,0],[0,0,2,2,0],[0,0,0,0,0]]")));

    }

    public static int maximumMinutes(int[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        int[][] mf = new int[M][N];

        LinkedList<int[]> queue = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                mf[i][j] = Integer.MAX_VALUE;
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                    mf[i][j] = 0;
                }
            }
        }

        bfs(grid, mf, queue);

        int l = -1;

        int r = 1000000001;

        int ans = -1;

        boolean[][] vis = new boolean[M][N];

        while (l + 1 < r) {

            int m = l + ((r - l) >> 1);

            queue.clear();

            for (int i = 0; i < M; i++)
                Arrays.fill(vis[i], false);

            if (check(grid, mf, vis, m, queue)) {
                ans = m;
                l = m;
            } else {
                r = m;
            }

        }

        return ans;
    }

    public static boolean check(
            int[][] grid,
            int[][] mf,
            boolean[][] vis,
            int exp,
            LinkedList<int[]> queue
    ) {

        int M = grid.length;

        int N = grid[0].length;

        queue.add(new int[]{0, 0, exp});

        while (!queue.isEmpty()) {

            int[] rm = queue.remove();

            int x = rm[0];

            int y = rm[1];

            int s = rm[2];

            for (int[] dir : DIRS) {

                int r = dir[0] + x;

                int c = dir[1] + y;

                if (r >= 0 && c >= 0 && r < M && c < N && grid[r][c] == 0 && !vis[r][c]) {

                    if (r == M - 1 && c == N - 1 && s + 1 <= mf[r][c])
                        return true;

                    if (s + 1 < mf[r][c]) {
                        queue.add(new int[]{r, c, s + 1});

                        vis[r][c] = true;
                    }
                }

            }
        }

        return vis[M - 1][N - 1];
    }

    static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public static void bfs(
            int[][] grid,
            int[][] mf,
            LinkedList<int[]> queue
    ) {

        int M = grid.length;

        int N = grid[0].length;

        boolean[][] vis = new boolean[M][N];

        while (!queue.isEmpty()) {

            int[] rm = queue.removeFirst();

            int x = rm[0];

            int y = rm[1];

            for (int[] dir : DIRS) {

                int r = dir[0] + x;

                int c = dir[1] + y;

                if (r >= 0 && c >= 0 && r < M && c < N && grid[r][c] == 0 && !vis[r][c]) {

                    queue.addLast(new int[]{r, c});

                    mf[r][c] = mf[x][y] + 1;

                    vis[r][c] = true;
                }

            }

        }

    }

}
