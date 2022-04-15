package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-04-15 11:30
 */
public class Leet136 {

    public static int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }

}
