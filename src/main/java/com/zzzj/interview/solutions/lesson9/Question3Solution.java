package com.zzzj.interview.solutions.lesson9;

/**
 * @author Zzzj
 * @create 2022-07-10 00:08
 */
public class Question3Solution {

    public static void main(String[] args) {
        // true
        System.out.println(question3("aab", "cd", "aacbd"));
        // true
        System.out.println(question3("aab", "cd", "caabd"));

        // false
        System.out.println(question3("aab", "cd", "baacd"));
        // false
        System.out.println(question3("aab", "cd", "acdba"));
    }

    // e.g.
    // ab
    // cd
    // acbd true
    // adcn false
    public static boolean question3(String str1, String str2, String aim) {
        int N = str1.length();
        int M = str2.length();

        int len = aim.length();

        if (N + M != len) {
            return false;
        }

        // dp[i][j] = str1[0 ~ i - 1] & str2[0 ~ j - 1] 是否可以拼凑出 aim[0 ~ i + j - 1]
        boolean[][] dp = new boolean[N + 1][M + 1];

        dp[0][0] = true;

        for (int i = 1; i <= M; i++) {

            if (str2.charAt(i - 1) != aim.charAt(i - 1)) {
                break;
            }

            dp[0][i] = true;
        }

        for (int i = 1; i <= N; i++) {

            if (str1.charAt(i - 1) != aim.charAt(i - 1)) {
                break;
            }

            dp[i][0] = true;
        }


        for (int i = 1; i <= N; i++) {

            for (int j = 1; j <= M; j++) {

                if (aim.charAt(i + j - 1) == str1.charAt(i - 1) && dp[i - 1][j]) {
                    dp[i][j] = true;
                } else if (aim.charAt(i + j - 1) == str2.charAt(j - 1) && dp[i][j - 1]) {
                    dp[i][j] = true;
                }
            }

        }

        return dp[N][M];
    }

}
