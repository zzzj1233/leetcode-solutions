package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-05-19 14:29
 */
public class Leet417 {

    static List<List<Integer>> ans;

    static int[][] DIRS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int M = heights.length;
        int N = heights[0].length;

        ans = new ArrayList<>();

        boolean[][] visited = new boolean[M][N];

        dfs(0, N - 1, visited, heights);
        dfs(M - 1, 0, visited, heights);

        return ans;
    }

    public static void dfs(int i, int j, boolean[][] visited, int[][] heights) {
        if (visited[i][j]) {
            return;
        }
        visited[i][j] = true;

        int height = heights[i][j];

        ans.add(Arrays.asList(i, j));

        for (int[] dir : DIRS) {
            int r = dir[0];
            int c = dir[1];
            if (i + r >= 0 && i + r < visited.length && heights[i + r][j] >= height) {
                dfs(i + r, j, visited, heights);
            }
            if (j + c >= 0 && j + c < visited[i].length && heights[i][j + c] >= height) {
                dfs(i, j + c, visited, heights);
            }
        }
    }

}
