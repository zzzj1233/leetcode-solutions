package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-15 16:16
 */
public class Leet2380 {

    public static void main(String[] args) {
        System.out.println(movingCount(3, 10, 3));
    }

    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    public static int ans;

    public static int movingCount(int m, int n, int k) {
        ans = 0;
        // 从00,到m-1,n-1
        // 格子之和不能大于k
        // 可以上下左右移动
        dfs(0, 0, k, new boolean[m][n], m, n);
        return ans;
    }

    public static void dfs(int i, int j, int k, boolean[][] visited, int m, int n) {
        visited[i][j] = true;
        if ((i / 10 + i % 10 + j / 10 + j % 10) > k) {
            return;
        }
        ans++;
        for (int[] dir : DIRS) {
            int row = dir[0];
            int col = dir[1];
            if (i + row >= 0 && i + row < m && j + col >= 0 && j + col < n && !visited[i + row][j + col]) {
                dfs(i + row, j + col, k, visited, m, n);
            }
        }
    }

}
