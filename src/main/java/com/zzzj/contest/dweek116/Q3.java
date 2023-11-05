package com.zzzj.contest.dweek116;

import java.util.Arrays;
import java.util.List;

public class Q3 {

    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubsequence(
                Arrays.asList(4, 1, 3, 2, 1, 5),
                7
        ));

//        System.exit(0);

        System.out.println(lengthOfLongestSubsequence(
                Arrays.asList(7, 4, 11, 2, 10, 6, 9, 12, 15),
                38
        ));

        System.out.println(lengthOfLongestSubsequence(
                Arrays.asList(9, 15, 8, 14, 11, 7, 13, 9, 4),
                48
        ));

        System.out.println(lengthOfLongestSubsequence(
                Arrays.asList(3, 5, 2, 3, 4),
                12
        ));

        System.out.println(lengthOfLongestSubsequence(
                Arrays.asList(1, 2, 3, 4, 5),
                9
        ));

        System.out.println(lengthOfLongestSubsequence(
                Arrays.asList(4, 1, 3, 2, 1, 5),
                7
        ));

        System.out.println(lengthOfLongestSubsequence(
                Arrays.asList(1, 1, 5, 4, 5),
                3
        ));
    }

    public static int lengthOfLongestSubsequence(List<Integer> nums, int target) {

        int N = nums.size();

        int[][] dp = new int[N][target + 1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        if (nums.get(0) <= target)
            dp[0][nums.get(0)] = 1;

        for (int i = 1; i < N; i++) {

            Integer num = nums.get(i);

            dp[i] = Arrays.copyOfRange(dp[i - 1], 0, dp[i - 1].length);

            if (num <= target)
                dp[i][num] = Math.max(dp[i][num], 1);

            for (int j = num; j <= target; j++) {

                if (dp[i - 1][j - num] != -1)
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - num] + 1);

            }

        }

//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(nums.get(i) + " --- " + Arrays.toString(dp[i]));
//        }

        return Arrays.stream(dp)
                .mapToInt(f -> f[target])
                .max()
                .orElse(0);
    }

}
