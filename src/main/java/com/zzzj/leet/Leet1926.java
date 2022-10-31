package com.zzzj.leet;

import java.util.LinkedList;

public class Leet1926 {

    public static void main(String[] args) {
        System.out.println(nearestExit(LeetUtils.convertChars("[[\"+\",\".\",\"+\",\"+\",\"+\",\"+\",\"+\"]\n" +
                "[\"+\",\".\",\"+\",\".\",\".\",\".\",\"+\"]\n" +
                "[\"+\",\".\",\"+\",\".\",\"+\",\".\",\"+\"]\n" +
                "[\"+\",\".\",\".\",\".\",\"+\",\".\",\"+\"]\n" +
                "[\"+\",\"+\",\"+\",\"+\",\"+\",\".\",\"+\"]]"), new int[]{0, 1}));
    }

    public static int nearestExit(char[][] maze, int[] entrance) {

        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(entrance);

        int M = maze.length;
        int N = maze[0].length;

        boolean[][] visited = new boolean[M][N];


        int ans = 0;

        final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] first = queue.removeFirst();
                int x = first[0];
                int y = first[1];

                if ((x == 0 || y == 0 || x == M - 1 || y == N - 1) && ans > 0) {
                    return ans;
                }

                visited[x][y] = true;

                for (int[] dirs : dir) {
                    int i1 = x + dirs[0];
                    int j1 = y + dirs[1];
                    if (i1 >= 0 && i1 < M && j1 >= 0 && j1 < N && maze[i1][j1] == '.' && !visited[i1][j1]) {
                        visited[i1][j1] = true;
                        queue.addLast(new int[]{i1, j1});
                    }
                }
            }
            ans++;
        }

        return ans;
    }

}
