package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2021-12-19 19:35
 */
public class Leet643 {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.generateArray(10, -50, 100);
        System.out.println(Arrays.toString(arr));
        int k = LeetUtils.random.nextInt(arr.length);
        System.out.println(k);
        System.out.println(findMaxAverage(arr, k));
    }

    public double findMaxAverage2(int[] nums, int k) {
        int sum = 0;

        int n = nums.length;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;

        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }

        return 1.0 * maxSum / k;
    }

    public static double findMaxAverage(int[] nums, int k) {

        double sum = 0;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }


        double max = sum;

        for (int i = k; i < nums.length; i++) {
            sum -= nums[i - k];
            sum += nums[i];
            max = Math.max(max, sum);
        }

        return sum / k;
    }

}
