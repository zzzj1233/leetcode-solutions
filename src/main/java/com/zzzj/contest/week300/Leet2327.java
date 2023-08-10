package com.zzzj.contest.week300;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-09 17:34
 */
public class Leet2327 {

    public static void main(String[] args) {

//        System.out.println(peopleAwareOfSecret(6, 2, 4));
//
//        System.out.println(peopleAwareOfSecret(4, 1, 3));

        System.out.println(peopleAwareOfSecret(289, 7, 23));

    }


    public static int peopleAwareOfSecret(int n, int delay, int forget) {

        final int MOD = 1000000007;

        int[] dp = new int[n + 1];

        dp[0] = 1;

        for (int i = 1; i < n; i++) {

            dp[i] = dp[i - 1];

            if (i - forget >= 0) {

                int start = i - forget;

                long del = dp[start];

                for (int j = start; j <= i; j++) {
                    dp[j] = (int) (((dp[j] + MOD) - del) % MOD);
                }

            }

            if (i - delay >= 0)
                dp[i] += dp[i - delay] % MOD;

            dp[i] %= MOD;
        }

        return dp[n - 1] % MOD;
    }

}
