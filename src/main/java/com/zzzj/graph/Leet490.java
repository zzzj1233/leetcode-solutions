package com.zzzj.graph;

import java.util.LinkedList;

public class Leet490 {


    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };


    public static boolean hasPath(int[][] maze, int[] start, int[] destination) {

        int M = maze.length;

        int N = maze[0].length;

        boolean[][] visited = new boolean[M][N];

        LinkedList<int[]> queue = new LinkedList<>();

        queue.addLast(start);

        while (!queue.isEmpty()) {
            int[] remove = queue.removeFirst();

            int x = remove[0];

            int y = remove[1];

            if (x == destination[0] && y == destination[1]) {
                return true;
            }

            visited[x][y] = true;

            for (int[] dir : DIRS) {
                int row = x + dir[0];
                int col = y + dir[1];

                while (row >= 0 && col >= 0 && row < M && col < N && maze[row][col] == 0) {
                    row += dir[0];
                    col += dir[1];
                }

                row -= dir[0];
                col -= dir[1];

                if (!visited[row][col]) {
                    queue.addLast(new int[]{row, col});
                }
            }

        }

        return false;
    }
}
