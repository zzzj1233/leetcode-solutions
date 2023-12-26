package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-12-26 16:03
 */
public class Leet2971 {

    public static void main(String[] args) {

        System.out.println(largestPerimeter(new int[]{1, 12, 1, 2, 5, 50, 3}));

        System.out.println(largestPerimeter(new int[]{5, 5, 50}));

    }

    public static long largestPerimeter(int[] nums) {

        Arrays.sort(nums);

        int N = nums.length;

        long[] preSum = new long[N + 1];

        for (int i = 1; i <= N; i++)
            preSum[i] = preSum[i - 1] + nums[i - 1];

        long ans = -1;

        for (int i = N - 1; i >= 2; i--) {

            int leftest = search(nums, i - 1, nums[i]);

            if (leftest == -1)
                break;

            if (preSum[leftest + 1] > nums[i])
                ans = Math.max(ans, nums[i] + preSum[leftest + 1]);
        }

        return ans;
    }

    private static int search(int[] nums, int right, int num) {

        int left = 0;

        int res = -1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if (nums[mid] <= num) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }

}
