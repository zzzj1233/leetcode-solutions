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
        System.out.println(dynamicPlanning("rabbbit", "rabbit"));

        System.out.println(numDistinct("babgbag", "bag"));
        System.out.println(dynamicPlanning("babgbag", "bag"));
    }

    private static int dynamicPlanning(String s, String t) {
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();

        int n1 = str1.length;
        int n2 = str2.length;

        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 0; i <= n1; i++) {
            dp[i][n2] = 1;
        }

        for (int j = n2 - 1; j >= 0; j--) {
            for (int i = n1 - 1; i >= 0; i--) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }

        return dp[0][0];
    }

    public static int numDistinct(String s, String t) {
        return process(t.toCharArray(), s.toCharArray(), 0, 0);
    }

    private static int process(char[] str1, char[] str2, int i, int j) {
        if (i >= str1.length) {
            return 1;
        }

        if (j >= str2.length) {
            return 0;
        }

        int val1 = 0;

        if (str1[i] == str2[j]) {
            val1 = process(str1, str2, i + 1, j + 1);
        }

        return process(str1, str2, i, j + 1) + val1;
    }

}
