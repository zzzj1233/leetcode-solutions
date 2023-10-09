package com.zzzj.str;


/**
 * @author zzzj
 * @create 2023-10-08 16:48
 */
public class Leet1092 {

    public static void main(String[] args) {

        System.out.println(shortestCommonSupersequence("qawbce", "abrtc"));

        System.out.println(longestCommonSubstringSequence("acb", "abc"));

    }

    // cabac
    public static String shortestCommonSupersequence(String str1, String str2) {

        String common = longestCommonSubstringSequence(str1, str2);

        int x = 0;

        int y = 0;

        int c = 0;

        StringBuilder builder = new StringBuilder(str1.length() + str2.length());

        while (c < common.length()) {

            char next = common.charAt(c);

            while (x < str1.length() && str1.charAt(x) != next)
                builder.append(str1.charAt(x++));

            while (y < str2.length() && str2.charAt(y) != next)
                builder.append(str2.charAt(y++));

            builder.append(next);

            c++;
            x++;
            y++;
        }

        while (x < str1.length())
            builder.append(str1.charAt(x++));

        while (y < str2.length())
            builder.append(str2.charAt(y++));

        return builder.toString();
    }

    public static String longestCommonSubstringSequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        // 填充dp数组
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 构建最长公共子字符串序列
        int i = m;
        int j = n;
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return sb.reverse().toString();
    }


}
