package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-09-23 11:55
 */
public class Leet217 {

    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }

        return false;
    }

}
