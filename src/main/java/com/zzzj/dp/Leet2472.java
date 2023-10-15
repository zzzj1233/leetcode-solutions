package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

public class Leet2472 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {

            int M = LeetUtils.random.nextInt(500) + 1;

            int K = LeetUtils.random.nextInt(M) + 1;

            String str = LeetUtils.randomString(M, false);

            int r = maxPalindromes(str, K);

            int rr = right(str, K);

            if (r != rr) {
                System.out.println("Error");
                System.out.println("str = " + str);
                System.out.println("K = " + K);
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                return;
            }
        }

        System.out.println("Ok");

    }

    public static int maxPalindromes(String s, int k) {

        int N = s.length();

        boolean[][] help = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++)
            help[i][i] = true;

        for (int y = 1; y <= N; y++) {

            for (int x = 1; x < y; x++) {

                if (s.charAt(x - 1) == s.charAt(y - 1))
                    help[x][y] = y - 1 == x || help[x + 1][y - 1];

            }

        }

        int[] dp = new int[N + 1];

        int ans = 0;

        for (int i = 1; i <= N; i++) {

            dp[i] = Math.max(dp[i], dp[i - 1]);

            for (int j = i + k - 1; j <= N; j++) {
                if (help[i][j])
                    dp[j] = Math.max(dp[j], 1 + dp[i - 1]);
                ans = Math.max(ans, dp[j]);
            }

        }

        return ans;
    }

    public static int right(String s, int k) {
        // 动态规划操作
        int ans = 0, n = s.length();
        boolean[][] isPal = new boolean[n][n];
        for (int i = 0; i < n; i++) isPal[i][i] = true;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (j == i + 1) isPal[i][j] = (s.charAt(i) == s.charAt(j));
                else {
                    if (s.charAt(i) == s.charAt(j)) {
                        isPal[i][j] = isPal[i + 1][j - 1];
                    }
                }
            }
        }
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i - k + 1; j++) {
                if (isPal[j - 1][i - 1]) {
                    f[i] = Math.max(f[j - 1] + 1, f[i]);
                }
            }
            f[i] = Math.max(f[i], f[i - 1]);
            ans = Math.max(f[i], ans);
        }

        return ans;
    }

}
