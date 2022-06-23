package com.zzzj.leet;

import com.zzzj.util.Unresolved;

/**
 * @author zzzj
 * @create 2022-06-23 18:38
 */
// O(N) 单调栈
@Unresolved
public class Leet2104 {

    public static void main(String[] args) {
        System.out.println(subArrayRanges(new int[]{1, 2, 3}));
    }


    public static long subArrayRanges(int[] nums) {
        long ans = 0;

        for (int i = 0; i < nums.length; i++) {

            int max = nums[i];

            int min = nums[i];

            for (int j = i - 1; j >= 0; j--) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                ans += max - min;
            }

        }

        return ans;
    }

}
