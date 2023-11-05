package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-10-25 10:41
 */
public class Leet1293 {

    public static void main(String[] args) {

        System.out.println(shortestPath(LeetUtils.convertInts("[[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]]"), 1));

        System.out.println(shortestPath(LeetUtils.convertInts("[[0,1,1],[1,1,1],[1,0,0]]"), 2));
        System.out.println(shortestPath(LeetUtils.convertInts("[[0,1,1],[1,1,1],[1,0,0]]"), 3));
        System.out.println(shortestPath(LeetUtils.convertInts("[[0,1,1],[1,1,1],[1,0,0]]"), 4));

    }

    public static int shortestPath(int[][] grid, int k) {

        int M = grid.length;

        int N = grid[0].length;

        LinkedList<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0, k, 0});

        boolean[][][] visited = new boolean[M][N][k + 1];

        while (!queue.isEmpty()) {

            int[] first = queue.removeFirst();

            int x = first[0];

            int y = first[1];

            int z = first[2];

            int step = first[3];

            if (x == M - 1 && y == N - 1)
                return step;

            visited[x][y][z] = true;

            for (int[] dir : DIRS) {
                int r = dir[0] + x;
                int c = dir[1] + y;

                if (r >= 0 && r < grid.length && c >= 0 && c < grid[r].length) {

                    if (grid[r][c] == 1) {
                        if (z > 0 && !visited[r][c][z - 1]) {
                            queue.add(new int[]{r, c, z - 1, step + 1});
                            visited[r][c][z - 1] = true;
                        }
                    } else {
                        if (!visited[r][c][z]) {
                            queue.add(new int[]{r, c, z, step + 1});
                            visited[r][c][z] = true;
                        }
                    }
                }

            }

        }

        return -1;
    }

    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

}
