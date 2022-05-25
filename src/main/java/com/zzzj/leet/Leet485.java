package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-25 12:25
 */
public class Leet485 {

    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                max++;
            } else {
                max = 0;
            }
            ans = Math.max(ans, max);
        }
        return ans;
    }

}
