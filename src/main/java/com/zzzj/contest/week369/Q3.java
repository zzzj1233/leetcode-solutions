package com.zzzj.contest.week369;

public class Q3 {

    public static void main(String[] args) {

        // [4,0,10,2,10,6]
        // 8
        System.out.println(minIncrementOperations(new int[]{4, 0, 10, 2, 10, 6}, 8));

        System.out.println(minIncrementOperations(new int[]{2, 3, 0, 0, 2}, 4));

        System.out.println(minIncrementOperations(new int[]{0, 1, 3, 3}, 5));
//
        System.out.println(minIncrementOperations(new int[]{1, 1, 2}, 1));

    }

    public static long minIncrementOperations(int[] nums, int k) {

        int N = nums.length;

        long[] dp = new long[N + 3];

        for (int i = 3; i < dp.length; i++) {

            int num = nums[i - 3];

            dp[i] = num >= k ? 0 : k - num;

            dp[i] += Math.min(
                    dp[i - 1],
                    Math.min(
                            dp[i - 2],
                            dp[i - 3]
                    )
            );
        }

        return Math.min(
                dp[dp.length - 1],
                Math.min(
                        dp[dp.length - 2],
                        dp[dp.length - 3]
                )
        );
    }

}
