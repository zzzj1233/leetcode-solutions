package com.zzzj.dp;


/**
 * @author Zzzj
 * @create 2022-07-12 15:53
 */
public class Leet97 {

    public static void main(String[] args) {
        System.out.println(isInterleave(
                "aabcc",
                "dbbca",
                "aadbbcbcac"
        ));

        System.out.println(isInterleave(
                "aabcc",
                "dbbca",
                "aadbbbaccc"
        ));
    }

    public static boolean isInterleave(String s1, String s2, String s3) {

        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }

        int N = s1.length();

        int M = s2.length();

        boolean[][] dp = new boolean[N + 1][M + 1];

        dp[N][M] = true;

        for (int i = M - 1; i >= 0; i--) {
            dp[N][i] = s2.charAt(i) == s3.charAt(N + i) && dp[N][i + 1];
        }

        for (int i = N - 1; i >= 0; i--) {
            dp[i][M] = s1.charAt(i) == s3.charAt(M + i) && dp[i + 1][M];
        }

        for (int i = N - 1; i >= 0; i--) {

            for (int j = M - 1; j >= 0; j--) {

                char c3 = s3.charAt(i + j);
                char c2 = s2.charAt(j);
                char c1 = s1.charAt(i);

                boolean result = false;

                if (c3 == c2) {
                    result |= dp[i][j + 1];
                }

                if (c3 == c1) {
                    result |= dp[i + 1][j];
                }

                dp[i][j] = result;
            }

        }

        return dp[0][0];
    }

    public static boolean dfs(
            String s1, String s2, String s3,
            int index1,
            int index2
    ) {

        int index3 = index1 + index2;

        if (index3 >= s3.length()) {
            return true;
        }

        if (index1 >= s1.length()) {
            return s2.charAt(index2) == s3.charAt(index3) && dfs(s1, s2, s3, index1, index2 + 1);
        }

        if (index2 >= s2.length()) {
            return s1.charAt(index1) == s3.charAt(index3) && dfs(s1, s2, s3, index1 + 1, index2);
        }

        char c3 = s3.charAt(index3);
        char c2 = s2.charAt(index2);
        char c1 = s1.charAt(index1);

        boolean result = false;

        if (c3 == c2) {
            result |= dfs(s1, s2, s3, index1, index2 + 1);
        }

        if (c3 == c1) {
            result |= dfs(s1, s2, s3, index1 + 1, index2);
        }

        return result;
    }

}
