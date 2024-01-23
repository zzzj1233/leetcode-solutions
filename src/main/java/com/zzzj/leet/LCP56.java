package com.zzzj.leet;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2024-01-02 14:20
 */
public class LCP56 {

    public static void main(String[] args) {

        System.out.println(conveyorBelt(
                new String[]{">>v", "v^<", "<><"},
                new int[]{0, 1},
                new int[]{2, 0}
        ));

    }

    static int[][] DIRS = {
            {0, 1, '>'},
            {1, 0, 'v'},
            {0, -1, '<'},
            {-1, 0, '^'}
    };

    public static int conveyorBelt(String[] matrix, int[] start, int[] end) {

        LinkedList<int[]> queue = new LinkedList<>();

        queue.add(new int[]{start[0], start[1], 0});

        int N = matrix.length;

        int M = matrix[0].length();

        int[][] visited = new int[N][M];

        visited[start[0]][start[1]] = 0;

        for (int i = 0; i < N; i++)
            Arrays.fill(visited[i], Integer.MAX_VALUE);

        int ans = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {

            int[] node = queue.removeFirst();

            if (node[0] == end[0] && node[1] == end[1]) {
                ans = Math.min(ans, node[2]);
                continue;
            }

            int change = node[2];

            for (int[] dir : DIRS) {
                int row = dir[0] + node[0];
                int col = dir[1] + node[1];
                int nextCnt = change + (dir[2] == matrix[node[0]].charAt(node[1]) ? 0 : 1);

                if (row >= 0 && col >= 0 && row < N && col < M && nextCnt < visited[row][col]) {
                    queue.add(new int[]{row, col, nextCnt});
                    visited[row][col] = nextCnt;
                }
            }

        }

        return ans;
    }

}
