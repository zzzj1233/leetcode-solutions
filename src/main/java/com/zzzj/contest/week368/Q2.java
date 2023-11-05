package com.zzzj.contest.week368;

public class Q2 {

    public static void main(String[] args) {

        System.out.println(minimumSum(new int[]{8, 6, 1, 5, 3}));

        System.out.println(minimumSum(new int[]{5, 4, 8, 7, 10, 2}));

    }

    public static int minimumSum(int[] nums) {

        int N = nums.length;

        int[] leftMin = new int[N];

        leftMin[0] = 0;

        for (int i = 1; i < N; i++)
            leftMin[i] = nums[i] <= nums[leftMin[i - 1]] ? i : leftMin[i - 1];

        int[] rightMin = new int[N];
        rightMin[N - 1] = N - 1;

        for (int i = N - 2; i >= 0; i--)
            rightMin[i] = nums[i] <= nums[rightMin[i + 1]] ? i : rightMin[i + 1];

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < N - 1; i++) {
            if (leftMin[i] != i && rightMin[i] != i)
                ans = Math.min(ans, nums[leftMin[i]] + nums[rightMin[i]] + nums[i]);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
