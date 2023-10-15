package com.zzzj.contest.week362;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public class Q3 {

    public static void main(String[] args) {

        System.out.println(minimumMoves(LeetUtils.convertInts("[[3,2,0],[0,1,0],[0,3,0]]")));

        System.out.println(minimumMoves(LeetUtils.convertInts("[[1,3,0],[1,0,0],[1,0,3]]")));

        System.out.println(minimumMoves(LeetUtils.convertInts("[[1,1,0],[1,1,1],[1,2,1]]")));

    }

    public static int minimumMoves(int[][] grid) {

        return dfs(grid, 0, 0);
//        int M = grid.length;
//
//        int N = grid[0].length;
//
//        int ans = 0;
//
//        for (int i = 0; i < M; i++) {
//
//            for (int j = 0; j < N; j++) {
//
//                if (grid[i][j] == 0) {
//
//                    ans += bfs(grid, i, j);
//
//                }
//
//
//            }
//
//        }
//
//        return ans;
    }

    static int[][] DIRS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public static int dfs(int[][] grid, int x, int y) {
        if (y >= grid.length) {
            x++;
            y = 0;
        }

        if (x >= grid.length)
            return 0;

        if (grid[x][y] > 0)
            return dfs(grid, x, y + 1);

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] > 1) {
                    grid[i][j]--;
                    result = Math.min(
                            result,
                            dfs(grid, x, y + 1) + Math.abs(j - y) + Math.abs(i - x)
                    );
                    grid[i][j]++;
                }

            }

        }

        return result;
    }

    public static int bfs(int[][] grid, int startX, int startY) {

        LinkedList<int[]> queue = new LinkedList<>();

        queue.add(new int[]{startX, startY});

        int step = 0;

        int M = grid.length;

        int N = grid[0].length;

        boolean[][] visited = new boolean[M][N];

        List<int[]> candidates = new ArrayList<>();

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] remove = queue.removeFirst();

                int x = remove[0];
                int y = remove[1];

                visited[x][y] = true;

                if (grid[x][y] > 1) {
                    candidates.add(new int[]{x, y});
                }

                for (int[] dir : DIRS) {
                    int r = x + dir[0];
                    int c = y + dir[1];

                    if (r >= 0 && c >= 0 && r < M && c < N && !visited[r][c]) {
                        queue.addLast(new int[]{r, c});
                    }

                }

            }

            if (!candidates.isEmpty()) {
                grid[startX][startY] = 1;
                return choose(candidates, grid, step);
            }

            step++;
        }

        return -1;
    }

    private static int choose(List<int[]> candidates, int[][] grid, int step) {
        int x, y;
        if (candidates.size() == 1) {
            x = candidates.get(0)[0];
            y = candidates.get(0)[1];
        } else {
            int[] ints = candidates.stream().max(Comparator.comparingInt(o -> o[0])).get();
            x = ints[0];
            y = ints[1];
        }

        grid[x][y]--;

        return step;
    }
}
