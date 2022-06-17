package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-17 15:08
 */
public class Leet1155 {

    public static void main(String[] args) {
        System.out.println(numRollsToTarget(1, 6, 3));
        System.out.println(numRollsToTarget(2, 6, 7));
        System.out.println(numRollsToTarget(3, 8, 21));
        System.out.println(numRollsToTarget(30, 30, 500));
    }

    public static int numRollsToTarget(int n, int k, int target) {
        // return dfs(n, k, target, 0, n);
        return dp(n, k, target);
    }

    public static int dp(int n, int k, int target) {
        if (n > target) {
            return 0;
        }

        int[][] dp = new int[n + 1][target + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][target] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int j = target - 1; j >= 0; j--) {

                for (int p = k - 1; p >= 0; p--) {
                    if (j + p > target) {
                        continue;
                    }
                    dp[i][j] += j + p == target ? 1 : dp[i + 1][j + p] % 1000000007;
                    dp[i][j] %= 1000000007;
                }

            }
        }

        return dp[0][n];
    }

    public static int dfs(int n, int k, int target, int i, int sum) {
        if (sum > target) {
            return 0;
        }
        if (sum == target) {
            return 1;
        }
        if (i >= n) {
            return 0;
        }
        int result = 0;

        for (int j = 0; j < k; j++) {
            result += dfs(n, k, target, i + 1, sum + j) % 1000000007;
        }

        return result;
    }

}
