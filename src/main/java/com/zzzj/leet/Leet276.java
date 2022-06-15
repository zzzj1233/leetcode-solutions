package com.zzzj.leet;

import com.zzzj.util.Unresolved;

/**
 * @author Zzzj
 * @create 2022-05-09 11:11
 */
@Unresolved
public class Leet276 {

    public static void main(String[] args) {
        System.out.println(numWays(3, 4));
    }

    public static int numWays(int n, int k) {
        return dp(n, k);
    }

    public static int dp(int n, int k) {
        if (n == 1) {
            return k;
        }

        if (n == 2) {
            return k * k;
        }

        int[][] dp = new int[n + 1][2];
        // 0 = 不同
        // 1 = 相同
        dp[n][0] = 1;
        dp[n][1] = 1;

        for (int i = n - 1; i >= 2; i--) {
            dp[i][1] = (k - 1) * dp[i + 1][0];
            dp[i][0] = dp[i + 1][1] + dp[i][1];
        }

        return k * dp[2][1] + (k * (k - 1) * dp[2][0]);
    }

    public static int dfs(int n, int k, int i, boolean same) {
        if (i == n) {
            return 1;
        }

        // 上两个位置相同
        if (same) {
            // k - 1种选择
            return (k - 1) * dfs(n, k, i + 1, false);
        } else {
            // 1种选择使相同
            int ways1 = dfs(n, k, i + 1, true);
            // k - 1 种选择使不同
            int ways2 = (k - 1) * dfs(n, k, i + 1, false);
            return ways1 + ways2;
        }
    }

}
