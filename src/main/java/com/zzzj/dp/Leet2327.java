package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-10-11 11:29
 */
public class Leet2327 {

    public static void main(String[] args) {
//        System.out.println(peopleAwareOfSecret(289, 7, 23));
        System.out.println(peopleAwareOfSecret(363, 13, 32));
    }

    public static int peopleAwareOfSecret(int n, int delay, int forget) {
        // 定义dp记录第i天所有知道秘密的人数

        // 那么第i - forget天就是第i天忘记的人数

        // i - delay就是第i天可以分享的人数

        long[] dp = new long[n + 1];
        dp[1] = 1;

        final int MOD = 1000000007;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1];

            // i - forget ~ i 的人全部减去

            if (i - forget >= 0) {
                long sub = dp[i - forget];
                for (int j = i - forget; j <= i; j++) {
                    dp[j] -= sub;
                    dp[j] += MOD;
                    dp[j] %= MOD;
                }
            }

            if (i - delay >= 0) {
                dp[i] += dp[i - delay];
                dp[i] %= MOD;
            }
        }

        return (int) dp[n];
    }

}
