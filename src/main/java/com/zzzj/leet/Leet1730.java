package com.zzzj.leet;

import com.zzzj.leet.LeetUtils;

import java.util.LinkedList;
import java.util.function.BinaryOperator;

public class Leet1730 {

    public static void main(String[] args) {
        System.out.println(getFood(LeetUtils.convertChars("[[\"X\",\"X\",\"X\",\"X\",\"X\",\"X\"],[\"X\",\"*\",\"O\",\"O\",\"O\",\"X\"],[\"X\",\"O\",\"O\",\"#\",\"O\",\"X\"],[\"X\",\"X\",\"X\",\"X\",\"X\",\"X\"]]")));
    }

    static int[][] DIRS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public static int getFood(char[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        int x = -1;
        int y = -1;

        for (int i = 0; i < M && x == -1; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == '*') {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        LinkedList<int[]> queue = new LinkedList<>();

        queue.add(new int[]{x, y});

        boolean[][] visited = new boolean[M][N];
        visited[x][y] = true;

        int ans = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int k = 0; k < size; k++) {
                int[] first = queue.removeFirst();

                int i = first[0];
                int j = first[1];

                if (grid[i][j] == '#') {
                    return ans;
                }

                for (int[] dir : DIRS) {
                    int r = dir[0];
                    int c = dir[1];

                    if (i + r >= 0 && i + r < visited.length && j + c >= 0 && j + c < N && !visited[i + r][j + c] && grid[i + r][j + c] != 'X') {
                        visited[i + r][j + c] = true;
                        queue.add(new int[]{i + r, j + c});
                    }

                }
            }

            ans++;
        }


        return -1;
    }

}
