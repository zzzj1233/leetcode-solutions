package com.zzzj.contest.week370;

import java.util.Arrays;
import java.util.LinkedList;

public class Q4 {

    public static void main(String[] args) {

        // [-1,2,-5,5,-2,6,-5,2]
        System.out.println(maxBalancedSubsequenceSum(new int[]{-1, 2, -5, 5, -2, 6, -5, 2}));

        System.exit(0);

        System.out.println(maxBalancedSubsequenceSum(new int[]{3, 3, 5, 6}));

        System.out.println(maxBalancedSubsequenceSum(new int[]{5, -1, -3, 8}));

        System.out.println(maxBalancedSubsequenceSum(new int[]{-2, -1}));

        System.out.println(maxBalancedSubsequenceSum(new int[]{2, 7}));
    }

    public static long maxBalancedSubsequenceSum(int[] nums) {

        int N = nums.length;

        LinkedList<Integer> stack = new LinkedList<>();

        int[] near = new int[N];

        Arrays.fill(near, -1);

        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && nums[stack.peekLast()] < nums[i] && (nums[i] - nums[stack.peekLast()]) >= i - stack.peekLast())
                near[stack.removeLast()] = i;

            while (!stack.isEmpty() && nums[stack.peekFirst()] < nums[i] && (nums[i] - nums[stack.peekFirst()]) >= i - stack.peekFirst())
                near[stack.removeFirst()] = i;

            stack.addLast(i);
        }

        long[] dp = new long[N];
        dp[N - 1] = nums[N - 1];

        long ans = dp[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            if (near[i] == -1) {
                dp[i] = nums[i];
            } else {
                dp[i] = Math.max(
                        nums[i],
                        nums[i] + dp[near[i]]
                );
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }


}
