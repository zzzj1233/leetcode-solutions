package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2021-11-03 14:06
 */
public class Leet72 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String str1 = LeetUtils.randomString(LeetUtils.random.nextInt(100), false);
            String str2 = LeetUtils.randomString(LeetUtils.random.nextInt(100), false);
            if (minDistance(str1, str2) != right(str1, str2)) {
                System.out.println(str1);
                System.out.println(str2);
                System.out.println(minDistance(str1, str2));
                System.out.println(right(str1, str2));
                return;
            }
        }
    }

    public static int minDistance(String word1, String word2) {

        if (word1.isEmpty()) {
            return word2.length();
        }

        if (word2.isEmpty()) {
            return word1.length();
        }

        int N = word1.length();
        int M = word2.length();

        int[][] dp = new int[N][M];

        dp[0][0] = word1.charAt(0) == word2.charAt(0) ? 0 : 1;

        for (int i = 1; i < M; i++) {
            if (word1.charAt(0) == word2.charAt(i) && dp[0][i - 1] == i) {
                dp[0][i] = dp[0][i - 1];
            } else {
                dp[0][i] = dp[0][i - 1] + 1;
            }
        }

        for (int i = 1; i < N; i++) {
            if (word1.charAt(i) == word2.charAt(0) && dp[i - 1][0] == i) {
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
            }
        }

        for (int i = 1; i < N; i++) {

            for (int j = 1; j < M; j++) {

                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }

        }

        return dp[N - 1][M - 1];
    }


    public static int right(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

}
