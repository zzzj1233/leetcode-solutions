package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-11-03 14:06
 */
public class Leet72 {

    /**
     * 给你两个单词word1 和word2，请你计算出将word1转换成word2 所使用的最少操作数。
     * <p>
     * 你可以对一个单词进行如下三种操作：
     * <p>
     * 插入一个字符
     * <p>
     * 删除一个字符
     * <p>
     * 替换一个字符
     * <p>
     * 示例1：
     * <p>
     * 输入：word1 = "horse", word2 = "ros"
     * <p>
     * 输出：3
     * <p>
     * 解释：
     * <p>
     * horse -> rorse (将 'h' 替换为 'r')
     * <p>
     * rorse -> rose (删除 'r')
     * <p>
     * rose -> ros (删除 'e')
     *
     * <p>
     * 示例2：
     * <p>
     * 输入：word1 = "intention", word2 = "execution"
     * <p>
     * 输出：5
     * <p>
     * 解释：
     * <p>
     * intention -> inention (删除 't')
     * <p>
     * inention -> enention (将 'i' 替换为 'e')
     * <p>
     * enention -> exention (将 'n' 替换为 'x')
     * <p>
     * exention -> exection (将 'n' 替换为 'c')
     * <p>
     * exection -> execution (插入 'u')
     */
    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
        System.out.println(dynamicPlanning("horse", "ros"));
    }

    private static int dynamicPlanning(String word1, String word2) {
        final char[] chars1 = word1.toCharArray();
        final char[] chars2 = word2.toCharArray();

        int len1 = chars1.length;
        int len2 = chars2.length;

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = len1; i >= 0; i--) {

            for (int j = len2; j >= 0; j--) {

                if (i == len1 && j == len2) {
                    continue;
                }

                if (i == len1) {
                    dp[i][j] = len2 - j;
                    continue;
                }

                if (j == len2) {
                    dp[i][j] = len1 - j;
                    continue;
                }

                if (chars1[i] == chars2[j]) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j + 1], Math.min(dp[i + 1][j], dp[i][j + 1]));
                }
            }

        }

        return dp[len1 - 1][len2 - 1];
    }

    private static int access(int[][] dp, int len1, int len2, char char1, char char2, int i, int j) {
        if (i == len1 && j == len2) {
            return 0;
        }

        if (i == len1) {
            return len2 - j;
        }

        if (j == len2) {
            return len1 - j;
        }

        if (char1 == char2) {
            return dp[i + 1][j + 1];
        }

        return 0;
    }

    public static int minDistance(String word1, String word2) {
        final char[] chars1 = word1.toCharArray();
        final char[] chars2 = word2.toCharArray();

        return process(chars1, chars2, chars1[0], chars2[0], chars1.length, chars2.length, 0, 0);
    }

    private static char access(char[] chars, int index) {
        if (index < 0 || index >= chars.length) {
            return 0;
        }
        return chars[index];
    }

    private static int process(char[] str1, char[] str2, char char1, char char2, int len1, int len2, int i, int j) {
        if (i >= len1 && j >= len2) {
            return 0;
        }

        if (i >= len1) {
            // removeLast
            return len2 - j;
        }

        if (j >= len2) {
            // insert
            return len1 - j;
        }

        if (char1 == char2) {
            return process(str1, str2, access(str1, i + 1), access(str2, j + 1), len1, len2, i + 1, j + 1);
        }

        // 删除
        int removal = 1 + process(str1, str2, access(str1, i + 1), access(str2, j), len1 - 1, len2, i, j);

        // 插入
        int inserted = 1 + process(str1, str2, char2, char2, len1 + 1, len2, i, j);

        // 替换
        int replace = 1 + process(str1, str2, char2, char2, len1, len2, i, j);

        return Math.min(removal, Math.min(inserted, replace));
    }

}
