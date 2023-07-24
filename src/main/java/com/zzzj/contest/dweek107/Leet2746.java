package com.zzzj.contest.dweek107;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-24 15:07
 */
public class Leet2746 {


    static boolean useDFS = false;

    public static void main(String[] args) {

        System.out.println(minimizeConcatenatedLength(new String[]{"abc", "cd", "ce", "cf"}));

//        System.exit(0);

        System.out.println(minimizeConcatenatedLength(new String[]{"aa", "ab", "bc"}));

        System.out.println(minimizeConcatenatedLength(new String[]{"ab", "b"}));

        System.out.println(minimizeConcatenatedLength(new String[]{"aaa", "c", "aba"}));

        System.out.println(minimizeConcatenatedLength(new String[]{"a", "bc", "c"}));
        useDFS = true;
        System.out.println(minimizeConcatenatedLength(new String[]{"a", "bc", "c"}));
        useDFS = false;

    }

    public static int minimizeConcatenatedLength(String[] words) {
        int N = words.length;

        int[][][] dp = new int[N][26][26];

        for (int i = 0; i < N; i++) for (int j = 0; j < 26; j++) Arrays.fill(dp[i][j], Integer.MAX_VALUE);

        String word = words[0];

        dp[0][index(word, true)][index(word, false)] = word.length();

        for (int i = 1; i < N; i++) {

            word = words[i];

            int firstIndex = index(word, true);

            int lastIndex = index(word, false);

            // last == pre.first

            for (int k = 0; k < 26; k++) {
                //  k  是之前的lastIndex
                // [k] 是长度

                int len = dp[i - 1][lastIndex][k];

                if (len != Integer.MAX_VALUE) {

                    dp[i][firstIndex][k] = Math.min(
                            dp[i][firstIndex][k],
                            len + word.length() - 1
                    );

                }

            }

            // first == pre.last

            for (int j = 0; j < 26; j++) {

                int len = dp[i - 1][j][firstIndex];

                if (len != Integer.MAX_VALUE) {
                    dp[i][j][lastIndex] = Math.min(
                            dp[i][j][lastIndex],
                            len + word.length() - 1
                    );
                }

            }

            for (int j = 0; j < 26; j++) {

                for (int k = 0; k < 26; k++) {

                    int len = dp[i - 1][j][k];

                    if (len != Integer.MAX_VALUE) {

                        // 1. 拼接在前面
                        dp[i][firstIndex][k] = Math.min(
                                dp[i][firstIndex][k],
                                len + word.length()
                        );

                        // 2. 拼接在后面
                        dp[i][j][lastIndex] = Math.min(
                                dp[i][j][lastIndex],
                                len + word.length()
                        );
                    }

                }

            }

        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 26; i++) {

            for (int j = 0; j < 26; j++) {
                min = Math.min(
                        min,
                        dp[N - 1][i][j]
                );
            }

        }

        return min;
    }

    public static int index(String word, boolean first) {
        if (first) return word.charAt(0) - 'a';
        return word.charAt(word.length() - 1) - 'a';
    }

    public static int dfs2(int index, char first, char last, String[] words) {

        if (index >= words.length) return 0;

        String word = words[index];

        char firstChar = word.charAt(0);

        char lastChar = word.charAt(word.length() - 1);

        int case1 = Integer.MAX_VALUE;
        int case2 = Integer.MAX_VALUE;
        int case3;

        if (firstChar == last) {
            case1 = dfs2(index + 1, first, lastChar, words) + word.length() - 1;
        }

        if (lastChar == first) {
            case2 = dfs2(index + 1, firstChar, last, words) + word.length() - 1;
        }

        case3 = Math.min(
                dfs2(index + 1, first, lastChar, words) + word.length(),
                dfs2(index + 1, firstChar, last, words) + word.length()
        );

        return Math.min(
                case1,
                Math.min(case2, case3)
        );
    }

    public static int dfs(String str, int index, String[] words) {

        if (index >= words.length) return str.length();

        String word = words[index];

        int length = str.length();

        // 第一个元素和builder的最后一个元素匹配
        if (word.charAt(0) == str.charAt(str.length() - 1)) {
            return dfs(str + word.substring(1), index + 1, words);
        } else if (word.charAt(word.length() - 1) == str.charAt(0)) {
            return dfs(word + str.substring(1), index + 1, words);
        }

        return Math.min(
                dfs(str + word, index + 1, words),
                dfs(word + str, index + 1, words)
        );
    }

}
