package com.zzzj.interview.solutions.lesson4;

/**
 * @author Zzzj
 * @create 2022-07-02 16:49
 */
public class Question5Solution {


    public static void main(String[] args) {
        System.out.println(longestCommonSubstring("ABCD", "CBCE"));
    }

    // 最长公共字串
    public static int longestCommonSubstring(String a, String b) {
        int N = a.length();
        int M = b.length();

        int[][] dp = new int[N][M];

        int ans = 0;

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i][j] = 1 + pick(dp, i - 1, j - 1);
                    ans = Math.max(ans, dp[i][j]);
                }
            }

        }

        return ans;
    }

    public static int pick(int[][] dp, int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        return dp[i][j];
    }

}
