package com.zzzj.contest.week392;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-05-23 17:44
 */
public class Leet3107 {

    public static void main(String[] args) {

        System.out.println(minOperationsToMakeMedianK(new int[]{2, 5, 6, 8, 5}, 4));

        System.out.println(minOperationsToMakeMedianK(new int[]{2, 5, 6, 8, 5}, 7));

        System.out.println(minOperationsToMakeMedianK(new int[]{1, 2, 3, 4, 5, 6}, 4));

    }

    public static long minOperationsToMakeMedianK(int[] nums, int k) {

        int N = nums.length;

        Arrays.sort(nums);

        int mi = N / 2;

        int mid = nums[mi];

        long ans = Math.abs(nums[mi] - k);

        if (nums[mi] > k)
            for (int i = 0; i < mi; i++) {
                if (nums[i] > k)
                    ans += nums[i] - k;
            }
        else
            for (int i = mi + 1; i < N; i++) {
                if (nums[i] < k)
                    ans += k - nums[i];
            }

        return ans;
    }

}
