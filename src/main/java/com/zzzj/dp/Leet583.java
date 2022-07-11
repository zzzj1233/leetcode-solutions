package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-03 12:01
 */
public class Leet583 {

    /**
     * 给定两个单词word1和word2，找到使得word1和word2相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
     * <p>
     * 示例：
     * <p>
     * 输入: "sea", "eat"
     * 输出: 2
     * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
     */
    public static void main(String[] args) {
        System.out.println(minDistance("sea", "eat"));
        System.out.println(minDistance("leetcode", "etco"));
    }

    public static int minDistance(String word1, String word2) {
//        return dfs(word1.toCharArray(), word2.toCharArray(), 0, 0);
        return dp(word1.toCharArray(), word2.toCharArray());
    }

    public static int dp(char[] word1, char[] word2) {
        int N = word1.length;
        int M = word2.length;

        int[][] dp = new int[N + 1][M + 1];

        for (int i = 0; i <= M; i++) {
            dp[N][i] = M - i;
        }

        for (int i = 0; i <= N; i++) {
            dp[i][M] = N - i;
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                if (word1[i] == word2[j]) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
                }
            }
        }
        return dp[0][0];
    }

    public static int dfs(char[] word1, char[] word2, int i, int j) {
        if (i >= word1.length) {
            return word2.length - j;
        }
        if (j >= word2.length) {
            return word1.length - i;
        }

        // 没有必要删
        if (word1[i] == word2[j]) {
            return dfs(word1, word2, i + 1, j + 1);
        }

        int ways1 = dfs(word1, word2, i + 1, j);
        int ways2 = dfs(word1, word2, i, j + 1);

        return 1 + Math.min(ways1, ways2);
    }


}
