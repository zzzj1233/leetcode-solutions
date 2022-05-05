package com.zzzj.daily;

import java.util.Map;

/**
 * @author zzzj
 * @create 2022-05-05 17:50
 */
public class Leet713 {

    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int l = 0;
        int r = 0;

        int ans = 0;

        long curSum = 1;

        while (r < nums.length) {
            curSum *= nums[r];
            // 缩短窗口
            while (l <= r && curSum >= k) {
                curSum /= nums[l++];
            }
            ans += (r - l + 1);
            r++;
        }

        return ans;
    }

}
