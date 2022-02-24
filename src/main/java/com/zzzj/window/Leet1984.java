package com.zzzj.window;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-02-22 11:51
 */
public class Leet1984 {

    public static void main(String[] args) {
        System.out.println(minimumDifference(new int[]{9, 4, 1, 7}, 2));
        System.out.println(minimumDifference(new int[]{90}, 1));
    }

    public static int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);

        // 维护一个k大的窗口

        int l = 0;
        int r = k;

        int ans = nums[r - 1] - nums[l];

        while (r < nums.length) {
            l++;
            r++;
            ans = Math.min(ans, nums[r - 1] - nums[l]);
        }

        return ans;
    }

}
