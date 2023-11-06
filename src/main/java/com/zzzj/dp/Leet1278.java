package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-27 11:13
 */
public class Leet1278 {

    public static void main(String[] args) {

        System.out.println(palindromePartition("abc", 2));

        System.out.println(palindromePartition("aabbc", 3));

        System.out.println(palindromePartition("leetcode", 8));

    }

    public static int palindromePartition(String s, int k) {

        int N = s.length();

        int[][] dp = new int[N][k + 1];

        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        int[][] cost = new int[N][N];

        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                cost[i][j] = minCost(s, i, j);

        for (int i = 0; i < N; i++) {

            dp[i][1] = cost[0][i];

            int limit = Math.min(i + 1, k);

            for (int j = 2; j <= limit; j++) {

                // i分成j份
                //
                // nums : 1 2 3 4 5
                // 1    : ALL
                // 2    : [0][1] + ms(1, 4) / [1][1] + ms(2, 4) / [2][1] + ms(3, 4) / [3][1] + ms(4, 4)
                // 3    : [1][2] + ms(2, 4) / [2][2] + ms(3, 4) / [3][2] + ms(4, 4)
                // 4    : [2][3] + ms(3, 4) / [3][3] + ms(4, 4)

                for (int x = 0; x < i; x++) {
                    if (dp[x][j - 1] == Integer.MAX_VALUE)
                        continue;
                    dp[i][j] = Math.min(
                            dp[i][j],
                            dp[x][j - 1] + cost[x + 1][i]
                    );
                }

            }

        }

        return dp[N - 1][k];
    }

    private static int minCost(String s, int start, int end) {

        int res = 0;

        while (start < end) {

            if (s.charAt(start) != s.charAt(end))
                res++;

            start++;
            end--;
        }

        return res;
    }

}
