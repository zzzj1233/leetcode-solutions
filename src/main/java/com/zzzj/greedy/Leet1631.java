package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.LinkedList;

public class Leet1631 {

    public static void main(String[] args) {

        System.out.println(minimumEffortPath(LeetUtils.convertInts("[[1,2,2],[3,8,2],[5,3,5]]")));

        System.out.println(minimumEffortPath(LeetUtils.convertInts("[[1,2,3],[3,8,4],[5,3,5]]")));

        System.out.println(minimumEffortPath(LeetUtils.convertInts("[[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]")));

    }

    static int[][] DIRS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public static int minimumEffortPath(int[][] heights) {

        int M = heights.length;

        int N = heights[0].length;

        int[][] distance = new int[M][N];

        for (int i = 0; i < M; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        distance[0][0] = 0;

        LinkedList<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {

            int[] rm = queue.remove();

            int row = rm[0];

            int col = rm[1];

            for (int[] dir : DIRS) {
                int r = dir[0] + row;
                int c = dir[1] + col;

                if (r >= 0 && r < M && c >= 0 && c < N) {

                    int diff = Math.abs(heights[row][col] - heights[r][c]);

                    int dis = Math.max(distance[row][col], diff);

                    if (dis < distance[r][c]) {
                        distance[r][c] = dis;
                        queue.add(new int[]{r, c});
                    }

                }

            }
        }

        return distance[M - 1][N - 1];
    }

}
