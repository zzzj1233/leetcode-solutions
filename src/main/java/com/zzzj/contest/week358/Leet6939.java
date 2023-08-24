package com.zzzj.contest.week358;

import java.util.Arrays;

public class Leet6939 {

    public static void main(String[] args) {

        System.out.println(maxSum(new int[]{51, 71, 17, 24, 42}));

        System.out.println(maxSum(new int[]{1, 2, 3, 4}));

    }

    public static int maxSum(int[] nums) {

        int ans = -1;

        int[] maxArr = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            String str = String.valueOf(num);

            int max = 0;

            for (int j = 0; j < str.length(); j++) {
                max = Math.max(max, Character.digit(str.charAt(j), 10));
            }

            maxArr[i] = max;
        }

        for (int i = 0; i < nums.length; i++) {

            for (int j = 0; j < nums.length; j++) {

                if (i == j) continue;

                if (maxArr[i] == maxArr[j])
                    ans = Math.max(ans, nums[i] + nums[j]);
            }

        }

        return ans;
    }

}
