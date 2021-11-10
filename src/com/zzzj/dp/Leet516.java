package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-03 15:37
 */
public class Leet516 {

    /**
     * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
     * <p>
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "bbbab"
     * 输出：4
     * 解释：一个可能的最长回文子序列为 "bbbb" 。
     * 示例 2：
     * <p>
     * 输入：s = "cbbd"
     * 输出：2
     * 解释：一个可能的最长回文子序列为 "bb" 。
     */
    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbabccb"));
    }

    private static int dynamicPlanning(String s) {
        int n = s.length();

        char[] chars = s.toCharArray();

        boolean[][] cache = new boolean[n][n];
        int[][] dp = new int[n][n];

        for (int i = 1; i < n; i++) {

            for (int j = 1; j < n; j++) {
                if (chars[i] == chars[j]) {
                    if (j - i < 2) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        cache[i][j] = true;
                    }
                } else {

                }
            }

        }

        return -1;
    }

    public static int longestPalindromeSubseq(String s) {
        return violentRecursion(s.toCharArray(), 0, 0, 0);
    }

    private static boolean isPalindrome(char[] chars, int i, int j, int k) {
        if (chars[i] != chars[k]) {
            return false;
        }

        while (i < k) {
            if (chars[i] != chars[k]) {
                return false;
            }
            i++;
            k--;
        }

        return true;
    }

    private static int violentRecursion(char[] chars, int i, int j, int k) {
        if (i >= chars.length || j >= chars.length) {
            return 0;
        }

        if (isPalindrome(chars, i, j, k)) {
            return 1 + violentRecursion(chars, i, j + 1, j + 1);
        } else {
            return Math.max(violentRecursion(chars, i, j + 1, i), violentRecursion(chars, i + 1, j + 1, i + 1));
        }
    }

}
