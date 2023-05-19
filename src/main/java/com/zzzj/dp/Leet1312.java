package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-05-19 15:41
 */
public class Leet1312 {

    public static void main(String[] args) {

        System.out.println(minInsertions("zzazz"));

        System.out.println(minInsertions("mbadm"));

        System.out.println(minInsertions("leetcode"));

    }

    // 虚假的hard
    public static int minInsertions(String s) {
        return dp(s);
    }

    public static int dp(String s) {
        int N = s.length();

        int[][] dp = new int[N][N];

        for (int i = N - 1; i >= 0; i--) {

            for (int j = i; j < N; j++) {

                if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 < N && j - 1 >= 0)
                        dp[i][j] = dp[i + 1][j - 1];
                } else {
                    int x = i + 1 >= N ? 0 : dp[i + 1][j];
                    int y = j - 1 < 0 ? 0 : dp[i][j - 1];

                    dp[i][j] = Math.min(x, y) + 1;
                }
            }

        }

        return dp[0][N - 1];
    }


    public static int dfs(char[] chars, int left, int right) {
        if (left >= right) {
            return 0;
        }

        if (chars[left] == chars[right]) {
            return dfs(chars, left + 1, right - 1);
        }

        return Math.min(
                dfs(chars, left + 1, right),
                dfs(chars, left, right - 1)
        ) + 1;
    }
}
