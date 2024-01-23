package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2024-01-22 15:02
 */
public class Leet317 {

    public static void main(String[] args) {

        System.out.println(shortestDistance(LeetUtils.convertInts("[[1,2,0]]")));

        System.out.println(shortestDistance(LeetUtils.convertInts("[[1,1,1,1,1,0],[0,0,0,0,0,1],[0,1,1,0,0,1],[1,0,0,1,0,1],[1,0,1,0,0,1],[1,0,0,0,0,1],[0,1,1,1,1,0]]")));

        System.out.println(shortestDistance(LeetUtils.convertInts("[[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]")));

    }

    public static int shortestDistance(int[][] grid) {

        int totalCnt = 0;

        int N = grid.length;

        int M = grid[0].length;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (grid[i][j] == 1)
                    totalCnt++;

        LinkedList<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (grid[i][j] == 0)
                    queue.add(new int[]{i, j, 0});

        int ans = bfs(queue, totalCnt, grid);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public static int bfs(
            LinkedList<int[]> queue,
            int totalCnt,
            int[][] grid
    ) {
        int vc = 0;

        int cost = 0;

        int N = grid.length;

        int M = grid[0].length;

        boolean[][] vis = new boolean[N][M];

        while (!queue.isEmpty() && vc < totalCnt) {

            int[] node = queue.removeFirst();

            int x = node[0];

            int y = node[1];

            int step = node[2];

            vis[x][y] = true;

            for (int[] dir : DIRS) {
                int row = x + dir[0];
                int col = y + dir[1];

                if (row >= 0 && col >= 0 && row < N && col < M && !vis[row][col] && grid[row][col] != 2) {

                    vis[row][col] = true;

                    if (grid[row][col] == 1) {
                        vc++;
                        cost += step + 1;
                    } else {
                        queue.addLast(new int[]{row, col, step + 1});
                    }

                }

            }

        }

        return vc == totalCnt ? cost : Integer.MAX_VALUE;
    }

}
