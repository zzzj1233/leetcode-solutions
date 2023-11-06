package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-11-02 12:05
 */
public class Leet940 {

    public static int distinctSubseqII(String s) {

        final int MOD = 1000000007;

        int N = s.length();

        int[] dp = new int[N + 1];

        OUTER:
        for (int i = 1; i <= N; i++) {
            char c = s.charAt(i - 1);

            dp[i] = (dp[i - 1] << 1) % MOD;

            for (int j = i - 1; j > 0; j--) {
                if (s.charAt(j - 1) == c) {
                    dp[i] -= dp[j - 1];
                    if (dp[i] < 0)
                        dp[i] += MOD;
                    dp[i] %= MOD;
                    continue OUTER;
                }

            }

            dp[i]++;
            dp[i] %= MOD;
        }

        return dp[N];
    }

}
