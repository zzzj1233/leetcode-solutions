package com.zzzj.bit;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-07 14:17
 */
public class Leet1928 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getMaximumXor(new int[]{0, 1, 1, 3}, 2)));
    }

    public static int[] getMaximumXor(int[] nums, int maximumBit) {
        int N = nums.length;

        int[] ans = new int[N];

        int max = (int) (Math.pow(2, maximumBit) - 1);

        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        for (int i = 0; i < N; i++) {
            ans[i] = xor ^ max;
            xor ^= nums[N - i - 1];
        }

        return ans;
    }

}
