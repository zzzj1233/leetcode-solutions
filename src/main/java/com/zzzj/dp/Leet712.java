package com.zzzj.dp;

/**
 * @author Zzzj
 * @create 2022-06-17 22:56
 */
public class Leet712 {

    public static void main(String[] args) {

    }

    public static int minimumDeleteSum(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        int sum = 0;
        for (char c : chars1) {
            sum += c;
        }
        for (char c : chars2) {
            sum += c;
        }

        return sum - (dp(chars1, chars2, 0, 0));
    }

    public static int dp(char[] str1, char[] str2, int n1, int n2) {
        int N1 = str1.length;
        int N2 = str2.length;

        int[][] dp = new int[N1][N2];

        for (int i = N1 - 1; i >= 0; i--) {

            for (int j = N2 - 1; j >= 0; j--) {
                int ways1 = i + 1 >= N1 ? 0 : dp[i + 1][j];
                int ways2 = j + 1 >= N2 ? 0 : dp[i][j + 1];
                int ways3 = str1[i] == str2[j] ? (2 * str1[i]) : 0;
                if (i + 1 < N1 && j + 1 < N2) {
                    ways3 += dp[i + 1][j + 1];
                }
                dp[i][j] = Math.max(ways1, Math.max(ways2, ways3));
            }

        }
        return dp[0][0];
    }

    public static int dfs(char[] str1, char[] str2, int i, int j) {
        if (i >= str1.length || j >= str2.length) {
            return 0;
        }
        int ways1 = dfs(str1, str2, i + 1, j);
        int ways2 = dfs(str1, str2, i, j + 1);
        int ways3 = (str1[i] == str2[j] ? 1 : 0) + dfs(str1, str2, i + 1, j + 1);
        return Math.max(ways1, Math.max(ways2, ways3));
    }

}
