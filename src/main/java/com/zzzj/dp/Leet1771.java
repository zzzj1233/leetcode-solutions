package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-10-17 15:18
 */
public class Leet1771 {

    public static void main(String[] args) {

        System.out.println(longestPalindrome("cca", "acad"));
//
//        System.out.println(longestPalindrome("ab", "ab"));

//        System.out.println(longestPalindrome("aa", "bb"));

//        System.out.println(longestPalindrome("bbab", "a"));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {

            int M = 50;

            String str1 = LeetUtils.randomString(LeetUtils.random.nextInt(M) + 1, "abcd");

            String str2 = LeetUtils.randomString(LeetUtils.random.nextInt(M) + 1, "abcd");

            if (str1.length() <= 1 || str2.length() <= 1)
                continue;

            int r = longestPalindrome(str1, str2);

            int rr = right(str1, str2);

            if (r != rr) {
                System.out.println("Error");
                System.out.println("str1 = " + str1);
                System.out.println("str2 = " + str2);
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                return;
            }

        }

        System.out.println("Ok");

    }

    // cacb | cbba
    // abcba
    public static int longestPalindrome(String word1, String word2) {

        String str = word1 + word2;

        int N = word1.length() + word2.length();

        int[][] dp = new int[N + 1][N + 1];

        int ans = 0;

        for (int i = N - 1; i >= 0; i--) {

            dp[i][i] = 1;

            for (int j = i + 1; j < N; j++) {

                char c = str.charAt(i);

                if (c == str.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;

                    if (i < word1.length() && j >= word1.length())
                        ans = Math.max(ans, dp[i][j]);
                } else {
                    dp[i][j] = Math.max(
                            dp[i + 1][j],
                            dp[i][j - 1]
                    );
                }

            }

        }

        return ans;
    }

    public static int right(String word1, String word2) {
        char[] s = (word1 + word2).toCharArray();
        int ans = 0, n = s.length;
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                    if (i < word1.length() && j >= word1.length()) {
                        ans = Math.max(ans, f[i][j]); // f[i][j] 一定包含 s[i] 和 s[j]
                    }
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return ans;
    }

}
