package com.zzzj.greedy;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-12 14:35
 */
public class Leet2009 {

    public static void main(String[] args) {
//
        System.out.println(minOperations(new int[]{1, 2, 3, 5, 6}));

        System.out.println(minOperations(new int[]{2, 3, 4, 5}));

        System.out.println(minOperations(new int[]{18, 24, 26, 28, 29, 33, 33, 35, 41, 47}));

        System.out.println(minOperations(new int[]{1, 10, 100, 1000}));

    }

    public static int minOperations(int[] nums) {

        int N = nums.length;

        nums = Arrays.stream(nums)
                .distinct()
                .toArray();

        Arrays.sort(nums);

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {

            int minimum = nums[i];

            int left = i + 1;
            int right = nums.length - 1;

            int maximum = nums[i] + N - 1;

            int index = i;

            while (left <= right) {

                int mid = left + ((right - left) >> 1);

                if (nums[mid] == maximum) {
                    index = mid;
                    break;
                } else if (nums[mid] < maximum) {
                    index = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            ans = Math.min(
                    ans,
                    N - (index - i + 1)
            );

        }

        return ans;
    }

}
