package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-08-18 17:27
 */
public class Leet2585 {

    public static void main(String[] args) {

        System.out.println(waysToReachTarget(6, LeetUtils.convertInts("[[6,1],[3,2],[2,3]]")));
        System.out.println(waysToReachTarget(5, LeetUtils.convertInts("[[50,1],[50,2],[50,5]]")));
        System.out.println(waysToReachTarget(18, LeetUtils.convertInts("[[6,1],[3,2],[2,3]]")));

    }

    public static int waysToReachTarget(int target, int[][] types) {

        int N = types.length;

        final int MOD = 1000000007;

        int[][] dp = new int[N + 1][target + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= N; i++) {

            int cnt = types[i - 1][0];

            int msk = types[i - 1][1];

            for (int j = 0; j <= target; j++)
                dp[i][j] = dp[i - 1][j];

            for (int j = 0; j <= target; j++) {

                for (int k = 1; k <= cnt; k++) {
                    int m = k * msk;

                    if (j - m < 0)
                        break;

                    dp[i][j] += dp[i - 1][j - m] % MOD;
                    dp[i][j] %= MOD;
                }

            }

        }

        return dp[N][target];
    }

}
