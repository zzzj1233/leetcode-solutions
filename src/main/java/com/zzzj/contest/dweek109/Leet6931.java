package com.zzzj.contest.dweek109;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.rmi.Remote;
import java.util.Arrays;

public class Leet6931 {

    public static void main(String[] args) {


        for (int i = 0; i < 1000000; i++) {
            int[] ARR = ArrayUtil.generateArray(10, 1, 30);
            int x = LeetUtils.random.nextInt(100);

            if (maxScore(ARR, x) > right(ARR, x)) {
                System.out.println(Arrays.toString(ARR));
                System.out.println(x);
                System.out.println(maxScore(ARR, x));
                System.out.println(right(ARR, x));
                return;
            }
        }
    }

    public static long maxScore(int[] nums, int x) {

        int N = nums.length;

        long[] dp = new long[N];

        dp[0] = nums[0];

        long ans = dp[0];

        long maxOdd = nums[0] % 2 != 0 ? nums[0] : nums[0] - x;
        long maxEven = nums[0] % 2 == 0 ? nums[0] : nums[0] - x;

        for (int i = 1; i < N; i++) {

            dp[i] = Integer.MIN_VALUE;

            if (nums[i] % 2 == 0) {
                dp[i] = Math.max(dp[i], maxEven + nums[i]);
                dp[i] = Math.max(dp[i], maxOdd + nums[i] - x);
                maxEven = Math.max(maxEven, dp[i]);
            } else {
                dp[i] = Math.max(dp[i], maxEven + nums[i] - x);
                dp[i] = Math.max(dp[i], maxOdd + nums[i]);
                maxOdd = Math.max(maxOdd, dp[i]);
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static long right(int[] nums, int x) {
        return dfs(0, 1, x, nums) + nums[0];
    }

    public static long dfs(int prev, int index, int x, int[] nums) {
        if (index >= nums.length) return 0;

        if (nums[index] % 2 == nums[prev] % 2) {
            return nums[index] + dfs(index, index + 1, x, nums);
        } else {
            return Math.max(
                    nums[index] + dfs(index, index + 1, x, nums) - x,
                    dfs(prev, index + 1, x, nums)
            );
        }
    }

}
