package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class Leet505 {

    public static void main(String[] args) {
        System.out.println(shortestDistance(LeetUtils.convertInts("[[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]]"), new int[]{0, 4}, new int[]{4, 4}));
    }

    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        // 撞到墙时才可以选择方向

        int M = maze.length;
        int N = maze[0].length;

        LinkedList<int[]> queue = new LinkedList<>();


        queue.add(new int[]{start[0], start[1], 0});

        boolean[][] visited = new boolean[M][N];

        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] remove = queue.removeFirst();

                int x = remove[0];
                int y = remove[1];
                int count = remove[2];

                if (x == destination[0] && y == destination[1]) {
                    return count;
                }

                // 往4个方向走,直到遇到墙,并且没有访问过
                outer:
                for (int[] dir : DIRS) {
                    int row = x + dir[0];
                    int col = y + dir[1];

                    int step = 0;

                    // 没走到墙
                    while (row >= 0 && col >= 0 && row < M && col < N && maze[row][col] == 0) {
                        row += dir[0];
                        col += dir[1];
                        step++;
                    }

                    if (!visited[row - dir[0]][col - dir[1]]) {
                        queue.addLast(new int[]{row - dir[0], col - dir[1], count + step});
                    }
                }
            }

        }

        return -1;
    }


    public static int right(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.add(new int[]{start[0], start[1], 0});
        int[][] dxy = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            if (pos[0] == destination[0] && pos[1] == destination[1]) {
                return pos[2];
            }
            visited[pos[0]][pos[1]] = true;
            for (int[] dir : dxy) {
                int x = pos[0] + dir[0];
                int y = pos[1] + dir[1];
                int cnt = 0;
                while (x >= 0 && y >= 0 && x < m && y < n && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    cnt++;
                }
                x -= dir[0];
                y -= dir[1];
                if (visited[x][y]) continue;
                queue.add(new int[]{x, y, pos[2] + cnt});
            }
        }
        return -1;
    }

}
