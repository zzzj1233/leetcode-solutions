package com.zzzj.contest.week422;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-11-03 10:35
 */
public class Q2 {

    public static void main(String[] args) {

        System.out.println(minTimeToReach(LeetUtils.convertInts("[[27,85],[22,53]]")));

        System.out.println(minTimeToReach(LeetUtils.convertInts("[[0,0,0],[0,0,0]]")));
//
        System.out.println(minTimeToReach(LeetUtils.convertInts("[[0,1],[1,2]]")));
//
        System.out.println(minTimeToReach(LeetUtils.convertInts("[[0,4],[4,4]]")));

        System.out.println(minTimeToReach(LeetUtils.convertInts("[[56,93],[3,38]]")));

    }


    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    public static int minTimeToReach(int[][] moveTime) {

        int M = moveTime.length;

        int N = moveTime[0].length;

        int ans = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(M * N, Comparator.comparingInt(o -> o[3]));

        int[][] dis = new int[M][N];

        for (int i = 0; i < M; i++)
            Arrays.fill(dis[i], Integer.MAX_VALUE);

        queue.add(new int[]{0, 0, 0, 1});

        boolean[][] vis = new boolean[M][N];

        while (!queue.isEmpty()) {

            int[] node = queue.remove();

            int x = node[0];
            int y = node[1];
            int time = node[2];
            int cost = node[3];

            if (time > dis[x][y])
                continue;

            vis[x][y] = true;

            dis[x][y] = time;

            for (int[] dir : DIRS) {

                int row = dir[0] + x;

                int col = dir[1] + y;

                if (row >= 0 && col >= 0 && row < M && col < N) {

                    int t = Math.max(time, moveTime[row][col]) + cost;

                    if (t < dis[row][col] && !vis[row][col]) {
                        dis[row][col] = t;
                        queue.add(new int[]{row, col, t, 1});
                    }

                }

            }
        }

        return dis[M - 1][N - 1];
    }

}
