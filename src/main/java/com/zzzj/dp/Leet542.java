package com.zzzj.dp;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-08-23 21:08
 */
public class Leet542 {

    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    public static int[][] updateMatrix(int[][] mat) {

        int N = mat.length;
        int M = mat[0].length;

        LinkedList<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {

                if (mat[i][j] == 0) {
                    queue.add(new int[]{i, j, 0});
                }

            }

        }

        boolean[][] visited = new boolean[N][M];

        return mat;
    }


}
