package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2023-04-20 14:45
 */
public class Leet2640 {

    public static long[] findPrefixScore(int[] nums) {

        int N = nums.length;

        long[] ans = new long[N];

        int max = -1;

        long preSum = 0;

        for (int i = 0; i < N; i++) {
            max = Math.max(max, nums[i]);
            preSum += ((long) nums[i]) + max;
            ans[i] = preSum;
        }

        return ans;
    }

}
