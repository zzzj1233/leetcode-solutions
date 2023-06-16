package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-06-16 17:22
 */
public class Leet1992 {

    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(findFarmland(LeetUtils.convertInts("[[1,0,0],[0,1,1],[0,1,1]]"))));

        System.out.println(Arrays.deepToString(findFarmland(LeetUtils.convertInts("[[1,1],[1,1]]"))));

        System.out.println(Arrays.deepToString(findFarmland(LeetUtils.convertInts("[[0]]"))));
    }

    public static int[][] findFarmland(int[][] land) {

        List<int[]> ans = new ArrayList<>();

        int N = land.length;

        int M = land[0].length;

        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {

                    int[] path = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

                    dfs(land, visited, path, i, j);

                    ans.add(path);
                }

            }
        }

        return ans.toArray(new int[0][]);
    }

    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    static final int T = 0;

    static final int L = 1;

    static final int D = 2;

    static final int R = 3;

    public static void dfs(int[][] land, boolean[][] visited, int[] path, int x, int y) {

        if (x < 0 || y < 0 || x >= land.length || y >= land[x].length || visited[x][y] || land[x][y] == 0)
            return;


        visited[x][y] = true;

        path[T] = Math.min(path[T], x);
        path[D] = Math.max(path[D], x);
        path[L] = Math.min(path[L], y);
        path[R] = Math.max(path[R], y);

        for (int[] dir : DIRS) dfs(land, visited, path, x + dir[0], y + dir[1]);
    }

}
