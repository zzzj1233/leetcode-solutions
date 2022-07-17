package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-25 14:45
 */
public class Leet5 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome2("aaaa"));
    }

    // 中心扩散法
    public static String longestPalindrome2(String s) {

        char[] chars = s.toCharArray();

        int start = 0;
        int end = 0;

        int N = s.length();

        for (int i = 1; i < N - 1; i++) {
            // 可以把c当成中心字符,也可以不当成中心字符
            // 可以当做中心字符
            int asCenter = tryExpand(i, i, N, chars);

            int nonAsCenter = tryExpand(i, i + 1, N, chars);

            int len = Math.max(asCenter, nonAsCenter);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }

        }

        return s.substring(start, end + 1);
    }

    public static int tryExpand(int left, int right, int N, char[] chars) {
        while (left >= 0 && right < N && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    // 动态规划
    public static String longestPalindrome(String s) {
        // 构建字符串回文DP

        int N = s.length();


        boolean[][] dp = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }

        // 第一次 01,12,23,34
        // 第二次 02,13,24
        // 第三次 03,14
        // 第四次 04


        // 外层循环N - 1次

        // 内层循环
        // i 永远从0开始
        // j 递增

        int left = 0;
        int right = 0;

        for (int k = 0; k < N - 1; k++) {

            // i永远从0开始
            int i = 0;

            for (int j = k + 1; j < N; j++, i++) {

                if (s.charAt(i) != s.charAt(j)) {
                    continue;
                }

                // 两个字符串相同

                // < 2 : aa j - i = 1, aba j - i = 2
                if (j - i <= 2) {
                    dp[i][j] = true;
                } else {
                    // eg: a????a
                    // 那么就要dp[i+1][j+1]是回文,当前字符串ij位置才是回文
                    dp[i][j] = dp[i + 1][j - 1];
                }

                if (dp[i][j]) {
                    if (j - i > right - left) {
                        right = j;
                        left = i;
                    }
                }
            }

        }

        return s.substring(left, right + 1);
    }

}
