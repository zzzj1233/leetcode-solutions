package com.zzzj.backtracking;

/**
 * @author zzzj
 * @create 2022-03-09 10:49
 */
public class Leet1820 {


    public static void main(String[] args) {
        System.out.println(maximumInvitations(new int[][]{{1, 1, 1}, {1, 0, 1}, {0, 0, 1}}));
        System.out.println(maximumInvitations(new int[][]{
                {1, 0, 1, 0},
                {1, 0, 0, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0}
        }));
    }

    public static int ans;

    // 返回可能的最多邀请的个数
    public static int maximumInvitations(int[][] grid) {
        ans = 0;

        dfs(grid, new boolean[grid[0].length], 0, 0);

        return ans;
    }

    public static void dfs(int[][] grid, boolean[] visited, int cur, int max) {
        if (cur >= grid.length) {
            ans = Math.max(ans, max);
            return;
        }

        int[] candidate = grid[cur];

        for (int i = 0; i < candidate.length; i++) {
            dfs(grid, visited, cur + 1, max);
            if (candidate[i] != 1) {
                continue;
            }
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            dfs(grid, visited, cur + 1, max + 1);
            visited[i] = false;
        }

    }

}
