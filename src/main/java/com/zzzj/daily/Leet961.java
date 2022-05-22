package com.zzzj.daily;

/**
 * @author Zzzj
 * @create 2022-05-21 10:26
 */
public class Leet961 {

    public int repeatedNTimes(int[] nums) {
        int num1 = nums[0];
        int num2 = nums[1];

        if (num1 == num2) {
            return num1;
        }


        for (int i = 2; i < nums.length; i++) {
            if (num1 == nums[i] || num2 == nums[i]) {
                return nums[i];
            }
            if (i % 2 == 0) {
                num1 = nums[i];
            } else {
                num2 = nums[i];
            }
        }

        return num1;
    }

}
