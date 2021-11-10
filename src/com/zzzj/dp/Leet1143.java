package com.zzzj.dp;


/**
 * @author zzzj
 * @create 2021-11-02 10:40
 */
public class Leet1143 {

    /**
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
     * <p>
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * <p>
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
     * <p>
     * 若这两个字符串没有公共子序列，则返回 0。
     * <p>
     * 示例 1:
     * <p>
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace"，它的长度为 3。
     * <p>
     * 示例 2:
     * 输入：text1 = "abc", text2 = "abc"
     * 输出：3
     * 解释：最长公共子序列是 "abc"，它的长度为 3。
     * <p>
     * 示例 3:
     * 输入：text1 = "abc", text2 = "def"
     * 输出：0
     * 解释：两个字符串没有公共子序列，返回 0。
     * <p>
     * 提示:
     * <p>
     * 1 <= text1.length <= 1000
     * 1 <= text2.length <= 1000 输入的字符串只含有小写英文字符
     */
    public static void main(String[] args) {
        // "bsbininm"
        // "jmjkbkjkv"
        // System.out.println(dynamicPlanning("abcde", "zace"));
        System.out.println(dynamicPlanning("abah", "wwda"));
    }


    private static int dynamicPlanning(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();

        if (n1 == 0 || n2 == 0) {
            return 0;
        }

        int[][] dp = new int[n1][n2];

        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();

        for (int i = 0; i < n1; i++) {
            char char1 = str1[i];

            for (int j = 0; j < n2; j++) {
                char char2 = str2[j];
                if (char1 == char2) {
                    dp[i][j] = i - 1 >= 0 && j - 1 >= 0 ? dp[i - 1][j - 1] + 1 : 1;
                } else {
                    dp[i][j] = Math.max(i - 1 >= 0 ? dp[i - 1][j] : 0, Math.max(j - 1 >= 0 ? dp[i][j - 1] : 0, i - 1 >= 0 && j - 1 >= 0 ? dp[i - 1][j - 1] : 0));
                }
            }

        }

        return dp[n1 - 1][n2 - 1];
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        return process1(text1.toCharArray(), text2.toCharArray(), 0, 0);
    }

    private static int process1(char[] str1, char[] str2, int i, int j) {
        if (i >= str1.length || j >= str2.length) {
            return 0;
        }
        if (str1[i] == str2[j]) {
            return 1 + process1(str1, str2, i + 1, j + 1);
        } else {
            return Math.max(process1(str1, str2, i + 1, j + 1), Math.max(process1(str1, str2, i + 1, j), process1(str1, str2, i, j + 1)));
        }
    }

}
