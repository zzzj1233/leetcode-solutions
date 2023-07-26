package com.zzzj.contest.dweek109;

public class Leet6922 {

    public static void main(String[] args) {

        System.out.println(numberOfWays(2, 1));

        System.out.println(numberOfWays(4, 1));

        System.out.println(numberOfWays(10, 2));

        System.out.println(numberOfWays(213, 1));

        System.exit(0);

        for (int i = 1; i < 15; i++) {
            for (int j = 1; j <= 5; j++) {
                if (numberOfWays(i, j) != right(i, j)) {
                    System.out.println("Error");
                    System.out.println("N = " + i);
                    System.out.println("X = " + j);
                    System.out.println("numberOfWays(i,j) = " + numberOfWays(i, j));
                    System.out.println("right(i,j) = " + right(i, j));
                    return;
                }
            }
        }

        System.out.println("Ok");
    }

    public static int numberOfWays(int n, int x) {

        int[][] dp = new int[n + 2][n + 1];

        final int MOD = 1000000007;

        for (int i = n; i > 0; i--) {

            for (int j = 0; j <= n; j++) {
                dp[i][j] = dp[i + 1][j];
            }

            int pow = (int) Math.pow(i, x);

            if (pow > n) continue;

            dp[i][pow]++;
            dp[i][pow] %= MOD;

            for (int j = n; j >= 0; j--) {

                if (pow + j <= n) {
                    dp[i][pow + j] += dp[i + 1][j] % MOD;
                    dp[i][pow + j] %= MOD;
                }

            }

        }

        return dp[1][n];
    }

    public static int right(int n, int x) {
        //方法二、0-1背包
        //时间复杂度： o(nlogn) 空间复杂度：o(n)
        int m = (int) Math.pow(n, 1.0 / x) + 1, mod = (int) (1e9 + 7);
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= m; i++) {
            int t = (int) Math.pow(i, x);
            for (int j = n; j >= t; j--) {
                f[j] = (f[j] + f[j - t]) % mod;
            }
        }
        return f[n];
    }

}
