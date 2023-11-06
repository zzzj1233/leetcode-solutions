package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

public class Leet2478 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int M = 500;

            int k = LeetUtils.random.nextInt(M) + 1;

            int min = LeetUtils.random.nextInt(M) + 1;

            String str = LeetUtils.randomString(M, "12345");

            int r = beautifulPartitions(str, k, min);

            int rr = right(str, k, min);

            if (r != rr) {
                System.out.println("str = " + str);
                System.out.println("k = " + k);
                System.out.println("min = " + min);
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                return;
            }
        }

        System.out.println("Ok");
    }

    static final boolean[] ok = new boolean[58];

    static {
        ok['2'] = true;
        ok['3'] = true;
        ok['5'] = true;
        ok['7'] = true;
    }

    static boolean canStart(char c) {
        return ok[c];
    }

    static boolean canEnd(char c) {
        return !ok[c];
    }

    public static int beautifulPartitions(String s, int k, int minLength) {

        int N = s.length();

        if (!ok[s.charAt(0)] || ok[s.charAt(N - 1)])
            return 0;

        final int MOD = 1000000007;

        int[][] dp = new int[N][k];

        int[][] preSum = new int[N + 1][k];

        for (int i = minLength - 1; i < N; i++) {

            if (canStart(s.charAt(i))) {
                if (i - 1 >= 0) for (int j = 0; j < k; j++) preSum[i][j] = (preSum[i - 1][j] + dp[i - 1][j]) % MOD;
                continue;
            }

            preSum[i] = preSum[i - 1];

            dp[i][0] = 1;

            for (int j = 1; j < k; j++) dp[i][j] = (dp[i][j] + preSum[i - minLength + 1][j - 1]) % MOD;

        }

        return dp[N - 1][k - 1];
    }

    public static int right(String s, int K, int mL) {
        int n = s.length(), mod = (int) 1e9 + 7;
        char[] arr = ("0" + s).toCharArray();
        int[][] dp = new int[n + 5][K + 5];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            if (check(arr[i])) continue;
            for (int j = 1; j <= K; j++) {
                for (int k = (j - 1) * mL + 1; k <= i - mL + 1; k++) {
                    if (check(arr[k])) dp[i][j] = (int) (((long) dp[i][j] + dp[k - 1][j - 1]) % mod);
                }
            }
        }
        return dp[n][K];
    }

    static boolean check(char x) {
        if (x == '2' || x == '3' || x == '5' || x == '7') return true;
        return false;
    }


}
