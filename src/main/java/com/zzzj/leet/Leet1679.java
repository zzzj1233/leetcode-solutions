package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-30 16:18
 */
public class Leet1679 {

    public static int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);

        int r = nums.length - 1;

        int l = 0;

        int ans = 0;

        while (l < r) {
            if (nums[l] + nums[r] == k) {
                ans++;
                l++;
                r--;
            } else if (nums[l] + nums[r] > k) {
                r--;
            } else {
                l++;
            }
        }

        return ans;
    }

}
