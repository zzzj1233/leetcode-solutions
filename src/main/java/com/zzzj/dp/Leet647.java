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
        int N = s.length();

        boolean[][] dp = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }

        int ans = N;

        for (int i = N - 2; i >= 0; i--) {

            for (int j = i + 1; j < N; j++) {


                if (s.charAt(i) != s.charAt(j)) {
                    continue;
                }

                if (j - i == 1 || dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    ans++;
                }

            }

        }

        return ans;
    }

}
