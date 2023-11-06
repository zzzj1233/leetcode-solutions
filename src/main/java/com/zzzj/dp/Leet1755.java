package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-31 15:56
 */
public class Leet1755 {

    public static void main(String[] args) {

        System.out.println(minAbsDifference(new int[]{5, -7, 3, 5}, 6));

        System.out.println(minAbsDifference(new int[]{7, -9, 15, -2}, -5));

        System.out.println(minAbsDifference(new int[]{1, 2, 3}, -7));

    }

    public static int minAbsDifference(int[] nums, int goal) {

        int N = nums.length;

        int half = N / 2;

        int[] left = calc(nums, 0, half - 1, goal);

        int[] right = calc(nums, half, N - 1, goal);

        Arrays.sort(left);

        Arrays.sort(right);

        int L = 0;

        int R = right.length - 1;

        int ans = Math.abs(goal);

        while (L < left.length && R >= 0) {

            int sum = left[L] + right[R];

            if (sum == goal)
                return 0;
            if (sum > goal) {
                R--;
                ans = Math.min(ans, sum - goal);
            } else {
                L++;
                ans = Math.min(ans, goal - sum);
            }

        }

        return ans;
    }

    private static int[] calc(int[] nums, int start, int end, int goal) {

        int len = end - start + 1;

        int limit = 1 << len;

        int[] dp = new int[limit];

        for (int stat = 0; stat < limit; stat++) {

            int sum = 0;

            for (int j = 0; j < len; j++) {
                if ((stat & (1 << j)) != 0)
                    sum += nums[start + j];
            }

            dp[stat] = sum;
        }

        return dp;
    }

}
