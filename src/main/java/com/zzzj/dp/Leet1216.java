package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2024-01-22 12:09
 */
public class Leet1216 {

    public static void main(String[] args) {

        System.out.println(isValidPalindrome("abcdeca", 2));

    }

    public static boolean isValidPalindrome(String s, int k) {

        // 最长回文子序列

        int N = s.length();

        int[][] f = new int[N + 1][N + 1];

        for (int i = N - 1; i >= 0; i--) {

            f[i][i] = 1;

            for (int j = i + 1; j < N; j++) {
                if (s.charAt(i) == s.charAt(j))
                    f[i][j] = f[i + 1][j - 1] + 2;
                else
                    f[i][j] = Math.max(
                            f[i + 1][j],
                            f[i][j - 1]
                    );
            }

        }

        return N - f[0][N - 1] <= k;
    }

}
