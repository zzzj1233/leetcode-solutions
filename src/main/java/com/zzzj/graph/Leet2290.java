package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-09-22 14:08
 */
public class Leet2290 {

    public static void main(String[] args) {

        System.out.println(minimumObstacles(LeetUtils.convertInts("[[0,1,1],[1,1,0],[1,1,0]]")));

        System.out.println(minimumObstacles(LeetUtils.convertInts("[[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]")));

    }

    static int[][] DIRS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public static int minimumObstacles(int[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        LinkedList<int[]> queue = new LinkedList<>();

        queue.add(new int[2]);

        boolean[][] visited = new boolean[M][N];

        visited[0][0] = true;

        int ans = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] rm = queue.removeFirst();

                int row = rm[0];

                int col = rm[1];

                if (row == M - 1 && col == N - 1) {
                    return ans;
                }

                for (int[] dir : DIRS) {
                    int r = dir[0] + row;
                    int c = dir[1] + col;

                    if (r >= 0 && r < M && c >= 0 && c < N && !visited[r][c]) {

                        if (grid[r][c] == 0) {
                            queue.addFirst(new int[]{r, c});
                            size++;
                        } else {
                            queue.addLast(new int[]{r, c});
                        }

                        visited[r][c] = true;
                    }

                }

            }

            ans++;
        }

        return -1;
    }

}
