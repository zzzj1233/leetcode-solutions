package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-20 18:57
 */
public class Leet478 {

    public static int findMaxConsecutiveOnes(int[] nums) {

        int l = 0;
        boolean allow = true;

        int r = 0;

        int ans = 0;

        while (r < nums.length) {
            if (nums[r] == 0) {
                if (allow) {
                    allow = false;
                } else {
                    // 直到跨过一个0
                    while (nums[l] != 0) {
                        l++;
                    }
                    l++;
                }
                ans = Math.max(ans, r - l + 1);
                r++;
            }
        }

        return ans;
    }

}
