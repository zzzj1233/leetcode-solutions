package com.zzzj.dp;

import java.util.Arrays;

public class Leet1220 {

    public static void main(String[] args) {

        System.out.println(countVowelPermutation(2));

        System.out.println(countVowelPermutation(5));

    }

    public static int countVowelPermutation(int n) {

        long[][] dp = new long[n][5];

        final int MOD = 1000000007;

        for (int i = 0; i < 5; i++)
            dp[0][i] = 1;

        int a = 0;
        int e = 1;
        int i = 2;
        int o = 3;
        int u = 4;

        for (int index = 1; index < n; index++) {

            long[] prev = dp[index - 1];

            long[] cur = dp[index];

            // a: e/i/u
            cur[a] = (prev[e] + prev[i] + prev[u]) % MOD;

            // e: a/i
            cur[e] = (prev[a] + prev[i]) % MOD;

            // i: e/o
            cur[i] = (prev[e] + prev[o]) % MOD;

            // o: i
            cur[o] = prev[i] % MOD;

            // u: i/o
            cur[u] = (prev[i] + prev[o]) % MOD;
        }

        return (int) (Arrays.stream(dp[n - 1]).sum() % MOD);
    }

}
