package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.List;

/**
 * @author zzzj
 * @create 2022-04-08 15:11
 */
public class Leet120 {

    public static void main(String[] args) {
        System.out.println(minimumTotal(LeetUtils.convertLists("[[2],[3,4],[6,5,7],[4,1,8,3]]")));
        System.out.println(minimumTotal(LeetUtils.convertLists("[[-10]]")));
        System.out.println(dp(LeetUtils.convertLists("[[-1],[-2,-3]]")));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        // [[-1],[-2,-3]]
        return dp(triangle);
    }

    public static int dp(List<List<Integer>> triangle) {
        int N = triangle.size();

        int M = triangle.get(N - 1).size();

        int[][] dp = new int[N][M];

        for (int i = 0; i < M; i++) {
            dp[N - 1][i] = triangle.get(N - 1).get(i);
        }

        for (int i = N - 2; i >= 0; i--) {
            List<Integer> possible = triangle.get(i);
            for (int j = possible.size() - 1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + possible.get(j);
            }
        }

        return dp[0][0];
    }

    public static int dfs(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) {
            return 0;
        }

        List<Integer> possible = triangle.get(i);

        // allow
        int min = dfs(triangle, i + 1, j) + possible.get(j);

        if (j + 1 < possible.size()) {
            min = Math.min(min, dfs(triangle, i + 1, j + 1) + possible.get(j + 1));
        }

        return min;
    }

}
