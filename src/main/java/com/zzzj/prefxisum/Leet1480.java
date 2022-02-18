package com.zzzj.prefxisum;

/**
 * @author zzzj
 * @create 2021-12-08 11:20
 */
public class Leet1480 {

    public static int[] runningSum(int[] nums) {
        int[] answer = new int[nums.length];

        answer[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            answer[i] = nums[i] + answer[i - 1];
        }

        return answer;
    }

}
