package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-09-28 15:02
 */
public class Leet664 {

    public static void main(String[] args) {

        // 4

//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            String str = LeetUtils.randomString(100);
            if (strangePrinter(str) != right(str)) {
                System.out.println("Error");
                System.out.println(str);
                System.out.println(strangePrinter(str));
                System.out.println(right(str));
                return;
            }
        }
        System.out.println("Ok");
    }

    // 1. 每次打印任意长度的同一字符
    // 2. 在已打印的字符中 替换任意一段字符

    public static int strangePrinter(String s) {
        char[] chars = s.toCharArray();

        int N = chars.length;

        int[][] dp = new int[N][N];

        for (int i = N - 1; i >= 0; i--) {

            dp[i][i] = 1;

            for (int j = i + 1; j < N; j++) {

                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                // i != j
                for (int k = i; k < j; k++) {
                    if (chars[k] == chars[j]) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j - 1]);
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }

            }

        }

        return dp[0][N - 1];
    }

    public static int right(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i][j - 1];
                } else {
                    int minn = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        minn = Math.min(minn, f[i][k] + f[k + 1][j]);
                    }
                    f[i][j] = minn;
                }
            }
        }
        return f[0][n - 1];
    }


}
