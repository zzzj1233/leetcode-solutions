package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-04 18:26
 */
public class Leet1420 {

    static final int MOD = 1000000007;

    public static void main(String[] args) {

        // 12
        System.out.println(numOfArrays(3, 3, 2));

//        System.out.println(right(3, 3, 2));
//
//        System.exit(0);

        // 6
        System.out.println(numOfArrays(2, 3, 1));

        // 0
        System.out.println(numOfArrays(5, 2, 3));

        System.out.println(numOfArrays(5, 5, 3));

//        System.out.println(numOfArrays(37, 17, 7));

    }

    public static int dp(int n, int m, int k) {

        int[][][] dp = new int[m + 1][n + 1][k + 1];

        for (int num = 1; num <= m; num++) {
            dp[num][1][1] = 1;
        }

//        for (int i = k; i <= m; i++) {
//            dp[i][n][k] = 1;
//        }

        for (int index = 1; index <= n; index++) {

            for (int num = 1; num <= m; num++) {

                for (int rank = 1; rank <= k; rank++) {

                    // 大于num的
                    for (int great = m; great >= num; great--) {

                        for (int greatIndex = 1; greatIndex < index; greatIndex++) {
                            dp[num][index][rank] += dp[great][greatIndex][rank];
                        }

                    }

                    // 小于num的
                    for (int less = 1; less < num; less++) {

                        for (int lessIndex = 1; lessIndex < index; lessIndex++) {
                            dp[num][index][rank] += dp[less][lessIndex][rank - 1];
                        }

                    }


                }

            }

        }

        int ans = 0;

        for (int i = 1; i <= m; i++) {
            ans += dp[i][n][k];
        }

        return ans;
    }

    public static int numOfArrays(int n, int m, int k) {
        int ans = 0;

        int[][][] memo = new int[k + 1][m + 1][n + 1];

        for (int i = 0; i < memo.length; i++) for (int j = 0; j < memo[i].length; j++) Arrays.fill(memo[i][j], -1);

        for (int i = 1; i <= m; i++) {
            ans += dfs(n, m, k - 1, i, 1, memo) % MOD;
            ans %= MOD;
        }

        return ans;
    }

    public static int dfs(int n, int m, int k, int prev, int index, int[][][] memo) {
        if (index >= n)
            return k == 0 ? 1 : 0;

        if (memo[k][prev][index] != -1)
            return memo[k][prev][index];

        int ret = 0;

        for (int i = 1; i <= prev; i++) {
            ret += dfs(n, m, k, prev, index + 1, memo) % MOD;
            ret %= MOD;
        }

        if (k >= 1)
            for (int i = prev + 1; i <= m; i++) {
                ret += dfs(n, m, k - 1, i, index + 1, memo) % MOD;
                ret %= MOD;
            }


        memo[k][prev][index] = ret;

        return ret;
    }

}
