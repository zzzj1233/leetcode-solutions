package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-11-03 15:21
 */
public class Leet2098 {

    public static long largestEvenSum(int[] nums, int k) {

        Arrays.sort(nums);

        int N = nums.length;

        long sum = 0;

        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;

        int i = N - 1;

        for (; i >= 0 && k > 0; i--) {
            if (nums[i] % 2 == 0) {
                minEven = nums[i];
            } else {
                minOdd = nums[i];
            }
            sum += nums[i];
            k--;
        }

        if (sum % 2 == 0) {
            return sum;
        }

        // 1. 删除一个奇数,再找一个偶数

        // 2. 删除一个偶数,再找一个奇数

        int maxEven = Integer.MIN_VALUE;

        int maxOdd = Integer.MIN_VALUE;

        for (int j = i; j >= 0; j--) {
            if (nums[j] % 2 == 0) {
                maxEven = Math.max(maxEven, nums[j]);
            } else {
                maxOdd = Math.max(maxOdd, nums[j]);
            }
        }

        long max = Math.max(sum - minOdd + maxEven, sum - minEven + maxOdd);

        return Math.max(-1, max);
    }

}
