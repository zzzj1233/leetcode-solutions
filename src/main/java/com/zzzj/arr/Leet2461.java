package com.zzzj.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-09-20 12:08
 */
public class Leet2461 {

    public static void main(String[] args) {

        System.out.println(maximumSubarraySum(new int[]{1, 5, 4, 2, 9, 9, 9}, 3));

        System.out.println(maximumSubarraySum(new int[]{4, 4, 4}, 3));

        System.out.println(maximumSubarraySum(new int[]{1, 2, 2}, 2));

        System.out.println(maximumSubarraySum(new int[]{9, 9, 9, 1, 2, 3}, 3));

        System.out.println(maximumSubarraySum(new int[]{3, 2, 3, 1}, 3));

    }

    public static long maximumSubarraySum(int[] nums, int k) {

        Map<Integer, Integer> indexes = new HashMap<>();

        long sum = 0;

        int end = k;

        int N = nums.length;

        int left = 0;

        long ans = 0;

        for (int i = 0; i < N; i++) {
            int num = nums[i];

            while (i - left >= k) {
                indexes.remove(nums[left]);
                sum -= nums[left];
                left++;
            }

            if (indexes.containsKey(num)) {

                Integer old = indexes.get(num);

                for (; left <= old; left++) {
                    indexes.remove(nums[left]);
                    sum -= nums[left];
                }

            }

            indexes.put(num, i);
            sum += num;

            if (i - left + 1 == k)
                ans = Math.max(ans, sum);
        }

        return ans;
    }


}
