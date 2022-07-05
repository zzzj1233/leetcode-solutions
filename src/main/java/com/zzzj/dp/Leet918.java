package com.zzzj.dp;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-07-04 18:33
 */
public class Leet918 {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int[] nums = ArrayUtil.generateArray(5, -10, 20);
            if (maxSubarraySumCircular(nums) != right(nums)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(nums));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int maxSubarraySumCircular(int[] nums) {
        int N = nums.length;

        int preMax = nums[0];
        int max = nums[0];

        int preMin = nums[0];
        int min = nums[0];

        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            preMax = Math.max(nums[i], preMax + nums[i]);
            max = Math.max(max, preMax);

            preMin = Math.min(nums[i], preMin + nums[i]);
            min = Math.min(min, preMin);

            sum += nums[i];
        }

        return max < 0 ? max : Math.max(max, sum - min);
    }

    public static int right(int[] A) {
        if (A == null || A.length < 1) {
            return 0;
        }
        int curMax, max, curMin, min, sum;
        curMax = max = curMin = min = sum = A[0];
        for (int i = 1; i < A.length; i++) {
            sum += A[i];
            curMax = curMax > 0 ? curMax + A[i] : A[i];
            max = Math.max(curMax, max);
            curMin = curMin < 0 ? curMin + A[i] : A[i];
            min = Math.min(curMin, min);
        }
        if (max < 0)
            return max;
        return Math.max(sum - min, max);
    }

}
