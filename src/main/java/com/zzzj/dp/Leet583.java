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
        System.out.println(dynamicPlanning("sea", "eat"));
    }

    private static int dynamicPlanning(String word1, String word2) {
        final char[] str1 = word1.toCharArray();
        final char[] str2 = word2.toCharArray();

        int n1 = str1.length;
        int n2 = str2.length;

        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = n1; i >= 0; i--) {

            for (int j = n2; j >= 0; j--) {
                if (i >= n1 && j >= n2) {
                    continue;
                }
                if (i >= str1.length) {
                    dp[i][j] = dp[i][j + 1] + 1;
                    continue;
                }
                if (j >= str2.length) {
                    dp[i][j] = dp[i + 1][j] + 1;
                    continue;
                }

                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    int val1 = dp[i + 1][j + 1] + 2;
                    int val2 = dp[i + 1][j] + 1;
                    int val3 = dp[i][j + 1] + 1;
                    dp[i][j] = Math.min(val1, Math.min(val2, val3));
                }
            }

        }
        return dp[0][0];
    }

    private static int access(int[][] dp, int i, int j) {
        if (i <= 0 && j <= 0) {
            return 0;
        }
        if (i <= 0) {
            return dp[dp.length - 1][j - 1] + 1;
        }
        return dp[i][dp[i].length - 1] + 1;
    }

    public static int minDistance(String word1, String word2) {
        // 如果这个字母不相同
        return process(word1.toCharArray(), word2.toCharArray(), 0, 0);
    }

    private static int process(char[] str1, char[] str2, int i, int j) {
        if (i >= str1.length && j >= str2.length) {
            return 0;
        }

        if (i >= str1.length) {
            return process(str1, str2, i, j + 1) + 1;
        }

        if (j >= str2.length) {
            return process(str1, str2, i + 1, j) + 1;
        }

        if (str1[i] == str2[j]) {
            return process(str1, str2, i + 1, j + 1);
        } else {
            return Math.min(process(str1, str2, i + 1, j + 1) + 2, 1 + Math.min(process(str1, str2, i + 1, j), process(str1, str2, i, j + 1) + 1));
        }
    }

}
