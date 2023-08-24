package com.zzzj.contest.dweek111;

import java.util.Arrays;
import java.util.List;

public class Leet6941 {

    public static void main(String[] args) {

        System.out.println(minimumOperations(Arrays.asList(2, 1, 3, 2, 1)));

        System.out.println(minimumOperations(Arrays.asList(1, 3, 2, 1, 3, 3)));

        System.out.println(minimumOperations(Arrays.asList(2, 2, 2, 2, 3, 3)));

    }

    public static int minimumOperations(List<Integer> nums) {

        int N = nums.size();

        int[][] dp = new int[N + 1][4];

        for (int i = 1; i <= N; i++) {

            Integer num = nums.get(i - 1);

            dp[i][1] = dp[i - 1][1] + (num == 1 ? 0 : 1);
            dp[i][2] = Math.min(
                    dp[i - 1][1],
                    dp[i - 1][2]
            ) + (num == 2 ? 0 : 1);
            dp[i][3] = Math.min(
                    Math.min(
                            dp[i - 1][1],
                            dp[i - 1][2]
                    ),
                    dp[i - 1][3]
            ) + (num == 3 ? 0 : 1);
        }

        return Math.min(
                Math.min(
                        dp[N][1],
                        dp[N][2]
                ),
                dp[N][3]
        );
    }

}
