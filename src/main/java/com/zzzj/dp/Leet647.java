package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-01 15:27
 */
public class Leet647 {

    /**
     * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
     * <p>
     * 回文字符串 是正着读和倒过来读一样的字符串。
     * <p>
     * 子字符串 是字符串中的由连续字符组成的一个序列。
     * <p>
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     * 示例 2：
     * <p>
     * 输入：s = "aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     */
    public static void main(String[] args) {
        String str = "aaa";
        System.out.println(countSubstrings(str));
    }


    public static int countSubstrings(String s) {
        char[] chars = s.toCharArray();

        int n = chars.length;

        int sum = 1;

        boolean[][] dp = new boolean[n][n];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if ((i - j < 2 || dp[i - 1][j + 1]) && chars[i] == chars[j]) {
                    dp[i][j] = true;
                    sum++;
                }
            }
        }

        return sum;
    }

}
