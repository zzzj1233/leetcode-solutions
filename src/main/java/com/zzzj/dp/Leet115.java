package com.zzzj.dp;


/**
 * @author zzzj
 * @create 2021-11-03 09:55
 */
public class Leet115 {

    /**
     * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     * <p>
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是）
     * <p>
     * 题目数据保证答案符合 32 位带符号整数范围。
     * <p>
     * 示例1：
     * <p>
     * 输入：s = "rabbbit", t = "rabbit"
     * 输出：3
     * <p>
     * 示例2：
     * <p>
     * 输入：s = "babgbag", t = "bag"
     * 输出：5
     */
    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit", "rabbit"));
        System.out.println(numDistinct("babgbag", "bag"));
    }

    public static int numDistinct(String s, String t) {
        int N = s.length();
        int M = t.length();

        int[][] dp = new int[N + 1][M + 1];

        for (int i = 0; i <= N; i++) {
            dp[i][M] = 1;
        }

        for (int i = N - 1; i >= 0; i--) {

            for (int j = M - 1; j >= 0; j--) {

                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }

        }

        return dp[0][0];
    }

    public static int dfs(String s, String t, int i, int j) {
        if (j >= t.length()) {
            return 1;
        }
        if (i >= s.length()) {
            return 0;
        }

        int ways1 = dfs(s, t, i + 1, j);

        if (s.charAt(i) == t.charAt(j)) {
            return dfs(s, t, i + 1, j + 1) + ways1;
        }

        return ways1;
    }

}
