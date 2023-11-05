package com.zzzj.dp;


import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-10-09 17:33
 */
public class Leet1425 {

    public static void main(String[] args) {

//        System.out.println(constrainedSubsetSum(new int[]{10, 2, -10, 5, 20}, 2));
//
//        System.out.println(constrainedSubsetSum(new int[]{-1, -2, -3}, 1));
//
//        System.out.println(constrainedSubsetSum(new int[]{10, -2, -10, -5, 20}, 2));

        System.out.println(constrainedSubsetSum(new int[]{-5266, 4019, 7336, -3681, -5767}, 2));

        System.out.println(right(new int[]{-5266, 4019, 7336, -3681, -5767}, 2));

        System.out.println(constrainedSubsetSum(new int[]{-8, 3, -4, -4, -6, 6, -3, 9, 4, -3}, 1));
    }

    public static int right(int[] nums, int k) {

        int N = nums.length;

        int[] dp = new int[N];

        dp[0] = nums[0];

        int ans = dp[0];

        for (int i = 1; i < N; i++) {

            int max = Integer.MIN_VALUE;

            for (int j = Math.max(0, i - k); j < i; j++) {
                max = Math.max(max, dp[j]);
            }

            if (i < k)
                max = Math.max(max, 0);

            dp[i] = max + nums[i];

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static int constrainedSubsetSum(int[] nums, int k) {

        int N = nums.length;

        int[] dp = new int[N];

        dp[0] = nums[0];

        int ans = dp[0];

        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(0);

        for (int i = 1; i < N; i++) {

            int left = Math.max(0, i - k);

            if (queue.peekFirst() < i - k)
                queue.removeFirst();

            int max = Math.max(dp[queue.peekFirst()], 0);

            dp[i] = max + nums[i];

            while (!queue.isEmpty() && dp[queue.peekLast()] < dp[i]) {
                queue.removeLast();
            }

            queue.add(i);

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

}
