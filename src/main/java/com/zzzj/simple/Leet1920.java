package com.zzzj.simple;

/**
 * @author zzzj
 * @create 2023-04-12 16:43
 */
public class Leet1920 {

    public static int[] buildArray(int[] nums) {
        int N = nums.length;

        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            ans[i] = nums[nums[i]];
        }

        return ans;
    }

}
