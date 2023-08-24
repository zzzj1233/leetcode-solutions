package com.zzzj.contest.week357;

import com.zzzj.leet.LeetUtils;

import java.util.LinkedList;
import java.util.List;

public class Leet6951 {

    public static void main(String[] args) {

        System.out.println(maximumSafenessFactor(LeetUtils.convertLists("[[0,1,1],[0,0,0],[0,0,0]]")));

        System.out.println(maximumSafenessFactor(LeetUtils.convertLists("[[1,0,0],[0,0,0],[0,0,1]]")));

        System.out.println(maximumSafenessFactor(LeetUtils.convertLists("[[0,0,1],[0,0,0],[0,0,0]]")));

        System.out.println(maximumSafenessFactor(LeetUtils.convertLists("[[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]")));

        System.out.println(maximumSafenessFactor(LeetUtils.convertLists("[[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1],[0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0],[0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0],[0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,0,0],[0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0],[1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0],[1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0],[0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0],[1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0],[0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0]]")));

    }


    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    public static int maximumSafenessFactor(List<List<Integer>> grid) {

        int M = grid.size();

        int N = grid.get(0).size();

        if (grid.get(0).get(0) == 1 || grid.get(M - 1).get(N - 1) == 1)
            return 0;

        int[][] distance = new int[M][N];

        LinkedList<int[]> queue = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                distance[i][j] = -1;
                if (grid.get(i).get(j) == 1) {
                    queue.add(new int[]{i, j});
                    distance[i][j] = 0;
                }

            }
        }


        while (!queue.isEmpty()) {

            int[] rm = queue.removeFirst();

            int row = rm[0];

            int col = rm[1];

            for (int[] dir : DIRS) {

                int r = dir[0] + row;

                int c = dir[1] + col;

                if (r >= 0 && c >= 0 && r < M && c < N && distance[r][c] == -1) {
                    queue.addLast(new int[]{r, c});
                    distance[r][c] = Math.abs(r - row) + Math.abs(c - col) + distance[row][col];
                }

            }

        }

        int left = 0;

        int right = M + N;

        int ans = -1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (check(distance, mid, M, N)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    public static boolean check(int[][] distance, int expect, int M, int N) {

        if (distance[0][0] < expect) return false;

        LinkedList<int[]> queue = new LinkedList<>();

        boolean[][] visited = new boolean[M][N];

        queue.addLast(new int[]{0, 0});

        visited[0][0] = true;

        while (!queue.isEmpty()) {

            int[] rm = queue.removeFirst();

            if (rm[0] == M - 1 && rm[1] == N - 1)
                return true;

            for (int[] dir : DIRS) {

                int r = dir[0] + rm[0];

                int c = dir[1] + rm[1];

                if (r >= 0 && c >= 0 && r < M && c < N && !visited[r][c] && distance[r][c] >= expect) {
                    visited[r][c] = true;
                    queue.addLast(new int[]{r, c});
                }

            }
        }

        return false;
    }


}
