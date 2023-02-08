package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-01-23 20:34
 */
public class Leet1765 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(highestPeak(LeetUtils.convertInts("[[0,0,1],[1,0,0],[0,0,0]]"))));
    }

    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    public static int[][] highestPeak(int[][] isWater) {
        int M = isWater.length;

        int N = isWater[0].length;

        int[][] ans = new int[M][N];

        LinkedList<int[]> queue = new LinkedList<>();

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                if (isWater[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }

            }

        }

        boolean[][] visited = new boolean[M][N];

        while (!queue.isEmpty()) {
            int[] remove = queue.removeFirst();
            int x = remove[0];
            int y = remove[1];

            visited[x][y] = true;

            int min = Integer.MAX_VALUE;

            for (int[] dir : DIRS) {
                int row = x + dir[0];
                int col = y + dir[1];

                if (row >= 0 && col >= 0 && row < M && col < N) {
                    min = Math.min(min, ans[row][col]);
                    if (!visited[row][col]) {
                        queue.add(new int[]{row, col});
                    }
                }
            }

            if (isWater[x][y] == 1) {
                ans[x][y] = 0;
            } else {
                ans[x][y] = min + 1;
            }

        }

        return ans;
    }

}
