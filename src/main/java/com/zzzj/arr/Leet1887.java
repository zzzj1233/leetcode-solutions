package com.zzzj.arr;

import java.util.Arrays;

public class Leet1887 {

    public static int reductionOperations(int[] nums) {
        int N = nums.length;

        Arrays.sort(nums);

        // 有多少个不同的数字
        int numCount = 1;

        int curNum = nums[0];

        for (int num : nums) {
            if (num != curNum) {
                numCount++;
                curNum = num;
            }
        }

        int ans = 0;

        int index = N - 1;

        curNum = nums[N - 1];

        while (numCount > 0) {
            if (nums[index] != curNum) {
                numCount--;
                curNum = nums[index];
            }
            ans += numCount - 1;
            index--;
        }

        return ans;
    }

}
