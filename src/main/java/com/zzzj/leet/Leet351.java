package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-11 17:54
 */
public class Leet351 {

    public static void main(String[] args) {
        System.out.println(numberOfPatterns(1, 2));
    }

    public static int ans;

    public static final int[][] DIRS = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
            {1, 1},
            {-1, -1},
            {1, -1},
            {-1, 1}
    };

    public static int numberOfPatterns(int m, int n) {
        ans = 0;

        // 3 * 3的矩阵
        boolean[][] visited = new boolean[3][3];

        for (int i = m; i <= n; i++) {
            dfs(i, 0, 0, visited);
        }

        return ans;
    }

    public static void dfs(int n, int i, int j, boolean[][] visited) {
        for (int[] dir : DIRS) {
            int r = dir[0];
            int c = dir[1];

            visited[i][j] = true;

            if (i + r >= 0 && i + r < 3 && !visited[i + r][j]) {

            }

            if (j + c >= 0 && j + c < 3 && !visited[i][j + c]) {

            }

            visited[i][j] = false;
        }

    }

}
